package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;
import org.springframework.samples.petclinic.utility.PetTimedCache;
import org.springframework.samples.petclinic.utility.SimpleDI;
import org.slf4j.Logger;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.visit.Visit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class PetManagerTest {
	private OwnerRepository owners;
	private Logger spyLogger;
	private PetType cat, dog, hamster;

	@BeforeEach
	public void setUp(){
		owners = mock(OwnerRepository.class);
		spyLogger = spy(Logger.class);

		cat = new PetType();
		dog = new PetType();
		hamster = new PetType();
	}

	@Test // Mock, Spy | Behaviour | Mockisty
	public void testFindOwner(){
		PetManager testPetManager = new PetManager(null, owners, spyLogger);

		Owner ownerA = new Owner();
		ownerA.setId(10);
		ownerA.setFirstName("Ali");

		when(owners.findById(10)).thenReturn(ownerA);

		Owner returnedOwner = testPetManager.findOwner(10);

		verify(spyLogger, times(1)).info("find owner {}", 10);

		assertEquals(ownerA, returnedOwner, "Wrong Owner!");
		verify(owners, times(1)).findById(10);
	}

	@Test //Mock, Spy | Behaviour | Mockisty
	public void testNewPet(){
		PetManager testPetManager = new PetManager(null, owners, spyLogger);

		Owner ownerA = mock(Owner.class);

		Pet petA = testPetManager.newPet(ownerA);
		verify(spyLogger, times(1)).info("add pet for owner {}", ownerA.getId());
		verify(ownerA, times(1)).addPet(petA);
		assertEquals(Pet.class, petA.getClass(), "Return type is not Pet");
	}

	@Test
	public void testFindPet(){
		PetTimedCache testPetCache = mock(PetTimedCache.class);
		Pet petA = new Pet();
		petA.setId(10);

		when(testPetCache.get(10)).thenReturn(petA);
		PetManager testPetManager = new PetManager(testPetCache, owners, spyLogger);

		Pet returnedPet = testPetManager.findPet(10);
		assertEquals(petA, returnedPet);
		verify(spyLogger, times(1)).info("find pet by id {}", 10);
	}

	@Test // Mock, Spy | Behaviour | Mockisty
	public void testSavePet(){
		PetTimedCache testPetCache = mock(PetTimedCache.class);

		Pet petA = mock(Pet.class);

		Owner ownerB = mock(Owner.class);

		PetManager testPetManager = new PetManager(testPetCache, owners, spyLogger);
		testPetManager.savePet(petA, ownerB);

		verify(ownerB, times(1)).addPet(petA);
		verify(testPetCache, times(1)).save(petA);
	}

	@Test  // Mock | State | Mockisty
	public void testGetOwnerPets(){
		PetTimedCache testPetCache = mock(PetTimedCache.class);

		Pet petA = mock(Pet.class);
		Pet petB = mock(Pet.class);
		Pet petC = mock(Pet.class);

		List<Pet> petList = new ArrayList<>();
		petList.add(petA);
		petList.add(petB);
		petList.add(petC);

		Owner ownerB = mock(Owner.class);

		when(owners.findById(10)).thenReturn(ownerB);
		when(ownerB.getPets()).thenReturn(petList);

		PetManager testPetManager = new PetManager(testPetCache, owners, spyLogger);
		List<Pet> returnedPetList = testPetManager.getOwnerPets(10);

		assertEquals(petList, returnedPetList);
	}

	@Test
	public void testGetOwnerPetTypes(){
		PetTimedCache testPetCache = mock(PetTimedCache.class);

		Pet petA = new Pet();
		petA.setType(cat);
		Pet petB = new Pet();
		petB.setType(dog);
		Pet petC = new Pet();
		petC.setType(hamster);

		Set<PetType> expectedPetTypeList = new HashSet<>();
		expectedPetTypeList.add(cat);
		expectedPetTypeList.add(dog);
		expectedPetTypeList.add(hamster);

		List<Pet> petList = new ArrayList<>();
		petList.add(petA);
		petList.add(petB);
		petList.add(petC);

		Owner ownerB = mock(Owner.class);

		when(owners.findById(10)).thenReturn(ownerB);
		when(ownerB.getPets()).thenReturn(petList);

		PetManager testPetManager = new PetManager(testPetCache, owners, spyLogger);
		Set<PetType> returnedPetsType = testPetManager.getOwnerPetTypes(10);

		assertEquals(expectedPetTypeList, returnedPetsType);
	}

	@Test
	public void testGetVisitsBetween(){
		PetTimedCache testPetCache = mock(PetTimedCache.class);

		LocalDate startDate = LocalDate.of(2017, 10, 25);
		LocalDate endDate = LocalDate.of(2020, 7,1);

		List<Visit> visitsList = new ArrayList<>();
		visitsList.add(mock(Visit.class));
		visitsList.add(mock(Visit.class));

		Pet petA = mock(Pet.class);

		when(petA.getVisitsBetween(startDate, endDate)).thenReturn(visitsList);
		when(testPetCache.get(10)).thenReturn(petA);

		PetManager testPetManager = new PetManager(testPetCache, owners, spyLogger);
		List<Visit> returnedVisitsList = testPetManager.getVisitsBetween(10, startDate, endDate);
		assertEquals(visitsList, returnedVisitsList);
	}
}
