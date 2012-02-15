/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2012 Ricardo Mariaca
 * http://dynamicreports.sourceforge.net
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.dynamicreports.test.jasper.component;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.test.jasper.AbstractJasperPositionTest;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ListComponentGroupTypeTest extends AbstractJasperPositionTest {
	
	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.setPageFormat(PageType.A8)
		  .setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
		  .title(cmp.text("title").setFixedHeight(130))
		  .summary(
		  		cmp.text("text1"), cmp.text("text2"), 
		  		cmp.horizontalList(cmp.verticalList(cmp.text("text3"), cmp.text("text4")), cmp.text("text5")),
		  		cmp.text("text6"))
		  .setSummarySplitType(SplitType.IMMEDIATE);
	}

	@Override
	public void test() {
		super.test();
		
		numberOfPagesTest(2);
		
		elementPositionTest("summary.list2", 0, 10, 10, 128, 32);
		
		elementPositionTest("summary.textField1", 0, 10, 140, 128, 16);
		elementPositionTest("summary.textField2", 0, 10, 156, 128, 16);
		elementPositionTest("summary.textField3", 0, 0, 0, 64, 16);
		elementPositionTest("summary.textField4", 0, 0, 16, 64, 16);
		elementPositionTest("summary.textField5", 0, 64, 0, 64, 32);
		elementPositionTest("summary.textField6", 0, 10, 42, 128, 16);
	}
}
