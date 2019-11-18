let em = new Vue({
    el: '#main-container',

    data: function () {
        return {
            pageInfo: {
                pageNum: '',
                pageSize: ''
            },
            map:{
                pageNum: '',
                pageSize: '',
                officeId:'',
                type:'',
                uName:''

            },
            name:'',

            setting:{
                data:{
                    key:{
                        title:'',
                    },
                    simpleData:{
                        enable:true,
                    }
                },
                callback:{
                    onClick: this.Click
                },
                view:{ fontCss: this.changeColor}

            },
            nodes:[
                 // ???
            ],

        }

    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            this.map.pageNum = pageNum;
            this.map.pageSize = pageSize;
            axios({
                url: "manager/examine/index/selectAll",
                method: "post",
                data: this.map

            }).then(response => {
                this.pageInfo = response.data;
                // console.log(this.pageInfo)
            }).catch(function (error) {
                layer.msg(error);
            })
        },
        initTree:function () {
            axios({
                url:'manager/office/list',
                method:'post',
                data:this.map
            }).then(response=>{
                this.pageInfo=response.data;
            $.fn.zTree.init($("#pullDownTreeCurNameone"),this.setting,this.nodes)
            }).catch(function (error) {
                layer.msg(error);
            })

        },

        Click: function (event, treeId, treeNode) {
            this.name = treeNode.name;
        },
        showTree:function(flag){

        },
        search: function () {
            let nodes = this.treeObj.getNodesByParamFuzzy("name",this.name,null)

            let nodeArr = this.treeObj.transformToArray(this.treeObj.getNodes());

            for (let index in nodeArr) {
                nodeArr[index].higtLine=false;
                this.treeObj.updateNode(nodeArr[index]);//更新节点，自动调用清除css
            }

            for (let index in nodeArr) {
                for (let nodeIndex in nodes) {
                    if(nodeArr[index].id==nodes[nodeIndex].id){
                        nodeArr[index].higtLine = true;//设置高亮标记
                        //更新节点  会触发自动的设置css等回调
                        this.treeObj.updateNode(nodeArr[index])
                    }
                }
            }

        },
        changeColor: function (treeId,treeNode) {
            return treeNode.higtLine?{color: "red"} : {color: ''}
        }

    },
    created: function () {
        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);

    },
    mounted:function(){
        this.initTree();
    }

})