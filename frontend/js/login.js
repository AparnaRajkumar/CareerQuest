document.getElementById("loginForm").addEventListener("submit", async function(event) {

    event.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    try {

        const response = await fetch(
            "http://localhost:8080/api/students/login",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email: email,
                    password: password
                })
            }
        );

        const data = await response.json();

        if (response.ok) {

            localStorage.setItem("studentId", data.studentId);

            alert("Login successful! Welcome to CareerQuest.");

            window.location.href = "dashboard.html";

        } else {

            alert(data.message || "Invalid email or password.");

        }

    } catch (error) {

        alert("Unable to connect to server. Please try again.");

        console.error(error);
    }

});