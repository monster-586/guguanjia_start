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
        this.map.pageNum=pageNum;
        this.map.pageSize=pageSize;
        console.log("pageNum*****"+this.map.pageNum)
        console.log("pageSize*****"+this.map.pageSize)
            console.log(this.map)
            axios({
                url: 'manager/area/selectByCondition',
                method: 'post',
                data: this.map
            }).then(response => {
                this.pageInfo = response.data;
                this.map.treeId ='';
                // console.log(this.pageInfo)
                this.map.areaName='';
            }).catch(function (error) {
                console.log(error);
            });


        },
        clear:function(){

        },

        initTree: function () {
            axios({
                url: "manager/area/list",
                method: 'get',
                params: ''
            }).then(response => {
                this.nodes = response.data;
                console.log(response.data);
                let treeObject = $.fn.zTree.init($("#treeMenu"), this.setting, this.nodes);

                this.treeObj = treeObject;
                console.log(this.treeObj)
            }).catch(function (error) {
                console.log(error);
            })
        },

        TreeClick: function (event, treeId, treeNode) {
            // console.log(treeNode.id)
            this.map.treeId = treeNode.id;
            this.selectByCondition(1,5);
            // this.map.treeId ='';

        },
        showTree: function (flag) {

        },


    },
    created: function () {
        this.selectByCondition();
    },
    mounted: function () {
        this.initTree();
    }


})