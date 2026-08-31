fetch("/authors/me")
    .then(response => response.json())
    .then(author => {
        const container = document.querySelector(".profile-picture");
        const img = document.createElement("img");
        img.src = author.profilePicture || "img/default-pfp.png";
        img.alt = author.alias;
        container.appendChild(img);

        const aliasContainer = document.querySelector(".profile-alias");
        aliasContainer.textContent = author.alias;

        const bioContainer = document.querySelector(".profile-bio");
        bioContainer.textContent = author.bio;
    })

document.getElementById("logout-button").addEventListener("click", (e) => {
    e.preventDefault();
    fetch("/authors/logout", { method: "POST" })
        .then(() => {
            try {
                localStorage.setItem("loggedIn", "false");
            } catch (e) {}
            window.location.href = "index.html";
        });
});