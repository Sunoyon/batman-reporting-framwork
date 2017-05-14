package org.hs.query;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class QueryBuilderTest {
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
	public void getReqportQueryTest() {
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("startDate", "2016-01-01");
		queryParam.put("endDate", "2016-01-01");
		queryParam.put("itemName", "Computer");
		queryParam.put("markets", Arrays.asList("BD", "SE", "DK"));
		String reportQuery = ReportQueryProvider.getInstance().getReportQuery("example1", queryParam, null);
		System.out.println(reportQuery);
	}

	@Test
	public void getReqportQueryMissingParamTest() {
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("startDate", "2016-01-01");
		queryParam.put("endDate", "2016-01-01");
		queryParam.put("itemName", "Computer");
		String reportQuery = ReportQueryProvider.getInstance().getReportQuery("example1", queryParam, null);
		System.out.println(reportQuery);
	}

	@Test
	public void getReportQueryWithInputGroupParams() {
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("startDate", "2016-01-01");
		queryParam.put("endDate", "2016-01-01");
		queryParam.put("itemName", "Computer");
		queryParam.put("markets", Arrays.asList("BD", "SE", "DK"));

		List<String> inputParams = Arrays.asList("market", "sale_date");
		String reportQuery = ReportQueryProvider.getInstance().getReportQuery("example1", queryParam, inputParams);
		System.out.println(reportQuery);
	}

}
