/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2016 Ricardo Mariaca
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

package net.sf.dynamicreports.test.jasper.tableofcontents;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.util.Locale;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class TableOfContents9Test extends AbstractJasperValueTest {

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		TextColumnBuilder<String> column1 = col.column("Column1", "field1", type.stringType());

		rb.tableOfContents().setLocale(new Locale("es"))
	  	.columns(
	  		column1,
	  		col.column("Column2", "field2", type.stringType()))
		  .groupBy(grp.group(column1))
		  .pageFooter(cmp.pageXofY());
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(2);

		elementValueTest("title.textField1", "Tabla de contenidos");
		elementValueTest("pageFooter.textField2", " de 1");
	}

	@Override
	protected JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("field1", "field2");
		for (int i = 0; i < 10; i++) {
			dataSource.add("value1", "text");
		}
		for (int i = 0; i < 10; i++) {
			dataSource.add("value2", "text");
		}
		return dataSource;
	}
}
