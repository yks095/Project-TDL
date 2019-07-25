$('.sendID').click(function () {

    var jsonData = JSON.stringify({

        email: $('.insertEmail').val()

    });
    $.ajax({
        url: "http://localhost:8080/toDoList/api/findID",
        type: "POST",
        data: jsonData,
        contentType: "application/json",
        dataType: "json",
        success: function () {
            alert('E-Mail로 ID가 전송되었습니다!');
            location.href = '/toDoList/login';
        },
        error: function () {
            alert('E-Mail을 다시 확인해주십시오.');
        }
    });
});

$('.sendCodeButton').click(function () {

    var jsonData = JSON.stringify({

        email: $('.sendCode').val()

    });
    $.ajax({
        url: "http://localhost:8080/toDoList/api/findPW",
        type: "POST",
        data: jsonData,
        contentType: "application/json",
        dataType: "json",
        success: function () {
            alert('E-Mail로 인증코드가 전송되었습니다!');
        },
        error: function () {
            alert('E-Mail을 다시 확인해주십시오.');
        }
    });
});

$('.insertCodeButton').click(function () {

    var auth = $('.insertCode').val();

    var change = $(this).parent().parent().parent().find('form');

    var jsonData = JSON.stringify({

        code : auth

    });
    $.ajax({
        url: "http://localhost:8080/toDoList/api/checkCode",
        type: "POST",
        data: jsonData,
        contentType: "application/json",
        dataType: "json",
        success: function () {
            alert('인증코드가 일치합니다!');
            
            console.log("비밀번호 변경 시작")
            
            var div = document.createElement("div");
            div.setAttribute("class", "changePW");

            var firstPW = document.createElement("input");
            firstPW.setAttribute("class", "firstPW");
            firstPW.setAttribute("name", "firstPW");
            firstPW.setAttribute("type", "text");
            firstPW.setAttribute("placeholder", "firstPW");
            div.append(firstPW);

            var secondPW = document.createElement("input");
            secondPW.setAttribute("class", "secondPW");
            secondPW.setAttribute("name", "secondPW");
            secondPW.setAttribute("type", "text");
            secondPW.setAttribute("placeholder", "secondPW");
            div.append(secondPW);

            var changeButton = document.createElement('button');
            changeButton.setAttribute("type", "button");
            changeButton.setAttribute("class", "changeButton");
            changeButton.appendChild(document.createTextNode("변경"));

            console.log("비밀번호 변경 마지막");
            
            changeButton.onclick = function changeButton() {

                var jsonData = JSON.stringify({

                    email: $('.insertEmail').val(),
                    firstPW: $('.firstPW').val(),
                    secondPW: $('.secondPW').val()

                });

                    $.ajax({
                        url: "http://localhost:8080/toDoList/api/changePW",
                        type: "POST",
                        data: jsonData,
                        contentType: "application/json",
                        dataType: "json",
                        success: function () {
                            alert('비밀번호 변경 성공!');
                            location.href = '/toDoList/login';
                        },
                        error: function () {
                            alert('비밀번호 변경 실패!');
                        }
                    });
            };

            div.append(changeButton);
            change.append(div);

        },
        error: function () {
            alert('인증코드가 일치하지않습니다.');
        }
    });
});