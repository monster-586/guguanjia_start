let em = new Vue({
    el: '#main-container',
    data: function () {
        return {

            map: {
                pageNum: '',
                pageSize: '',
                offId: '',
                roleType: '',
                roleName: '',
                DesCri: ''
            },
            pageInfo: {
                pageNum: 1,
                pageSize: 5
            },
            nodes: [],
            treeObj: {},
            name: '',
            // checkRole: '',
            rid: '',
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
                view: {fontCss: this.changeColor}
            }

        }
    },
    methods: {
        selectByCondition: function (pageNum, pageSize) {
            this.map.pageNum = pageNum;
            this.map.pageSize = pageSize;
            console.log(this.map);
            axios({
                url: "manager/role/selectByCondition",
                method: 'post',
                data: this.map

            }).then(response => {
                this.pageInfo = response.data;
                this.map.offId = '';
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
            this.name = treeNode.name;
            this.map.offId = treeNode.id;
        },
        toManager: function (roleId) {
            axios({
                url: 'manager/office/list',
            }).then(response => {
                layer.role_user = response.data;
                layer.roleId = roleId;
                console.log(layer.roleId)
                let rol = layer.open({
                    type: 2,
                    title: "修改",
                    content: 'html/role/role-user.html',
                    area: ['80%', '80%'],
                    end: () => {
                        console.log("**********");
                        // this.selectByCondition(this.pageInfo.pageNum, this.pageInfo.pageSize);
                    }
                });
            }).catch(function (error) {
                console.log(error)
            })


        },
        searchClear: function () {

            let nodeArr = this.treeObj.transformToArray(this.treeObj.getNodes());

            for (let index in nodeArr) {
                nodeArr[index].higtLine = false;
                this.treeObj.updateNode(nodeArr[index]);//更新节点，自动调用清除css
            }
        },
        search: function () {

            console.log("bbbb")
            let node = this.treeObj.getNodesByParamFuzzy("name", this.name, null)

            let nodeArr = this.treeObj.transformToArray(this.treeObj.getNodes());
            // let nodeArr = this.treeObj.transformToArray(this.treeObj.getNodes());

            // this.map.officeName = this.name;
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
        },
        toSave: function (obj) {
            // this.checkRole = obj;
            axios({
                url: 'manager/resource/selectByRid',
                method: 'get',
                params: {
                    rid: obj.id
                }
            }).then(response => {
                layer.Role = obj;
                layer.roleList = response.data;
                let vm = layer.open({
                    type: 2,
                    title: "编辑角色权限",
                    content: 'html/role/role-save.html',
                    area: ['80%', '80%'],
                    end: () => {
                        console.log("**********");
                    }
                })


            }).catch(function (error) {

            })

        }
    },

    created: function () {
        this.selectByCondition(this.map.pageNum, this.map.pageSize)
    },
    mounted: function () {
        this.initTree();
    }


})