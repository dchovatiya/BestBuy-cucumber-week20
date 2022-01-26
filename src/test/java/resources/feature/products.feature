Feature: Testing different request on the products application

  Scenario:  Verify if the products application can be accessed by users
    When User sends a GET request to products endpoint
    Then User must get a valid status code

  Scenario: Create a new record of product and verify it
    When I create a new product record by providing the following information
      | name               | type      | price | upc  | shipping | description                                           | manufacturer | model    | url                  | image                       |
      | Kodak AA Batteries | HardSolid | 80    | 1235 | 90       | Long term batteries with good Power technology:6-pack | Kodak        | KDK12654 | http://www.kodak.com | http://img.kodak.com/kk.jpg |
    Then  Verify that the product with productId is created

  Scenario Outline: Update the product data and verify it
    When I update a product record by passing the information  "<name>" "<type>" "<price>" "<upc>" "<shipping>" "<description>" "<manufacturer>" "<model>" "<url>" and "<image>"
    Then Verify that the product with "<type>" is created

    Examples:

      | name         | type  | price | upc  | shipping | description                  | manufacturer | model    | url                  | image                       |
      | AA Batteries | Solid | 8     | 1879 | 5        | good Power technology:6-pack | AA           | KDK12654 | http://www.kodak.com | http://img.kodak.com/kk.jpg |

  Scenario: Delete the product from the data and verify if it's deleted
    When I delete a product by providing id
    Then verify that the product is deleted








