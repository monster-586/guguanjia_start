let em = new Vue({
    el: '#main-container',
    data: {
        areaDetail: {},
    },

    created:function (){
        this.areaDetail=parent.layer.areaDetail;
        console.log(this.areaDetail)
    }
})