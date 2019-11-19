let em = new Vue({
    el: '#main-container',
    data: function () {
        return {
            treeObj: {},
            pageInfo: {
                pageNum: '',
                pageSize: ''
            },

            map: {
                pageNum: '',
                pageSize: '',
                name: '',
                type: '',
                uName: ''
            },



            setting: {
                data: {
                    key: {
                        title: '',
                    },
                    simpleData: {
                        enable: true,
                    }
                },
                callback: {
                    onClick: this.Click
                },
                view: {fontCss: this.changeColor}

            },
            nodes: [],

        }

    },
    methods: {
        selectByCondition: function (pageNum, pageSize) {
            this.map.pageNum = pageNum;
            this.map.pageSize = pageSize;
            console.log(this.map)
            // console.log(this.pageInfo)

            axios({
                url: "manager/examine/index/selectByCondition",
                method: "post",
                data: this.map

            }).then(response => {
                this.pageInfo = response.data;
                // console.log(this.pageInfo)
            }).catch(function (error) {
                console.log(error);
            })
        },
        initTree: function () {
            axios({
                url: 'manager/office/list',
                method: 'get',
                params: ''
            }).then(response => {
                this.nodes = response.data;
                // console.log(response.data)
                let treeObject = $.fn.zTree.init($("#pullDownTreeone"), this.setting, this.nodes);
                treeObject.expandAll(true);//展开所有节点
                this.treeObj = treeObject;//给vue传值
                // console.log(treeObject)
            }).catch(function (error) {
                console.log(error);
            })

        },

        Click: function (event, treeId, treeNode) {
            this.map.name = treeNode.name;

        },
        showTree: function (flag) {

        },
        search: function () {
            let node = this.treeObj.getNodesByParamFuzzy("map.name", this.map.name, null)

            let nodeArr = this.treeObj.transformToArray(this.treeObj.getNodes());
            // let nodeArr = this.treeObj.transformToArray(this.treeObj.getNodes());

            for (let index in nodeArr) {
                nodeArr[index].higtLine = false;
                this.treeObj.updateNode(nodeArr[index]);//更新节点，自动调用清除css
            }

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
        this.selectByCondition(this.pageInfo.pageNum, this.pageInfo.pageSize);

    },
    mounted: function () {
        this.initTree();
    }

})