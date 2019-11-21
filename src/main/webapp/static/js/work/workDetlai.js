let em = new Vue({
    el: '#main-container',
    data: {
        workOrder: {},
    },

    created:function (){
        this.workOrder=parent.layer.workOrder;
        console.log(this.workOrder)
    }
})