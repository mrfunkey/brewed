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
            <div class="post-meta" style="display: flex; align-items: center; justify-content: space-between;">
                <span>${new Date(post.createdAt).toLocaleDateString()}</span>
                <div style="display: flex; align-items: center;">
                    <a href="" class="nav-button" style="font-size:12px; padding:4px 8px;">+</a>
                    <span>${post.likes} ${pluralize(post.likes, "like")}</span>
                </div>
            </div>
            <span class="post-content">${post.content}</span>
            
        `;
        container.appendChild(postElement);
    })