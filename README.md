Assignment #4
Given Task: In Assignment #4 you will build upon and improve the existing framework that you have created in the first three assignments for our Marketplace App. For this particular assignment you will be examining the impact that Concurrency has on our application. As mentioned in lecture Java RMI provides the vague definition on the handling of concurrency (specifically threading): 

"3.2 Thread Usage in Remote Method Invocations 
A method dispatched by the RMI runtime to a remote object implementation may or may not execute in a separate thread. The RMI runtime makes no guarantees with respect to mapping remote object invocations to threads. Since remote method invocation on the same remote object may execute concurrently, a remote object implementation needs to make sure its implementation is thread-safe." 

Our goal is to examine this definition within the scope of our application. The focus of this assignment will be to implement previously unimplemented functionality in your system as well as to explore, in your report, the consequences of concurrency in our Marketplace Application. In your report you should discuss this “guarantee” that Java RMI offers us with respect to concurrency and the impacts that it has on system design. For this assignment we will make use of the follow machines (listed on the next page) to demonstrate this across the network. Your server should demonstrate the ability to handle multiple concurrent requests from different clients. These clients should be able to run on any of the given machines and should locate the server running on a given machine. You should specify which machine you have selected to be your “server” in your README – failure to do so will result in a 0 on the program execution portion of the grading rubric. For this assignment we will make the assumption that there is only ever one server but many clients. As part of this assignment you should now fully implement the following three required functions: Purchase Item, Add Item, and Browse Item. All of the other requirements are still valid. Any updates to your design should be reflected in your domain model, software architectural model, and a discussion of these new design decisions as part of your report. The key to this assignment is your report and discussion! 

Directory Structure:
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
└── docs

Server On : 10.234.136.55

Clients on : 10.234.136.56, 10.234.136.57, 10.234.136.58, 10.234.136.59, 10.234.136.60

Compilation through make file:
-go to csci50700_spring2017_marketplace/code/MarketPlace/src/ 
$ cd csci50700_spring2017_marketplace/code/MarketPlace/src/
-run make
$ make

Execution Instructions:
make sure you are in src folder (csci50700_spring2017_marketplace/code/MarketPlace/src/)
-run rmiregistry since 2010 is hard coded as port use 2010 for rmiregistry
$ rmiregistry 2010&
-run the server on 10.234.136.55
$ java com.iupui.marketplace.server.MarketplaceServer
-run client (in another duplicated session)
$ java com.iupui.marketplace.client.MarketplaceClient

Credentials:

Admin : 
username:admin
password:admin

Customers :

username: user1
password: user


username: user2
password: user

username: user3
password: user

username: user4
password: user

username: user5
password: user

Functionalities and view implemented:
-browse (admin + customer)
-product details view (admin + customer)
-add item to inventory(admin)
-add item to cart (customer)
-shopping cart view (customer)
-purchase (customer)
-order confirmation view (customer)
-order history view (customer)
__________________________________________________________________________________________________________________________________________
Assignment #3
Given Task: In Assignment #3 you will build upon and improve the existing framework that you have created in Assignments #1 and #2 for our Marketplace App. For this particular assignment you will be expanding your application to make use of the Authorization pattern through a role-based access control (RBAC) approach that will make use of Java Annotations. As part of this process you will also implement and explore further the Proxy pattern and the Reflection pattern within the Java programming language. As with Assignment #2, for right now we will consider the two roles within the application to be two distinct roles – meaning an administrator account cannot act like a customer account – if the same individual wants to play both roles he/she must have two separate accounts – although with the use of annotations this could be accommodated in the future. All of the other requirements are still valid.

Directory Structure:
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
└── docs

Compilation through make file:
-go to csci50700_spring2017_marketplace/code/MarketPlace/src/ 
$ cd csci50700_spring2017_marketplace/code/MarketPlace/src/
-run make
$ make

Execution Instructions:
make sure you are in src folder (csci50700_spring2017_marketplace/code/MarketPlace/src/)
-run rmiregistry since 2010 is hard coded as port use 2010 for rmiregistry
$ rmiregistry 2010&
-run the server (in duplicated session)
$ java com.iupui.marketplace.server.MarketplaceServer
-run client (in another duplicated session)
$ java com.iupui.marketplace.client.MarketplaceClient


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

Directory Structure:
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
└── docs

Compilation through make file:
-go to csci50700_spring2017_marketplace/code/MarketPlace/src/ 
$ cd csci50700_spring2017_marketplace/code/MarketPlace/src/
-run make
$ make

Execution Instructions:
make sure you are in src folder (csci50700_spring2017_marketplace/code/MarketPlace/src/)
-run rmiregistry since 2010 is hard coded as port use 2010 for rmiregistry
$ rmiregistry 2010&
-run the server 
$ java com.iupui.marketplace.server.MarketplaceServer
-run client (in another duplicated session)
$ java com.iupui.marketplace.client.MarketplaceClient

Credentials:

For customer view:
username: customer
password: customer

For admin view:
username: admin
password: admin

__________________________________________________________________________________________________________________________________________


Assignment #1

Given Task: For Assignment #1 your job is to build the skeleton framework for the online marketplace. Your task is to take the requirements provided in this document and design a Domain Model for the application. You should create only those classes (Domain Model, Model-View-Controller, etc.) necessary to demonstrate a working application using Java RMI. At this point, we are only concerned that you get a working implementation of Java RMI running. The purpose of this assignment is to familiarize you with the Java programming language and Java RMI while translating customer requirements to a Domain Model. In subsequent assignments we will build upon this initial work and build out the project

Directory Structure:

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
└── docs



Compilation Instructions:
-navigate to following folder:
$ cd csci50700_spring2017_marketplace/code/MarketPlace/src/
-to compile all the .java files in all packages (use the following command in above directory (make sure you are in src folder)
$ javac com/iupui/marketplace/*.java com/iupui/marketplace/client/*.java com/iupui/marketplace/server/*.java com/iupui/marketplace/controller/*.java com/iupui/marketplace/model/beans/*.java

Compilation through make file
-go to csci50700_spring2017_marketplace/code/MarketPlace/src/ 
$ cd csci50700_spring2017_marketplace/code/MarketPlace/src/
-run make
$ make

Execution Instructions:
make sure you are in src folder (csci50700_spring2017_marketplace/code/MarketPlace/src/)
-run rmiregistry since 2010 is hard coded as port use 2010 for rmiregistry
$ rmiregistry 2010&
-run the server 
$ java com.iupui.marketplace.server.MarketplaceServer
-run client (in another duplicated session)
$ java com.iupui.marketplace.client.MarketplaceClient

