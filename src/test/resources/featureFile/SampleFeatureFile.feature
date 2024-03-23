@apiTest1
Feature: Api Automation

  Scenario: To execute GET API Call without Header
    When user calls "GET" Method on "https://reqres.in/api/users"
    Then user verifies status code is "200"