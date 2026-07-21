fetch("/dashboard/recent-posts?days=30")
    .then((res) => res.json())
    .then(data => {
        const container = document.getElementById("post-list");
        data.forEach(post => {
            const postElement = document.createElement("div");
            postElement.classList.add("post");
            postElement.innerHTML = `
                <h2>${post.title}</h2>
                <p>${post.description}</p>
                <p>${post.author.fullName}</p>
                 `;
            container.appendChild(postElement);
        })
    })