function createAccountConfirm() {
    console.log("createAccountConfirm() CALLED!!!");

    let form = document.createAccountForm;

    if(form.school_no.value == ''){
        alert("학교를 입력해주세요!");
        form.school_no.focus();
    } else if(form.id.value == ''){
        alert("아이디를 입력해주세요!");
        form.id.focus();
    } else if(form.password.value == ''){
        alert("비밀번호를 입력해주세요!");
        form.password.focus();
    } else if(form.password_again.value == ''){
        alert("비밀번호 확인을 다시 입력해주세요!");
        form.password_again.focus();
    } else if(form.password.value != form.password_again.value){
        alert("비밀번호와 비밀번호 확인이 맞지 않습니다.");
        form.password.focus();
    } else if(form.name.value == ''){
        alert("이름을 입력해주세요!");
        form.name.focus();
    } else if(form.gender.value == ''){
        alert("성별을 선택해주세요!");
        form.gender.focus();
    } else if(form.position.value == ''){
        alert("부서를 입력해주세요!");
        form.gender.focus();
    } else if(form.phone.value == ''){
        alert("핸드폰 번호를 입력해주세요!");
        form.phone.focus();
    } else if(form.address.value == ''){
        alert("주소를 입력해주세요!");
        form.address.focus();
    }else if(form.mail.value == ''){
        alert("도메인을 입력해주세요!");
        form.mail.focus();
    } else {

        form.submit();
    }

}