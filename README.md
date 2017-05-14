
# BATMAN: Reporting Framework

This is a reporting framework. It supports

*   Multiple datasources simultaneously. Presently it supports **Mysql** and **Postgresql**.
*   Report SQL are written in **DSL** with simple YAML file.

## How to use
### STEP 1: Create databases and tables.

Schema of databases and tables can be found in `src/main/resources/schema/` directory. This example shows four seperate databases of **Mysql** and **Postgresql**.

### STEP 2: Configure datasources

Configurations of datasources are mentioned in `src/main/resources/config/db.config.yaml`. e.g,

	data-sources:
      - name: sale3ReportDs
        db-url: jdbc:postgresql://localhost:5432/sale3
        db-user: sunoyon
        db-password: 
        db-max-connection: 10
        db-type: postgresql
        
### STEP 3: Configure report SQL in DSL YAML file


*   Report SQL has to be written in DSL YAML file. A sample report: `reports/v1/examples/example1.yaml`
*   All deployed reports' path must be mentioned in `src/main/resources/config/report.location.yaml`.

e.g,

	sql: >
      SELECT [s.sale_date, s.market, i.name %groupFields],
      SUM(i.price) as totalSale  
      FROM SALE s 
      JOIN ITEM i USING (item_id)
      WHERE {date s.sale_date BETWEEN %startDate AND %endDate}
      AND {market s.market IN %markets}
      AND {item i.name = %itemName}
      GROUP BY [s.sale_date, s.market, i.name %groupFields]
      
	data-source: sale1ReportDs

	group-translator-map: >
	                       {
	                         "s.sale_date": "sale_date",
	                         "s.market": "market", 
	                         "i.name": "itemName"
	                       }

### STEP 4: Get report using CommonApi

Sample test queries are found in `org.hs.report.api.CommonApiTest`

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
	
Result of the query
	
	{sale_date=2017-05-02, market=BD, name=Computer, totalSale=600}
	{sale_date=2017-05-02, market=SE, name=Computer, totalSale=1200}

### Miscellaneous

`CommonApi`	returns List of Map object. You can write your own `Api` and return results in your desired types. 
