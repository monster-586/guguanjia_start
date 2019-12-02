let em = new Vue({
    el: '#main-container',
    data:function(){
        return {
            Role: {},
            roleList: {},
            setting:{
                data:{
                    key:{
                        title:''
                    },
                    simpleData:{
                        enable:true
                    }
                },
                callback:{},
                view:{},
                nodes:[]
            }
        }
    },
    methods:{

    },
    created:function (){
        this.Role=parent.layer.Role;
        this.roleList=parent.layer.roleList;
        console.log(this.Role);
        console.log(this.roleList);
    }
})