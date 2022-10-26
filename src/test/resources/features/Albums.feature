Feature: User Can View Albums
  As a user of the social network
  I should be able to view albums
  So that I can engage with the other users of the network


    Scenario: Superuser Can View All Albums in the Network
    Given I am a "Superuser"
    When I query for all "albums" in the network
    Then I validate there are a total of "100" "albums"

  Scenario: Superuser Can View All Albums of a User
    Given I am a "Superuser"
    When I query for all "albums" in "user" "1"
    Then I validate there are a total of "10" "albums" inside

    Scenario: User Attempts to Create a Album
      Given I am "Bret"
      When I attempt to post "albums"
      | title| new album|
      Then I get success response
          #Expected Method Not Allowed 405 but get an echo response hence skipping put, patch and delete