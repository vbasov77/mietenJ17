var stompClient = null;
let session = false;

$(document).ready(function () {
    connect();
});

/**
 * Сработает, если ввести от 1-го символа в инпуте
 */
document.querySelector('#private-message').addEventListener('keydown', function () {
    if (this.value === '' && session === false) {
        session = true;
        goPing();
    }
})

/**
 * Сработает, если обновить страницу
 * @type {confirmExit}
 */
window.onbeforeunload = confirmExit;

function confirmExit() {
    goPing();
}

function goPing() {
    data = {
        "userId": to_user_id,
        "fromUserId": from_user_id,
        "chatId": chatId,
    };
    $.ajax({
        url: '/read',
        type: 'post',
        data: data,
        dataType: 'json',
        success: function (res) {

        }
    });
}


function connect() {
    var socket = new SockJS('/our-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        stompClient.subscribe('/user/topic/private-messages/' + chatId, function (message) {
            showMessage(JSON.parse(message.body));
            notificationsMsg();
        });

        stompClient.subscribe('/user/topic/read-messages/' + chatId, function (message) {
            changeBackground(JSON.parse(message.body));
        });

        // stompClient.subscribe('/user/topic/notificationsMsg/user-id:' + from_user_id, function (message) {
        //     notificationsMsg(JSON.parse(message.body));
        // });

    });
}
function notificationsMsg(res){
    $("#notificationsMsg").append(`<div class="notifications">!</div>`)
}
document.addEventListener("DOMContentLoaded", () => {
    goPing();
});

function changeBackground(res) {
    if (res.length) {
        const checkBackground = (id) => {
            const element = document.querySelector(`div[data-id="${id}"]`);
            element.style.backgroundColor = '#f5f5f5';

        };
        for (let i = 0; i < res.length; i++) {
            checkBackground(res[i]);
        }
    }
    session = false;
}




function showMessage(res) {
    $(".messages ul").append(`<li class="send"> <div class="myClass">
<div id="${res.id}" data-id="${res.id}" style="font-size: 17px;" class="messageBlock">
<div class="round-popup"></div>${escapeHtml(res.body)}<br><small  style="font-size: 10px" class="mb-0 text-left">${res.createdAt}</small >
                </div></div></li>`);
    $('.messages').animate({scrollTop: $('.messages ul').height()}, "fast");// Центровка по новому сообщению
}


