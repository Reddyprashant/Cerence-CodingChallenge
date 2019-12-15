# Cerence-CodingChallenge


RESTFUL service for generating fibonacci series.

1. git clone https://github.com/Reddyprashant/Cerence-CodingChallenge.git
2. on intelliJ terminal run "mvn clean install" command
3. run "mvn spring-boot:run" Command to to Build, package and Run the application

4. Open Postman

   a. Generate the token using http://localhost:8080/v1/token
   
   b. To generate the fibonacci sequence 
   
       1. copy the generated token and select Authorization as "Bearer Token" in Postman and paste the token
       
       2. http://localhost:8080/v1/fibonacci/number

Testing the application:
1. To test various scenarios for fibonacci sequence generation run Test class FibonacciGeneratorApplicationTests

2. To test different scenarios for Token validation run Test class TokenServiceTest
    
Authorization:
To secure the RESTFUL service I have used 


API Documentation using Swagger:
1. http://localhost:8080/swagger-ui.html to check the API documentation.
2. we can check the methods implemented in our web service. 




