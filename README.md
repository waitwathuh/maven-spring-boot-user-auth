# maven-spring-boot-user-auth
Simple web service created using Maven, Spring Boot, OrmLite for persisting, h2 in-memory DB and jbcrypt for password hashing.

The project compiles to a runable JAR file containing all required components which makes it ideal to run in docker.

The project comes with very simple (bad) frontend files to show the API's in action. The application will start up on port 8080 and exposes the index.html file. A default user account gets created (username: admin, password: password) which is needed to login.

Items not completed from SpecSheet:
4. Create a view that contains a count or a list of users that have called login within the last 5 minutes.
6. The list or number of users in 4. must update dynamically. When a 2nd user has started a session, the counter or list in 4. must increase.
7. Expire the security token after 3 minutes if the user is inactive. When a user's token expires or is logged out, the counter or list in 4. must decrease.
