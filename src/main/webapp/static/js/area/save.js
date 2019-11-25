let em = new Vue({
    el: '#main-container',
    data: {

        areaSave: {},

        name: '',

    },
    methods: {
        selectIcon: function () {
            let index = layer.open({
                type: 2,
                title: '区域图标',
                content: 'html/modules/font-awesome.html',
                area: ['80%', '80%'],
                end: () => {//将then函数中的this传递到end的回调函数中
                    console.log(this.areaSave)
                    this.areaSave.icon = layer.icon;//将替换掉的icon值给vue
                }
            });
        },
        toSelect: function () {
            // this.areaSave.oldParentId=this.areaSave.parentId;
            // this.areaSave.oldParentIds=this.areaSave.parentIds;
            axios({
                url: 'manager/area/list',
                method: 'get',
                params: ''
            }).then(response => {
                layer.obj = response.data;
                let vv = layer.open({
                    type: 2,
                    title: "选择上级区域",
                    content: 'html/area/select.html',
                    area: ['80%', '80%'],
                    end: () => {
                        this.areaSave.parentId = layer.pId;
                        this.areaSave.parentName = layer.name;

                    }
                })
            }).catch(function (error) {
                console.log(error)
            })
        },

        // getById: function (pId) {
        //     axios({
        //         url: 'manager/area/selectOneById',
        //         params: {
        //             id: pId
        //         }
        //     }).then(response => {
        //         this.areaSave = response.data;
        //         // console.log(response.data);
        //     }).catch(function (error) {
        //         layer.msg(error)
        //     })
        //
        // }
        save: function () {

            axios({
                url: 'manager/area/update',
                method: 'post',
                data: this.areaSave
            }).then(response => {
                layer.msg(response.data)
                // let index = parent.layer.getFrameIndex(window.name);
                // parent.layer.close(index)
            }).catch(function (error) {
                console.log(error)
            })
        }
    },
    created: function () {
        this.areaSave = parent.layer.areaSave;
        console.log("*********111111111*1*" + this.areaSave)
    }
})