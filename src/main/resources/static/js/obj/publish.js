$('body').on('click', '#publish', function () {
    if (!confirm('Вы хотите опубликовать?')) return false;
    let $this = $(this);
    const id = $this.data('id');
    data = {'id': id};
    const preload = `<div class="preload" >
            <img src="/images/loader/preloader.svg" width="35px" >
        </div>`;
    $.ajax({
        url: '/auth/publish',
        type: 'post',
        data: data,
        dataType: 'json',
        success: function (response) {
            if (response.answer === 'ok') {
                const removeItems = (number) => {
                    let elements = document.querySelectorAll(`div[data-id="${number}"]`);
                    elements.forEach((e) => {
                        e.remove()
                    });
                };

                if (response.answer === 'ok') {
                    removeItems($this.data('id'));
                    $(`div[data-preload="${id}"]`).html($(preload).show().delay(2000).hide('slow', function () {
                        let inHtml = `<div data-id="` + id + `" class="d-inline-block">
                                        <a id="takeOff" title="Снять с публикации" class="rem"
                                           data-id="` + id + `">
                                            <img src="/icons/take_off.svg"
                                                 style="width: 18px; ">
                                        </a>
                                    </div>`;
                        $(`div[data-inn="${id}"]`).html(inHtml);

                        let inHtmlFooter = `<div data-id="` + id + `" class="d-inline-block">
                                        <div style="color: #2fa360">Опубликовано</div>
                                        <a id="takeOff"  style="text-decoration: none; color: red;"
                                           >
                                            Снять с публикации</a>
                                    </div>`;


                        $(`div[data-footer="${id}"]`).html(inHtmlFooter);
                    }));
                }
            }
        },
        error: function () {
            alert('Ошибка!!!')
        }
    });

});
