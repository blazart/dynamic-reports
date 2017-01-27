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

package net.sf.dynamicreports.test.jasper.report;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.Serializable;

import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class ValueTypeNamesTest extends AbstractJasperValueTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.columns(col.column("Column1", new ColumnExpression()))
			.fields(field("field1", type.stringType()))
			.parameters(parameter("field1", "parameterValue"));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);
	}

	@Override
	protected JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("field1");
		dataSource.add("fieldValue");
		return dataSource;
	}

	private class ColumnExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			Assert.assertEquals("Field value", "fieldValue", reportParameters.getValue("field1"));
			Assert.assertEquals("Field value", "fieldValue", reportParameters.getFieldValue("field1"));
			Assert.assertEquals("Parameter value", "parameterValue", reportParameters.getParameterValue("field1"));
			return "";
		}
	}
}
