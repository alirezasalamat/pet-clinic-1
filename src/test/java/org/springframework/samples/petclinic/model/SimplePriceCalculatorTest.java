package org.springframework.samples.petclinic.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.UserType;

import org.junit.Test;

import java.util.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.priceCalculators.SimplePriceCalculator;

import static org.junit.Assert.*;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
class SimplePriceCalculatorTest {

	private SimplePriceCalculator simplePriceCalculator;
	private List<Pet> pets;
	private Pet pet;
	private PetType petType;

	@BeforeEach
	void setup() {
		pets = new ArrayList<>();
		pet = new Pet();
		petType = mock(PetType.class);
		pet.setType(petType);
		simplePriceCalculator = new SimplePriceCalculator();
	}

	@Test
	void calcPriceFirstCondition() {
		//Given
		when(this.petType.getRare()).thenReturn(true);
		pets.add(pet);

		//When
		double res = simplePriceCalculator.calcPrice(pets, 10, 20, UserType.SILVER);

		//Then
		assertEquals("calcPriceFirstCondition Failed!", res, 34, 0.0);
	}

	@Test
	void calcPriceElseCondition() {
		//Given
		when(this.petType.getRare()).thenReturn(false);
		pets.add(pet);

		//When
		double res = simplePriceCalculator.calcPrice(pets, 10, 20, UserType.SILVER);

		//Then
		assertEquals("calcPriceElseCondition Failed!", res, 30, 0.0);
	}

	@Test
	void calcPriceNoForSecondCondition() {

		//When
		double res = simplePriceCalculator.calcPrice(pets, 10, 20, UserType.NEW);

		//Then
		assertEquals("calcPriceNoForSecondCondition Failed!", res, 9.5, 0.0);
	}

	@Test
	void calcPriceNoForNoCondition() {

		//When
		double res = simplePriceCalculator.calcPrice(pets, 10, 20, UserType.GOLD);

		//Then
		assertEquals("calcPriceNoForNoCondition Failed!", res, 10, 0.0);
	}

	@Test
	void calcPriceFirstConditionSecondCondition() {

		//When
		double res = simplePriceCalculator.calcPrice(pets, 10, 20, UserType.GOLD);

		//Then
		assertEquals("calcPriceFirstConditionSecondCondition Failed!", res, 32.3, 0.0);
	}

	@Test
	void calcPriceElseConditionSecondCondition() {

		//When
		double res = simplePriceCalculator.calcPrice(pets, 10, 20, UserType.GOLD);

		//Then
		assertEquals("calcPriceElseConditionSecondCondition Failed!", res, 28.5, 0.0);
	}

	@AfterEach
	void tearDown() {
		pet = null;
		pets = null;
	}
}
