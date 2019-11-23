let em = new Vue({
    el: '#main-container',
    data: {
        areaSave: {}
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
        dones: function () {
            axios({
                url: 'manager/area/list',
                method: 'get',
                params: ''
            }).then(response => {
                layer.done = response.data;
                let vv = layer.open({
                    type: 2,
                    title: "选择上级区域",
                    content: 'html/area/select.html',
                    area: ['80%', '80%'],
                    end: () => {
                        console.log("***********************2");
                        this.dones();
                    }
                })
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