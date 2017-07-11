package org.hs.report.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SignUpReportTest {

	@Test
	public void getReportTest() {
		String reportName = "signUpReport";

		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("date", "2017-05-01");

		List<Map<String, Object>> report = CommonApi.getReport(reportName, queryParam, null);

		for (Map<String, Object> r : report) {
			System.out.println(r);
		}
	}

}
