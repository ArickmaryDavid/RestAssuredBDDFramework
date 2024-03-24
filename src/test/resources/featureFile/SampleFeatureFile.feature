@apiTest1
Feature: Api Automation

#  Scenario: To execute GET API Call without Header
#    When user calls "GET" Method on "https://reqres.in/api/users"
#    Then user verifies status code is "200"

  Scenario: To execute Post API Call with String Payload
    Given API headers in key-value pair as
    |Content-type|application/json|
    And Request json payload as String "{\"name\": \"morpheus\",\"job\": \"leader\"}"
#    And Request json payload as File from filePath ""
    When user calls "POST" Method on "https://reqres.in/api/users"
    Then user verifies status code is "200"

  @apiTest2
  Scenario: To execute Post API Call with payload from file
    Given API headers in key-value pair as
      |Content-type|application/json|
    And Request json payload as File from filePath "/src/test/resources/RequestPayloads/Sample1.json"
    And update json property ""
    |name|abc|
    When user calls "POST" Method on "https://reqres.in/api/users"
    Then user verifies status code is "200"