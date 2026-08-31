const params = new URLSearchParams(window.location.search);
const id = params.get("id");

fetch(`/posts/${id}`)
    .then((res) => res.json())
    .then(post => {
        const container = document.getElementById("post-detail");
        const postElement = document.createElement("div");
        postElement.classList.add("post-detail-card");
        postElement.innerHTML = `
            <h2 class="post-title">${post.title}</h2>
            <p class="post-author">${post.author.alias}</p>
            <div class="post-meta">
                <span>${new Date(post.createdAt).toLocaleDateString()}</span>
                <span>${post.likes} ${pluralize(post.likes, "like")}</span>
            </div>
            <span class="post-content">${post.content}</span>
            
        `;
        container.appendChild(postElement);
    })