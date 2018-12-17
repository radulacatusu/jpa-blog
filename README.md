Blog Engine powered by Spring Boot
=

This project is a simple REST application for serving a blog. It uses Spring Boot, Spring Data JPA and some other helper libraries.

Currently, application can only serve blog post details by executing GET request at `/posts/{id}`, where `{id}` is a post identifier.
Also, comments feature are supported - POST at `/posts/{1}/comments` - which save a new comment for post with passed {id} and 
GET at `/posts/{1}/comments` - which return all comments for a post with passed {id}