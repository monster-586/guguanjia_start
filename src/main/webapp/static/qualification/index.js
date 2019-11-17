let em = new Vue({
    el: '#main-container',
    data: {
        pageInfo: {
            pageNum: 1,
            pageSize: 5,

        },
        map:{
            pageNum: '',
            pageSize: '',
            startDate: '',
            endDate: '',
            type: '',
            check: ''
        },
        qualification:{}

    },
    methods: {
        selsctByorder: function (pageNum, pageSize) {
            this.map.pageNum = pageNum;
            this.map.pageSize = pageSize;
            axios({
                url: "manager/qualification/index/selsctByorder",
                method: "post",
                data: this.map

            }).then(response => {
                this.pageInfo = response.data;
                console.log(this.pageInfo)
            }).catch(function (error) {
                console.log(error)
            })
        },
        toUpdate: function (id) {
            axios({
                url: 'manager/qualification/index/toUpdate',
                params: {
                    id: id
                }
            }).then(response => {
                layer.qualification = response.data;
                console.log(response.data)
                let upd = layer.open({
                    type: 2,
                    title: "更改资质",
                    content: 'html/qualification/update.html',
                    area: ['80%', '80%'],
                    end: () => {
                        console.log("***********************2")
                        this.selsctByorder(this.pageInfo.pageNum, this.pageInfo.pageSize)
                    }
                });


            }).catch(function (error) {
                console.log(error)
            })


        }


        // selectAll:function (pageNum, pageSize) {
        //     map='';
        //     this.selsctByorder(pageNum, pageSize)
        // }



    },
    created: function () {
        this.selsctByorder();
    }


})