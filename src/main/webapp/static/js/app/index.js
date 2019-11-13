let em = new Vue({
    el: '#main-container',
    data: {
        pageInfo: {},
        appversion: {}

    },
    methods: {
        selectALL: function (pageNum, pageSize) {
            axios({
                url: "app/controller/selectAll",
                // method:"get",
                params: {
                    pageNum: pageNum,
                    pageSize: pageSize

                }

            }).then(response => {
                // console.log(response.data);
                this.pageInfo = response.data;
            }).catch(function (error) {
                console.log(error)
            })
        },
        updata: function (id) {
            axios({
                url: 'app/controller/toupdata',
                params: {
                    id:id
                }
            }).then(response => {
                this.appversion=response.data;
            }).catch(function (error) {
                console.log(error)
            })


        }
    },
    created: function () {

        this.selectALL();
    }


})