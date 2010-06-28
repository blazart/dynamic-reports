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
package net.sf.dynamicreports.examples.column;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;
import java.util.Date;

import net.sf.dynamicreports.examples.DataSource;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ExpressionColumnReport {
	private FieldBuilder<Integer> quantityField;
	
	public ExpressionColumnReport() {
		build();
	}
	
	private void build() {
		try {
			report()
			  .setTemplate(Templates.reportTemplate)
			  .fields(
			  	field("orderdate", Date.class),
			  	quantityField = field("quantity", Integer.class),
			  	field("unitprice", BigDecimal.class))
			  .columns(
			  	col.column("Item", "item", type.stringType()),
			  	col.column("Expression column", new ExpressionColumn()))
			  .title(Templates.createTitleComponent("ExpressionColumn"))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
		
	private JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("item", "orderdate", "quantity", "unitprice");
		dataSource.add("Notebook", new Date(), 1, new BigDecimal(500));
		return dataSource;
	}
	
	public static void main(String[] args) {
		new ExpressionColumnReport();
	}
	
	private class ExpressionColumn extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		public String evaluate(ReportParameters reportParameters) {
			return 
				"Item = " + reportParameters.getValue("item") + ", " +
				"Order date = " + type.dateType().valueToString("orderdate", reportParameters) + ", " +
				"Quantity = " + type.integerType().valueToString(quantityField, reportParameters) + ", " +
				"Unit price = " + type.bigDecimalType().valueToString("unitprice", reportParameters);
		}		
	}
}