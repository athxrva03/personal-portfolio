const roles = [
    "Software Developer",
    "Java & Spring Boot Developer",
    "AI Enthusiast",
    "Open Source Contributor"
];

const typingElement =
document.getElementById("typing");

let roleIndex = 0;
let charIndex = 0;

let isDeleting = false;

function typeEffect(){

    const currentRole =
    roles[roleIndex];

    if(!isDeleting){

        typingElement.textContent =
        currentRole.substring(0,charIndex+1);

        charIndex++;

        if(charIndex === currentRole.length){

            isDeleting = true;

            setTimeout(typeEffect,1500);

            return;
        }

    }else{

        typingElement.textContent =
        currentRole.substring(0,charIndex-1);

        charIndex--;

        if(charIndex === 0){

            isDeleting = false;

            roleIndex++;

            if(roleIndex >= roles.length){

                roleIndex = 0;
            }
        }
    }

    const speed =
    isDeleting ? 50 : 120;

    setTimeout(typeEffect,speed);
}

document.addEventListener(
    "DOMContentLoaded",
    typeEffect
);