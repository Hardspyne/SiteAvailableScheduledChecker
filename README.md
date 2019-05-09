# SiteAvailableScheduledChecker
App for scheduled check the availability of the site using Spring Boot, Spring MVC, Thymeleaf
____

 Application checks  status of the site in the specified interval and sends an email with a screenshot of site in case of changes site status.
### How to run:
 1. Change mail login and password in application.properties located in resources, this is the mail from send the message.
 2. Open command line in the project directory and enter 'mvn spring-boot:run'
 
### How to use:
 1. Open your browser and enter http://localhost:8080
 2. Fill form: specify site to check, check interval, and e-mail to send the message to. 
