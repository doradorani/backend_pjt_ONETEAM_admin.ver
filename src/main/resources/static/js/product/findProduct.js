function findProduct(){
    console.log('findProduct()');

    let form = document.registProductForm;

    if(form.product_name.value == ''){
        alert('Input Please Product');
        form.product_name.focus();
    }
    else {
        ajax_addProduct(form.product_name.value);
    }
}

function ajax_addProduct(name){
    console.log('ajax_addProduct()');

    let msgDto = {
        name : name
    }

    $.ajax({
        url: '/product/selectProduct',
        method: 'POST',
        data: JSON.stringify(msgDto),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(data) {
            console.log('AJAX SUCCESS - ajax_addProduct()');

            if(data.productDtos.length == 0)
                alert('상품을 찾을 수 없습니다.');

            else{
                $("#selectProduct table").children().remove();

                for (let i = 0; i < data.productDtos.length; i += 5) {
                    let appendTag = "<tr>";

                    for (let j = i; j < i + 5 && j < data.productDtos.length; j++) {
                        appendTag += "<td>";
                        appendTag += "<a href='#none' class='add_product' data-product-name='" + data.productDtos[j].name;
                        appendTag += "' data-product-price='" + data.productDtos[j].price + "'>";
                        appendTag += data.productDtos[j].name + " " + data.productDtos[j].price;
                        appendTag += "</td>";
                    }

                    appendTag += "</tr>";
                    $("#selectProduct table").append(appendTag);
                }
            }

        },
        error: function(data) {
            console.log('AJAX ERROR - ajax_addProduct()');

            alert('상품을 찾을 수 없습니다.');
        }
    });
}