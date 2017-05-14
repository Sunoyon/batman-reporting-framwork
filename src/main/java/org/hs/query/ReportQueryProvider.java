package org.hs.query;

import java.util.List;
import java.util.Map;

import org.hs.config.ReportQueryConfigurator;
import org.hs.constparam.ConstParamReplacer;
import org.hs.criteria.CriterionTranslator;
import org.hs.group.GroupTranslator;

public class ReportQueryProvider {

	private static Map<String, ReportQuery> reportConfig;

	private static final ReportQueryProvider instance = new ReportQueryProvider();

	private ReportQueryProvider() {
		reportConfig = ReportQueryConfigurator.getReportConfig();
	}

	public static ReportQueryProvider getInstance() {
		return instance;
	}

//	public String getReportQuery(String reportName, Map<String, Object> queryParam) {
//		if (!reportConfig.containsKey(reportName)) {
//			return "";
//		}
//
//		ReportQuery reportConf = reportConfig.get(reportName);
//		StringBuffer sql = new StringBuffer(reportConf.getSql());
//		CriterionTranslator.getInstance().translate(sql, reportConf.getCriteria(), queryParam);
//		ConstParamReplacer.getInstance().replaceAllConstParam(sql, reportConf.getConstantParams(), queryParam);
//		return sql.toString();
//	}

	public String getReportQuery(String reportName, Map<String, Object> queryParam, List<String> inputParamGroup) {
		if (!reportConfig.containsKey(reportName)) {
			return "";
		}

		ReportQuery reportConf = reportConfig.get(reportName);
		StringBuffer sql = new StringBuffer(reportConf.getSql());
		CriterionTranslator.getInstance().translate(sql, reportConf.getCriteria(), queryParam);
		GroupTranslator.getInstance().translate(sql, reportConf.getGroups(), reportConf.getGroupTranslatorMap(),
				inputParamGroup);
		ConstParamReplacer.getInstance().replaceAllConstParam(sql, reportConf.getConstantParams(), queryParam);
		return sql.toString();
	}

	public String getReportQuery(String reportName) {
		if (!reportConfig.containsKey(reportName)) {
			return "";
		}

		ReportQuery reportConf = reportConfig.get(reportName);
		StringBuffer sql = new StringBuffer(reportConf.getSql());
		return sql.toString();
	}

	public String getReportDataSourceName(String reportName) {
		ReportQuery reportConf = reportConfig.get(reportName);
		if (reportConf == null) {
			return "";
		}
		return reportConf.getDataSourceName();
	}
}
