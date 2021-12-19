package org.springframework.samples.petclinic.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.visit.Visit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PriceCalculatorTest {
	private Pet p1, p2, p3, p4, p5, p6;
	private Visit v1, v2, v3, v4, v5, v6;
	private List<Pet> petList;

	private double BASE_CHARGE = 10;
	private double BASE_PRICE_PER_PET = 2;

	@BeforeEach
	public void setUp(){
		p1 = new Pet();
		p2 = new Pet();
		p3 = new Pet();
		p4 = new Pet();
		p5 = new Pet();
		p6 = new Pet();
		v1 = new Visit();
		v2 = new Visit();
		v3 = new Visit();
		v4 = new Visit();
		v5 = new Visit();
		v6 = new Visit();
		petList = new ArrayList<Pet>();
	}

	@Test
	public void empty_petList_test(){
		double returnedValue = PriceCalculator.calcPrice(petList, BASE_CHARGE, BASE_PRICE_PER_PET);
		assertEquals(returnedValue, 0);
	}

	@Test
	public void one_infant_pet_empty_visit_test(){
		p1.setBirthDate(LocalDate.now().minusYears(1));
		petList.add(p1);
		double returnedValue = PriceCalculator.calcPrice(petList, BASE_CHARGE, BASE_PRICE_PER_PET);
		assertEquals(returnedValue, 3.36);
	}

	@Test
	public void one_not_infant_pet_empty_visit_test(){
		p1.setBirthDate(LocalDate.now().minusYears(3));
		petList.add(p1);
		double returnedValue = PriceCalculator.calcPrice(petList, BASE_CHARGE, BASE_PRICE_PER_PET);
		assertEquals(returnedValue, 2.4);
	}

	@Test
	public void one_infant_pet_not_empty_visit_test(){
		v1.setDate(LocalDate.now().minusDays(120));
		p1.addVisit(v1);
		p1.setBirthDate(LocalDate.now().minusYears(1));
		petList.add(p1);
		double returnedValue = PriceCalculator.calcPrice(petList, BASE_CHARGE, BASE_PRICE_PER_PET);
		assertEquals(returnedValue, 3.36);
	}

	@Test
	public void one_not_infant_pet_not_empty_visit_test(){
		v1.setDate(LocalDate.now().minusDays(120));
		p1.addVisit(v1);
		p1.setBirthDate(LocalDate.now().minusYears(3));
		petList.add(p1);
		double returnedValue = PriceCalculator.calcPrice(petList, BASE_CHARGE, BASE_PRICE_PER_PET);
		assertEquals(returnedValue, 2.4);
	}

	@Test
	public void more_than_discount_min_score_test(){
		v1.setDate(LocalDate.now().minusDays(120));
		v2.setDate(LocalDate.now().minusDays(120));
		v3.setDate(LocalDate.now().minusDays(120));
		v4.setDate(LocalDate.now().minusDays(120));
		v5.setDate(LocalDate.now().minusDays(90));
		v5.setDate(LocalDate.now().minusDays(120));
		p1.addVisit(v1);
		p1.setBirthDate(LocalDate.now().minusYears(1));
		p2.addVisit(v2);
		p2.setBirthDate(LocalDate.now().minusYears(1));
		p3.addVisit(v3);
		p3.setBirthDate(LocalDate.now().minusYears(1));
		p4.addVisit(v4);
		p4.setBirthDate(LocalDate.now().minusYears(1));
		p5.addVisit(v5);
		p5.setBirthDate(LocalDate.now().minusYears(1));
		p6.addVisit(v6);
		p6.setBirthDate(LocalDate.now().minusYears(3));
		petList.add(p1);
		petList.add(p2);
		petList.add(p3);
		petList.add(p4);
		petList.add(p5);
		petList.add(p6);
		double returnedValue = PriceCalculator.calcPrice(petList, BASE_CHARGE, BASE_PRICE_PER_PET);
		assertEquals(returnedValue, 112.88);
	}


}
