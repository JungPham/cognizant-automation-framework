Feature: Upload file

  Background: Common step for scenario
    Given User goes to Url
    Given Setup base request specification
    And Set user credentials null
    And Clean database

  @UI
  Scenario: TC05 - Verify that user should be able to upload a csv file to a portal so that I can populate the database from a UI
    When User clicks on Browse button and upload CSV file on Home page
    And User clicks on Refresh Tax Relief Table button
    Then Data of NatId table on UI is correctly displayed
    And Data of Relief table on UI is correctly displayed
