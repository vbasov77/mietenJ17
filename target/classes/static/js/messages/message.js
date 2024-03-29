const escapeHtml = (unsafe) => {
    return unsafe.replaceAll('&', '&amp;').replaceAll('<', '&lt;').replaceAll('>', '&gt;').replaceAll('"', '&quot;').replaceAll("'", '&#039;');
};
let arrayId = [];

// Формируем массив по data
const attributes = document.getElementsByClassName('messageBlock');
for (const attribute of attributes) {
    if (attribute.getAttribute('data-notified') == 0) {
        arrayId.push(attribute.getAttribute('id'))
    }
}

// setInterval(checkNewMsg, 3000);
// setInterval(notified, 5000);


function notified() {
    if (arrayId.length) {
        data = {
            "to_user_id": to_user_id,
            "from_user_id": from_user_id,
            "obj_id": obj_id,
            "array_id": arrayId,
        };
        console.log(arrayId)
        $.ajax({
            url: '/notified',
            type: 'post',
            data: data,
            dataType: 'json',
            success: function (res) {
                if (res.length) {
                    const checkBackgroun = (id) => {
                        let element = document.getElementById(id);
                        element.style.backgroundColor = '#f5f5f5';
                    };
                    for (let j = 0; j < res.length; j++) {
                        if (res[j].to_user_id = to_user_id) {
                            checkBackgroun(res[j].id);
                        }
                    }
                    arrayId = [];
                }
            }


        });
    }

}


$('.messages').animate({scrollTop: $('.messages ul').height()}, "fast");

function newMessage() {
    var message = /*escapeHtml(*/$('.message-input input').val();
    data = {
        "to_user_id": to_user_id,
        "from_user_id": from_user_id,
        "obj_id": obj_id,
        "chatId": chatId,
        "body": message,
    };
    $.ajax({
        url: '/user/add_message',
        type: 'post',
        data: data,
        dataType: 'json',
        success: function (res) {
            arrayId.push(res.id);
            let date = res.date;
            if ($.trim(message) == '') {
                message = $('.message-input .emoji-wysiwyg-editor').html();
                return false;
            }

            $(`<li class="send"> <div class="myClass">
<div id="` + res.id + `" data-id="` + res.id + `" style="float: right; font-size: 17px; background-color: #dad6f5; " class="messageBlock">
<div class="round-popup">
<button data-id="` + res.id + `" type="button" class="close"
                                            aria-label="Close"><span aria-hidden="true">&times;</span></button> </div>
${escapeHtml(res.body)}<br>
                <small  style="font-size: 10px" class="mb-0 text-left">${date.toLocaleString()}</small >
                </div></div></li>`).appendTo($('.messages ul'));
            $('.message-input input').val('');
            $('.message-input .emoji-wysiwyg-editor').html('');
            $('.messages').animate({scrollTop: $('.messages ul').height()}, "fast");
        }
    });


};

$('.submit').click(function () {
    let input = document.querySelector('input');
    if (input.value !== "") {
        newMessage();
    }

});

// отправить сообщение по Enter
$("#framechat .content .message-input").keyup(function (event) {
    if (event.keyCode === 13) {
        $(".submit").click();
    }
});


// Удаление сообщения
$('body').on('click', '.close', function () {
    if (!confirm('Подтвердите удаление')) return false;
    let $this = $(this);
    data = {'id': $this.data('id')};
    $.ajax({
        url: '/user/delete_msg',
        type: 'post',
        data: data,
        dataType: 'json',
        success: function (result) {

            const removeItems = (number) => {
                let elements = document.querySelectorAll(`div[data-id="${number}"]`);
                elements.forEach((e) => {
                    e.remove()
                });
            };
            if (result.answer === 'ok') {
                removeItems($this.data('id'));
            }
        }
    });

});
