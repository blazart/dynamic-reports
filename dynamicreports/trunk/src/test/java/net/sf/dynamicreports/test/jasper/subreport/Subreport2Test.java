/* Dynamic reports - Free Java reporting library for creating reports dynamically
 *
 * (C) Copyright 2010 Ricardo Mariaca
 *
 * http://dynamicreports.sourceforge.net
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 3 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA. 
 */
package net.sf.dynamicreports.test.jasper.subreport;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.Serializable;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class Subreport2Test extends AbstractJasperValueTest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private TextColumnBuilder<String> column;
	
	@Override
	protected void configureReport(JasperReportBuilder rb) {
		SubreportBuilder subreport = Components.subreport(subreport())
    .setDataSource(new SubreportDataSourceExpression());
		
		rb.detail(subreport);
	}

	@Override
	public void test() {
		super.test();
		
		numberOfPagesTest(1);
		
		elementCountTest("detail.subreport1", 3);
		
		//title
		elementCountTest("title.textField1", 3);
		elementValueTest("title.textField1", "Master1", "Master2", "Master3");
		
		columnDetailCountTest(column, 6);
		columnDetailValueTest(column,	"1_1", "1_2", "2_1", "2_2", "3_1", "3_2");
	}
	
	@Override
	protected JRDataSource createDataSource() {
		return new JREmptyDataSource(3);
	}
	
	private JasperReportBuilder subreport() {
		JasperReportBuilder report = report();
		column = col.column(new ValueExpression());
		report		   
		  .columns(column)
		  .title(cmp.text(new SubreportTitleExpression()));
		return report;
	}
	
	private class SubreportDataSourceExpression extends AbstractSimpleExpression<JRDataSource> {
		private static final long serialVersionUID = 1L;

		public JRDataSource evaluate(ReportParameters reportParameters) {
			return new JREmptyDataSource(2);
		}
	}
	
	private class SubreportTitleExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		public String evaluate(ReportParameters reportParameters) {			
			return "Master" + reportParameters.getMasterParameters().getReportRowNumber();
		}
	}
	
	private class ValueExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		public String evaluate(ReportParameters reportParameters) {
			return reportParameters.getMasterParameters().getReportRowNumber() + "_" + reportParameters.getReportRowNumber();
		}		
	}
}
