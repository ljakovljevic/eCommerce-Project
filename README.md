# eCommerce-Project
Full stack eCommerce Store Project implemented using React framework with Tailwind for stylization on frontend, and Java Spring framework on backend, using Spring Boot for fast and easy configuration, Spring Security and Spring JPA (Hibernate) for communication with PostgreSQL database. This project has Authorization feature implemented with JWT using Spring Security, various types of filtering through the products using categories or search as well as few types of sorting.

# Frontend
This project's frontend is built with React. Below is a concise summary of the technologies used to develop it:
- React & React Hooks
- React Router
- Axios
- Tailwind CSS
- Context API

#### How to run
```
- Install Dependencies: npm install
- Start the Development Server: npm run dev

```
# Backend
This project's backend is built with Spring Boot. Below is a concise summary of the technologies and approaches used to develop it:
- We used **Spring Boot** for rapid and opinionated configuration, simplifying the creation of **RESTful** services.
- **Spring Data JPA** leverages **Hibernate** under the hood to handle Object-Relational Mapping (ORM).
- Used **Spring Security** to protect endpoints and handle authentication/authorization.
- Generated and validated **JWT tokens** for user authentication.
- Implemented **JWT**-based authentication with a custom filter (OncePerRequestFilter) to validate tokens on each request.
- Handled request/response logic in **REST controllers**.

#### How to run
```
Configure application.properties
- Set up the database connection (e.g., spring.datasource.url, spring.datasource.username, spring.datasource.password).

Build & Run
- mvn clean install
- java -jar target/<your-app>.jar
or run directly via IDE if using Spring Boot.

Endpoints
- /user/login: Logs in user, returns JWT.
- /user/register: Creates a new user.
- /api/products: Returns available products.
- /user/cart: Manages cart items (protected).
- /user/orders: Manages orders (protected).

```
