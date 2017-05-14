package org.hs.criteria;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.hs.criteria.CriterionParser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CriterionTest {

	// Run once, e.g. Database connection, connection pool
	@BeforeClass
	public static void runOnceBeforeClass() {
	}

	// Run once, e.g close connection, cleanup
	@AfterClass
	public static void runOnceAfterClass() {
	}

	// Should rename to @BeforeTestMethod
	// e.g. Creating an similar object and share for all @Test
	@Before
	public void runBeforeTestMethod() {
	}

	// Should rename to @AfterTestMethod
	@After
	public void runAfterTestMethod() {
	}

	@Test
	public void betweenTest() {
		String criterionClause = "probability probability BETWEEN %fromProb AND %toProb";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fromProb", 0.5);
		params.put("toProb", 0.5);
		System.out.println(CriterionParser.getInstance().getCriterionQuery(criterionClause, params));
	}
	
	@Test
	public void equlTest() {
		String criterionClause = "probability probability = %prob";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("prob", 0.5);
		System.out.println(CriterionParser.getInstance().getCriterionQuery(criterionClause, params));
	}
	
	@Test
	public void inTest() {
		String criterionClause = "marketId marketId in %marketlist";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("marketlist", Arrays.asList(1,2,3,4,6,7,37,38,75));
		System.out.println(CriterionParser.getInstance().getCriterionQuery(criterionClause.trim(), params));
	}
	
	@Test
	public void createdTest() {
		String criterionClause = "created TO_CHAR(created, 'YYYY-MM-DD') BETWEEN %startDate AND %endDate";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startDate", "2016-01-01");
		params.put("endDate", "2016-01-01");
		System.out.println(CriterionParser.getInstance().getCriterionQuery(criterionClause.trim(), params));
	}

}
