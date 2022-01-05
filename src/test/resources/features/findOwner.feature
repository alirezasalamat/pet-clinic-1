@find_owner_annotation

Feature: Find Owner

  Scenario: An owner is searched with ID
    Given There is an owner with ID
    When He is searched by his ID
    Then He is returned
