Feature: Pet API Integration
  Scenario: Get pet by ID when app.canine is true (default)
    Given a pet with ID 1 exists
    When I request the pet by ID 1
    Then the response status should be 200
    And the response should contain the pet details 