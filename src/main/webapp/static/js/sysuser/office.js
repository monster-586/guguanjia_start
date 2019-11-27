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
                // console.log(response.data);
                let treeObject = $.fn.zTree.init($("#treeMenu"), this.setting, this.nodes);

                this.treeObj = treeObject;
                // console.log(this.treeObj)
            }).catch(function (error) {
                console.log(error);
            })
        },
        TreeClick: function (event, treeId, treeNode) {
            this.name=treeNode.name;
        },
        toSave: function (uid) {

            axios({
                url: 'manager/sysuser/selectOneById',
                params: {
                    id: id
                }
            }).then(response => {
                layer.areaSave = response.data;
                console.log(response.data);
                let _this = this;
                let upd = layer.open({
                    type: 2,
                    title: "修改",
                    content: 'html/user/user-save.html',
                    area: ['80%', '80%'],
                    end: () => {
                        console.log("**********");
                        this.selectByCondition(this.pageInfo.pageNum, this.pageInfo.pageSize);
                    }
                });
            }).catch(function (error) {
                layer.msg(error)
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