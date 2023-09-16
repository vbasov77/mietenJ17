let inHTML = "";
count = 0;

for (j = 0; j < images.length; j++){
    if (j === 0) {
        document.getElementById("slider")
            .insertAdjacentHTML("afterend",
                `<div class="carousel-item active">
                                        <img src="/img_for_mieten17/` + images[j] + `"alt="">
                                    </div>
                                   `);
    } else {
        document.getElementById("slider")
            .insertAdjacentHTML("afterend",
                `<div class="carousel-item">
                                        <img src="/img_for_mieten17/` + images[j] + `"alt="">
                                    </div>
                                   `);    }
}

// $.each(images, function (key, value) {
//     if (count === 0) {
//         document.getElementById("slider").innerHTML += `<div class="carousel-item active">
//                                         <img src="/images/photo_obj/` + value + `"alt="">
//                                     </div>
//                                    `;
//     } else {
//         document.getElementById("slider").innerHTML += `<div class="carousel-item"> <img src="/images/photo_obj/` + value + `" alt=""></div>`;
//     }
//     count++;
// })