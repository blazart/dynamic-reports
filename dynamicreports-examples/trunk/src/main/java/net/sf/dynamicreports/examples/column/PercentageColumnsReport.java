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

import net.sf.dynamicreports.examples.DataSource;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.PercentageColumnBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class PercentageColumnsReport {
	private FieldBuilder<BigDecimal> unitPriceField;
	
	public PercentageColumnsReport() {
		build();
	}
	
	private void build() {
		try {
			unitPriceField = field("unitprice", BigDecimal.class);
			
			TextColumnBuilder<String>  itemColumn          = col.column("Item", "item", type.stringType());
			TextColumnBuilder<Integer> quantityColumn      = col.column("Quantity", "quantity", type.integerType());
			PercentageColumnBuilder    quantityPercColumn  = col.percentageColumn("Quantity [%]", quantityColumn);
			PercentageColumnBuilder    unitPricePercColumn = col.percentageColumn("Unit price [%]", unitPriceField);
			
			report()
			  .setTemplate(Templates.reportTemplate)
			  .fields(
			  	unitPriceField)
			  .columns(
			  	itemColumn, quantityColumn, quantityPercColumn, unitPricePercColumn)
			  .title(Templates.createTitleComponent("PercentageColumns"))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
	
	private JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("item", "quantity", "unitprice");
		dataSource.add("Book", 3, new BigDecimal(11));
		dataSource.add("Book", 1, new BigDecimal(15));
		dataSource.add("Book", 5, new BigDecimal(10));
		dataSource.add("Book", 8, new BigDecimal(9));
		return dataSource;
	}
	
	public static void main(String[] args) {
		new PercentageColumnsReport();
	}
}