let em = new Vue({
    el: '#main-container',
    data: function () {
        return {

            map: {
                pageNum: '',
                pageSize: '',
                roleId: '',
                userId: '',
                officeId: '',
                userName: ''
            },
            pageInfo: {
                pageNum: '',
                pageSize: ''
            },
            setting: {
                data: {
                    key: {
                        title: ""
                    },
                    simpleData: {
                        enable: true,
                        /*   pIdKey: 'parentId'*/
                    }
                },
                callback: {onClick: this.TreeClick},

            },
            nodes: [],
            treeObj: {},
            name:""
        }
    },
    methods: {
        selectByCondition: function (pageNum, pageSize) {
            this.map.pageNum = pageNum;
            this.map.pageSize = pageSize;
            axios({
                url: "manager/sysuser/selectByCondition",
                method: "post",
                data: this.map

            }).then(response => {
                this.pageInfo = response.data;
                this.map.officeId='';
            }).catch(function (error) {
                console.log(error);
            })
        },
        initTree: function () {
            axios({
                url: "manager/office/list",
                method: 'get',
                params: ''
            }).then(response => {
                this.nodes = response.data;
                // console.log(response.data);
                let treeObject = $.fn.zTree.init($("#pullDownTreeone"), this.setting, this.nodes);

                this.treeObj = treeObject;
                // console.log(this.treeObj)
            }).catch(function (error) {
                console.log(error);
            })
        },
        TreeClick: function (event, treeId, treeNode) {
            this.name=treeNode.name;
            this.map.officeId=treeNode.id;
        },
        toSave: function (uid) {
            this.map.userId=uid;
            axios({
                url: 'manager/sysuser/selectByCondition',
                method: "post",
                data: this.map
            }).then(response => {
                layer.areaSave = response.data;
                console.log(response.data);
                let _this = this;
                let upd = layer.open({
                    type: 2,
                    title: "修改",
                    content: 'html/user/detail.html',
                    area: ['80%', '80%'],
                    end: () => {
                        console.log("**********");
                        this.selectByCondition(this.pageInfo.pageNum, this.pageInfo.pageSize);
                    }
                });
            }).catch(function (error) {
                layer.msg(error)
            })


        },
        searchClear: function () {
            console.log("aaaa");

            let nodeArr = this.treeObj.transformToArray(this.treeObj.getNodes());

            for (let index in nodeArr) {
                nodeArr[index].higtLine = false;
                this.treeObj.updateNode(nodeArr[index]);//更新节点，自动调用清除css
            }
        }
        ,
        search: function () {

            console.log("bbbb")
            let node = this.treeObj.getNodesByParamFuzzy("name", this.name, null)

            let nodeArr = this.treeObj.transformToArray(this.treeObj.getNodes());
            // let nodeArr = this.treeObj.transformToArray(this.treeObj.getNodes());

            this.map.officeName = this.name;
            for (let index in nodeArr) {
                for (let nodeIndex in node) {
                    if (nodeArr[index].id == node[nodeIndex].id) {
                        nodeArr[index].higtLine = true;//设置高亮标记
                        //更新节点  会触发自动的设置css等回调
                        this.treeObj.updateNode(nodeArr[index])

                    }
                }
            }

        },
        changeColor: function (treeId, treeNode) {
            return treeNode.higtLine ? {color: "red"} : {color: ''}
        }



    },

    created: function () {
        this.selectByCondition(this.map.pageNum, this.map.pageSize)
    },
    mounted: function () {
        this.initTree();
    }


})