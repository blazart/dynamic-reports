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

package net.sf.dynamicreports.test.jasper.column;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ColumnTest extends AbstractJasperValueTest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private TextColumnBuilder<String> column2;
	private TextColumnBuilder<Date> column3;
	private TextColumnBuilder<Double> column4;
	private TextColumnBuilder<BigDecimal> column5;
	
	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.setLocale(Locale.ENGLISH)
			.fields(
					field("field1", Integer.class))
			.columns(					
					column2 = col.column("Column2", "field2", String.class),
					column3 = col.column("Column3", "field3", Date.class).setPattern("dd.MM.yyyy"), 
					column4 = col.column("Column4", "field4", Double.class).setPattern("#,###.00"), 
					column5 = col.column("Column5", "field5", BigDecimal.class).setValueFormatter(new ColumnValueFormatter()));
	}

	@Override
	public void test() {
		super.test();
		
		numberOfPagesTest(3);
		//column2
		columnDetailCountTest(column2, 110);
		columnDetailValueTest(column2, 50, "test");
		columnTitleCountTest(column2, 3);
		columnTitleValueTest(column2, "Column2", "Column2", "Column2");
		//column3
		columnDetailCountTest(column3, 110);
		columnDetailValueTest(column3, 50, new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
		columnTitleCountTest(column3, 3);
		columnTitleValueTest(column3, "Column3", "Column3", "Column3");
		//column4
		columnDetailCountTest(column4, 110);
		columnDetailValueTest(column4, 50, "1.00");
		columnTitleCountTest(column4, 3);
		columnTitleValueTest(column4, "Column4", "Column4", "Column4");
		//column5
		columnDetailCountTest(column5, 110);
		columnDetailValueTest(column5, 50, "value = 10");
		columnTitleCountTest(column5, 3);
		columnTitleValueTest(column5, "Column5", "Column5", "Column5");
	}
	
	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1", "field2", "field3", "field4", "field5");
		for (int i = 0; i < 110; i++) {
			dataSource.add(1, "test", new Date(), 1d, new BigDecimal(10));
		}		
		return dataSource;
	}
	
	private class ColumnValueFormatter extends AbstractValueFormatter<String, BigDecimal> {
		private static final long serialVersionUID = 1L;

		public String format(BigDecimal value, ReportParameters reportParameters) {
			return "value = " + value;
		}		
	}
}