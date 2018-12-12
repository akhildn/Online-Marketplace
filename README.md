# Online Marketplace (OO Design Principles and Patterns)

# Project Overview:

Online Marketplace is a platform where an organization can sell their goods to customer who are geographically dispersed all around the world. This application allows the organization’s administrator to add items into the inventory update a specific item or remove an item from the inventory. The customers can access this application from anywhere in world and try to purchase items from the platform. The customer has capability to go back the review his orders from past.

Below are the details of each functionality implemented in this project:

### Login
Login functionality allows the user (could be a admin or customer) to authenticate using their username and password. Once the user is authenticated the users are redirected to their respective unique home pages. Customer and administrator have different home pages. This is a common functionality for both admin and customer.

### Browse Items
This view is common for both admin and customer. It displays the product list i.e. displays all the products which are available in inventory on the server. A product list is retrieved from server which contains objects of each product. These products are iterated through and displayed here in browse view.

You will able to navigate to product view, from the browse when you select a product from the browse. This product view lists out all the details of the product from its object which was passed on from the browse view. From this view, you can add that product to cart if the user type is customer or can go back to home. The screenshots for these are shown in sample run.

 ### Update Product
This is an admin specific function where admin will be able to update the product. In product update view, the admin is asked for product id and then he need to populate new product details for that product id. These new details are updated in database.

### Remove Product
This is an admin specific function where admin will be able to remove the product. In this specific view, admin is asked for product id and entered product id is removed from the database.

### Add Item
This is an admin specific functionality where admin can add an item to the inventory. It calls add item view where details for new product which is to be added are taken and is added to product list on the server. Screenshot is provided in sample runs.

### Add Item to cart
This is only available to customer, if admin tries to add an item to cart, an exception is thrown showing not authorized to perform this task. You can add an item to the cart from product details view. And also you can see the cart details from the home view. All the cart details are preserved until purchase has been done. Cart is saved until it is been cleared out i.e. user can add the items and come back later to purchase them. When the item is added to cart it redirects customer to cart view.

Also, if the user enters quantity more than what is available then the product will not be added to the cart.

### Cart View
In this view it displays the user all the items which were added to cart by the user. It contains the product details and total cart price i.e. the total price of all the items in the cart. This is view is called when user adds an item to cart or from the home page. If the user has an associated cart with items attached to it already then it returns that cart or it displays that cart is empty. If the user doesn’t have a cart already mapped to him, then a cart is created for him. Each time the cart has been processed for purchased, it is emptied.

If the user is new, i.e. never added any item to cart, then he/she has no associated cart mapped to them, where it displays cart is empty. But when user adds item to cart for first time, a shopping cart is created and mapped to him/her.

### Purchase Item
You can purchase items from the cart view. If cart is not empty you can see purchase option for the list of items which are on cart. Upon purchase request, the system ask user for his shipping  address and later the list of items in cart is passed to server for validation.

On server, it validates the cart items as follows:

- Step 1: gets all the items from the cart
- Step 2: gets latest item details from the server for items which are on cart
- Step 3: checks if items are still available
- Step 4: if requested quantity is available for the item, item is placed and quantity for that product on server is reduced, and status for that item is set to processed
- Step 5: if requested quantity is not available, the item is not placed and status is set has not processed and current available quantity of that item is returned so that user is notified why the item was not placed.

After validation and order has been placed, it redirects user to order confirmation view. Where details of the order is displayed. All the items which were placed and all those which were not placed. It displays order id, order date, order total, shipping address (item total + total tax).

The items which were placed and those which were not placed are displayed based on the status message set for each item during validation of cart on the server. Once the order has been placed the shopping cart is cleared.

### Order History View
It is a customer specific view which displays all the orders which were successfully placed by the user so far (till date).

# UML Diagrams:

### Domain Model
A domain model illustrates meaningful conceptual classes in problem domain. The following diagram illustrates the domain model for this assignment. It lists all the conceptual classes that will be used to build the framework for this assignment.

