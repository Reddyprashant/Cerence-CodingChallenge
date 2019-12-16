# Cerence-CodingChallenge


RESTFUL service for generating fibonacci series.

    1. git clone https://github.com/Reddyprashant/Cerence-CodingChallenge.git
    2. UnZip and Import the project
    3. Run "mvn clean install" command on intelliJ Terminal to build and package the application
    4. Run "mvn spring-boot:run" Command to Run the application on Tomcat Server

    4. Open Postman

       a. Generate the token using GET method and URI http://localhost:8080/v1/token
   
       b. To generate the fibonacci sequence 
   
           1. copy the generated token and select Authorization as "Bearer Token" in Postman and paste the token
       
           2. GET method with URI http://localhost:8080/v1/fibonacci/number

Testing the application:

     1. To test various scenarios for fibonacci sequence generation run Test class FibonacciGeneratorApplicationTests

     2. To test different scenarios for Token validation run Test class TokenServiceTest
    
Authorization:

    RESTFUL service is secured by token generated using AES Encryption and Decryption algorithm.


API Documentation using Swagger:

    1. http://localhost:8080/swagger-ui.html to check the API documentation.
    
    2. we can check the methods implemented in our web service. 




