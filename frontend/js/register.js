document.getElementById("registerForm")
.addEventListener("submit", async function(event) {

    event.preventDefault();

    const student = {
        fullName: document.getElementById("fullName").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
        department: document.getElementById("department").value,
        graduationYear: Number(
            document.getElementById("graduationYear").value
        )
    };

    try {

        const response = await fetch(
            "http://localhost:8080/api/students/register",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(student)
            }
        );

        const data = await response.json();

        if (response.ok) {

            alert("Registration successful! Please login.");

            window.location.href = "index.html";

        } else {

            alert(data.message || "Registration failed.");

        }

    } catch (error) {

        alert("Cannot connect to CareerQuest server.");

        console.error(error);
    }

});