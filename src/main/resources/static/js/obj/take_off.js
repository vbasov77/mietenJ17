$('body').on('click', '#takeOff', function () {
    if (!confirm('Вы хотите снять с публикации?')) return false;
    let $this = $(this);
    const id = $this.data('id');
    data = {'id': id};
    const preload = `<div class="preload" >
            <img src="/images/loader/preloader.svg" width="35px" >
        </div>`;
    $.ajax({
        url: '/user/take_off',
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
                removeItems($this.data('id'));
                $(`div[data-preload="${id}"]`).html($(preload).show().delay(2000).hide('slow', function () {

                    let inHTML = `<div data-id="` + id + `" class="d-inline-block">
                                        <a id="publish" title="Опубликовать" class="rem" data-id="` + id + `">
                                            <img src="/icons/publish.svg"
                                                 style="width: 15px; ">
                                        </a>
                                    </div>`;

                    $(`div[data-inn="${id}"]`).html(inHTML);
                    let inHtmlFooter = `<div data-id="` + id + `" class="d-inline-block">
                                    <div style="color: red">Не опубликовано</div>
                                    
                                </div>`;
                    $(`div[data-footer="${id}"]`).html(inHtmlFooter);
                }));


            }
        },
        error: function () {
            alert('Ошибка!!!')
        }
    });

});
