let em = new Vue({
    el: '#mian-container',
    data: {
        pageInfo: {
            pageNum: 1,
            pageSize: 4
     }
    },
    methods: {
        fun: function () {
            axios({
                url:"index",
                param:{
                    pageNum: 1,
                    pageSize: 4
                }

            })
        }
    }


})