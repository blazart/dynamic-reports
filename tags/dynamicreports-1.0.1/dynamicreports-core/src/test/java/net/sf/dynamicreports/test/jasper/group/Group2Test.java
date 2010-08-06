/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 Ricardo Mariaca
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

package net.sf.dynamicreports.test.jasper.group;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class Group2Test extends AbstractJasperValueTest {	
	private TextColumnBuilder<Integer> column2;
	private TextColumnBuilder<Integer> column3;
	private TextColumnBuilder<Integer> column4;
	private AggregationSubtotalBuilder<Integer> subtotal1;
	
	@Override
	protected void configureReport(JasperReportBuilder rb) {
		TextColumnBuilder<String> column1;		
		ColumnGroupBuilder group1;
		
		rb.columns(				
				column1 = col.column("Column1", "field1", String.class),	
				column2 = col.column("Column2", "field2", Integer.class),
				column3 = col.column("Column3", "field3", Integer.class),
				column4 = col.column("Column4", "field4", Integer.class))
			.groupBy(
				grp.group(column1)
					.setStartInNewPage(true)
					.header(cmp.text("header1"))
					.footer(cmp.text("footer1")),
				group1 = grp.group(column1)
					.header(cmp.text("header2"))
					.footer(cmp.text("footer2"))
					.setShowColumnHeaderAndFooter(true)
					.setReprintHeaderOnEachPage(true)
					.setPrintSubtotalsWhenExpression(exp.printNotInFirstPage()))
			.subtotalsAtGroupFooter(group1, subtotal1 = sbt.sum(column4));
	}

	@Override
	public void test() {
		super.test();
		
		numberOfPagesTest(3);
		
		columnTitleCountTest(column2, 6);
		columnTitleValueTest(column2, "Column2", "Column2", "Column2", "Column2", "Column2", "Column2");
		columnTitleCountTest(column3, 6);
		columnTitleValueTest(column3, "Column3", "Column3", "Column3", "Column3", "Column3", "Column3");
		columnTitleCountTest(column4, 6);
		columnTitleValueTest(column4, "Column4", "Column4", "Column4", "Column4", "Column4", "Column4");
		
		elementCountTest("groupHeader.textField1", 2);
		elementValueTest("groupHeader.textField1", "header1", "header1");
		
		elementCountTest("groupFooter.textField1", 2);
		elementValueTest("groupFooter.textField1", "footer1", "footer1");

		elementCountTest("groupHeader.textField2", 3);
		elementValueTest("groupHeader.textField2", "header2", "header2", "header2");
		
		elementCountTest("groupFooter.textField2", 2);
		elementValueTest("groupFooter.textField2", "footer2", "footer2");
		
		subtotalCountTest(subtotal1, 1);
		subtotalValueTest(subtotal1, "1225");
	}
	
	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1", "field2", "field3", "field4");
		for (int i = 0; i < 10; i++) {
			dataSource.add("group1", i, i, i);
		}
		for (int i = 0; i < 50; i++) {
			dataSource.add("group2", i, i, i);
		}		
		return dataSource;
	}
}
