/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2013 Ricardo Mariaca
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

package net.sf.dynamicreports.test.jasper.style;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.BooleanColumnBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.test.jasper.AbstractJasperStyleTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class Style8Test extends AbstractJasperStyleTest {
	private BooleanColumnBuilder column1;
	private BooleanColumnBuilder column2;
	private BooleanColumnBuilder column3;
	private TextColumnBuilder<String> column4;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.setColumnStyle(stl.style(stl.pen1Point()).bold())
		  .columns(
		  	column1 = col.booleanColumn("field1"),
		  	column2 = col.booleanColumn("field2").setComponentType(BooleanComponentType.IMAGE_STYLE_1).setHorizontalAlignment(HorizontalAlignment.LEFT),
		  	column3 = col.booleanColumn("field3").setComponentType(BooleanComponentType.IMAGE_STYLE_1).setStyle(stl.style(stl.pen2Point()).setVerticalAlignment(VerticalAlignment.MIDDLE)),
		  	column4 = col.column("field4", type.stringType()));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		columnDetailStyleTest(column1, 0, null, null, "Arial", 10, true, null);
		columnDetailBorderTest(column1, 0, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1);
		columnDetailBorderTest(column2, 0, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1);
		columnDetailBorderTest(column3, 0, null, LineStyleEnum.SOLID, 2, null, LineStyleEnum.SOLID, 2, null, LineStyleEnum.SOLID, 2, null, LineStyleEnum.SOLID, 2);
		columnDetailStyleTest(column4, 0, null, null, "Arial", 10, true, null);
		columnDetailBorderTest(column4, 0, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1);

		columnDetailAlignmentTest(column1, 0, HorizontalAlignEnum.CENTER);
		columnDetailAlignmentTest(column2, 0, HorizontalAlignEnum.LEFT);
		columnDetailAlignmentTest(column3, 0, HorizontalAlignEnum.CENTER);

		columnDetailAlignmentTest(column1, 0, VerticalAlignEnum.TOP);
		columnDetailAlignmentTest(column2, 0, VerticalAlignEnum.TOP);
		columnDetailAlignmentTest(column3, 0, VerticalAlignEnum.MIDDLE);
	}

	@Override
	protected JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4");
		dataSource.add(true, true, true, "text");
		return dataSource;
	}
}
