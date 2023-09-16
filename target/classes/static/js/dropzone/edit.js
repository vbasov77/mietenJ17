$(document).ready(function () {
    let noneBalcony = document.getElementById("noneBalcony");
    let balcony = document.getElementById("balcony");
    let loggia = document.getElementById("loggia");
    let noneParking = document.getElementById("noneParking");
    let onTheStreet = document.getElementById("onTheStreet");
    let inTheBuilding = document.getElementById("inTheBuilding");
    let linen = document.getElementById("linen");
    let towels = document.getElementById("towels");
    let hygieneProducts = document.getElementById("hygieneProducts");
    let noneComfort = document.getElementById("noneComfort");
    let noneInternet = document.getElementById("noneInternet");
    let viFi = document.getElementById("viFi");
    let television = document.getElementById("television");

    if (noneBalcony.checked) {
        balcony.disabled = true;
        loggia.disabled = true;
    }
    $('body').on('click', '#noneBalcony', function () {
        if (noneBalcony.checked) {
            balcony.disabled = true;
            balcony.checked = false;
            loggia.disabled = true;
            loggia.checked = false;
        } else {
            balcony.disabled = false;
            loggia.disabled = false;
        }
    });

    if (noneParking.checked) {
        onTheStreet.disabled = true;
        inTheBuilding.disabled = true;
    }
    $('body').on('click', '#noneParking', function () {
        if (noneParking.checked) {
            onTheStreet.disabled = true;
            onTheStreet.checked = false;
            inTheBuilding.disabled = true;
            inTheBuilding.checked = false;
        } else {
            onTheStreet.disabled = false;
            inTheBuilding.disabled = false;
        }
    });
    if (noneComfort.checked) {
        linen.disabled = true;
        towels.disabled = true;
        hygieneProducts.disabled = true;

    }
    $('body').on('click', '#noneComfort', function () {
        if (noneComfort.checked) {
            linen.disabled = true;
            linen.checked = false;
            towels.disabled = true;
            towels.checked = false;
            hygieneProducts.disabled = true;
            hygieneProducts.checked = false;
        } else {
            linen.disabled = false;
            towels.disabled = false;
            hygieneProducts.disabled = false;
        }
    });
    if (noneInternet.checked) {
        viFi.disabled = true;
        television.disabled = true;
    }
    $('body').on('click', '#noneInternet', function () {
        if (noneInternet.checked) {
            viFi.disabled = true;
            viFi.checked = false;
            television.disabled = true;
            television.checked = false;
        } else {
            viFi.disabled = false;
            television.disabled = false;
        }
    });

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>УДАЛИТЬ ФОТО<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    $('body').on('click', '.del', function () {
        if (!confirm('Подтвердите удаление')) return false;
        var $this = $(this);
        file = $this.data('file');
        $.ajax({
            url: '/delete_img',
            data: {file: file},
            success: function (responce) {
                $this.fadeOut();
            },

            error: function () {
                alert('Ошибка!!!')
            }
        });
    });

    let myDropzone = new Dropzone("div#file", {
        url: "/add_img/id" + id,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        maxFilesize: 2,
        maxFiles: 25,
        parallelUploads: 1,
        acceptedFiles: ".jpg",
        dictInvalidFileType: "Разрешены к загрузке только файлы .png, .jpg, .gif, .jpeg",
        dictMaxFilesExceeded: "Максимум 20 фото",
        dictFileSizeUnits: "Максимум 2 MB",
        dictDefaultMessage: '<div class="dz-button" type="button">Нажмите здесь или перетащите сюда файлы для загрузки</div>',
        success: function (file, response) {
            var url = file.dataURL;
            var ar = String(response.fil);
            array = ar.split(',');
            var inHTML = '';

            if (response.answer === 'error') {
                $('.preview').html(' <div class="alert alert-danger alert-dismissible" role="alert" > <button type="button" class="close" data-dismiss="allert"' +
                    'aria-label="Close" > <span aria-hidden="true" >&times;</span></button>' + res.mess + '</div>');
            } else {
                $('div#filImg').empty();
                $.each(array, function (key, value) {
                    $('div#files').empty();
                    var html = `<img class="img-thumbnail del" src="/img_for_mieten17/${value.trimStart()}" data-file="${value.trimStart()}"/>`;
                    inHTML += html;
                    $('div#files').html(inHTML);
                })
            }
            this.removeFile(file);
            $(this.element).html(this.options.dictDefaultMessage).delay(500).fadeIn('slow');
        },

        init: function () {
            $(this.element).html(this.options.dictDefaultMessage);
        },

    });

    $('#form').on('submit', function (e) {
        e.preventDefault();
        var his = $(this),
            btn = his.find("button.submit"),
            data = $("#form").serialize(),
            preloader = $('.preloader-img');
        preloader.fadeIn(300);


        if (!noneBalcony.checked && !balcony.checked && !loggia.checked) {
            preloader.delay(500).fadeOut('slow', function () {
                let balconyAp = document.getElementById('balconyAp');
                balconyAp.scrollIntoView({behavior: "smooth"});
                let messageBalcony = $('.messageBalcony').html(' <div  class="alert alert-danger alert-dismissible" role="alert" > <button id="close" type="button" class="close" data-dismiss="allert"' +
                    'aria-label="Close" > <span aria-hidden="true" ></span></button>Обязательно для выбора</div>');
                // Закрыть через 5 сек

                $(messageBalcony).show().delay(5000).hide('slow');
            });

        } else if (!noneParking.checked && !onTheStreet.checked && !inTheBuilding.checked) {
            preloader.delay(500).fadeOut('slow', function () {
                let parkingAp = document.getElementById('parkingAp');
                parkingAp.scrollIntoView({behavior: "smooth"});
                let messageParking = $('.messageParking').html(' <div  class="alert alert-danger alert-dismissible" role="alert" > <button id="close" type="button" class="close" data-dismiss="allert"' +
                    'aria-label="Close" > <span aria-hidden="true" ></span></button>Обязательно для выбора</div>');
                // Закрыть через 5 сек

                $(messageParking).show().delay(5000).hide('slow');
            });
        } else if (!noneComfort.checked && !linen.checked && !towels.checked && !hygieneProducts.checked) {
            preloader.delay(500).fadeOut('slow', function () {
                let comfortAp = document.getElementById('comfortAp');
                comfortAp.scrollIntoView({behavior: "smooth"});
                let messageComfort = $('.messageComfort').html(' <div  class="alert alert-danger alert-dismissible" role="alert" > <button id="close" type="button" class="close" data-dismiss="allert"' +
                    'aria-label="Close" > <span aria-hidden="true" ></span></button>Обязательно для выбора</div>');
                // Закрыть через 5 сек

                $(messageComfort).show().delay(5000).hide('slow');
            });
        } else if (!noneInternet.checked && !viFi.checked && !television.checked) {
            preloader.delay(500).fadeOut('slow', function () {
                let internetAp = document.getElementById('internetAp');
                internetAp.scrollIntoView({behavior: "smooth"});
                let messageInternet = $('.messageInternet').html(' <div  class="alert alert-danger alert-dismissible" role="alert" > <button id="close" type="button" class="close" data-dismiss="allert"' +
                    'aria-label="Close" > <span aria-hidden="true" ></span></button>Обязательно для выбора</div>');
                // Закрыть через 5 сек

                $(messageInternet).show().delay(5000).hide('slow');
            });
        } else {

            $.ajax({
                url: '/edit_obj/id' + id,
                type: 'POST',
                data: data,
                dataType: "html", //формат данных
                beforeSend: function () {
                    preloader.fadeIn(300);
                },
                success: function (response) {
                    var res = JSON.parse(response);
                    console.log(typeof res)
                    preloader.delay(500).fadeOut('slow', function () {
                        // var button = document.querySelector('#close');
                        if (res.answer === 'ok') {
                            // Закрыть при клике
                            // $('body').on('click', '.close', function () {
                            //     $('.preview').hide('slow');
                            // });

                            let message = $('.preview').html(' <div  class="alert alert-success alert-dismissible" role="alert" > <button id="close" type="button" class="close" data-dismiss="allert"' +
                                'aria-label="Close" > <span aria-hidden="true" ></span></button> Данные сохранены</div>');
                            // Закрыть через 5 сек
                            $(message).show().delay(3000).hide('slow');
                        } else {
                            let message = $('.preview').html(' <div class="alert alert-danger alert-dismissible" role="alert" > <button type="button" id="close"  class="close" data-dismiss="allert"' +
                                'aria-label="Close" > <span aria-hidden="true" >&times;</span></button> Ошибка сохранения данных</div>');

                            $(message).show().delay(3000).hide('slow');
                        }
                        myDropzone.removeAllFiles();
                    });
                    return false;
                },
                error: function () {
                    alert('Ошибка');
                }
            });
        }

    });
});


