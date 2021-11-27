package org.springframework.samples.petclinic.utility;

import com.github.mryf323.tractatus.*;
import com.github.mryf323.tractatus.experimental.extensions.ReportingExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(ReportingExtension.class)
class TriCongruenceTest {

	private static final Logger log = LoggerFactory.getLogger(TriCongruenceTest.class);

	@Test
	public void sampleTest() {
		Triangle t1 = new Triangle(2, 3, 7);
		Triangle t2 = new Triangle(7, 2, 3);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}

	/** TODO First Question ## First part */

	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "a",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@Test
	public void line14_1_Test(){
		Triangle t1 = new Triangle(2, 3, 3);
		Triangle t2 = new Triangle(7, 3, 3);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}

	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "b",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@Test
	public void line14_2_Test(){
		Triangle t1 = new Triangle(7, 10, 3);
		Triangle t2 = new Triangle(7, 3, 3);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}

	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "c",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true)
		}
	)
	@Test
	public void line14_3_Test(){
		Triangle t1 = new Triangle(5, 3, 10);
		Triangle t2 = new Triangle(5, 3, 3);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}

	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "a",
		clause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@Test
	public void line14_4_1_Test(){
		Triangle t1 = new Triangle(7, 3, 5);
		Triangle t2 = new Triangle(7, 3, 5);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertTrue(areCongruent);
	}

	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "b",
		clause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@Test
	public void line14_4_2_Test(){
		Triangle t1 = new Triangle(7, 3, 5);
		Triangle t2 = new Triangle(7, 3, 5);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertTrue(areCongruent);
	}

	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "c",
		clause = 'c',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@Test
	public void line14_4_3_Test(){
		Triangle t1 = new Triangle(7, 3, 5);
		Triangle t2 = new Triangle(7, 3, 5);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertTrue(areCongruent);
	}


	/** TODO First Question ## Second part */

	//TODO clause coverage
	@ClauseCoverage(
		predicate = "a + b",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false)
		}
	)
	@CACC(
		predicate = "a + b",
		majorClause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false)
		},
		predicateValue = true
	)
	@Test
	public void line15_1_Test(){
		Triangle t1 = new Triangle(-10, 12, 1);
		Triangle t2 = new Triangle(-10, 12, 1);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}


	@ClauseCoverage(
		predicate = "a + b",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true)
		}
	)
	@CACC(
		predicate = "a + b",
		majorClause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true)
		},
		predicateValue = true
	)
	@Test
	public void line15_2_Test(){
		Triangle t1 = new Triangle(5, 6, 12);
		Triangle t2 = new Triangle(5, 6, 12);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}

	@CACC(
		predicate = "a + b",
		majorClause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false)
		},
		predicateValue = false
	)
	@Test
	public void line15_3_Test(){
		Triangle t1 = new Triangle(5, 6, 10);
		Triangle t2 = new Triangle(5, 6, 10);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertTrue(areCongruent);
	}

	//same as the previous test
	@CACC(
		predicate = "a + b",
		majorClause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false)
		},
		predicateValue = false
	)
	@Test
	public void line15_3_2_Test(){
		Triangle t1 = new Triangle(5, 6, 10);
		Triangle t2 = new Triangle(5, 6, 10);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertTrue(areCongruent);
	}

	//-------------------------

	@UniqueTruePoint(
		predicate = "ab + cd",
		dnf = "ab + cd",
		implicant = "a",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)

	@NearFalsePoint(
		predicate = "ab + cd",
		dnf = "ab + cd",
		implicant = "ab",
		clause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false),
			@Valuation(clause = 'd', valuation = false)
		}
	)

	@UniqueTruePoint(
		predicate = "ab + cd",
		dnf = "ab + cd",
		implicant = "b",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)

	@NearFalsePoint(
		predicate = "ab + cd",
		dnf = "ab + cd",
		implicant = "ab",
		clause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false),
			@Valuation(clause = 'd', valuation = false)
		}
	)

	@UniqueTruePoint(
		predicate = "ab + cd",
		dnf = "ab + cd",
		implicant = "c",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true),
			@Valuation(clause = 'c', valuation = true)
		}
	)

	@NearFalsePoint(
		predicate = "ab + cd",
		dnf = "ab + cd",
		implicant = "cd",
		clause = 'c',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false),
			@Valuation(clause = 'd', valuation = true)
		}
	)

	@UniqueTruePoint(
		predicate = "ab + cd",
		dnf = "ab + cd",
		implicant = "d",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true),
			@Valuation(clause = 'c', valuation = true)
		}
	)

	@NearFalsePoint(
		predicate = "ab + cd",
		dnf = "ab + cd",
		implicant = "cd",
		clause = 'd',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true),
			@Valuation(clause = 'd', valuation = false)
		}
	)


	private static boolean questionTwo(boolean a, boolean b, boolean c, boolean d, boolean e) {
		boolean predicate = false;
		//Consider f = ab+cd. A possible CUPTPNP set is {T T F T, T F F T, F T F T } for implicant ab
		// (Unique true point with all near false points) combined with {F T T T, F T T F, F T F T }
		//for implicant cd. Note that there is a near false point in common; Hence the
		//resulting CUTPNFP set has five elements: {T T F T, T F F T, F T F T, F T T T, F T T F}.
		//To consider UTPC we need to compute a minimal form for ¯f.
		//¯f = ¯ac¯+ ¯a
		//¯d + ¯bc¯+ ¯b
		//¯d

		//Note that we have 4 prime implicants in ¯f, along with 2 prime implicants in f; hence
		//we need exactly 6 tests for UTPC. The CUTPNFP set has only 5; hence CUTPNFP
		//does not subsume UTPC.
//		predicate = a predicate with any number of clauses
		return predicate;
	}
}
