/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.owner;

import java.text.ParseException;
import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Test class for {@link PetTypeFormatter}
 *
 * @author Colin But
 */
@ExtendWith(MockitoExtension.class)
class PetTypeFormatterTests {

	@Mock
	private PetRepository pets;

	private PetTypeFormatter petTypeFormatter;

	@BeforeEach
	void setup() {
		this.petTypeFormatter = new PetTypeFormatter(pets);
	}

	@Test
	void testPrint() {
		PetType petType = new PetType();
		petType.setName("Hamster");
		String petTypeName = this.petTypeFormatter.print(petType, Locale.ENGLISH);
		assertThat(petTypeName).isEqualTo("Hamster");
	}

	@Test
	void shouldParse() throws ParseException {
		given(this.pets.findPetTypes()).willReturn(makePetTypes());
		PetType petType = petTypeFormatter.parse("Bird", Locale.ENGLISH);
		assertThat(petType.getName()).isEqualTo("Bird");
	}

	@Test
	void shouldThrowParseException() throws ParseException {
		given(this.pets.findPetTypes()).willReturn(makePetTypes());
		Assertions.assertThrows(ParseException.class, () -> {
			petTypeFormatter.parse("Fish", Locale.ENGLISH);
		});
	}

	//Test haye khodemoon

	@Test
	public void testParseStateNotNull(){
		PetRepository petsRepo = mock(PetRepository.class);

		PetType cat = mock(PetType.class);
		when(cat.getName()).thenReturn("cat");
		PetType dog = mock(PetType.class);
		when(dog.getName()).thenReturn("dog");
		PetType hamster = mock(PetType.class);

		List<PetType> petTypeList = Arrays.asList(cat, dog, hamster);
		when(petsRepo.findPetTypes()).thenReturn(petTypeList);

		PetTypeFormatter petTypeFormatter2 = new PetTypeFormatter(petsRepo);
		PetType returnedType = null;
		try {
			returnedType = petTypeFormatter2.parse("dog", Locale.ENGLISH);
		}catch(Exception e){}

		assertEquals(dog, returnedType);
	}

	@Test
	public void testParseStateNull(){
		PetRepository petsRepo = mock(PetRepository.class);

		PetType cat = mock(PetType.class);
		when(cat.getName()).thenReturn("cat");
		PetType dog = mock(PetType.class);
		when(dog.getName()).thenReturn("dog");
		PetType hamster = mock(PetType.class);
		when(hamster.getName()).thenReturn("hamster");

		List<PetType> petTypeList = Arrays.asList(cat, dog, hamster);
		when(petsRepo.findPetTypes()).thenReturn(petTypeList);

		PetTypeFormatter petTypeFormatter2 = new PetTypeFormatter(petsRepo);
		PetType returnedType = null;

		assertThrows(ParseException.class, () -> {petTypeFormatter2.parse("bird", Locale.ENGLISH);});
	}

	@Test
	public void testParseBehaviourNotNull(){
		PetRepository petsRepo = mock(PetRepository.class);

		PetType cat = mock(PetType.class);
		when(cat.getName()).thenReturn("cat");
		PetType dog = mock(PetType.class);
		when(dog.getName()).thenReturn("dog");
		PetType hamster = mock(PetType.class);

		List<PetType> petTypeList = Arrays.asList(cat, dog, hamster);
		when(petsRepo.findPetTypes()).thenReturn(petTypeList);

		PetTypeFormatter petTypeFormatter2 = new PetTypeFormatter(petsRepo);
		PetType returnedType = null;
		try {
			returnedType = petTypeFormatter2.parse("dog", Locale.ENGLISH);
		}catch(Exception e){}

		verify(petsRepo, times(1)).findPetTypes();
		verify(cat, times(1)).getName();
		verify(dog, times(1)).getName();
		verify(hamster, times(0)).getName();
	}

	@Test
	public void testParseBehaviourNull(){
		PetRepository petsRepo = mock(PetRepository.class);

		PetType cat = mock(PetType.class);
		when(cat.getName()).thenReturn("cat");
		PetType dog = mock(PetType.class);
		when(dog.getName()).thenReturn("dog");
		PetType hamster = mock(PetType.class);
		when(hamster.getName()).thenReturn("hamster");

		List<PetType> petTypeList = Arrays.asList(cat, dog, hamster);
		when(petsRepo.findPetTypes()).thenReturn(petTypeList);

		PetTypeFormatter petTypeFormatter2 = new PetTypeFormatter(petsRepo);
		PetType returnedType = null;
		try {
			returnedType = petTypeFormatter2.parse("bird", Locale.ENGLISH);
		}catch(Exception e){}

		verify(petsRepo, times(1)).findPetTypes();
		verify(cat, times(1)).getName();
		verify(dog, times(1)).getName();
		verify(hamster, times(1)).getName();
	}

	/**
	 * Helper method to produce some sample pet types just for test purpose
	 * @return {@link Collection} of {@link PetType}
	 */
	private List<PetType> makePetTypes() {
		List<PetType> petTypes = new ArrayList<>();
		petTypes.add(new PetType() {
			{
				setName("Dog");
			}
		});
		petTypes.add(new PetType() {
			{
				setName("Bird");
			}
		});
		return petTypes;
	}

}
