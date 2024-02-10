var stompClient = null;

$(document).ready(function () {
    connectWebSocket();
});

document.addEventListener("DOMContentLoaded", () => {
    goPing();
});

/**
 * Сработает, если ввести от 1-го символа в инпуте
 */
document.querySelector('#private-message').addEventListener('keydown', function () {
    if (this.value === '') {
        counterJq.html("");
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

function connectWebSocket() {
    var socket = new SockJS('/our-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        stompClient.subscribe('/user/topic/private-messages/' + chatId, function (message) {
            showMessage(JSON.parse(message.body));
        });

        stompClient.subscribe('/user/topic/read-messages/' + chatId, function (message) {
            changeBackground(JSON.parse(message.body));
        });

    });
}


function changeBackground(res) {
    if (res) {
        const checkBackground = (id) => {
            const element = document.querySelector(`div[data-id="${id}"]`);
            element.style.backgroundColor = '#f5f5f5';
        };
        for (let i = 0; i < res.length; i++) {
            checkBackground(res[i]);
        }
    }
}

function showMessage(res) {
    $(".messages ul").append(`<li class="send"> <div class="myClass">
<div id="${res.id}" data-id="${res.id}" style="font-size: 17px;" class="messageBlock">
<div class="round-popup"></div>${escapeHtml(res.body)}<br><small  style="font-size: 10px" class="mb-0 text-left">${res.createdAt}</small >
                </div></div></li>`);
    $('.messages').animate({scrollTop: $('.messages ul').height()}, "fast");// Центровка по новому сообщению
}


