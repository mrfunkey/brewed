let cachedLoggedIn = false;
try {
    cachedLoggedIn = localStorage.getItem("loggedIn") === "true"
}
catch (e) {

}

document.getElementById("nav-bar").innerHTML = `
    <div class="logo-wrap">
        <img class="logo" src="img/logo.png" alt="Second Listen">
    </div>
    <a class="nav-button" href="index.html">Home</a>
    <a class="nav-button" href="posts.html">Posts</a>
    <div class="profile-wrap">
        <a class="nav-button" href="createpost.html">Create Post</a>
        <a class="nav-button" id="auth-link" 
            href="${cachedLoggedIn ? "profile.html" : "newuserscreen.html"}">
            ${cachedLoggedIn ? "Profile" : "Sign Up"}
        </a>
    </div>
    <a class="nav-button" href="about.html">About</a>
`;

function setAuthLink(loggedIn) {
    const authLink = document.getElementById('auth-link');
    authLink.textContent = loggedIn ? "Profile" : "Sign Up";
    authLink.href = loggedIn ? "profile.html" : "newuserscreen.html"
}

fetch("/authors/me")
    .then(res => {
        setAuthLink(res.ok);
        try {
            localStorage.setItem("loggedIn", res.ok);
        } catch (e) {}
    });




