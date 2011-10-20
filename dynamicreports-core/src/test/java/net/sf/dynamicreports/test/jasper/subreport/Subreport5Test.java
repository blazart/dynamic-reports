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

package net.sf.dynamicreports.test.jasper.subreport;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.Serializable;
import java.util.List;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.expression.AbstractComplexExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class Subreport5Test extends AbstractJasperValueTest implements Serializable {
	private static final long serialVersionUID = 1L;

	private TextColumnBuilder<String> column1 = col.column("field1", type.stringType());

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		SubreportBuilder subreport = Components.subreport(new SubreportExpression())
			.setDataSource(new SubreportDataSourceExpression());

		rb.detail(subreport);
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		elementCountTest("title.textField1", 3);
		elementValueTest("title.textField1", "Subreport text1", "Subreport text2", "Subreport text3");

		columnDetailCountTest(column1, 6);
		columnDetailValueTest(column1,	"text1a", "text1b", "text2a", "text2b", "text3a", "text3b");
	}

	private class SubreportExpression extends AbstractComplexExpression<JasperReportBuilder> {
		private static final long serialVersionUID = 1L;

		public SubreportExpression() {
			addExpression(field("field1", String.class));
		}

		@Override
		public JasperReportBuilder evaluate(List<?> values, ReportParameters reportParameters) {
			JasperReportBuilder report = report();
			report
			  .title(cmp.text("Subreport " + values.get(0)))
			  .columns(column1);

			return report;
		}
	}

	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1");
		dataSource.add("text1");
		dataSource.add("text2");
		dataSource.add("text3");
		return dataSource;
	}

	private class SubreportDataSourceExpression extends AbstractComplexExpression<JRDataSource> {
		private static final long serialVersionUID = 1L;

		public SubreportDataSourceExpression() {
			addExpression(field("field1", String.class));
		}

		@Override
		public JRDataSource evaluate(List<?> values, ReportParameters reportParameters) {
			DataSource dataSource = new DataSource("field1");
			dataSource.add(values.get(0) + "a");
			dataSource.add(values.get(0) + "b");
			return dataSource;
		}
	}
}
