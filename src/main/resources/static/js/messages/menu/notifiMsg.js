var stompClientW = null;
let notifiMsg = $("#notificationsMsg");
let counterJq = $("#counter");

let counter = document.getElementById("counter");

$(document).ready(function () {
    connect();

    $.ajax({
        url: '/find-count-notifications',
        type: 'get',
        dataType: 'json',
        success: function (res) {
            notificationsMsg(res);
        }
    });
});

function connect() {
    var socketW = new SockJS('/our-websocket-menu');
    stompClientW = Stomp.over(socketW);
    stompClientW.connect({}, function (frame) {

        stompClientW.subscribe('/user/topic/notificationsMsg', function (message) {
            notificationsMsg(JSON.parse(message.body));
        });

        stompClientW.subscribe('/user/topic/read-notificationsMsg', function (message) {
            removeNotifiMenu(JSON.parse(message.body))
        });
    });
}


function notificationsMsg(res) {
    notifiMsg.html("");
    counterJq.html("");
    if (res !== 0) {
        notifiMsg.append(res);
    }
}

function removeNotifiMenu(res) {
    console.log(res)
    notifiMsg.html("");
    counterJq.html("");
    if (res !== 0) {
        notifiMsg.append(res);
    }
}