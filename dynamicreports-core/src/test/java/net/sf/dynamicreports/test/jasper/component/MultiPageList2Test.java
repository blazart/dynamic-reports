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
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.MultiPageListBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class MultiPageList2Test extends AbstractJasperPositionTest {
	private TextColumnBuilder<Integer> column1;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		MultiPageListBuilder multiPageList = cmp.multiPageList();
		for (int i = 0; i < 150; i++) {
			multiPageList.add(cmp.text("text"));
		}
		rb.title(multiPageList)
			.columns(
				column1 = col.column("Column1", "field1", Integer.class));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(3);

		for (int i = 0; i < 51; i++) {
			elementPositionTest("title.textField1", i, 10, 10 + i * 16, 575, 16);
		}
		for (int i = 0; i < 51; i++) {
			elementPositionTest("title.textField1", 51 + i, 10, 10 + i * 16, 575, 16);
		}
		for (int i = 0; i < 48; i++) {
			elementPositionTest("title.textField1", 102 + i, 10, 10 + i * 16, 575, 16);
		}

		columnTitlePositionTest(column1, 0, 10, 778, 575, 16);
		columnDetailPositionTest(column1, 0, 10, 794, 575, 16);
		columnDetailPositionTest(column1, 1, 10, 810, 575, 16);
	}

	@Override
	protected JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("field1");
		for (int i = 0; i < 2; i++) {
			dataSource.add(i);
		}
		return dataSource;
	}
}
