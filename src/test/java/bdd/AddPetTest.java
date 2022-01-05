package bdd;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import io.cucumber.java.en.Given;
import org.springframework.samples.petclinic.owner.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddPetTest {

	@Autowired
	PetService petService;

	@Autowired
	OwnerRepository ownerRepository;

	private Owner owner;
	private Pet newPet;

	@Before("@add_pet_to_owner_annotation")
	public void setup() {
	}


	@Given("There is an owner {string}")
	public void pet(String name) {
		owner = new Owner();
		owner.setFirstName(name);
		owner.setLastName(name);
		owner.setAddress("gisha");
		owner.setCity("shiraz");
		owner.setTelephone("02188254502");
		ownerRepository.save(owner);
	}

	@When("Add a new pet")
	public void save() {
		newPet = petService.newPet(owner);
	}

	@Then("He has the pet")
	public void seved() {
		assertEquals(owner.getId(), newPet.getOwner().getId());
	}
}
