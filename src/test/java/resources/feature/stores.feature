Feature: Validate different responses of the store application
@SMOKE
  Scenario:  Check if the store application can be accessed by users
    When User sends a GET request to stores endpoint
    Then User must get back a valid status code 200

  Scenario: Create a new store and verify if the store is added
    When I create a new store by providing the following information
      | Glenwood | Off licence | 389 Street | Goose Lane | London | Others | 55026 | 45.2365 | 89.1235 | Mon: 9-9; Tue: 9-9; Wed: 9-9; Thurs: 9-9; Fri: 9-9; Sat: 9-9; Sun: 9-8 |
    Then I verify that the store with storeId is created

  Scenario Outline: Update the store information and verify the updated information
    When I update a store record by passing the information  "<name>" "<type>" "<address>" "<address2>" "<city>" "<state>" "<zip>" "<lat>" "<lng>" and "<hours>"
    Then Verify that the store with "<name>" is created

    Examples:

      | name    | type     | address    | address2 | city    | state  | zip     | lat     | lng     | hours                                                                  |
      | Updated | Big Bang | Hover Lane | 65 Lane  | Specify | Others | Cf6 9GR | 89.1235 | 42.5689 | Mon: 9-9; Tue: 9-9; Wed: 9-9; Thurs: 9-9; Fri: 9-9; Sat: 9-9; Sun: 9-8 |

  Scenario: Delete the store and verify if it is deleted
    When I delete a store by providing id
    Then verify that the store is deleted













