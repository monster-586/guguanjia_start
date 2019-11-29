let em = new Vue({
    el: '#main-container',
    data: function () {
        return {
            map: {},
            role_user: {},
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
            },
            nodes: [],
            treeObj: {},
            name: "",
            
        }
    },
    methods: {
        initTree: function () {

            let treeObject = $.fn.zTree.init($("#treeOffice"), this.setting, this.nodes);
            this.treeObj = treeObject;

        },
        TreeClick: function (event, treeId, treeNode) {
            this.searchClear();

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

        yxUser:function(){//根据当前角色id，查询后台，得到当前角色已经授权的用户id和name
            axios({
                url:'manager/sysuser/selectNotRole',
                params:{rid:this.rid}
            }).then(response => {
                this.checkedUser=response.data;

                //给每个用户绑定新属性show ,用于控制被选中与否
                for (let i = 0; i <this.checkedUser.length ; i++) {
                    this.checkedUser[i].show=false;
                }


            }).catch(function (error) {
                layer.msg(error);
            })
        },


    },

    created: function () {
        this.nodes = parent.layer.role_user;

    },
    mounted: function () {
        this.initTree();
    }


})