<div align="left">
	<code><img width="50" src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/html.png" alt="HTML" title="HTML"/></code>
	<code><img width="50" src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/css.png" alt="CSS" title="CSS"/></code>
	<code><img width="50" src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/javascript.png" alt="JavaScript" title="JavaScript"/></code>
	<code><img width="50" src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/java.png" alt="Java" title="Java"/></code>
	<code><img width="50" src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/spring_boot.png" alt="Spring Boot" title="Spring Boot"/></code>
</div>
<br>
<img width= 30% src="https://github.com/user-attachments/assets/c329fcb9-4962-4af0-bd7f-dc77369392b8" />

A full-stack blog platform for long-form music and album criticism. Built with a Spring Boot REST API backend and a vanilla HTML/CSS/JS frontend.
Live View: soon!

## About

Second Listen is a place for close, repeated-listen album reviews rather than quick takes; the kind of writing that treats a record as something worth revisiting rather than reacting to once. Authors sign up, log in, and publish reviews, while readers browse a "crate" of posts styled after flipping through a stack of records.

## Features

- **Account system** — sign up and log in with session-based authentication (Spring Security, BCrypt-hashed passwords, no plaintext credentials ever stored or returned by the API)
- **Posts** — create, browse, and read individual album reviews, each with a title, description, full write-up, like/dislike counts, and author byline
- **Author profiles** — alias, bio, and profile picture, editable per account
- **Stacked card UI** — the posts feed renders as an overlapping, hover-to-reveal stack rather than a flat list, leaning into the site's "crate-digging" identity
- **Recent posts feed** — the homepage surfaces posts from the last 30 days separately from the full archive

## Tech Stack

**Backend**
- Java 21
- Spring Boot 4.1
- Spring Security (session-based auth, custom `UserDetailsService`)
- Spring Data JPA / Hibernate
- PostgreSQL

**Frontend**
- Plain HTML, CSS, and vanilla JavaScript (`fetch`-based calls to the REST API, no framework)

**Build**
- Maven

## Project Structure

```
src/main/java/com/example/secondspin/
├── author/          # Author entity, repository, service, controller, auth (UserDetailsService)
├── post/            # Post entity, repository, service, controller
├── dashboard/        # Recent-posts feed endpoint
└── config/          # SecurityConfig (session auth, route protection)

src/main/resources/
├── static/           # All frontend pages, styles, and scripts
│   ├── *.html         # index, posts, post, createpost, signup, login, profile, about, newuserscreen
│   ├── css/style.css
│   ├── js/            # One script per page, matching REST endpoints via fetch
│   └── img/
└── application.properties
```

## Getting Started

**Prerequisites**
- Java 21
- Maven (or use the included `mvnw` wrapper)
- PostgreSQL, with a database named `secondspin`

**Setup**

1. Create the database:
   ```sql
   CREATE DATABASE secondspin;
   ```
2. Configure the connection via environment variables (or rely on the defaults in `application.properties`):
   ```
   DB_URL=jdbc:postgresql://localhost:5432/secondspin
   DB_USERNAME=postgres
   DB_PASSWORD=postgres
   ```
3. Run the app:
   ```
   ./mvnw spring-boot:run
   ```
4. Visit `http://localhost:8080`

Schema is managed automatically via `spring.jpa.hibernate.ddl-auto=update` — tables are created/updated on startup, no manual migrations needed for local development.

## API Overview

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/posts` | All posts, newest first |
| `GET` | `/posts/{id}` | A single post |
| `POST` | `/posts` | Create a post (author is derived from the logged-in session) |
| `GET` | `/dashboard/recent-posts?days=30` | Posts from the last N days |
| `GET` | `/authors` | All authors |
| `GET` | `/authors/{id}` | A single author |
| `GET` | `/authors/{id}/posts` | All posts by an author |
| `POST` | `/authors/signup` | Create an account |
| `POST` | `/authors/login` | Authenticate and start a session |
| `GET` | `/authors/me` | The currently logged-in author, or `401` if not authenticated |

## Pages

| Page | Purpose |
|---|---|
| `index.html` | Homepage — recent posts, welcome greeting for logged-in users |
| `posts.html` | Full archive of posts, stacked card layout |
| `post.html` | Single post detail view |
| `createpost.html` | Publish a new post |
| `newuserscreen.html` | Routes to signup/login, or straight to your profile if already logged in |
| `signup.html` / `login.html` | Account creation and authentication |
| `profile.html` | Author's profile picture, alias, and bio |
| `about.html` | About the site |
=======