![alt text](https://github.com/akhildn/Online-Marketplace/blob/master/images/domain_model.png)  

### Activity Diagram for Add Item to cart:

![alt text](https://github.com/akhildn/Online-Marketplace/blob/master/images/Activity_add_item_cart.png)

### Activity Diagram for purchase item:

![alt text](https://github.com/akhildn/Online-Marketplace/blob/master/images/Acitvity_purchase_item.png)

### Sequence flow when user has access to the method:

![alt text](https://github.com/akhildn/Online-Marketplace/blob/master/images/sequence_has_access.png)

### Sequence flow when user has no access to the method:

![alt text](https://github.com/akhildn/Online-Marketplace/blob/master/images/sequence_has_no_access.png)

### Class diagram for Front Controller Pattern

![alt text](https://github.com/akhildn/Online-Marketplace/blob/master/images/Controller_pattern.png) 

### Class diagram for Abstract Factory Pattern

![alt text](https://github.com/akhildn/Online-Marketplace/blob/master/images/Abstract_factory.png)  

### Class diagram for Command Pattern

![alt text](https://github.com/akhildn/Online-Marketplace/blob/master/images/RBAC_Proxy.PNG)


# Synchronization Discussion 

#### **Database Connection through Singleton Pattern:**

This pattern is responsible for to create an object, and this is the only object which is created. This class will provide a way to access its only object which can be directly accessed without any need to instantiate the object of this class. We will try restrict this connection object to just one because they highly memory consuming and to centralize the process.

We will create a static class object and a static method. This object will be initialized in the static method. We used this pattern in our database connection class. In this class, the static object is initialized first time, i.e. when it is null. From next calls it returns the same object since was already initialized.

This static method needs to be synchronized because there is chance that 2 threads might initialize this object since there is no restriction on how they are entering it, So 2 threads get access to this method at same time, then the first thread will create an instance which is later replaced by second thread, so to overcome this we will make this method synchronized.

But having this method synchronize has a drawback, i.e. it slows down the call, as each thread will wait for another thread to step out, so that it can get the object, which is not necessary as we only need to synchronize first creation of instance. So we will make use of synchronized block, so when 2 threads come in at same time for first time, then only one enters which will create an instance and since instance would not be null, 2nd thread we will reuse the created instance. This is also known as double scope locking [1].

#### **How synchronization is achieved in the application:**  

Identification of methods which needs to be synchronized:

**Purchase order:**
```
- No 2 customer should be able to buy same product at same time, that specific product should be processed one after the other. (If we allow them to purchase same product at same time, then both might end up placing the order even though there only 1 quantity available)
- But they should be able to buy at same time if, they are not buying the same product. Hence it can’t be synchronized on method, since we should allow multiple customers to place order at same time.
- Also, it should not process the order at same time when the same product is being updated/removed by the administrator.
```

**Update order:**

```
- No 2 admins should be able to update same product at same time.
- But they should be able to update products at same time, when the product they are update are not the same.
- Should not allow, customer to buy the product when it is being updated.
```

**Remove order:**

```
- No 2 admins should be able to remove same product at same time.
- But they should be able to remove products at same time, when the product they are remove are not the same.
- Should not allow, customer to buy the product when it is being removed.
```

#### **Why can’t we synchronize on the methods and what is the need for synchronized block and how is thread-safe synchronization achieved?**

We cannot synchronize on methods as synchronizing methods will affect the performance, since no 2 customer threads will be able to access the method, even though they are trying to buy different products. We cannot stop a customer from buying a television, just because some other customer is purchasing a phone at the same time. We should only try to avoid situations where both of them are trying to purchase the same product. To allow customers to purchase concurrently when the products they are purchasing are not same, we shouldn’t synchronize on methods.

Since we can’t synchronize methods, we will use **synchronized block**. We will synchronize that block which has critical portion. We will synchronize this block on a monitor object which we would like to lock the block on. Hence we will trying to synchronize it on product id. By doing this we can implementing monitor object, scoped-locking which are required for this method. **Future pattern** can be used by displaying a notice for assuring a user that his/her process is being put forward and will be processed soon, this pattern can we used where these synchronized methods are being called, since one method cannot execute when a lock is placed by the other. Hence future pattern will be helpful here to do some useful work while that is being processed.

The **Thread-safe Interface pattern** ensures that intra-component method calls avoid self-deadlock and minimize locking overhead [2]. There are scenarios where one method will acquire a lock and then calls another method that tries to reacquire the same lock. This leads to a self-deadlock situation. To avoid this we have synchronize methods on outer boundaries on implementation and avoid synchronizing the inner methods. We achieved this by pushing acquiring and releasing of locking to outer-boundary methods. We made sure that no database read and write operations are inside the code where acquiring and releasing of lock is done.

As mentioned before since the monitor object is product id, the performance of the system is not degraded, since respective users can still buy/update at same time when product id is not same i.e. if they are not trying to make changed on same product.

**Monitor object:** product id
These are the methods which needs to be synchronized. So, these methods can be synchronized
if product id is used as monitor object. It appropriate to lock on product id since, we only need to
acquire and release locks only when:
-  when 2 customers threads are trying to buy same product at same time
-  when 2 admins threads are trying to update/remove same product at same time
-  When a customer thread and a admin are trying to update and other trying to purchase same product at same time.


#### **Problem faced while trying synchronize on product id as monitor object [3]:**

We can only make 2 methods run mutually exclusively only when they are locked on the same monitor object. Since our application, product id is not from the same object in the methods we are trying to synchronize. Hence we had to figure out a way we could synchronize on same object. We used ConcurrentHashMap, this class has a method “putIfAbsent” which we will check if there is any object associated with existing key there is none it will put an object or if there exists a object it returns it, we used product id as key, so when same product id matches in the other method, it will get the object. Hence we were able to get the same object and were able to make these methods mutually exclusive.

# References

[1] - https://www.youtube.com/watch?v=zUYLY8kYavs

[2] - Strategized Locking, Thread-safe Interface, and Scoped Locking - Patterns and Idioms for
Simplifying Multi-threaded C++ Components, Douglas C. Schmidt, Department of Computer
Science, Washington University. [http://www.cs.wustl.edu/~schmidt/PDF/locking-patterns.pdf](http://www.cs.wustl.edu/~schmidt/PDF/locking-patterns.pdf)

[3] - [http://stackoverflow.com/questions/6616141/java-threads-locking-on-a-specific-object](http://stackoverflow.com/questions/6616141/java-threads-locking-on-a-specific-object)

________________________________________________________________________________________________________________________________________

## Reports in each sprint:

1. https://github.com/akhildn/Online-Marketplace/blob/master/docs/Assignment-1%20%23report.docx
2. https://github.com/akhildn/Online-Marketplace/blob/master/docs/Assignment-2%20%23report.docx
3. https://github.com/akhildn/Online-Marketplace/blob/master/docs/Assignment-3%20%23report.docx
4. https://github.com/akhildn/Online-Marketplace/blob/master/docs/Assignment-4%20%23report.docx
5. https://github.com/akhildn/Online-Marketplace/blob/master/docs/Assignment-5%20%23report.docx

________________________________________________________________________________________________________________________________________

## Assignment #5
### Given Task: 
In Assignment #5, you will be examining the impact that the concept that Synchronization has on our application. As we saw in Assignment #4, there are many significant challenges with respect to the construction of multi-threaded or concurrent systems. As part of this assignment you were tasked with examining some solutions to the challenges that you identified – often these challenges/problems were ones that could directly be solved through the use of Synchronization design patterns. 

The focus of this assignment is to apply the information we have learned regarding the use of synchronization in the Java programming language to ensure that access to our shared resources are indeed thread-safe. Specifically, we will be examining how Java implements the following patterns: Monitor Object, Future, Guarded Suspension, Scoped Locking, and Thread-Safe Interface. Your job is to make use of the various Java defined constructs to accomplish this thread-safety within our application. As part of this assignment you will need to make sure that your application now makes use of the MySQL database that has been setup for us to use on: (in-csci-rrpc01.cs.iupui.edu). In addition, as this will be the last assignment for the semester, you are expected to fully complete all of the required functions as outlined in the project specifications. You should use good design practices, principles, and patterns, when applicable, to accomplish this. This final submission should be a polished product and something that should be proud of. 

For this assignment we will make use of the following machines (listed on this page) to demonstrate the role of synchronization and the functionality of your application in a concurrent environment. Your server should demonstrate the ability to handle multiple concurrent requests from different clients. These clients should be able to run on any of the given machines and should locate the server running on a given machine. For this assignment we will, again, make the assumption that there is only ever one server but many clients. All of the other requirements are still valid. Any updates to your design should be reflected in your domain model, software architectural model, and a discussion of these new design decisions as part of your report. This final report should contain a complete overview and all proper documentation related to the creation of this application and the ongoing work we have done this semester. 

Your code should be properly documented and should make use of common language standards and implementation practices. This code should be your own work – each student should submit their own project; failure to do so or any student caught cheating will be subject to the IUPUI guidelines on academic dishonesty as provided in the course syllabus.

`Directory Structure:
.
├── code
│   └── MarketPlace
│       ├── lib
│       ├── nbproject
│       │   └── private
│       └── src
│           └── com
│               └── iupui
│                   └── marketplace
│                       ├── client
│                       │   ├── command
│                       │   ├── handlers
│                       │   └── view
│                       ├── controller
│                       ├── dao
│                       ├── database
│                       ├── model
│                       │   └── beans
│                       └── server
└── docs`

| Server on: | 10.234.136.55|
|------------|--------------|

| Clients on:| 10.234.136.56, 10.234.136.57, 10.234.136.58, 10.234.136.59, 10.234.136.60 |
|------------|---------------------------------------------------------------------------|

| Database:                                       |  
|-------------------------------------------------|
| https://in-csci-rrpc01.cs.iupui.edu/phpmyadmin/ |
| Username: anayabu                               |
| password: marketplace                           |

{username:password} =
Admins :   {admin1:admin,admin2:admin}
customers: {user1:user,user2:user,user3:user,user4:user,user5:user,user6:user}


Compilation through make file:
- go to csci50700_spring2017_marketplace/code/MarketPlace/src/ 
`$ cd csci50700_spring2017_marketplace/code/MarketPlace/src/`
- run make
`$ make`

Execution Instructions:
make sure you are in src folder (csci50700_spring2017_marketplace/code/MarketPlace/src/)
- run rmiregistry since 2011 is hard coded as port use 2011 for rmiregistry
`$ rmiregistry 2011&`
- run the server on 10.234.136.55
`$ java -cp ".:../lib/mysql-connector-java-5.0.8-bin.jar" com.iupui.marketplace.server.MarketplaceServer`
- run client (in another duplicated session)
`$ java com.iupui.marketplace.client.MarketplaceClient`


**All functionalities implemented, synchronization achieved. Final product**
Documents: https://github.com/akhildn/Online-Marketplace/tree/master/docs

__________________________________________________________________________________________________________________________________________
## Assignment #4
### Given Task: 
In Assignment #4 you will build upon and improve the existing framework that you have created in the first three assignments for our Marketplace App. For this particular assignment you will be examining the impact that Concurrency has on our application. As mentioned in lecture Java RMI provides the vague definition on the handling of concurrency (specifically threading): 

3.2 Thread Usage in Remote Method Invocations 
A method dispatched by the RMI runtime to a remote object implementation may or may not execute in a separate thread. The RMI runtime makes no guarantees with respect to mapping remote object invocations to threads. Since remote method invocation on the same remote object may execute concurrently, a remote object implementation needs to make sure its implementation is thread-safe." 

Our goal is to examine this definition within the scope of our application. The focus of this assignment will be to implement previously unimplemented functionality in your system as well as to explore, in your report, the consequences of concurrency in our Marketplace Application. In your report you should discuss this “guarantee” that Java RMI offers us with respect to concurrency and the impacts that it has on system design. For this assignment we will make use of the follow machines (listed on the next page) to demonstrate this across the network. Your server should demonstrate the ability to handle multiple concurrent requests from different clients. These clients should be able to run on any of the given machines and should locate the server running on a given machine. You should specify which machine you have selected to be your “server” in your README – failure to do so will result in a 0 on the program execution portion of the grading rubric. For this assignment we will make the assumption that there is only ever one server but many clients. As part of this assignment you should now fully implement the following three required functions: Purchase Item, Add Item, and Browse Item. All of the other requirements are still valid. Any updates to your design should be reflected in your domain model, software architectural model, and a discussion of these new design decisions as part of your report. The key to this assignment is your report and discussion! 

`Directory Structure:
.
├── code
│   └── MarketPlace
│       ├── nbproject
│       │   └── private
│       └── src
│           └── com
│               └── iupui
│                   └── marketplace
│                       ├── client
│                       │   ├── command
│                       │   ├── handlers
│                       │   └── view
│                       ├── controller
│                       ├── dao
│                       ├── model
│                       │   └── beans
│                       └── server
└── docs`


| Server on: | 10.234.136.55|
|------------|--------------|

| Clients on:| 10.234.136.56, 10.234.136.57, 10.234.136.58, 10.234.136.59, 10.234.136.60 |
|------------|---------------------------------------------------------------------------|

Compilation through make file:
- go to csci50700_spring2017_marketplace/code/MarketPlace/src/  
`$ cd csci50700_spring2017_marketplace/code/MarketPlace/src/`
- run make  
`$ make`

Execution Instructions:
- make sure you are in src folder (csci50700_spring2017_marketplace/code/MarketPlace/src/)
- run rmiregistry since 2010 is hard coded as port use 2010 for rmiregistry   
`$ rmiregistry 2010&`
- run the server on 10.234.136.55  
`$ java com.iupui.marketplace.server.MarketplaceServer`
- run client (in another duplicated session)  
`$ java com.iupui.marketplace.client.MarketplaceClient`

Credentials: 

{username:password} =  
Admins :   {admin1:admin,admin2:admin}  
customers: {user1:user,user2:user,user3:user,user4:user,user5:user,user6:user}

**Functionalities and view implemented:**
- browse (admin + customer)
- product details view (admin + customer)
- add item to inventory(admin)
- add item to cart (customer)
- shopping cart view (customer)
- purchase (customer)
- order confirmation view (customer)
- order history view (customer)
__________________________________________________________________________________________________________________________________________
## Assignment #3
### Given Task: 
In Assignment #3 you will build upon and improve the existing framework that you have created in Assignments #1 and #2 for our Marketplace App. For this particular assignment you will be expanding your application to make use of the Authorization pattern through a role-based access control (RBAC) approach that will make use of Java Annotations. As part of this process you will also implement and explore further the Proxy pattern and the Reflection pattern within the Java programming language. As with Assignment #2, for right now we will consider the two roles within the application to be two distinct roles – meaning an administrator account cannot act like a customer account – if the same individual wants to play both roles he/she must have two separate accounts – although with the use of annotations this could be accommodated in the future. All of the other requirements are still valid.

`Directory Structure:  
.  
├── code  
│   └── MarketPlace  
│       ├── nbproject  
│       │   └── private  
│       └── src  
│           └── com  
│               └── iupui  
│                   └── marketplace   
│                       ├── client   
│                       │   ├── command   
│                       │   ├── commands  
│                       │   ├── handlers  
│                       │   └── view  
│                       ├── controller  
│                       ├── dao  
│                       ├── model  
│                       │   └── beans  
│                       └── server  
└── docs`

Compilation through make file:
- go to csci50700_spring2017_marketplace/code/MarketPlace/src/   
`$ cd csci50700_spring2017_marketplace/code/MarketPlace/src/`  
- run make  
`$ make`

Execution Instructions:
- make sure you are in src folder (csci50700_spring2017_marketplace/code/MarketPlace/src/)  
- run rmiregistry since 2010 is hard coded as port use 2010 for rmiregistry  
`$ rmiregistry 2010&`
- run the server (in duplicated session)  
`$ java com.iupui.marketplace.server.MarketplaceServer`
- run client (in another duplicated session)  
`$ java com.iupui.marketplace.client.MarketplaceClient`


Credentials: 

For customer view:  
username: customer  
password: customer  

For admin view:  
username: admin  
password: admin  

Both admin and customer have same Browse View, press 1 for browse view  
Customer does not have access to edit items in browse view  
Admin does not have access to add items to cart in browse view  
__________________________________________________________________________________________________________________________________________

Assignment #2
Given Task: For Assignment #2 your job is to build upon and improve the existing framework that you have created in Assignment #1. For this particular assignment you will be looking at an important Application Control pattern that can be applied to the Model-View-Control architecture that you defined in the first assignment. For this you will be focused on the impact that the role and place that Controllers have on the system specifically looking at the Front Controller pattern with regards to how it can be implemented as part of your existing Marketplace application. We will also be partially implementing the logic for the Login requirement. As outlined, this function should be included for both Administrators as well as Customers – however, each should see a distinctly different view. In order to accomplish this implementation and realize its full potential, we will be making use of two additional, yet related, patterns: the Command and the Abstract Factory patterns. In Assignment #3 we will focus on the login and role-based access to our application and compare this solution with an alternative approach. All of the other requirements are still valid. Any updates to your design should be reflected in an updated domain model and any updated discussion of design decisions should be made. You also need to ensure that any changes necessary from Assignment #1 are made as part of this assignment. 

`Directory Structure:
.
├── code
│   └── MarketPlace
│       ├── nbproject
│       │   └── private
│       └── src
│           └── com
│               └── iupui
│                   └── marketplace
│                       ├── client
│                       │   ├── commands
│                       │   ├── handlers
│                       │   └── view
│                       ├── controller
│                       ├── dao
│                       ├── model
│                       │   └── beans
│                       └── server
└── docs`

Compilation through make file:  
- go to csci50700_spring2017_marketplace/code/MarketPlace/src/  
`$ cd csci50700_spring2017_marketplace/code/MarketPlace/src/`
- run make  
`$ make`

Execution Instructions: 
- make sure you are in src folder (csci50700_spring2017_marketplace/code/MarketPlace/src/)  
- run rmiregistry since 2010 is hard coded as port use 2010 for rmiregistry  
`$ rmiregistry 2010&`
- run the server  
`$ java com.iupui.marketplace.server.MarketplaceServer`
- run client (in another duplicated session)  
`$ java com.iupui.marketplace.client.MarketplaceClient`


** Credentials:

For customer view:  
username: customer  
password: customer  

For admin view:  
username: admin  
password: admin  

__________________________________________________________________________________________________________________________________________

## Assignment #1

### Given Task: 
For Assignment #1 your job is to build the skeleton framework for the online marketplace. Your task is to take the requirements provided in this document and design a Domain Model for the application. You should create only those classes (Domain Model, Model-View-Controller, etc.) necessary to demonstrate a working application using Java RMI. At this point, we are only concerned that you get a working implementation of Java RMI running. The purpose of this assignment is to familiarize you with the Java programming language and Java RMI while translating customer requirements to a Domain Model. In subsequent assignments we will build upon this initial work and build out the project

`Directory Structure:

csci50700_spring2017_marketplace/
├── code
│   └── MarketPlace
│       ├── bin
│       │   └── com
│       │       └── iupui
│       │           └── marketplace
│       │               ├── client
│       │               ├── controller
│       │               ├── dao
│       │               ├── model
│       │               │   └── beans
│       │               └── server
│       ├── build
│       │   └── com
│       │       └── iupui
│       │           └── marketplace
│       │               └── model
│       │                   └── beans
│       ├── nbproject
│       │   └── private
│       └── src
│           └── com
│               └── iupui
│                   └── marketplace
│                       ├── client
│                       ├── controller
│                       ├── dao
│                       ├── model
│                       │   └── beans
│                       └── server
└── docs`



Compilation Instructions:
- navigate to following folder:  
`$ cd csci50700_spring2017_marketplace/code/MarketPlace/src/`
- to compile all the .java files in all packages (use the following command in above directory (make sure you are in src folder)  
`$ javac com/iupui/marketplace/*.java com/iupui/marketplace/client/*.java com/iupui/marketplace/server/*.java com/iupui/marketplace/controller/*.java com/iupui/marketplace/model/beans/*.java`

Compilation through make file
- go to csci50700_spring2017_marketplace/code/MarketPlace/src/   
`$ cd csci50700_spring2017_marketplace/code/MarketPlace/src/`
- run make  
`$ make`

Execution Instructions:
- make sure you are in src folder (csci50700_spring2017_marketplace/code/MarketPlace/src/)  
- run rmiregistry since 2010 is hard coded as port use 2010 for rmiregistry  
`$ rmiregistry 2010&`
- run the server   
`$ java com.iupui.marketplace.server.MarketplaceServer`
- run client (in another duplicated session)  
`$ java com.iupui.marketplace.client.MarketplaceClient`

