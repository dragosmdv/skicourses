# SkiCourseFinder

## Introduction
Introducing SkiCourseFinder. This web application makes it easy for ski and snowboarding enthusiasts to search for courses and lessons in their area, view details, and contact instructors directly. The app also allows users to upload and store their ski-related documents, such as rental agreements, lift tickets, and insurance information, in one secure and convenient place. With SkiCourseFinder, users can plan their ski trips with ease and have all their important information in one place.

## System Context diagram
![Untitled Diagram-Page-1 drawio](https://user-images.githubusercontent.com/52666207/217804248-18b75559-c18a-4500-aeda-769c21c2d0ca.png)

## Container diagram
![Container Diagram](https://user-images.githubusercontent.com/52666207/217680294-ac4b806d-07c5-4116-b9f4-65c5e08b8856.jpg)

## UML Diagrams 

### CoordinatorDB
- stores data about user credentials and roles

![Coordinator_db](https://user-images.githubusercontent.com/52666207/217680449-8bc25b15-05b4-4150-87f1-49baf782b097.png)

### ClientsDB
- manages client profiles and documents

![clients_db](https://user-images.githubusercontent.com/52666207/217680495-30ea5052-138c-4bee-bbb3-ee8a66811388.png)

### CoursesDB
- manages ski/snowboarding courses

![courses_db](https://user-images.githubusercontent.com/52666207/217680524-4060e727-9564-4a3b-8872-5e0b10e6dc25.png)


# Used Patterns:

## Database per service
Using a database per service has the following benefits:

- Helps ensure that the services are loosely coupled. Changes to one service’s database does not impact any other services.

- Each service can use the type of database that is best suited to its needs. For example, a service that does text searches could use ElasticSearch. A service that manipulates a social graph could use Neo4j.

Using a database per service has the following drawbacks:

- Implementing business transactions that span multiple services is not straightforward. Distributed transactions are best avoided because of the CAP theorem. Moreover, many modern (NoSQL) databases don’t support them.

- Implementing queries that join data that is now in multiple databases is challenging.

- Complexity of managing multiple SQL and NoSQL databases

## API Gateway and Access token
The API gateway handles requests in one of two ways. Some requests are simply proxied/routed to the appropriate service. It handles other requests by fanning out to multiple services.

The API gateway is the single entry point for client requests. It authenticates requests, and forwards them to other services, which might in turn invoke other services.
This pattern has the following benefits:
- The identity of the requestor is securely passed around the system
- Services can verify that the requestor is authorized to perform an operation


## Decompose by subdomain
The microservice architecture structures an application as a set of loosely coupled services. The goal of the microservice architecture is to accelerate software development by enabling continuous delivery/deployment.
