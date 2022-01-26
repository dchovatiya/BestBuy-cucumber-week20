Feature: Testing different request on the Categories application

  Scenario: Verify if the category application can be accessed by users
    When User sends a GET request to category endpoint
    Then User must get back a valid status code


  Scenario: Create a new record of category and verify it
    When I create a new category record by providing id and name
    Then  Verify that the category with categoryID is created

  Scenario: Update the Category data and verify it
    When I update a category record by passing the name
    Then Verify that the category with categoryID is created

  Scenario: Delete the category from the data and verify if it's deleted
    When I delete a category by providing id
    Then verify that the category is deleted
