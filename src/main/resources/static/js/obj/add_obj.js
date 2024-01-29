$(function () {
    $('#form').on('submit', function (e) {
        e.preventDefault();
        var his = $(this),
            preloader = $('.preloader-img');
        const address = document.getElementById('suggest').value;
        ymaps.ready(inits);

        function inits() {
            $.ajax({
                method: 'GET',
                url: 'https://geocode-maps.yandex.ru/1.x/?apikey=c5583e4f-c62e-418f-bb87-3c814516c530&format=json&geocode=' + address + '',
                success: function (data) {
                    $.ajax({
                        method: 'POST',
                        contentType: 'application/json; charset=UTF-8',
                        dataType: "json",
                        url: "/user/add_object",
                        data: JSON.stringify(data),
                        beforeSend: function () {
                            preloader.fadeIn(300);
                        },
                        success: function (id) {
                            preloader.delay(500).fadeOut('slow', function () {
                                if (id > -1) {
                                    window.location.replace("/user/edit_obj/id" + id);
                                } else {
                                    let message = $('.preview').html(' <div class="alert alert-danger alert-dismissible" role="alert" > <button type="button" id="close"  class="close" data-dismiss="allert"' +
                                        'aria-label="Close" > <span aria-hidden="true" >&times;</span></button> Ошибка сохранения данных</div>');

                                    $(message).show().delay(3000).hide('slow');
                                }
                            });
                        }
                    });
                }
            });
        }
    });


});


