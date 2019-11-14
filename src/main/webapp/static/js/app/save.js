let lay = new Vue({
    el: '#profile',
    data: {
        appversion: {

        }
    },
    methods: {
        add: function () {
            axios({
                url: 'manager/app/add',
                method: 'post',
                data: this.appversion
            }).then(response => {
                layer.msg(response.data);
            }).catch(function (error) {
                console.log(error)
            })
        }
    }


})