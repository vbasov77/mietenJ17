$('body').on('click', '#reset', function () {
    let localityName = document.getElementById("value")
    let capacity = document.getElementById("capacity")
    let rooms = document.getElementById("rooms")
    let count = document.getElementById("count")
    let priceFrom = document.getElementById("price_from")
    let priceTo = document.getElementById("price_to")
    let areaFrom = document.getElementById("area_from")
    let balcony = document.getElementById("balcony")
    let loggia = document.getElementById("loggia")
    let notFirst = document.getElementById("notFirst")
    let notEnd = document.getElementById("notEnd")
    let children = document.getElementById("children")
    let animals = document.getElementById("animals")
    let smoking = document.getElementById("smoking")
    let party = document.getElementById("party")
    let documents = document.getElementById("documents")
    let monthly = document.getElementById("monthly")

    localityName.value = "";
    capacity.value = "";

    count.disabled = false;
    count.value = "";
    rooms.checked = false;
    balcony.checked = false;
    loggia.checked = false;
    notFirst.checked = false;
    notEnd.checked = false;
    children.checked = false;
    animals.checked = false;
    smoking.checked = false;
    party.checked = false;
    documents.checked = false;
    monthly.checked = false;
    priceFrom.value = "";
    priceTo.value = "";
    areaFrom.value = "";

    $.ajax({
        url: '/remove_filter',
        type: 'get',
        dataType: 'json',
        success: function (result) {
            if (result.answer === 'ok') {
                window.location.href = '/';
            }
        }
    });

});