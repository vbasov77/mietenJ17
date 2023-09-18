$('body').on('click', '#delete_obj', function () {
    if (!confirm('Подтвердите удаление?')) return false;
    let $this = $(this);
    data = {'id': id};
    const preload = `<div class="preload" >
            <img src="/images/loader/preloader.svg" width="35px" >
        </div>`;
    $.ajax({
        url: '/auth/delete_obj',
        type: 'post',
        data: data,
        dataType: 'json',
        success: function (response) {
            if (response.answer === 'ok') {
                let message = $('.preview').html(' <div  class="alert alert-success alert-dismissible" style="color: white" role="alert" > ' +
                    '<button id="close" type="button" class="" data-dismiss="allert"' +
                    'aria-label="Close" > <span aria-hidden="true" ></span></button> Ваш объект был удалён</div>');
                // Закрыть через 5 сек
                $(message).show().delay(3000).hide('slow');
                window.setTimeout(slowAlert, 4000);
                function slowAlert() {
                    window.location.replace("/auth/my_obj");
                }


            }
        },
        error: function () {
            alert('Ошибка!!!')
        }


    });
});

function removeFadeOut(el, speed) {
    var seconds = speed / 500;
    el.style.transition = "opacity " + seconds + "s ease";
    el.style.opacity = 0;
    setTimeout(function () {
        el.parentNode.removeChild(el);
    }, speed);
}