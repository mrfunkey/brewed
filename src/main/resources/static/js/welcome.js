fetch("/authors/me")
    .then(res => res.json())
    .then(author => {
        const container = document.querySelector(".name")
        container.textContent = author.alias + "!";
    })