let em = new Vue({
    el: '#main-container',
    data: function () {
        return {
            pageInfo: {
                pageNum: '',
                pageSize: ''
            },
            map: {
                pageNum: '',
                pageSize: '',
                status: '',
                officeName: '',
                startDate: '',
                endDate: ''
                // code:''
            },
            setting: {
                data: {
                    key: {
                        title: ""
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
            treeObj: {},
            name: ''


        }
    },
    methods: {
        detail: function (id) {
            // let ly = layer.open({
            //     type: 2,
            //     title: "详细信息",
            //     content: 'html/work/work-detail.html',
            //     area: ['80%', '80%'],
            //     end: () => {
            //         console.log("***********************")
            //         this.selectByCondition(this.pageInfo.pageNum, this.pageInfo.pageSize)
            //     }
            // })
            axios({
                url: 'manager/company/work/selectOneByCondition',
                params:{
                    id:id
                }
            }).then(response => {
                layer.workOrder = response.data;

                let ly = layer.open({
                    type: 2,
                    title: "详细信息",
                    content: 'html/work/work-detail.html',
                    area: ['80%', '80%'],
                    end: () => {
                        console.log("***********************")
                        this.selectByCondition(this.pageInfo.pageNum, this.pageInfo.pageSize)
                    }
                })
            }).catch(function (error) {
                console.log(error)
            })
        },
        selectByCondition: function (pageNum, pageSize) {
            this.map.pageNum = pageNum;
            this.map.pageSize = pageSize;
            // console.log(this.map)
            // console.log(this.pageInfo)

            axios({
                url: "manager/company/work/selectByCondition",
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
                url: "manager/sysuser/list",
                method: 'get',
                params: ''
            }).then(response => {
                this.nodes = response.data;
                // console.log(response.data)
                let treeObject = $.fn.zTree.init($("#pullDownTreeone"), this.setting, this.nodes);
                this.treeObj = treeObject;
                // console.log(treeO)
            }).catch(function (error) {
                console.log(error);
            })
        },
        search: function () {
            console.log("bbbb")
            let checkNodes = this.treeObj.getNodesByParamFuzzy("name", this.name, null);
            let nodesAll = this.treeObj.transformToArray(this.treeObj.getNodes());
            for (let index in nodesAll) {
                this.map.officeName = this.name;
                for (let checkIndex in checkNodes) {
                    if (checkNodes[checkIndex].id == nodesAll[index].id) {
                        nodesAll[index].higtLine = true;
                        this.treeObj.updateNode(nodesAll[index])
                    }

                }
            }
        },
        searchClear: function () {
            console.log("aaaa");

            let nodesAll = this.treeObj.transformToArray(this.treeObj.getNodes());

            for (let index in nodesAll) {
                nodesAll[index].higtLine = false;
                this.treeObj.updateNode(nodesAll[index]);//更新节点，自动调用清除css
            }
        }
        ,

        Click: function (event, treeId, treeNode) {
            this.name = treeNode.name;

            // console.log(treeNode.name)
        },
        showTree: function (flag) {

        },
        changeColor: function (treeId, treeNode) {
            return treeNode.higtLine ? {color: 'red'} : {color: ''}
        }

    },
    created: function () {
        this.selectByCondition(this.pageInfo.pageNum, this.pageInfo.pageSize);
    },
    mounted: function () {
        this.initTree();
    }


})