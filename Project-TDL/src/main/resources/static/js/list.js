$('.status').click(function () {

    var dc = $(this).parent().parent().parent().find('.description').text()

    $.ajax({
        url: "http://localhost:8080/toDoList/api/status/" + $(this).val(),
        type: "PUT",
        contentType: "application/json",
        dataType: "json",
        success: function() {
            alert('완료 갱신 성공!');
            location.href = '/toDoList/list';

            // dc.css("color", "")

        },
        error: function () {
            alert('완료 갱신 실패!');
        }
    });
});


$('#insert').click(function () {

    var jsonData = JSON.stringify({
        description: $('#toDoList_description').val()
    });
    $.ajax({
        url: "http://localhost:8080/toDoList/api/list",
        type: "POST",
        data: jsonData,
        contentType: "application/json",
        dataType: "json",
        success: function () {
            alert('저장 성공!');
            location.href = '/toDoList/list';
        },
        error: function () {
            alert('저장 실패!');
        }
    });
});

$('.update').click(function () {

    var dc = $(this).parent().parent().find('.description').text()

    $.ajax({
        url: "http://localhost:8080/toDoList/api/update/" + $(this).val(),
        type: "PUT",
        data: dc,
        contentType: "application/json",
        dataType: "json",
        success: function () {
            alert('수정 성공!');
            location.href = '/toDoList/list';
        },
        error: function () {
            alert('수정 실패!');
        }
    });
});

$('.delete').click(function () {
    console.log("게시글 삭제");
    $.ajax({
        url: "http://localhost:8080/toDoList/api/delete/" + $(this).val(),
        type: "DELETE",
        contentType: "application/json",
        success: function () {
            alert('삭제 성공!');
            location.reload();
        },
        error: function () {
            alert('삭제 실패!');
        }
    });
});

$('.reply').click(function () {

    var reply_parent_id = $(this).parent().find('.insertReply');
    reply_parent_id.fadeToggle();
    var show = $(this).parent().find('.showReply');
    show.fadeToggle();
});

$('.saveReply').click(function () {

    var reply = $('.insertReply input');

    var reply_text = $(this).parent().parent().find('.inputReply').val();
    var reply_parent = $(this).parent().parent().parent().find('ul');

    var jsonData = JSON.stringify({
        content: reply_text

    });
    $.ajax({
        url: "http://localhost:8080/toDoList/api/reply/" + $(this).val(),
        type: "POST",
        data: jsonData,
        contentType: "application/json",
        dataType: "json",
        success: function (data) {


            reply.val('');
            reply.focus('');

            var li = document.createElement("li");
            li.setAttribute("class", "liReply" + data.idx);

            var a = data['content'];
            var b = data['createdDate'];

            var text = document.createElement('h3');
            text.setAttribute("class", "content");
            text.setAttribute("value", data.idx);
            text.setAttribute("contenteditable", "true");
            text.appendChild(document.createTextNode(a));
            li.append(text);

            var text2 = document.createElement('span');
            text2.setAttribute("class", "createdDate");
            text2.setAttribute("value", data.idx);
            text2.appendChild(document.createTextNode(b.substring(0, 10) + ' ' + b.substring(11, 16)));
            li.append(text2);

            var text4 = document.createElement('button');
            text4.setAttribute("type", "button");
            text4.setAttribute("value", data.idx);
            text4.setAttribute("class", "updateReply");
            text4.setAttribute("style", "outline: none; border: 0px; background-color: white");
            text4.onclick = function upReply() {

                var dc = $(this).parent().find('.content').text()

                console.log("댓글 수정 js");
                console.log("content = " + dc);
                $.ajax({
                    url: "http://localhost:8080/toDoList/api/updateReply/" + $(this).val(),
                    type: "PUT",
                    data: dc,
                    contentType: "application/json",
                    dataType: "json",
                    success: function () {
                        alert('댓글 수정 성공!');
                    },
                    error: function () {
                        alert('댓글 수정 실패!');
                    }
                });
            };

            text4.appendChild(document.createTextNode("수정"));
            li.append(text4);

            var text5 = document.createElement('button')
            text5.setAttribute("type", "button");
            text5.setAttribute("value", data.idx);
            text5.setAttribute("class", "deleteReply");
            text5.setAttribute("style", "outline: none; border: 0px; background-color: white");
            text5.onclick = function deReply() {
                var idx = $(this).val();
                console.log(idx);
                $.ajax({
                    url: "http://localhost:8080/toDoList/api/deleteReply/" + $(this).val(),
                    type: "DELETE",
                    contentType: "application/json",
                    success: function () {
                        alert('댓글 삭제 성공!');
                        location.reload();
                    },
                    error: function () {
                        alert('댓글 삭제 실패!');
                    }
                });
            };
            text5.appendChild(document.createTextNode("삭제"));
            li.append(text5);

            reply_parent.append(li);

        },
        error: function () {
            alert('댓글 저장 실패!');
        }
    });
});

$('.updateReply').click(function () {

    var dc = $(this).parent().find('.content').text()

    console.log("댓글 수정 js");
    console.log("content = " + dc);
    $.ajax({
        url: "http://localhost:8080/toDoList/api/updateReply/" + $(this).val(),
        type: "PUT",
        data: dc,
        contentType: "application/json",
        dataType: "json",
        success: function () {
            alert('댓글 수정 성공!');
        },
        error: function () {
            alert('댓글 수정 실패!');
        }
    });
});

$('.deleteReply').click(function () {

    console.log("댓글 삭제 js");

    $.ajax({
        url: "http://localhost:8080/toDoList/api/deleteReply/" + $(this).val(),
        type: "DELETE",
        contentType: "application/json",
        success: function () {
            alert('댓글 삭제 성공!');
            location.reload();
        },
        error: function () {
            alert('댓글 삭제 실패!');
        }
    });
});
