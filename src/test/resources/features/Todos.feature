Feature: User Can View Todos
  As a user of the application
  I should be able to operate on todos
  So that I can organize myself

  Scenario: Superuser Can View All Todos
    Given I am a "Superuser"
    When I query for all "todos" in the network
    Then I validate there are a total of "200" "todos"

  Scenario: Superuser Can View All Todos of a User
    Given I am a "Superuser"
    When I query for all "todos" in "user" "1"
    Then I validate there are a total of "20" "todos" inside

  Scenario: User Attempts to Add a Todo
    Given I am "Bret"
    When I attempt to post "todos"
      | title     | Title |
      | completed | false |
    Then I get success response
          #Expected Method Not Allowed 405 but get an echo back response hence skipping put, patch and delete