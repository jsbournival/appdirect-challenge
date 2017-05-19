# APPDIRECT

## Description

The application is a Java 8 and Spring Boot Maven project.  

Simply clone it, 
`git clone https://github.com/jsbournival/appdirect`

and run it using:
`cd appdirect ; mvn spring-boot:run`

## Functionalities

The application provides integration to create and cancel subscription at:

* http://localhost:8080/subscription/create?url={notificationUrl}
* http://localhost:8080/subscription/cancel?url={notificationUrl}

Go to [http://localhost:8080](http://localhost:8080) to view the list of subscriptions.

## Design

The application uses the Spring framework and its dependency injection implementation, as well as its MVC web framework.

Data is stored in a MongoDB instance in the cloud.  The application uses Spring Data for interacting with MongoDB.

The application also runs [in the cloud](https://lit-cove-80323.herokuapp.com/) on Heroku.