let em = new Vue({
    el: '#main-container',
    data: function () {
        return {
            Role: {},
            roleList: {},
            map: {
                resourceIds: [],
                offIds: [],
                roleId: ''
            },

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
                callback: {onClick: ''},
                view: {fontCss: this.changeColor},
                check: {
                    enable: true,
                    chkStyle: "checkbox",
                    chkboxType: {"Y": "s", "N": "s"}
                },
                officeNodes: [],
            },
            oofTreeObj: '',
            treeObj: ''
        }
    },
    methods: {
        changeScope: function () {
            //如果被选中的dataScope是9则显示公司树，如果不是则隐藏公司树
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
                let treeObj = $.fn.zTree.init($("#select-treetreeSelectResEdit"), this.setting, this.nodes);
                this.treeObj = treeObj;
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
                let treeObject = $.fn.zTree.init($("#select-treetreeSelectOfficeEdit"), this.officeSetting, this.officeNodes);
                this.oofTreeObj = treeObject;
            }).catch(function (error) {
                layer.msg(error)
            })
        },
        saveNewRoles: function (rId) {
            this.map.resourceIds = [];
            this.map.offIds = [];
            this.map.roleId=rId;
            /*取得resource的id数组*/
            let treeNodes = this.treeObj.transformToArray(this.treeObj.getNodes());
            console.log("start");
            for (let index in treeNodes) {
                if (treeNodes[index].checked) {
                    this.map.resourceIds.push(treeNodes[index].id)
                }
            }
            console.log(this.map.resourceIds);
            /*取得office的id数组*/
            let offTreeNodes = this.oofTreeObj.transformToArray(this.oofTreeObj.getNodes());
            console.log("start");
            for (let index in offTreeNodes) {
                if (offTreeNodes[index].checked) {
                    this.map.offIds.push(offTreeNodes[index].id)
                }
            }
            console.log(this.map.offIds);

            axios({
                url:'manager/resource/upDataResource',
                method:'post',
                data:this.map
            }).then(response=>{
                layer.msg(response.data)
            }).catch(function (error) {
                layer.msg(error)
            })

        }

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