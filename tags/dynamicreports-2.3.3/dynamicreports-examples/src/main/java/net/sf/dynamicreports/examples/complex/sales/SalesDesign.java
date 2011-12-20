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

package net.sf.dynamicreports.examples.complex.sales;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;
import java.util.Date;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.examples.complex.ReportDesign;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.chart.PieChartBuilder;
import net.sf.dynamicreports.report.builder.chart.TimeSeriesChartBuilder;
import net.sf.dynamicreports.report.builder.column.PercentageColumnBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class SalesDesign implements ReportDesign<SalesData> {

	public void configureReport(ReportBuilder<?> rb, SalesData invoiceData) throws DRException {
		//init styles
		FontBuilder  boldFont = stl.fontArialBold();
		//init columns
		TextColumnBuilder<String>     stateColumn     = col.column("State", "state", type.stringType());
		TextColumnBuilder<String>     itemColumn      = col.column("Item", "item", type.stringType()).setPrintRepeatedDetailValues(false);
		TextColumnBuilder<Date>       orderDateColumn = col.column("Order date", "orderdate", type.dateType());
		TextColumnBuilder<Integer>    quantityColumn  = col.column("Quantity", "quantity", type.integerType());
		TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Unit price", "unitprice", Templates.currencyType);
		//price = unitPrice * quantity
		TextColumnBuilder<BigDecimal> priceColumn     = unitPriceColumn.multiply(quantityColumn).setTitle("Price")
		                                                               .setDataType(Templates.currencyType);
		PercentageColumnBuilder       pricePercColumn = col.percentageColumn("Price %", priceColumn);
		//init groups
		ColumnGroupBuilder stateGroup = grp.group(stateColumn);
		//init subtotals
		AggregationSubtotalBuilder<Number> priceAvg         = sbt.avg(priceColumn)
		                                                         .setValueFormatter(Templates.createCurrencyValueFormatter("avg = "));
		AggregationSubtotalBuilder<BigDecimal> unitPriceSum = sbt.sum(unitPriceColumn).setLabel("Total:").setLabelStyle(Templates.boldStyle);
		AggregationSubtotalBuilder<BigDecimal> priceSum     = sbt.sum(priceColumn).setLabel("").setLabelStyle(Templates.boldStyle);
		//init charts
		Bar3DChartBuilder      itemChart  = cht.bar3DChart()
		                                       .setTitle("Sales by item")
		                                       .setTitleFont(boldFont)
		                                       .setOrientation(Orientation.HORIZONTAL)
		                                       .setCategory(itemColumn)
		                                       .addSerie(
		                                        	cht.serie(unitPriceColumn), cht.serie(priceColumn));
		TimeSeriesChartBuilder dateChart  = cht.timeSeriesChart()
		                                       .setTitle("Sales by date")
		                                       .setTitleFont(boldFont)
		                                       .setFixedHeight(150)
		                                       .setTimePeriod(orderDateColumn)
		                                       .addSerie(
		                                        	cht.serie(unitPriceColumn), cht.serie(priceColumn));
		PieChartBuilder        stateChart = cht.pieChart()
		                                       .setTitle("Sales by state")
		                                       .setTitleFont(boldFont)
		                                       .setFixedHeight(100)
		                                       .setShowLegend(false)
		                                       .setKey(stateColumn)
		                                       .addSerie(
		                                        	cht.serie(priceColumn));

		//configure report
		rb.setTemplate(Templates.reportTemplate)
		  //columns
		  .columns(
		  	stateColumn, itemColumn, orderDateColumn, quantityColumn, unitPriceColumn, priceColumn, pricePercColumn)
		  //groups
		  .groupBy(stateGroup)
		  //subtotals
		  .subtotalsAtFirstGroupFooter(
		  	sbt.sum(unitPriceColumn), sbt.sum(priceColumn))
		  .subtotalsOfPercentageAtGroupFooter(stateGroup,
		  	sbt.percentage(priceColumn).setShowInColumn(pricePercColumn))
		  .subtotalsAtSummary(
		  	unitPriceSum, priceSum, priceAvg)
		  //band components
		  .title(
		  	Templates.createTitleComponent("Sales"),
		  	cmp.horizontalList(
		  		itemChart, cmp.verticalList(dateChart, stateChart)),
		  	cmp.verticalGap(10))
		  .pageFooter(
		  	Templates.footerComponent);
	}
}