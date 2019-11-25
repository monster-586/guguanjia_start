let em = new Vue({
    el: '#main-container',
    data: function () {
        return {
            id: '',
            name:'',
            obj: {},
            setting: {
                data: {
                    key: {
                        title: ""
                    },
                    simpleData: {
                        enable: true,
                        pIdKey: 'parentId'
                    }
                },
                callback: {onClick: this.TreeClick},
                view: {
                    fontCss: this.changeColor
                },
            },
            nodes: [],
            treeObj: {},

        }
    },
    methods: {
        initTree: function () {
            axios({
                url: "manager/area/list",
                method: 'get',
                params: ''
            }).then(response => {
                this.nodes = response.data;
                // console.log(response.data);
                let treeObject = $.fn.zTree.init($("#treeMenu"), this.setting, this.nodes);

                this.treeObj = treeObject;
                // console.log(this.treeObj)
            }).catch(function (error) {
                console.log(error);
            })
        },

        search: function () {
            console.log("bbbb");
            let checkNodes = this.treeObj.getNodesByParamFuzzy("name", this.name, null);
            let nodesAll = this.treeObj.transformToArray(this.treeObj.getNodes());
            for (let index in nodesAll) {
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

        TreeClick: function (event, treeId, treeNode) {
            // parent.areaSave.name = treeNode.name;
            // console.log("selected:"+treeNode.parentId);
            parent.layer.pId = treeNode.id;
            parent.layer.name=treeNode.name;
            //关闭自身
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index)


        },
        showTree: function (flag) {

        },
        changeColor: function (treeId, treeNode) {
            return treeNode.higtLine ? {color: 'red'} : {color: ''}
        }
    },

    created: function () {
        // this.obj = parent.layer.obj;
        console.log(this.obj)
    },
    mounted: function () {
        this.initTree();
    }
})