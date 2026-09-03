function loadPosts(id, url){
    fetch(url)
        .then((res) => res.json())
        .then(data => {
            const container = document.getElementById(id);
            data.forEach(post => {
                const postElement = document.createElement("div");
                postElement.classList.add("post-card");
                postElement.innerHTML = `
                <a class="post-link" href="post.html?id=${post.id}">
                    <h2 class="post-title">${post.title}</h2>
                    <p class="post-author">${post.author.alias}</p>
                    <div class="post-meta">
                        <span>${new Date(post.createdAt).toLocaleDateString()}</span>
                        <span class="post-likes">${post.likes} ${pluralize(post.likes, "like")}</span>
                    </div>
                    <p>${post.description}</p>
                </a>
            `;
                container.appendChild(postElement);
            })
        })
}



function pluralize(count, word) {
    return count === 1 ? word : word + "s";
}

const containerIds = ["post-list", "most-liked-posts", "most-liked-by-week", "recent-posts", "my-posts"];
const activeId = containerIds.find(id => document.getElementById(id));

switch (activeId) {
    case "post-list":
        loadPosts("post-list", "/posts");
        break;
    case "most-liked-posts":
        loadPosts("most-liked-posts", "/posts?sort=likes");
        break;
    case "most-liked-by-week":
        loadPosts("most-liked-by-week", "/posts?sort=likes&days=7");
        break;
    case "recent-posts":
        loadPosts("recent-posts", "/posts?days=7");
        break;
    case "my-posts":
        fetch("/authors/me")
            .then(res => {
                if (!res.ok) {
                    return;
                }
                res.json().then(author => loadPosts("my-posts", `/authors/${author.id}/posts`));
            });
        break;
}
