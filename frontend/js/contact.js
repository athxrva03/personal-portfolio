const contactForm = document.getElementById("contact-form");
const statusText = document.getElementById("form-status");

contactForm.addEventListener("submit", async function (e) {

    e.preventDefault();

    const submitBtn = this.querySelector("button");

    submitBtn.disabled = true;
    submitBtn.innerText = "Sending...";

    const contactData = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        number : document.getElementById("number").value, 
        message: document.getElementById("message").value
    };

    try {

        const response = await fetch("http://localhost:8080/api/contact", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(contactData)
        });

        if (!response.ok) {
            throw new Error("Failed to send message");
        }

        const result = await response.text();

        statusText.textContent = result;
        statusText.style.color = "#22c55e";

        contactForm.reset();

    } catch (error) {

        console.error(error);

        statusText.textContent = "Failed to send message.";
        statusText.style.color = "#ef4444";

    } finally {

        submitBtn.disabled = false;
        submitBtn.innerText = "Send Message";

    }

});