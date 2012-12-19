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

package net.sf.dynamicreports.examples.gettingstarted;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.math.BigDecimal;

import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.column.PercentageColumnBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class SimpleReport_Step06 {
	
	public SimpleReport_Step06() {
		build();
	}
	
	private void build() {
		StyleBuilder boldStyle         = stl.style().bold();
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
		StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle)
		                                    .setBorder(stl.pen1Point())
		                                    .setBackgroundColor(Color.LIGHT_GRAY);
		
		//                                                           title,     field name     data type
		TextColumnBuilder<String>     itemColumn      = col.column("Item",       "item",      type.stringType()).setStyle(boldStyle);
		TextColumnBuilder<Integer>    quantityColumn  = col.column("Quantity",   "quantity",  type.integerType());
		TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Unit price", "unitprice", type.bigDecimalType());
		//price = unitPrice * quantity
		TextColumnBuilder<BigDecimal> priceColumn     = unitPriceColumn.multiply(quantityColumn).setTitle("Price");
		PercentageColumnBuilder       pricePercColumn = col.percentageColumn("Price %", priceColumn);
		TextColumnBuilder<Integer>    rowNumberColumn = col.reportRowNumberColumn("No.")
		                                                    //sets the fixed width of a column, width = 2 * character width
		                                                   .setFixedColumns(2)
		                                                   .setHorizontalAlignment(HorizontalAlignment.CENTER);
		Bar3DChartBuilder itemChart = cht.bar3DChart()
		                                 .setTitle("Sales by item")
		                                 .setCategory(itemColumn)
		                                 .addSerie(
		                                	 cht.serie(unitPriceColumn), cht.serie(priceColumn));
		Bar3DChartBuilder itemChart2 = cht.bar3DChart()
		                                 .setTitle("Sales by item")
		                                 .setCategory(itemColumn)
		                                 .setUseSeriesAsCategory(true)
		                                 .addSerie(
		                                	 cht.serie(unitPriceColumn), cht.serie(priceColumn));		
		try {
			report()//create new report design
			  .setColumnTitleStyle(columnTitleStyle)
			  .setSubtotalStyle(boldStyle)
			  .highlightDetailEvenRows()
			  .columns(//add columns
			  	rowNumberColumn, itemColumn, quantityColumn, unitPriceColumn, priceColumn, pricePercColumn)
			  .groupBy(itemColumn)
			  .subtotalsAtSummary(
			  	sbt.sum(unitPriceColumn), sbt.sum(priceColumn))
			  .subtotalsAtFirstGroupFooter(
			  	sbt.sum(unitPriceColumn), sbt.sum(priceColumn))
			  .title(cmp.text("Getting started").setStyle(boldCenteredStyle))//shows report title
			  .pageFooter(cmp.pageXofY().setStyle(boldCenteredStyle))//shows number of page at page footer
			  .summary(itemChart, itemChart2)
			  .setDataSource(createDataSource())//set datasource
			  .show();//create and show report
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
	
	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("item", "quantity", "unitprice");
		dataSource.add("Notebook", 1, new BigDecimal(500));
		dataSource.add("DVD", 5, new BigDecimal(30));
		dataSource.add("DVD", 1, new BigDecimal(28));
		dataSource.add("DVD", 5, new BigDecimal(32));
		dataSource.add("Book", 3, new BigDecimal(11));
		dataSource.add("Book", 1, new BigDecimal(15));
		dataSource.add("Book", 5, new BigDecimal(10));
		dataSource.add("Book", 8, new BigDecimal(9));
		return dataSource;
	}
	
	public static void main(String[] args) {
		new SimpleReport_Step06();
	}
}