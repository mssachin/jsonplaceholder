Feature: User Can Operate with Comments
  As a user of the social network
  I should be able to operate on comments
  So that I can engage with the other users of the network


  Scenario: User can View Comments on a Post
    Given I am "Bret"
    When I query for all "comments" pertinent to post "1"
    Then I validate that I can only view comments for post "1"

  Scenario: User can Filter Comments on a Post
    Given I am "Bret"
    When I filter comments pertinent to post "1"
    Then I validate that I can only view comments for post "1"


  Scenario: User Attempts to Add a Comment
    Given I am "Bret"
    When I attempt to post "comments"
      | postId | 1             |
      | name   | Sachin        |
      | email  | msach@gml.com |
      | body   | new body      |
    Then I get success response
          #Expected Method Not Allowed 405 but get an echo back response hence skipping put, patch and delete