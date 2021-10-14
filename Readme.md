- Gizachew Mekonnen - 612779
- Alazar Lemma - 612733
- Khalid mohamed - 612814
- Fikadu Shanko - 612721
- 
E-Store Application Mini Project-2
--
This application provides the listed functionality in the requirement from starting choosing the product to making payments. It has features that is used to 
create profile as a vendor or client and interact with the system accordingly.

Major Features Included
-
- Create profile(Client, Vendor) 
- Post products
- Place order
- Make payments

List of Microservices 
-
- Service discovery(Eureka)
- Api-Gateway
- Account Service
- Product Service
- Order Service
- Payment Service
- Credit Card Service
- Master Card Service
- ---------------------------------------------
get in folder lastV
run cmd
- kubectl apply ./
then go inside credentials folder and run
- kubectl apply ./
go inside accountConfig folder and run
- kubectl apply ./
go inside folder productConfig and run
- kubectl apply ./
  go inside folder orderConfig and run
- kubectl apply ./
then run 
- kubectl port-forward service/gateway-svc 8999:8999
then use postman to hit the endpoints 
----------------------------------------------------------
Major API's that we used:
- hard coded Users {"email":"devmedk@gmail.com", "password":"med"}
- localhost:8999/api/auth/login - for login and token generation
send token with every request in the header Authorization starting with "Bearer "+token
- localhost:8999/api/user/{id}/products - for posting product as vendor
- localhost:8999/api/user/{id}/products - getting list of products
- localhost:8999/api/user/{id}/products/{id2}/payments/{type} - making a payment for order with optional payment type 
- localhost:8999/api/user/{id}/orders -getting a list of order for specific user
- localhost:8999/api/user/{userId}/orders/{orderId}/products - getting products for specific order for specific user


