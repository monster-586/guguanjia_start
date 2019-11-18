let lay = new Vue({
    el: '#main-container',
    data: {
        qualification: {

        }


    },
    methods: {
        updateCheck: function (check) {
            this.qualification.check=check;

            console.log(this.qualification)
            axios({
                url: 'manager/qualification/index/updateCheck',
                method: 'post',
                data:this.qualification
            }).then(response => {
                parent.layer.msg(response.data)
                let index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }).catch(function (error) {
                console.log(error)
            });
        },


    },
    created:function (){
        this.qualification=parent.layer.qualification;
        // console.log(this.qualification)
    }


})