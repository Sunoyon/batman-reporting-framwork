package org.hs.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hs.constparam.ConstParamParser;
import org.hs.criteria.Criterion;
import org.hs.criteria.CriterionParser;
import org.hs.group.Group;
import org.hs.group.GroupParser;
import org.hs.query.ReportQuery;
import org.hs.util.Constants;
import org.hs.yaml.ReportYamlParser;
import org.yaml.snakeyaml.Yaml;

public class ReportQueryConfigurator {
	final static Logger logger = Logger.getLogger(ReportQueryConfigurator.class);

	public static Map<String, ReportQuery> getReportConfig() {
		Map<String, ReportQuery> reportConfig = new HashMap<String, ReportQuery>();
		try {
			ReportYamlParser yaml = ReportYamlParser.getInstance();

			List<String> reportLocation = getReportsLocation();

			for (String rtLocation : reportLocation) {
				Map<String, Object> yamlConfig = yaml.getYamlConfig(rtLocation);
				String sql = yaml.getSql(yamlConfig);
				String dataSourceName = yaml.getDataSource(yamlConfig);
				Map<String, String> translatorMap = yaml.getTranslatorMap(yamlConfig);
				
				List<Criterion> criteria = CriterionParser.getInstance().getCriteria(sql);
				List<String> constantParams = ConstParamParser.getInstance().getConstantParams(sql);
				List<Group> groups = GroupParser.getInstance().getGroups(sql);
				
				ReportQuery reportQuery = new ReportQuery(sql, criteria, constantParams, dataSourceName, groups,
						translatorMap);
				String reportName = StringUtils.removeEnd(rtLocation.substring(rtLocation.lastIndexOf("/") + 1),
						Constants.YAMLEXT);
				reportConfig.put(reportName, reportQuery);
			}
		} catch (Exception e) {
			logger.error("Invalid report configuration." + e.getMessage());
		}
		return reportConfig;
	}

	@SuppressWarnings("unchecked")
	public static List<String> getReportsLocation() {
		Yaml yaml = new Yaml();
		String yamlLocation = Constants.REPORT_NAME_LOCATION;

		Map<String, Object> yamlParsers = (Map<String, Object>) yaml
				.load(ReportQueryConfigurator.class.getClassLoader().getResourceAsStream(yamlLocation));
		return (List<String>) yamlParsers.get("report-location");
	}
}
