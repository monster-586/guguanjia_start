// let lay = new Vue({
//     el: '#main-container',
//     data: {
//         appversion: {}
//     },
//     methods: {
//         updata: function () {
//             axios({
//                 url: 'manager/app/updata',
//                 method: 'post',
//                 data: this.appversion
//             }).then(response => {
//                 parent.layer.msg(response.data)
//                 let index = parent.layer.getFrameIndex(window.name);
//                 parent.layer.close(index);
//             }).catch(function (error) {
//                 console.log(error)
//             });
//         },
//         cancle:function () {
//             let index = parent.layer.getFrameIndex(window.name);
//             parent.layer.close(index);
//         }
//
//     },
//     created:function (){
//         this.appversion=parent.layer.appversion;
//     }
//
//
// })