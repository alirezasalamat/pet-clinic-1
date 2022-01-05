@find_pet_annotation
Feature: Find Pet
  Scenario: A pet is searched with ID
    Given There is a pet with ID
    When The pet is searched by his ID
    Then The pet is returned
