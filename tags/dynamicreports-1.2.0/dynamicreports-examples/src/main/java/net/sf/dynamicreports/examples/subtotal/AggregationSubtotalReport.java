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

package net.sf.dynamicreports.examples.subtotal;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import net.sf.dynamicreports.examples.DataSource;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class AggregationSubtotalReport {
	
	public AggregationSubtotalReport() {
		build();
	}
	
	private void build() {
		TextColumnBuilder<String>     itemColumn      = col.column("Item",       "item",      type.stringType());
		TextColumnBuilder<Date>       orderDateColumn = col.column("Order Date", "orderdate", type.dateType());
		TextColumnBuilder<Integer>    quantityColumn  = col.column("Quantity",   "quantity",  type.integerType());
		TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Unit price", "unitprice", type.bigDecimalType());

		AggregationSubtotalBuilder<Long>       itemCount     = sbt.count(itemColumn).setLabel("count");
		AggregationSubtotalBuilder<Long>       itemDistCount = sbt.distinctCount(itemColumn).setLabel("distinct count");
		AggregationSubtotalBuilder<BigDecimal> itemSum       = sbt.sum(unitPriceColumn).setLabel("unit price sum")
		                                                          .setShowInColumn(itemColumn);

		AggregationSubtotalBuilder<Long> orderDateCount     = sbt.count(orderDateColumn).setLabel("count");
		AggregationSubtotalBuilder<Long> orderDateDistCount = sbt.distinctCount(orderDateColumn).setLabel("distinct count");
		AggregationSubtotalBuilder<Date> orderDateMin       = sbt.min(orderDateColumn).setLabel("min value");
		AggregationSubtotalBuilder<Date> orderDateMax       = sbt.max(orderDateColumn).setLabel("max value");
		
		AggregationSubtotalBuilder<Integer> quantitySum       = sbt.sum(quantityColumn).setLabel("sum");
		AggregationSubtotalBuilder<Number>  quantityAvg       = sbt.avg(quantityColumn).setLabel("avg");
		AggregationSubtotalBuilder<Long>    quantityCount     = sbt.count(quantityColumn).setLabel("count");
		AggregationSubtotalBuilder<Long>    quantityDistCount = sbt.distinctCount(quantityColumn).setLabel("distinct count");
		AggregationSubtotalBuilder<Integer> quantityMin       = sbt.min(quantityColumn).setLabel("min value");
		AggregationSubtotalBuilder<Integer> quantityMax       = sbt.max(quantityColumn).setLabel("max value");
		
		AggregationSubtotalBuilder<BigDecimal> unitPriceSum       = sbt.sum(unitPriceColumn).setLabel("sum");
		AggregationSubtotalBuilder<Number>     unitPriceAvg       = sbt.avg(unitPriceColumn).setLabel("avg");
		AggregationSubtotalBuilder<Long>       unitPriceCount     = sbt.count(unitPriceColumn).setLabel("count");
		AggregationSubtotalBuilder<Long>       unitPriceDistCount = sbt.distinctCount(unitPriceColumn).setLabel("distinct count");
		AggregationSubtotalBuilder<BigDecimal> unitPriceFirst     = sbt.first(unitPriceColumn).setLabel("first value");
		AggregationSubtotalBuilder<Number>     unitPriceStdDev    = sbt.stdDev(unitPriceColumn).setLabel("standard deviation");
		AggregationSubtotalBuilder<Number>     unitPriceVar       = sbt.var(unitPriceColumn).setLabel("variance");
		
		try {			
			report()
			  .setTemplate(Templates.reportTemplate)
			  .columns(
			  	itemColumn,	orderDateColumn, quantityColumn, unitPriceColumn)
			  .subtotalsAtSummary(
			  	itemSum, itemCount, itemDistCount,
			  	orderDateCount, orderDateDistCount, orderDateMin, orderDateMax,
			  	quantitySum, quantityAvg, quantityCount, quantityDistCount, quantityMin, quantityMax,
			  	unitPriceSum, unitPriceAvg, unitPriceCount, unitPriceDistCount, unitPriceFirst, unitPriceStdDev, unitPriceVar)
			  .title(Templates.createTitleComponent("AggregationSubtotal"))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
	
	private JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("item", "orderdate", "quantity", "unitprice");
		dataSource.add("Book", toDate(2010, 1, 1), 3, new BigDecimal(11));
		dataSource.add("Book", toDate(2010, 2, 1), 1, new BigDecimal(15));
		dataSource.add("Book", toDate(2010, 2, 1), 3, new BigDecimal(10));
		dataSource.add("Book", toDate(2010, 4, 1), 8, new BigDecimal(9));
		return dataSource;
	}
	
	private Date toDate(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);
		return c.getTime();
	}
	
	public static void main(String[] args) {
		new AggregationSubtotalReport();
	}
}