let em = new Vue({
    el: '#main-container',
    data: function () {
        return {
            map: {
                treeId: '',
                areaName: '',
                pageNum: '',
                pageSize: ''
            },
            // saveArea: {},
            pageInfo: {
                pageNum: '',
                pageSize: ''
            },
            setting: {
                data: {
                    key: {title: ""},
                    simpleData: {
                        enable: true,
                        pIdKey: 'parentId'
                    }
                },
                callback: {onClick: this.TreeClick},
                view: {}
            },
            nodes: [],
            treeObj: {}
        }

    },
    methods: {
        selectByCondition: function (pageNum, pageSize) {
            this.map.pageNum = pageNum;
            this.map.pageSize = pageSize;
            // console.log("pageNum*****"+this.map.pageNum)
            // console.log("pageSize*****"+this.map.pageSize)
            //     console.log(this.map)
            axios({
                url: 'manager/area/selectByCondition',
                method: 'post',
                data: this.map
            }).then(response => {
                this.pageInfo = response.data;
                this.map.treeId = '';
                // console.log(this.pageInfo)
                this.map.areaName = '';
            }).catch(function (error) {
                console.log(error);
            });


        },
        clear: function () {

        },

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

        TreeClick: function (event, treeId, treeNode) {
            this.map.treeId = treeNode.id;
            this.selectByCondition(1, 5);
        },
        showTree: function (flag) {

        },
        selectOneById: function (id) {
            axios({
                url: 'manager/area/selectOneById',
                params: {
                    id: id
                }
            }).then(response => {
                layer.areaDetail = response.data;
                console.log(response.data)
                let upd = layer.open({
                    type: 2,
                    title: "详细信息",
                    content: 'html/area/detail.html',
                    area: ['80%', '80%'],
                    end: () => {
                        console.log("***********************2")
                    }
                });


            }).catch(function (error) {
                console.log(error)
            })

        },

        exportExcel: function () {
            location.href = 'manager/area/exportExcel';
        },
        importExcel: function (e) {
            console.log(e.target);//获取事件源对象   input
            let file = e.target.files[0];//获取上传的文件对象
            let form = new FormData();//构建表单对象
            form.append("file", file);//绑定file对象到key file上，该key必须与后台的接收参数名一致
            //获取nodes
            axios({
                url: 'manager/area/importExcel',
                method: "post",
                headers: {"content-type": 'multipart/form-data'},//设置请求头为文件上传
                data: form
            }).then(response => {
                layer.msg(response.data);

            }).catch(function (error) {
                layer.msg(error);
            })
        },

        toSave: function (id) {
            axios({
                url: 'manager/area/selectOneById',
                params: {
                    id: id
                }
            }).then(response => {
                layer.areaSave = response.data;
                console.log(response.data)
                let upd = layer.open({
                    type: 2,
                    title: "编辑",
                    content: 'html/area/save.html',
                    area: ['80%', '80%'],
                    end: () => {
                        console.log("**********");
                        this.selectByCondition(this.pageInfo.pageNum,this.pageInfo.pageSize)
                    }
                });
            }).catch(function (error) {
                layer.msg(error)
            })


        }

    },
    created: function () {
        this.selectByCondition();
    },
    mounted: function () {
        this.initTree();
    }


})