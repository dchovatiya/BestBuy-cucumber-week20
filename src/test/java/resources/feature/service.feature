Feature: Testing different request on the service application

  Scenario:  Verify if the service application can be accessed by users
    When User sends a GET request to service endpoint
    Then User must get back a status code which is valid

  Scenario: Create a new record of service and verify it
    When I create a new service record by providing name
    Then  Verify that the service with serviceID is created

  Scenario: Update the service data and verify it
    When I update a service record by passing the name
    Then Verify that the service with serviceID is created

  Scenario: Delete the service from the data and verify if it's deleted
    When I delete a service by providing id
    Then verify that the service is deleted








