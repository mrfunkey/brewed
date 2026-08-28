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
    <a class="nav-button" href="posts.html">posts</a>
    <div class="profile-wrap">
        <a class="nav-button" href="createpost.html">create post</a>
        <a class="nav-button" id="auth-link" 
            href="${cachedLoggedIn ? "profile.html" : "newuserscreen.html"}">
            ${cachedLoggedIn ? "Profile" : "sign up"}
        </a>
    </div>
    <a class="nav-button" href="about.html">about</a>
`;

function setAuthLink(loggedIn) {
    const authLink = document.getElementById('auth-link');
    authLink.textContent = loggedIn ? "profile" : "sign up";
    authLink.href = loggedIn ? "profile.html" : "newuserscreen.html"
}

fetch("/authors/me")
    .then(res => {
        setAuthLink(res.ok);
        try {
            localStorage.setItem("loggedIn", res.ok);
        } catch (e) {}
    });




