document.addEventListener("DOMContentLoaded", () => {

    const previewButtons =
    document.querySelectorAll(".preview-btn");

    const certificateModal =
    document.getElementById("certificateModal");

    const certificateImage =
    document.getElementById("certificatePreviewImage");

    const closeCertificateModal =
    document.getElementById("closeCertificateModal");

    if(
        !certificateModal ||
        !certificateImage ||
        !closeCertificateModal
    ){
        console.error(
            "Certificate modal elements not found"
        );
        return;
    }

    previewButtons.forEach(button=>{

    button.addEventListener("click",()=>{

        console.log("Preview clicked");
        console.log(button);
        console.log(button.dataset.image);

        certificateImage.src =
        button.dataset.image;

        certificateModal.style.display =
        "flex";

    });

});

    closeCertificateModal.addEventListener("click", () => {

        certificateModal.style.display =
        "none";

    });

    window.addEventListener("click", (e) => {

        if(e.target === certificateModal){

            certificateModal.style.display =
            "none";

        }

    });

});