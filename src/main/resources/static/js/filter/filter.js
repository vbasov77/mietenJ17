let percent = "%";

let loft = document.getElementById("rooms");
if (countRooms === "студия") {
    loft.checked = true;
    document.getElementById('count').disabled = true;
}


balconyArr = balcony.split(",")
$('#balconyAp').find(':checkbox[name="balcony[]"]').each(function () {
    if (balconyArr.some(v => v.trimStart() === $(this).val())) {
        $(this).prop('checked', true);
    }
});


let notFirstEl = document.getElementById("notFirst");
if(notFirst !== 2147483647) {
    notFirstEl.checked = true;
}

let notEndEl = document.getElementById("notEnd");
if(notEnd === 1){
    notEndEl.checked = true;
}

let childrenEl = document.getElementById("children");
if(children !== percent){
    childrenEl.checked = true;
}


let animalsEl = document.getElementById("animals");
if(animals != null && animals !== percent){
    animalsEl.checked = true;
}


let smokingEl = document.getElementById("smoking");
if(smoking != null && smoking !== percent){
    smokingEl.checked = true;
}


let partyEl = document.getElementById("party");
if(party != null && party !== percent){
    partyEl.checked = true;
}


let documentsEl = document.getElementById("documents");
if(documents != null && documents !== percent){
    documentsEl.checked = true;
}


let monthlyEl = document.getElementById("monthly");
if(monthly != null && monthly !== percent){
    monthlyEl.checked = true;
}