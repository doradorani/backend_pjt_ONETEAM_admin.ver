function registProductConfirm(){
    console.log("registProductConfirm()");

    let form = document.registProductForm;

    if(form.product_name.value == ''){
        alert("INPUT PRODUCT NAME");
        form.product_name.focus();
    }
    else if(form.product_price.value == ''){
        alert("INPUT PRODUCT PRICE");
        form.product_price.focus();
    }
    else{
        form.submit();
    }

}