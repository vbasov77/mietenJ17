const bod1 = document.querySelector('article');

bod1.style.display = 'none';
document.addEventListener("DOMContentLoaded", () => {

    setTimeout(() => {
        document.querySelector('.preloader').remove();
        bod1.style.display = 'block'
    }, 1000);
});