// function editCart(cartItemId, orderCount){
//     if(orderCount >= 0){
//         window.location.href='cart.do?operate=editCart&cartItemId='+ cartItemId +'&orderCount='+ orderCount;
//     }
//     else {
//         alert("商品數量必須大於等於0！");
//     }
// }
window.onload=function(){
    var vue = new Vue({
        "el":"#cart_div",
        "data":{
            cart:{

            }

        },
        "methods":{
            getCart:function (){
                axios({
                    "method":"POST",
                    "url":"cart.do",
                    "params":{
                        "operate":'cartInfo'
                    }
                }).then(function(value){
                    var cart = value.data;
                    vue.cart = cart;
                    console.log(value.data);
                }).catch(function(reason){

                })
            },
            editCartInfo:function (cartItemId, orderCount){
                if(orderCount < 0){
                    alert("商品數量必須大於等於0！");
                }else {
                    axios({
                        "method":"POST",
                        "url":"cart.do",
                        "params":{
                            "operate":"editCartInfo",
                            "cartItemId":cartItemId,
                            "orderCount":orderCount
                        }
                    }).then(function (value) {
                        console.log(value.data);
                        vue.getCart();
                    }).catch(function (reason) {

                    })
                }
            }
        },
        "mounted":function(){
           this.getCart();
        }
    });
}
