package bdd;

import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import io.cucumber.java.en.Given;
import org.springframework.samples.petclinic.owner.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindOwnerTest {
	@Autowired
	PetService petService;

	@Autowired
	OwnerRepository ownerRepository;

	private Owner owner;
	private Owner actualResult;
	private PetType petType;

	@Before("@find_owner_annotation")
	public void setup() {
	}

	@Given("There is an owner with ID")
	public void owner() {
		owner = new Owner();
		owner.setFirstName("owner");
		owner.setLastName("john");
		owner.setAddress("Gisha");
		owner.setCity("Tehran");
		owner.setTelephone("09121234567");
		ownerRepository.save(owner);
	}

	@When("He is searched by his ID")
	public void searchedById() {
		actualResult = petService.findOwner(owner.getId());
	}

	@Then("He is returned")
	public void ownerReturned() {
		assertEquals(owner.getId(), actualResult.getId());
	}
}
