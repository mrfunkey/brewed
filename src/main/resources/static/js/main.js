fetch("/dashboard/recent-posts?days=30")
    .then((res) => res.json())
    .then(data => {
        const container = document.getElementById("post-list");
        data.forEach(post => {
            const postElement = document.createElement("div");
            postElement.classList.add("post");
            postElement.innerHTML = `
                <a class="post-link" href="post.html?id=${post.id}">
                    <h2 class="post-title">${post.title}</h2>
                    <p class="post-author">${post.author.fullName}</p>
                    <p>${post.description}</p>
                    <div class="post-data">
                        <span>${new Date(post.createdAt).toLocaleDateString()}</span>
                        <span>${post.likes} ${pluralize(post.likes, "like")}</span>
                    </div>
                </a>
            `;
            container.appendChild(postElement);
        })
    })

function pluralize(count, word) {
    return count === 1 ? word : word + "s";
}