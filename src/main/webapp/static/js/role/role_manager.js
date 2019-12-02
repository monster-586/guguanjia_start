let em = new Vue({
    el: '#main-container',
    data: function () {
        return {
            Role: {},
            roleList: {},
            setting: {
                data: {
                    key: {
                        title: ''
                    },
                    simpleData: {
                        enable: true,
                        pIdKey: 'parentId'
                    }
                },
                callback: {onClick: this.TreeClick},
                check: {enable: true},
                nodes: []
            },
            officeSetting: {
                data: {
                    key: {
                        title: ''
                    },
                    simpleData: {
                        enable: true,
                        pIdKey: 'parentId'
                    }
                },
                callback: {onClick: this.offTreeClick},
                view: {fontCss: this.changeColor},
                check: {enable: true},
                officeNodes: []
            }
        }
    },
    methods: {
        changeScope: function () {
            //根据被选中元素进行判断，如果被选中的dataScope是9则显示公司树，如果不是则隐藏公司树
           let option = $("#chosenSelectEdit option:selected");

            if (option.val() == 9) {
                $("#treeSelectOfficeEdit").css("display", "inline-block");
            } else {
                $("#treeSelectOfficeEdit").css("display", "none");
            }
            // console.log(this.option.val())

        },

        initTree: function () {
            axios({
                url: 'manager/resource/list'
            }).then(response => {
                this.nodes = response.data;
                for (let i = 0; i < this.nodes.length; i++) {
                    for (let j = 0; j < this.roleList.length; j++) {
                        if (this.nodes[i].id == this.roleList[j].id) {
                            this.nodes[i].checked = true;
                        }
                    }
                }
                $.fn.zTree.init($("#select-treetreeSelectResEdit"), this.officeSetting, this.nodes);
            }).catch(
                function (error) {
                    layer.msg(error)
                })

        },
        initOffTree: function () {
            axios({
                url: 'manager/office/list'
            }).then(response => {
                this.officeNodes = response.data;
                this.officeNodes[this.officeNodes.length] = {
                    "id": 0,
                    "name": "所有机构"
                }
                let treeObject = $.fn.zTree.init($("#select-treetreeSelectOfficeEdit"), this.setting, this.officeNodes);
                this.treeObj=treeObject;
            }).catch(function (error) {
                layer.msg(error)
            })
        },
        TreeClick:function (event, treeId, treeNode) {

        },
        offTreeClick:function (event, treeId, treeNode) {

        },
    },
    created: function () {
        this.Role = parent.layer.Role;
        this.roleList = parent.layer.roleList;


    },
    mounted: function () {
        this.initTree();

        $("#chosenSelectEdit").chosen({width: "40%", search_contains: true});
        $("#chosenSelectEdit").on("change", () => {
            this.initOffTree();
            this.changeScope();//this在jq中是元素对象
        })
    }
})