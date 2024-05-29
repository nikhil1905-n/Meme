# Meme Stream Backend

This project serves as the backend for a Meme Stream Page, allowing users to post memes and view the latest 100 posted memes.

## Features

- Users can post memes by providing their name, a caption for the meme, and the URL for the meme image.
- The page displays the latest 100 posted memes, including the name of the person who posted the meme, the caption for the meme, and the image pulled from the meme URL.
- RESTful API endpoints are provided for posting memes, fetching the latest 100 memes, and fetching a single meme by its ID.
- Error handling is implemented according to HTTP status code standards.
- Duplicate POST requests with the same payload return a 409 conflict status.

## Architecture

The backend is implemented using a layered architecture with the following components:

* **Controller:** Handles incoming requests and delegates them to the service layer.
* **Service:** Handles business logic and interacts with the repository layer.
* **Repository:** Provides access to the database (MongoDB in this case).

## Endpoints

### POST /memes/

- Create a new meme.
- Request Body: { "name": "username", "url": "meme_url", "caption": "meme_caption" }
- Response Body: { "id": "meme_id" }

### GET /memes/

- Fetch the latest 100 memes.
- Response Body: List of memes with { "id", "name", "url", "caption", "createdAt" }

### GET /memes/{id}

- Fetch a single meme by its ID.
- Path Parameter: meme ID
- Response Body: Single meme with { "id", "name", "url", "caption", "createdAt" }

## Error Handling

- 400 Bad Request: Invalid request body or missing required fields.
- 404 Not Found: Meme with the specified ID not found.
- 409 Conflict: Duplicate POST requests with the same payload.

## Development Setup

1. Clone the repository.
2. Ensure MongoDB is installed and running.
3. Configure MongoDB connection settings in `application.properties`.
4. Build and run the application using Gradle.
5. Access the API endpoints using a REST client or HTTP requests.

## Technologies Used

- Spring Boot
- MongoDB
- Gradle

## Tools and Technologies

- Java 11 or higher
- Spring Boot
- MongoDB
- Gradle

## Setup and Usage

1. Clone the repository `git clone https://github.com/nikhil1905-n/Meme.git`.
2. Configure MongoDB database settings in `application.properties`.
3. Run the application using `./gradlew bootrun`.
4. Use endpoints to perform CRUD operations on user.
