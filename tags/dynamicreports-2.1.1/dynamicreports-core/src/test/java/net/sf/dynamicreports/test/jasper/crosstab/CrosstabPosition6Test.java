/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2011 Ricardo Mariaca
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

package net.sf.dynamicreports.test.jasper.crosstab;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.test.jasper.AbstractJasperCrosstabPositionTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class CrosstabPosition6Test extends AbstractJasperCrosstabPositionTest {
	private static final long serialVersionUID = 1L;

	private CrosstabRowGroupBuilder<String> rowGroup1;
	private CrosstabRowGroupBuilder<String> rowGroup2;
	private CrosstabColumnGroupBuilder<String> columnGroup1;
	private CrosstabColumnGroupBuilder<String> columnGroup2;
	private CrosstabMeasureBuilder<Integer> measure;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		TextColumnBuilder<String> column1 = col.column("Column1", "field1", String.class);
		TextColumnBuilder<String> column2 = col.column("Column2", "field1", String.class);
		TextColumnBuilder<String> column3 = col.column("Column3", "field2", String.class);
		TextColumnBuilder<String> column4 = col.column("Column4", "field2", String.class);
		TextColumnBuilder<Integer> column5 = col.column("Column5", "field3", Integer.class);

		measure = ctab.measure("measure", column5, Calculation.SUM);

		CrosstabBuilder crosstab = ctab.crosstab()
			.rowGroups(
				rowGroup1 = ctab.rowGroup(column1),
				rowGroup2 = ctab.rowGroup(column2).setShowTotal(false))
			.columnGroups(
				columnGroup1 = ctab.columnGroup(column3),
				columnGroup2 = ctab.columnGroup(column4).setShowTotal(false))
			.measures(measure);

		rb.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
			.summary(crosstab);
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		setCrosstabBand("summary");

		//column group 1
		crosstabGroupHeaderPositionTest(columnGroup1, 0, 0, 0, 100, 16);
		crosstabGroupTotalHeaderPositionTest(columnGroup1, 0, 0, 0, 100, 24);

		//column group 2
		crosstabGroupHeaderPositionTest(columnGroup2, 0, 0, 0, 100, 16);

		//row group 1
		crosstabGroupHeaderPositionTest(rowGroup1, 0, 0, 0, 100, 16);
		crosstabGroupTotalHeaderPositionTest(rowGroup1, 0, 0, 0, 200, 16);

		//row group 2
		crosstabGroupHeaderPositionTest(rowGroup2, 0, 0, 0, 100, 16);
	}

	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1", "field2", "field3");
		dataSource.add("a", "c", 1);
		dataSource.add("a", "c", 2);
		dataSource.add("a", "d", 3);
		dataSource.add("a", "d", 4);
		return dataSource;
	}
}
