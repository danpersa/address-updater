## README

### Technologies

- This is a **Java 8** application
- It uses **Spring 4** for dependency injection
- The application is a **Spring Batch** application.
- I'm using **Spring Boot** to quickly start the Spring Batch Job.
- I use **Spring Data JPA** to persist the Entities in a database.
- I'm using the **Apache Tika** library to extract the text inside of the HTML body.
- I'm using **Lucene** to index the text of the body of the HTML, in order to faster find the city of the addresses.
- I'm using **regular expressions** to identify the addresses inside of the text
- **Guava** for preconditions

For tests:

- **Mockito** for mocking objects
- **Hamcrest Matchers** for matching inside assertions
- I've created some integration tests, with the **SpringJUnit4ClassRunner** runner

### Architecture

- The classes follow the SRP - Single Responsibility Principle
- The entities are immutable, so they can be safely shared between layers
- There is a clear separation between the layers, with one way dependencies

### Flow Description

I start a job. This job retrieves batches of companies.

- For each batch of companies: 
    * I use another thread to process the extraction of addresses. (I use the @Async annotation)
    * For each company in the batch:
        - I retrieve the html from the server, using the url
        - I extract the body of the html, and transform it to text
        - I process the text, to eliminate end of lines, white spaces
        - I regulate the street names
        - I search and extract addresses from the text of the page using regular expressions
        - I add new addresses to the company, in case they changed

I've written: 
    - **Integration Tests** for **Services** and **Repositories**
    - **Unit Tests** for the other classes
        
## Sequence Diagrams
        
The first part of the flow:
![Address Updater 1](img/address-updater-1.png)

The second part of the flow
![Address Updater 2](img/address-updater-2.png)

## TODOS

There are two TODOS inside of the code:
  - Implement the sending of the email
  - Use a JMS queue to decouple the part where we identify the changed addresses and the part where we send emails and save them
  - Create more UMLs and update the existing ones
  - Create builders for the entities
