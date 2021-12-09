package org.springframework.samples.petclinic.owner;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.assertj.core.util.Lists;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(PetController.class)
class PetControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PetRepository pets;
	@MockBean
	private OwnerRepository owners;
	@MockBean
	private PetService petService;

	private Pet testPet;
	private Owner testOwner;
	private PetType testType;

	@BeforeEach
	public void setUp(){
		testPet = new Pet();
		testPet.setName("A");
		testType = new PetType();
		testType.setName("testType");
		testPet.setType(testType);

		testOwner = new Owner();
		when(this.petService.findOwner(1)).thenReturn(testOwner);
		when(this.petService.newPet(testOwner)).thenReturn(testPet);
		when(this.petService.findPet(2)).thenReturn(testPet);
		when(this.pets.findPetTypes()).thenReturn(Lists.newArrayList(testType));
	}

	@Test
	public void initCreationFormTest() throws Exception{
		MvcResult result = mockMvc.perform(get("/owners/1/pets/new")).andExpect(model().attributeExists("pet"))
			.andExpect(view().name("pets/createOrUpdatePetForm")).andReturn();
	}

	@Test
	public void processCreationForm_Error_Test() throws Exception{
		MvcResult result = mockMvc.perform(post("/owners/1/pets/new").param("name", "A")
			.param("type","testType")).
			andExpect(view().name("pets/createOrUpdatePetForm")).
			andExpect(model().attributeHasFieldErrors("pet", "birthDate")).
			andExpect(model().attributeExists("pet")).andReturn();
	}

	@Test //TODO
	public void processCreationForm_NoError_Test() throws Exception{
		MvcResult result = mockMvc.perform(post("/owners/1/pets/new").param("name", "testType")
			.param("type","testType").param("birthDate","2020-10-10")).
			andExpect(view().name("redirect:/owners/{ownerId}")).andReturn();
	}

	@Test
	public void initUpdateFormTest() throws Exception{
		MvcResult result = mockMvc.perform(get("/owners/1/pets/2/edit")).
			andExpect(model().attributeExists("pet")).
			andExpect(view().name("pets/createOrUpdatePetForm")).andReturn();
	}

	@Test
	public void processUpdateForm_Error_Test() throws Exception{
		MvcResult result = mockMvc.perform(post("/owners/1/pets/2/edit").param("name", "A")
				.param("type","testType")).
				andExpect(model().attributeHasFieldErrors("pet", "birthDate")).
				andExpect(view().name("pets/createOrUpdatePetForm")).andReturn();
	}
}
