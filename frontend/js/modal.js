const modal =
document.getElementById("projectModal");

const closeBtn =
document.getElementById("closeModal");

const cards =
document.querySelectorAll(".project-card");

const modalTitle =
document.getElementById("modalTitle");

const modalDescription =
document.getElementById("modalDescription");

const modalTech =
document.getElementById("modalTech");

const modalGithub =
document.getElementById("modalGithub");

const modalDemo =
document.getElementById("modalDemo");

cards.forEach(card=>{

    card.addEventListener("click",()=>{

        modalTitle.textContent =
        card.dataset.title;

        modalDescription.textContent =
        card.dataset.description;

        modalTech.textContent =
        card.dataset.tech;

        modalGithub.href =
        card.dataset.github;

        modalDemo.href =
        card.dataset.demo;

        modal.style.display = "flex";
    });

});

closeBtn.addEventListener("click",()=>{

    modal.style.display = "none";

});

window.addEventListener("click",(e)=>{

    if(e.target === modal){

        modal.style.display = "none";
    }

});