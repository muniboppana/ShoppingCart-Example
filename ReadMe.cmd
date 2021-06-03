//Below are the steps to execute the application and require tools & software

Intellij or Eclipse
Java 8 or higher
install PostgreSQL and provide the details to connect DB  in Intellij
DB details information updated in application.properties file
mvn plug in require to compile the code
clone the code from GitHub as mentinoned below URL and import the project into Intellij or Eclipse.Intellij
Once Improted then initiate the compile by using below command in  Terminal
   mvn clean install
Once build get success  and execute below command
mvn spring-boot:run
Once server started successfully and open the Postman tool.
In Postman, perform below URI's to perform createShoppingCart,addproduct,updateproduct,deleteProduct and findproduct,fetchproduct.

//To create Shopping cart   --GET
URL :  http://localhost:8080/api/carts/createShoppingCart
JSON Value :
{
 "countryCode": "us",
 "currency": "usd"
}
//To Add product into ShoppingCart and insert into DB
URL :http://localhost:8080/api/carts/createProduct

{
 "countryCode": "NO",
 "currency": "NOK",
  "products" : [
{

"description" : "lapTap",
 "category" :   "electronic",
 "price":  1234567.12

}
]

}

//To get the product from shopping cart  -GET
http://localhost:8080/api/carts/products/cartId

//update the product from shopping cart   --PUT

http://localhost:8080/api/carts/updateProduct/cartId
JSON :

{

            "id": "",
            "description": "",
            "category": "",
            "price": 10.00
}

//Delete Product
URL:
http://localhost:8080/api/carts/deleteProduct/cartId
JSON is empty.

//Fetch all Shoppingcarts and Products

http://localhost:8080/api/carts

JSON is empty.
