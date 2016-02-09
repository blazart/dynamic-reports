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

package net.sf.dynamicreports.examples.gettingstarted;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.math.BigDecimal;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.column.PercentageColumnBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.datatype.BigDecimalType;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class SimpleReport_Step10 {

	public SimpleReport_Step10() {
		build();
	}

	private void build() {
		CurrencyType currencyType = new CurrencyType();

		StyleBuilder boldStyle         = stl.style().bold();
		StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle)
		                                    .setBorder(stl.pen1Point())
		                                    .setBackgroundColor(Color.LIGHT_GRAY);
		StyleBuilder titleStyle        = stl.style(boldCenteredStyle)
		                                    .setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
		                                    .setFontSize(15);

		//                                                           title,     field name     data type
		TextColumnBuilder<String>     itemColumn      = col.column("Item",       "item",      type.stringType()).setStyle(boldStyle);
		TextColumnBuilder<Integer>    quantityColumn  = col.column("Quantity",   "quantity",  type.integerType());
		TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Unit price", "unitprice", currencyType);
		//price = unitPrice * quantity
		TextColumnBuilder<BigDecimal> priceColumn     = unitPriceColumn.multiply(quantityColumn).setTitle("Price")
		                                                               .setDataType(currencyType);
		PercentageColumnBuilder       pricePercColumn = col.percentageColumn("Price %", priceColumn);
		TextColumnBuilder<Integer>    rowNumberColumn = col.reportRowNumberColumn("No.")
		                                                    //sets the fixed width of a column, width = 2 * character width
		                                                   .setFixedColumns(2)
		                                                   .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
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
		ColumnGroupBuilder itemGroup = grp.group(itemColumn);
		itemGroup.setPrintSubtotalsWhenExpression(exp.printWhenGroupHasMoreThanOneRow(itemGroup));
		try {
			report()//create new report design
			  .setColumnTitleStyle(columnTitleStyle)
			  .setSubtotalStyle(boldStyle)
			  .highlightDetailEvenRows()
			  .columns(//add columns
			  	rowNumberColumn, itemColumn, quantityColumn, unitPriceColumn, priceColumn, pricePercColumn)
			  .columnGrid(
			  	rowNumberColumn, quantityColumn, unitPriceColumn, grid.verticalColumnGridList(priceColumn, pricePercColumn))
			  .groupBy(itemGroup)
			  .subtotalsAtSummary(
			  	sbt.sum(unitPriceColumn), sbt.sum(priceColumn))
			  .subtotalsAtFirstGroupFooter(
			  	sbt.sum(unitPriceColumn), sbt.sum(priceColumn))
			  .title(//shows report title
			  	cmp.horizontalList()
			  		.add(
			  			cmp.image(Templates.class.getResource("images/dynamicreports.png")).setFixedDimension(80, 80),
			  			cmp.text("DynamicReports").setStyle(titleStyle).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
			  			cmp.text("Getting started").setStyle(titleStyle).setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT))
			  		.newRow()
			  		.add(cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10)))
			  .pageFooter(cmp.pageXofY().setStyle(boldCenteredStyle))//shows number of page at page footer
			  .summary(
			  	cmp.horizontalList(itemChart, itemChart2))
			  .setDataSource(createDataSource())//set datasource
			  .show();//create and show report
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private class CurrencyType extends BigDecimalType {
		private static final long serialVersionUID = 1L;

		@Override
		public String getPattern() {
			return "$ #,###.00";
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
		new SimpleReport_Step10();
	}
}