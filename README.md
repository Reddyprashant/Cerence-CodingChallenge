# Cerence-CodingChallenge


RESTFUL service for generating fibonacci series.

How to run the web service?

    1. git clone https://github.com/Reddyprashant/Cerence-CodingChallenge.git
    
    2. UnZip and Import the project
    
    3. Run "mvn clean install" command in IDE to build and package the application
    
    4. Run "mvn spring-boot:run" Command to Run the application on Tomcat Server

    5. Open Postman
       a. Using GET method and URI http://localhost:8080/v1/fibonacci/ we can check whether our application is working or not just a "health check"
       
       b. Generate the token using GET method and URI http://localhost:8080/v1/token and Token validity is for 2 minutes after 2 minutes we should generate the new token
       
   
       c. To generate the fibonacci sequence 
   
           1. copy the generated token and select Authorization as "Bearer Token" in Postman and paste the token
           
           2. Contstraints of number n, its value should be between 0 and 2000
       
           3. Using GET method and URI http://localhost:8080/v1/fibonacci/number (input number returns first n fibonacci numbers)
           
    6. Logging has been implemented using Log4j2, we can check the errorlogs in "application.log" file inside the folder named "logs" in our  web service
     
Testing the application:

     1. To Test various scenarios for fibonacci sequence generation run FibonacciGeneratorApplicationTests Test Class in IDE

     2. To Test different scenarios for Token validation run TokenServiceTest Test Class in IDE
    
Authorization:

    RESTFUL service is secured by token generated using AES Encryption and Decryption algorithm.


API Documentation using Swagger:

    1. http://localhost:8080/swagger-ui.html to check the API documentation.
    
    2. we can verify the methods implemented in our web service. 


