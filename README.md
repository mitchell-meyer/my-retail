# my-retail

## Steps to Deploy
Follow these steps to successfully deploy the **my-retail** project.
1. Install MongoDB.
1. Start the MongoDB service.

## Testing the Project
### Unit Tests
To run the unit tests, run the 'test' Gradle task under the 'verification' section.

Example: Run `gradlew test` from your projects directory. 
### Manual Testing
Testing manually can be accomplished many ways, but this section will outline one way to test `GET` and `PUT` requests.

First, the application must be started. This can be accomplished in a development setting by following these steps:
1. Start the MongoDB service.
1. Run the 'bootRun' Gradle task.

#### Testing GET Requests
Testing a GET requests can be accomplished by using your favorite web browser, providing the appropriate URL for the product ID that you would like to retrieve.

Example URL: `http://localhost:8080/products/13860429`
#### Testing PUT Requests
Testing a PUT request can be accomplished in Linux by using the `curl` command.

Example: `curl -X PUT -T price.json -H "accept: application/json" -H "Content-Type: application/json" http://localhost:8080/products/13860429`

Where 'price.json' is a file with the payload that is to be written out to the body of the request.
## Production Ready Requirements
The following is a list of features that should be developed yet to consider **my-retail** to be production ready:
1. Setup a persistent instance of MongoDB database, and configure the **my-retail** application to connect to it.
1. Add security to the application.
1. Make the external ULR for the product API configurable, instead of hard-coded.
