Feature: User Can Operate on Posts
  As a user of the social network
  I should be able to operate on posts
  So that I can engage with the content of the network


  Scenario: Superuser Can Query for All Posts in the Network
    Given I am a "Superuser"
    When I query for all "posts" in the network
    Then I validate there are a total of "100" "posts"

  Scenario: User Can Query for the Posts specific to the user
    Given I am "Bret"
    When I query for my "posts" in the network
    Then Only the posts pertinent to me are visible
    And I validate there are a total of "10" "posts"

  Scenario: No Posts Available for Non-Existing user
    Given I am "Sachin"
    When I query for my "posts" in the network
    And I validate there are a total of "0" "posts"

  Scenario: User can Post in the Network
    Given I am "Antonette"
    When I make a post in the network
      | title | This is a Sample Post By Antonette  |
      | body  | Antonette is posting in the network |
    Then I validate that post is successful

  Scenario: User Makes a Post Without Title
    Given I am "Karianne"
    When I make a post in the network
      | body | Karianne is posting in the network |
    Then I validate that post is successful

  Scenario: User Makes a Post Without Body
    Given I am "Kamren"
    When I make a post in the network
      | title | This is a Sample Post By Kamren |
    Then I validate that post is successful

  Scenario: User makes an empty post
    Given I am "Antonette"
    When I make a post in the network
      | title |  |
      | body  |  |
    Then I validate that post is successful

  Scenario: Superuser can Query for a Specific Post
    Given I am a "Superuser"
    When I query for a specific post "1"
    Then I validate the post details
      | userId | 1                                                                                                                                                                 |
      | id     | 1                                                                                                                                                                 |
      | title  | sunt aut facere repellat provident occaecati excepturi optio reprehenderit                                                                                        |
      | body   | quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto |

  Scenario: Superuser Queries for a Non-Existing Post
    Given I am a "Superuser"
    When I query for a specific post "1000000"
    Then I validate the post is not available


  Scenario: User can Update a Post
    Given I am "Bret"
    When I update a post of mine
      | userId | 1            |
      | id     | 1            |
      | title  | update title |
      | body   | update body  |
    Then The post is successfully updated

  Scenario: User cannot Update a Non-Existing Post
    Given I am "Bret"
    When I update a post of mine
      | userId | 1            |
      | id     | 121          |
      | title  | update title |
      | body   | update body  |
    Then The get an error response


  Scenario: User can Edit a Post
    Given I am "Bret"
    When I edit a post of mine
      | userId | 1            |
      | id     | 1            |
      | title  | update title |
      | body   | update body  |
    Then The post is successfully updated

  Scenario: User cannot Edit a Non-Existing Post
    Given I am "Bret"
    When I edit a post of mine
      | userId | 1            |
      | id     | 121          |
      | title  | update title |
      | body   | update body  |
    # Expected to get an error response
    Then The post is successfully updated

  Scenario: User can Delete a Post
    Given I am a "Superuser"
    When I delete "post" "1"
    Then I validate the post is successfully deleted