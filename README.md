
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
-run rmiregistry since 2010 is hard coded as port use 2010 use 2010 for rmiregistry
$ rmiregistry 2010
-run the server (in duplicated session)
$ java com.iupui.marketplace.server.MarketplaceServer
-run client (in another duplicated session)
$ java com.iupui.marketplace.client.MarketplaceClient

