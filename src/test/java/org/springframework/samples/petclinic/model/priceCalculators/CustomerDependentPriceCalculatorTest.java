package org.springframework.samples.petclinic.model.priceCalculators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.UserType;
import org.springframework.samples.petclinic.model.priceCalculators.CustomerDependentPriceCalculator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

public class CustomerDependentPriceCalculatorTest {

	private CustomerDependentPriceCalculator customerDependentPriceCalculator;
	private List<Pet> pets;
	private PetType petType1,petType2;
	private Date date1,date2;

	@BeforeEach
	public void setup(){
		pets = new ArrayList<>();

		petType1 = mock(PetType.class);
		petType2 = mock(PetType.class);

		date1 = new GregorianCalendar(2012, Calendar.SEPTEMBER, 21).getTime();
		date2 = new GregorianCalendar(2020, Calendar.APRIL, 30).getTime();

		customerDependentPriceCalculator = new CustomerDependentPriceCalculator();
	}

	@Test
	public void calcPriceIsRareFirstIfSecondElseIf() {
		//Given
		Pet pet1 = new Pet();
		pet1.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet1.setType(petType1);
		pets.add(pet1);

		//When
		double res = customerDependentPriceCalculator.calcPrice(pets, 10, 20, UserType.GOLD);

		//Then
		assertEquals("calcPriceIsRareFirstIfSecondElseIf Failed!", res, 36.88, 0.01);

	}

	@Test
	public void calcPriceIsRareFirstIfNoSecondCondition() {
		//Given
		Pet pet1 = new Pet();
		pet1.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet1.setType(petType1);
		pets.add(pet1);

		//When
		double res = customerDependentPriceCalculator.calcPrice(pets, 10, 20, UserType.SILVER);

		//Then
		assertEquals("calcPriceIsRareFirstIfNoSecondCondition Failed!", res, 33.6, 0.01);

	}

	@Test
	public void calcPriceIsRareFirstIfSecondConditionIf() {
		//Given
		Pet pet1 = new Pet();
		pet1.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet1.setType(petType1);
		pets.add(pet1);

		Pet pet2 = new Pet();
		pet2.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet2.setType(petType1);
		pets.add(pet2);

		Pet pet3 = new Pet();
		pet3.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet3.setType(petType1);
		pets.add(pet3);

		Pet pet4 = new Pet();
		pet4.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet4.setType(petType1);
		pets.add(pet4);

		Pet pet5 = new Pet();
		pet5.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet5.setType(petType1);
		pets.add(pet5);


		//When
		double res = customerDependentPriceCalculator.calcPrice(pets, 10, 20, UserType.NEW);

		//Then
		assertEquals("calcPriceIsRareFirstIfNoSecondCondition Failed!", res, 169.6, 0.01);

	}

	@Test
	public void calcPriceIsRareElseSecondConditionIf() {
		//Given
		Pet pet1 = new Pet();
		pet1.setBirthDate(date1);
		when(petType1.getRare()).thenReturn(true);
		pet1.setType(petType1);
		pets.add(pet1);

		Pet pet2 = new Pet();
		pet2.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet2.setType(petType1);
		pets.add(pet2);

		Pet pet3 = new Pet();
		pet3.setBirthDate(date1);
		when(petType1.getRare()).thenReturn(true);
		pet3.setType(petType1);
		pets.add(pet3);

		Pet pet4 = new Pet();
		pet4.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet4.setType(petType1);
		pets.add(pet4);

		Pet pet5 = new Pet();
		pet5.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet5.setType(petType1);
		pets.add(pet5);

		Pet pet6 = new Pet();
		pet6.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet6.setType(petType1);
		pets.add(pet6);


		//When
		double res = customerDependentPriceCalculator.calcPrice(pets, 10, 20, UserType.NEW);

		//Then
		assertEquals("calcPriceIsRareElseSecondConditionIf Failed!", res, 183.28, 0.01);

	}

	@Test
	public void calcPriceNotRareSecondConditionIf() {
		//Given
		Pet pet1 = new Pet();
		pet1.setBirthDate(date1);
		when(petType1.getRare()).thenReturn(true);
		pet1.setType(petType1);
		pets.add(pet1);

		Pet pet2 = new Pet();
		pet2.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet2.setType(petType1);
		pets.add(pet2);

		Pet pet3 = new Pet();
		pet3.setBirthDate(date1);
		when(petType2.getRare()).thenReturn(false);
		pet3.setType(petType2);
		pets.add(pet3);

		Pet pet4 = new Pet();
		pet4.setBirthDate(date2);
		when(petType2.getRare()).thenReturn(false);
		pet4.setType(petType2);
		pets.add(pet4);

		Pet pet5 = new Pet();
		pet5.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet5.setType(petType1);
		pets.add(pet5);

		Pet pet6 = new Pet();
		pet6.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet6.setType(petType1);
		pets.add(pet6);

		Pet pet7 = new Pet();
		pet7.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet7.setType(petType1);
		pets.add(pet7);



		//When
		double res = customerDependentPriceCalculator.calcPrice(pets, 10, 20, UserType.NEW);

		//Then
		assertEquals("calcPriceNotRareSecondConditionIf Failed!", res, 202.28, 0.01);

	}

	@Test
	public void calcPriceNotRareSecondConditionElse() {
		//Given
		Pet pet1 = new Pet();
		pet1.setBirthDate(date1);
		when(petType1.getRare()).thenReturn(true);
		pet1.setType(petType1);
		pets.add(pet1);

		Pet pet2 = new Pet();
		pet2.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet2.setType(petType1);
		pets.add(pet2);

		Pet pet3 = new Pet();
		pet3.setBirthDate(date1);
		when(petType2.getRare()).thenReturn(false);
		pet3.setType(petType2);
		pets.add(pet3);

		Pet pet4 = new Pet();
		pet4.setBirthDate(date2);
		when(petType2.getRare()).thenReturn(false);
		pet4.setType(petType2);
		pets.add(pet4);

		Pet pet5 = new Pet();
		pet5.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet5.setType(petType1);
		pets.add(pet5);

		Pet pet6 = new Pet();
		pet6.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet6.setType(petType1);
		pets.add(pet6);

		Pet pet7 = new Pet();
		pet7.setBirthDate(date2);
		when(petType1.getRare()).thenReturn(true);
		pet7.setType(petType1);
		pets.add(pet7);



		//When
		double res = customerDependentPriceCalculator.calcPrice(pets, 10, 20, UserType.GOLD);

		//Then
		assertEquals("calcPriceNotRareSecondConditionElse Failed!", res, 169.92, 0.01);

	}
}
