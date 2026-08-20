fetch("/posts", {
    method: "POST",
    body: JSON.stringify({
        title: document.getElementById('title').value,
        author: "Jonathan",
        description: document.getElementById('description').value,
        content: document.getElementById('content').value
    }),
    headers: {
        "Content-Type": "application/json; charset=utf-8"
    }
})
.then((res) => res.json())
.then((json) => {console.log(json)})