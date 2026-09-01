let cachedLoggedIn = false;
try {
    cachedLoggedIn = localStorage.getItem("loggedIn") === "true"
}
catch (e) {

}

document.getElementById("nav-bar").innerHTML = `
    <div class="logo-wrap">
        <span class="logo">brewed</span>
    </div>
    <a class="nav-button" href="index.html">home</a>
    <a class="nav-button" href="posts.html">feed</a>
    <div class="profile-wrap">
        <a class="nav-button" href="createpost.html">brew</a>
        <a class="nav-button" id="auth-link" 
            href="${cachedLoggedIn ? "profile.html" : "signup.html"}">
            ${cachedLoggedIn ? "Profile" : "sign up"}
        </a>
    </div>
`;

function setAuthLink(loggedIn) {
    const authLink = document.getElementById('auth-link');
    authLink.textContent = loggedIn ? "profile" : "sign up";
    authLink.href = loggedIn ? "profile.html" : "signup.html"
}

fetch("/authors/me")
    .then(res => {
        setAuthLink(res.ok);
        try {
            localStorage.setItem("loggedIn", res.ok);
        } catch (e) {}
    });




