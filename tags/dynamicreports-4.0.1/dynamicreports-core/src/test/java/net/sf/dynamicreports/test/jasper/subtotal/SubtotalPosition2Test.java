/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2015 Ricardo Mariaca
 * http://www.dynamicreports.org
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

package net.sf.dynamicreports.test.jasper.subtotal;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class SubtotalPosition2Test extends AbstractJasperPositionTest {
	private AggregationSubtotalBuilder<Integer> subtotal1;
	private AggregationSubtotalBuilder<Integer> subtotal2;
	private AggregationSubtotalBuilder<Integer> subtotal3;
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;
	private TextColumnBuilder<String> column3;
	private TextColumnBuilder<Integer> column4;
	private TextColumnBuilder<Integer> column5;
	private TextColumnBuilder<String> column6;
	private TextColumnBuilder<Integer> column7;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		StyleBuilder textStyle = stl.style(stl.pen1Point()).setPadding(2);
		StyleBuilder columnStyle = stl.style(textStyle).setVerticalTextAlignment(VerticalTextAlignment.MIDDLE);

		rb.setTextStyle(textStyle)
		.setColumnStyle(columnStyle)
			.columns(
				column1 = col.column("", "field1", type.stringType()).setFixedWidth(200),
				column2 = col.column("", "field2", type.stringType()),
				column3 = col.column("", "field3", type.stringType()),
				column4 = col.column("", "field4", type.integerType()).setRows(2),
				column5 = col.column("", "field5", type.integerType()),
				column6 = col.column("", "field6", type.stringType()).setFixedColumns(3),
				column7 = col.column("", "field7", type.integerType()).setTitleRows(2))
			.subtotalsAtSummary(
					subtotal1 = sbt.sum(column4),
					subtotal2 = sbt.sum(column5).setRows(2),
					subtotal3 = sbt.sum(column7))
			.columnGrid(
					column1, column2, column3,
					grid.horizontalColumnGridList()
						.add(column4).newRow()
						.add(column5, column6, column7));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		//columns
		elementPositionTest("columnHeader.list1", 0, 10, 10, 575, 43);
		columnTitlePositionTest(column1, 0, 0, 0, 200, 43);
		columnTitlePositionTest(column2, 0, 200, 0, 87, 43);
		columnTitlePositionTest(column3, 0, 287, 0, 87, 43);
		//elementPositionTest("columnHeader.list2", 0, 374, 0, 201, 43);
		columnTitlePositionTest(column4, 0, 374, 0, 201, 16);
		elementPositionTest("columnHeader.list3", 0, 374, 16, 201, 27);
		columnTitlePositionTest(column5, 0, 0, 0, 85, 27);
		columnTitlePositionTest(column6, 0, 85, 0, 31, 27);
		columnTitlePositionTest(column7, 0, 116, 0, 85, 27);

		elementPositionTest("detail.list1", 0, 10, 53, 575, 43);
		columnDetailPositionTest(column1, 0, 0, 0, 200, 43);
		columnDetailPositionTest(column2, 0, 200, 0, 87, 43);
		columnDetailPositionTest(column3, 0, 287, 0, 87, 43);
		//elementPositionTest("detail.list2", 0, 374, 0, 201, 43);
		columnDetailPositionTest(column4, 0, 374, 0, 201, 27);
		elementPositionTest("detail.list3", 0, 374, 27, 201, 16);
		columnDetailPositionTest(column5, 0, 0, 0, 85, 16);
		columnDetailPositionTest(column6, 0, 85, 0, 31, 16);
		columnDetailPositionTest(column7, 0, 116, 0, 85, 16);

		//summary
		elementPositionTest("summary.list1", 0, 10, 96, 575, 43);
		//elementPositionTest("summary.list2", 0, 374, 0, 201, 43);
		subtotalPositionTest(subtotal1, 0, 374, 0, 201, 16);
		elementPositionTest("summary.list3", 0, 374, 16, 201, 27);
		subtotalPositionTest(subtotal2, 0, 0, 0, 85, 27);
		subtotalPositionTest(subtotal3, 0, 116, 0, 85, 16);
	}

	@Override
	protected JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4", "field5", "field6", "field7");
		dataSource.add("", "", "", 1, 1, "", 1);
		return dataSource;
	}
}
