package bdd;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import io.cucumber.java.en.Given;
import org.springframework.samples.petclinic.owner.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FindPestTest {
	@Autowired
	PetService petService;

	@Autowired
	PetRepository petRepository;

	@Autowired
	PetTypeRepository petTypeRepository;

	@Autowired
	OwnerRepository ownerRepository;

	private Pet pet;
	private Pet actualResult;

	@Before("@find_pet_annotation")
	public void setup() {
	}

	@Given("There is a pet with ID")
	public void pet() {
		Owner owner = new Owner();
		owner.setFirstName("owner");
		owner.setLastName("john");
		owner.setAddress("gisha");
		owner.setCity("ahwaz");
		owner.setTelephone("09324567319");
		ownerRepository.save(owner);
		PetType petType = new PetType();
		petType.setName("bird");
		petTypeRepository.save(petType);
		pet = new Pet();
		pet.setName("joojoo");
		pet.setType(petType);
		pet.setBirthDate(LocalDate.of(2000,12,12));
		owner.addPet(pet);
		petRepository.save(pet);
	}

	@When("The pet is searched by his ID")
	public void search() {
		actualResult = petService.findPet(pet.getId());
	}

	@Then("The pet is returned")
	public void returned() {
		assertEquals(pet.getId(), actualResult.getId());
	}
}
