Feature: Upload file

  Background: Common step for scenario
    Given User goes to Url
    Given Setup base request specification
    And Set user credentials null
    And Clean database

  @API @UI
  Scenario: TC03 - Verify that user should be able to query the amount of tax relief for each person in the database so that I can report the figures to my Bookkeeping Manager
    When User clicks on Browse button and upload CSV file on Home page
    And User sends a valid GET request to get taxRelief
    Then natid field must be masked from the 5th character onwards with dollar sign $ when uploading CSV file
    And The taxRelief should be correctly calculated when uploading CSV file

  @UI
  Scenario: TC04 - Verify that user should be able to see a button on the screen so that I can dispense tax relief for my working class heroes
    Then The button with name Dispense Now is displayed
    And The button must be red color
    When User clicks on button with name Dispense Now on Home page
    Then User should be redirected to a page with dispense as last part of url
    And The text Cash dispensed is displayed
