let em = new Vue({
    el: '#main-container',
    data: {
        pageInfo: {},
        appversion: {}

    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            axios({
                url: "app/controller/selectAll",
                // method:"get",
                params: {
                    pageNum: pageNum,
                    pageSize: pageSize

                }

            }).then(response => {
                console.log(response.data);
                this.pageInfo = response.data;
            }).catch(function (error) {
                console.log(error)
            })
        },
        toupdata: function (id) {
            axios({
                url: 'app/controller/toupdata',
                params: {
                    id: id
                }
            }).then(response => {
                layer.appversion = response.data;
                console.log(layer)
                let upd = layer.open({
                    type: 2,
                    title: "更新",
                    content: 'html/app/update.html',
                    area: ['80%', '80%'],
                    end: () => {

                        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize)
                    }
                });


            }).catch(function (error) {
                console.log(error)
            });


        }
    },
    created: function () {

        this.selectAll();
    }


})