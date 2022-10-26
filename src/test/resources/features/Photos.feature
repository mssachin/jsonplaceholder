Feature: User Can View Photos
  As a user of the social network
  I should be able to view photos
  So that I can engage with the other users of the network


  Scenario: Superuser Can View All Photos in the Network
    Given I am a "Superuser"
    When I query for all "photos" in the network
    Then I validate there are a total of "5000" "photos"

  Scenario: Superuser Can View All Photos in an Album
    Given I am "Bret"
    When I query for all "photos" in "album" "1"
    Then I validate there are a total of "50" "photos" inside


  Scenario: User Attempts to Add a Photo
    Given I am "Bret"
    When I attempt to post "photos"
      | albumId      | 1            |
      | id           | Sachin       |
      | title        | Title        |
      | url          | www.sach.com |
      | thumbnailUrl | www.sach.com |
    Then I get success response
          #Expected Method Not Allowed 405 but get an echo back response hence skipping put, patch and delete