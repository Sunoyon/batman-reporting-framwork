package org.hs.report.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hs.report.api.CommonApi;
import org.junit.Test;

public class CommonAPiTest {
	
	
	@Test
	public void allQueryParamAndAllInputGroupParam() {
		String reportName = "example1";
		
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("startDate", "2017-05-01");
		queryParam.put("endDate", "2017-05-03");
		
		List<String> markets = Arrays.asList("BD", "SE");
		queryParam.put("markets", markets);
		
		queryParam.put("itemName", "Computer");
		
		List<String> inputParams = Arrays.asList("sale_date", "market", "itemName");
		
		List<Map<String, Object>> report = CommonApi.getReport(reportName, queryParam, inputParams);
		
		for (Map<String, Object> r : report) {
			System.out.println(r);
		}
	}

	@Test
	public void allQueryParamAndSomeInputGroupParam() {
		String reportName = "example1";
		
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("startDate", "2017-05-01");
		queryParam.put("endDate", "2017-05-03");
		
		List<String> markets = Arrays.asList("BD", "SE");
		queryParam.put("markets", markets);
		
		queryParam.put("itemName", "Computer");
		
		List<String> inputParams = Arrays.asList("sale_date", "itemName");
		
		List<Map<String, Object>> report = CommonApi.getReport(reportName, queryParam, inputParams);
		
		for (Map<String, Object> r : report) {
			System.out.println(r);
		}
	}

	@Test
	public void someQueryParamAndSomeInputGroupParam() {
		String reportName = "example3";
		
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("startDate", "2017-05-01");
		queryParam.put("endDate", "2017-05-03");
		
		queryParam.put("itemName", "Computer");
		
		List<String> inputParams = Arrays.asList("sale_date", "itemName");
		
		List<Map<String, Object>> report = CommonApi.getReport(reportName, queryParam, inputParams);
		
		for (Map<String, Object> r : report) {
			System.out.println(r);
		}
	}

	@Test
	public void noQueryParamAndNoInputGroupParam() {
		String reportName = "example1";
		
		List<Map<String, Object>> report = CommonApi.getReport(reportName, null, null);
		
		for (Map<String, Object> r : report) {
			System.out.println(r);
		}
	}
	
	@Test
	public void withoutGroupTranslatorMap() {
		String reportName = "example2";
		
		List<Map<String, Object>> report = CommonApi.getReport(reportName, null, null);
		
		for (Map<String, Object> r : report) {
			System.out.println(r);
		}
	}
	
	@Test
	public void constantValueInSelectQueryUsingQueryParam() {
		String reportName = "example4";
		
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("startDate", "2017-05-01");
		queryParam.put("endDate", "2017-05-03");
		
		queryParam.put("itemName", "Computer");
		
		List<String> inputParams = Arrays.asList("sale_date", "itemName");
		
		List<Map<String, Object>> report = CommonApi.getReport(reportName, queryParam, inputParams);
		
		for (Map<String, Object> r : report) {
			System.out.println(r);
		}
	}
}
