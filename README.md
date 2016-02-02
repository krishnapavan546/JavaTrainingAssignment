# JavaTrainingAssignment
TrainingAssignment

Project has 3 services :

1.eProduct microservice

2.ePricing microservice

3.ClientService Service

eProduct serice is running on jetty server on port 8080. To run this service , run Application.java as SpringBoot application . Following are the end points of this service :

Adds a product 
POST

http://localhost:8080/products/product/append

Json Input{{ "product":"productName", "productId": "0" } }

Retrieve the list of products
GET

http://localhost:8080/products

Retrieve the list of products based on simple search criteria (by creating and using a view in couchbase) Search criteria is Product Type
GET

http://localhost:8080/products/category/{categoryName}


Implement remove a product feature from the catalogue.
DELETE

http://localhost:8080/products/{id}

pricing service is built using SpringBoot and Maven and currently using embedded tomcat server running on port 8085. To run this service , run gradle clean run or using Eclipse gradle plugin . Following are the end points of this service :

Return the price of a given product.
GET

http://localhost:8085/price/productid

ClientService 

Client service running on 8090 port

To get a single product and price information together in a JSON format
GET

http://localhost:8090/getProductId?productId=1009

