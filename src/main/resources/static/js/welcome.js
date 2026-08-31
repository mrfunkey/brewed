fetch("/authors/me")
    .then(res => res.ok ? res.json() : null)
    .then(author => {
        const heading = document.querySelector(".heading");
        if (author) {
            const container = document.querySelector(".name");
            container.textContent = author.alias + "!";
        } else {
            heading.textContent = "welcome!";
        }
    })
    .catch(() => {
        document.querySelector(".heading").textContent = "welcome!";
    })