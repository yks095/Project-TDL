var getID= RegExp(/^[a-zA-Z0-9]{5,20}$/);
var getPassword= RegExp(/^[a-zA-Z0-9]{10,30}$/);
var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
var check = false;
$( '#user_id' ).blur( function(){

    if($("#user_id").val() == "" || !getID.test($("#user_id").val())){
        $("#user_id").val("");
        $("#user_id").focus();
        $( '.login-form .displayID' ).html( "아이디는 대,소문자와 숫자 조합으로 5자에서 20자입니다.");
        return false;
    }else   {
        $( '.login-form .displayID' ).html( "사용 가능한 아이디입니다. 중복검사를 실시하세요");

        $('#idButton').click(function () {
            var jsonData = JSON.stringify({
                id : $('#user_id').val()
            });
            $.ajax({
                url: "http://localhost:8080/toDoList/check/register",
                type: "POST",
                data: jsonData,
                contentType: "application/json",
                dataType: "json",
                success: function () {
                    $( '.login-form .displayID' ).html( "사용 가능한 아이디입니다.");
                    check = true;
                },
                error: function () {
                    $( '.login-form .displayID' ).html( "사용 불가능한 아이디입니다.");
                    $("#user_id").val("");
                    $("#user_id").focus();
                    return false;
                }
            });
        });
    }
});

$( '#user_password' ).blur( function(){

    if($("#user_password").val() == "" || !getPassword.test($("#user_password").val())){
        $("#user_password").val("");
        $("#user_password").focus();
        $( '.login-form .displayPassword' ).html( "비밀번호는 대,소문자와 숫자 조합으로 10자에서 30자입니다.");
        return false;
    }else   {
        $( '.login-form .displayPassword' ).html( "사용가능한 비밀번호입니다.");
    }
});

$( '#user_email' ).blur( function(){

    if($("#user_email").val() == "" || !getMail.test($("#user_email").val())){
        $("#user_email").val("");
        $("#user_email").focus();
        $( '.login-form .displayEmail' ).html( "이메일형식에 맞게 입력해주세요.");
        return false;
    }else   {
        $( '.login-form .displayEmail' ).html( "사용가능한 이메일입니다.");
    }
});

$('#insert').click(function () {

    if(check == false){
        alert("중복 검사를 실시하세요.");
        return false;
    }
    var jsonData = JSON.stringify({
        id: $('#user_id').val(),
        password: $('#user_password').val(),
        email: $('#user_email').val()
    });
    $.ajax({
        url: "http://localhost:8080/toDoList/api/register",
        type: "POST",
        data: jsonData,
        contentType: "application/json",
        dataType: "json",
        success: function () {
            alert('저장 성공!');
            location.href = '/toDoList/login';
        },
        error: function () {
            alert('저장 실패!');
        }
    });
});