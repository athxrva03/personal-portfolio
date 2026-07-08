window.addEventListener("scroll", () => {

    const header = document.querySelector("header");

    if(window.scrollY > 50){
        header.style.boxShadow =
        "0 4px 15px rgba(0,0,0,0.4)";
    }else{
        header.style.boxShadow = "none";
    }

});

const hamburger =
document.querySelector(".hamburger");

const navLinks =
document.querySelector(".nav-links");

hamburger.addEventListener("click",()=>{

    navLinks.classList.toggle("active");

});

AOS.init({

    duration:1000,

    once:true,

    offset:100

});