@add_pet_to_owner_annotation

Feature: Add pet
  Scenario: Add a pet to an owner
    Given There is an owner "owner"
    When Add a new pet
    Then He has the pet
