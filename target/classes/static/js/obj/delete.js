$('body').on('click', '#delete_obj', function () {
    if (!confirm('Подтвердите удаление?')) return false;
    let $this = $(this);
    const id = $this.data('id');
    data = {'id': id};
    const preload = `<div class="preload" >
            <img src="/images/loader/preloader.svg" width="35px" >
        </div>`;
    $.ajax({
        url: '/delete_obj',
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
                $(`div[data-preload="${id}"]`).html($(preload).show().delay(1000).hide('slow', function () {
                    const removeObj = (num) => {
                        let elem = document.querySelectorAll(`div[data-card="${num}"]`);
                        elem.forEach((e) => {
                            removeFadeOut(e, 300)
                        });
                    };
                    removeObj($this.data('id'));
                }));
            }
        },
        error: function () {
            alert('Ошибка!!!')
        }


    });
});

function removeFadeOut( el, speed ) {
    var seconds = speed/500;
    el.style.transition = "opacity "+seconds+"s ease";
    el.style.opacity = 0;
    setTimeout(function() {
        el.parentNode.removeChild(el);
    }, speed);
}