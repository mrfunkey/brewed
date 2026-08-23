const accountbutton = document.getElementById("login");
accountbutton.addEventListener("click", function(event) {
    event.preventDefault();
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    fetch("/authors/login", {
        method: "POST",
        body: JSON.stringify({
            email: email,
            password: password,
        }),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    })
        .then((res) => {
            if (!res.ok) {
                throw new Error("Login failed!");
            }
            return res.json();
        })
        .then((author) => {
            console.log(author);
            window.location.href="/index.html";
        })
        .catch((err) => {
            console.log(err);
            alert(err);
        })
    })
