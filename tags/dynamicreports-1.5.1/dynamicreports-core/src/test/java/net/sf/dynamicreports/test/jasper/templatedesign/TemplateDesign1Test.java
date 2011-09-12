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

package net.sf.dynamicreports.test.jasper.templatedesign;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.InputStream;
import java.io.Serializable;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class TemplateDesign1Test extends AbstractJasperValueTest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<Integer> column2;
	private AggregationSubtotalBuilder<Integer> subtotal1;
	
	@Override
	protected void configureReport(JasperReportBuilder rb) throws DRException {		
		InputStream is = TemplateDesign1Test.class.getResourceAsStream("templatedesign1.jrxml");
		rb.setTemplateDesign(is)
		  .columns(				
				column1 = col.column("Column1", "field1", String.class),
				column2 = col.column("Column2", "field2", Integer.class))
			.subtotalsAtSummary(subtotal1 = sbt.sum(column2));
	}
	
	@Override
	public void test() {
		super.test();
		
		numberOfPagesTest(1);
		
		columnTitleValueTest(column1, "Column1");
		columnDetailValueTest(column1, "row0", "row1");
		columnTitleValueTest(column2, "Column2");
		columnDetailValueTest(column2, "0", "1");
		subtotalValueTest(subtotal1, "1");
		
		elementValueTest("templateDesign.title", "title");
		elementValueTest("templateDesign.pageHeader", "pageHeader");
		elementValueTest("templateDesign.pageFooter", "pageFooter");
		elementValueTest("templateDesign.detail", "detail");
	}
	
	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1", "field2");
		for (int i = 0; i < 2; i++) {
			dataSource.add("row" + i, i);
		}		
		return dataSource;
	}
}