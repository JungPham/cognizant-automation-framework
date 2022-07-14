Feature: Upload file

  Background: Common step for scenario
    Given Setup base request specification
    And Set user credentials null
    And Clean database

  @API
  Scenario: TC01 - Verify that user should be able to insert a single record of working class hero into database via an API
    When User sends a valid POST request to insert
    Then Response status should be 202
    And Response body should contain 'Alright'
    And User sends a valid GET request to get taxRelief
    And New single records is inserted into database correctly when using endpoint insert data

  @API
  Scenario: TC02 - Verify that user should be able to insert more than one working class hero into database via an API
    When User sends a valid POST request to insertMultiple
    Then Response status should be 202
    And Response body should contain 'Alright'
    And User sends a valid GET request to get taxRelief
    And New multi records is inserted into database correctly when using endpoint insert data
