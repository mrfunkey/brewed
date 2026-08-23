const accountbutton = document.getElementById("signup");
accountbutton.addEventListener("click", function (event) {
    event.preventDefault();
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm-password").value;
    if (password !== confirmPassword) {
        alert("Passwords do not match");
        return;
    }
    fetch("/authors/signup", {
        method: "POST",
        body: JSON.stringify({
            alias: document.getElementById("alias").value,
            email: document.getElementById("email").value,
            password: password,
        }),
        headers:{
            "Content-Type": "application/json; charset=utf-8"
        }
    })
    .then(response => response.json())
    .then(json => {
        console.log(json);
        window.location.href="/login.html";
    })

})