# maven-spring-boot-user-auth
Simple web service created using Maven, Spring Boot, OrmLite for persisting, h2 in-memory DB and jbcrypt for password hashing.

The project compiles to a runable JAR file containing all required components which makes it ideal to run in docker.

The project comes with very simple (bad) frontend files to show the API's in action. The application will start up on port 8080 and exposes the index.html file. A default user account gets created (username: admin, password: password) which is needed to login.
