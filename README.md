# Spring Boot Security Project with Thymeleaf, Hibernate and MySQL

1) User can register.

2) User can login.

3) User can post a status by checking in a location (from a drop down box).

4) User can change the privacy of the post to public, private. 

5) If user chooses public, any users (including anonymous user) can see the post in home page of the web application. If he chooses private only that user can see the post from his personal profile page.

6) User can edit his status.

7) User can pin his status top of his/her profile.


# Configuration 

1) Configure DB credential and server port in <b>application.properties</b> file.

2) Set Admin user credential in <b>com.travel.agent.Constants.java</b> file.
 
 
# Saving Default Values (Admin user, Location)

1) While loading the application for the first time; it will create an Admin user and insert default locations.


# Admin User Credential:

Email : admin@mail.com
Password : 12345