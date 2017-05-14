package org.hs.group;

import java.util.List;

import org.hs.group.Group;
import org.hs.group.GroupParser;
import org.junit.Test;

public class GroupParserTest {

	@Test
	public void getPrimaryFieldsTest() {
		String clause = "si.mId, si.value as        sg, si.prob %fields";
		List<String> primaryFields = GroupParser.getInstance().getPrimaryFields(clause);
		for (String field : primaryFields) {
			System.out.println(field);
		}
	}

	@Test
	public void getGroups() {
		String sql = "SELECT [si.mId, si.value as sg, si.prob %fields], count(distinct si.userid) as uu, count(distinct id) as ids FROM sg_inv si JOIN items l ON si.item = l.item WHERE {created TO_CHAR(si.created, 'YYYY-MM-DD') BETWEEN %startDate AND %endDate} GROUP BY [si.mId, si.value as sg, si.prob %fields]";
		List<Group> groups = GroupParser.getInstance().getGroups(sql);
		for (Group group : groups) {
			System.out.println(group.getClause());
			for (String f : group.getPrimaryFields()) {
				System.out.println(f);
			}
			System.out.println("\n");
		}
	}
}
