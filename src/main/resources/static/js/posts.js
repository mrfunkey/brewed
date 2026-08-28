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
                        <span>${post.likes} ${pluralize(post.likes, "like")}</span>
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

if (document.getElementById("post-list")) {
    loadPosts("post-list", "/posts");
}
if (document.getElementById("most-liked-posts")) {
    loadPosts("most-liked-posts", "/posts?sort=likes");
}
//loadPosts("friend-posts", "/posts?sort=likes");
if (document.getElementById("recent-posts")) {
    loadPosts("recent-posts", "/dashboard/recent-posts?days=7");
}
