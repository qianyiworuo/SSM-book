function editCart(cartItemId, orderCount){
    if(orderCount >= 0){
        window.location.href='cart.do?operate=editCart&cartItemId='+ cartItemId +'&orderCount='+ orderCount;
    }
    else {
        alert("商品數量必須大於等於0！");
    }
}