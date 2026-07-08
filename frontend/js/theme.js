const themeBtn =
document.getElementById("themeToggle");

const currentTheme =
localStorage.getItem("theme");

if(currentTheme === "light"){

    document.body.classList.add("light-theme");

    themeBtn.innerHTML =
    '<i class="fa-solid fa-sun"></i>';
}

themeBtn.addEventListener("click",()=>{

    document.body.classList.toggle("light-theme");

    const isLight =
    document.body.classList.contains("light-theme");

    if(isLight){

        localStorage.setItem(
            "theme",
            "light"
        );

        themeBtn.innerHTML =
        '<i class="fa-solid fa-sun"></i>';

    }else{

        localStorage.setItem(
            "theme",
            "dark"
        );

        themeBtn.innerHTML =
        '<i class="fa-solid fa-moon"></i>';
    }
});