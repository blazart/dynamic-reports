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

package net.sf.dynamicreports.test.jasper.column;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class BooleanColumnPosition4Test extends AbstractJasperPositionTest {

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.setColumnStyle(stl.style().setPadding(2))
		  .columns(
				col.booleanColumn("1", "field1"),
				col.booleanColumn("1", "field1").setComponentType(BooleanComponentType.IMAGE_STYLE_1));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		elementPositionTest("columnHeader.list1", 0, 10, 10, 575, 16);
		elementPositionTest("columnHeader.column_field1.title1", 0, 0, 0, 287, 16);
		elementPositionTest("columnHeader.column_field1.title2", 0, 287, 0, 288, 16);

		elementPositionTest("detail.list1", 0, 10, 26, 575, 16);
		elementPositionTest("detail.column_field11", 0, 0, 0, 287, 16);
		elementPositionTest("detail.column_field12", 0, 287, 0, 288, 16);
		elementPositionTest("detail.list2", 0, 0, 0, 288, 16);
		elementPositionTest("detail.image1", 0, 136, 1, 15, 15);
	}

	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1");
		dataSource.add(true);
		return dataSource;
	}
}
