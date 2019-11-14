let em = new Vue({
    el: '#main-container',
    data: {
        pageInfo: {},
        appversion: ''

    },
    methods: {
        selectAll: function (pageNum, pageSize) {
            axios({
                url: "manager/app/selectAll",
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
                url: 'manager/app/toupdata',
                params: {
                    id: id
                }
            }).then(response => {
                layer.appversion = response.data;
                console.log(`你好，${layer}`)
                let upd = layer.open({
                    type: 2,
                    title: "更新",
                    content: 'html/app/update.html',
                    area: ['80%', '80%'],
                    end: () => {
                        console.log("***********************")
                        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize)
                    }
                });


            }).catch(function (error) {
                console.log(error)
            })


        },
        del: function (id) {
            layer.msg("删除否?", {
                time: 0,
                btn: ['是', '否'],
                yes: index => {
                    axios({
                        url: 'manager/app/del',
                        params: {
                            id: id
                        }
                    }).then(response => {
                        layer.close(index);
                        layer.msg(response.data)
                        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize)
                    }).catch(function (error) {
                        console.log(error)
                    })
                }
            })


        }

    },
    created: function () {

        this.selectAll();
    }


})