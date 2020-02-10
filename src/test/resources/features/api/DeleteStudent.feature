@api @delete_student
Feature: Delete student

  Scenario Outline: Delete student as <role> and verify status code is 403 (negative)
    Given authorization token is provided for "<role>"
    And user accepts content type as "application/json"
    When user sends DELETE request to "/api/students/5760" to exclude student
    And user verifies that response status code is 403
    And user verifies that status line contains "Forbidden"

    Examples:
      | role        |
      | team member |
      | team leader |

  @ignore
  Scenario: Delete student as a teacher and verify status code is 204 (positive)
    Given authorization token is provided for "teacher"
    And user accepts content type as "application/json"
    When user sends DELETE request to "/api/students/2908" to exclude student
    And user verifies that response status code is 204



  Scenario: Delete student as a teacher and verify status code is 422 (negative)
    Given authorization token is provided for "teacher"
    And user accepts content type as "application/json"
    When user sends DELETE request to "/api/students/0" to exclude student
    And user verifies that response status code is 422

    Scenario: Try to delete a student with incorrect ID and verify that status code is 422(negative)
      Given authorization token is provided for "teacher"
      And user accepts content type as "application/json"
      When user sends DELETE request to "/api/students/0" to exclude student
      And user verifies that response status code is 422
      And user verifies that payload contains "requested student-id resource was not found." message


