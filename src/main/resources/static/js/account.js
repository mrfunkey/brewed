fetch("/authors/me")
    .then(res => {
        if (res.ok) {
            window.location.href="profile.html";
        }
        else{
            document.querySelector(".account-buttons-container").style.display = "";
            const signup = document.getElementById("signup");
            const login = document.getElementById("login");

            signup.addEventListener('click', function (event) {
                event.preventDefault();
                window.location.href="/signup.html";
            })

            login.addEventListener('click', function (event) {
                event.preventDefault();
                window.location.href="/login.html";
            })
        }
    });



