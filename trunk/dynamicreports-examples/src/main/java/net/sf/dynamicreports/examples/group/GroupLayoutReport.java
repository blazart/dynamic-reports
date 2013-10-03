/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2013 Ricardo Mariaca
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

package net.sf.dynamicreports.examples.group;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.CustomGroupBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class GroupLayoutReport {

	public GroupLayoutReport() {
		build();
	}

	private void build() {
		TextColumnBuilder<String> yearColumn = col.column("Order year", exp.text(""));
		TextColumnBuilder<Date> orderDateColumn = col.column("Order date", "orderdate", type.dateType());
		TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType());
		TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType());
		TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Unit price", "unitprice", type.bigDecimalType());

		CustomGroupBuilder yearGroup = grp.group(new YearExpression())
			.groupByDataType()
			.setHeaderLayout(GroupHeaderLayout.EMPTY)
			.setPadding(0);

		try {
			report()
				.setTemplate(Templates.reportTemplate)
				.setSubtotalStyle(stl.style(Templates.boldStyle))
				.fields(
					field("orderdate", type.dateYearType()))
				.columns(
					yearColumn,	orderDateColumn, itemColumn, quantityColumn, unitPriceColumn)
				.groupBy(yearGroup)
				.subtotalsAtGroupHeader(yearGroup,
					sbt.first(new YearExpression(), yearColumn), sbt.sum(quantityColumn), sbt.sum(unitPriceColumn))
				.subtotalsAtSummary(
					sbt.aggregate(exp.text("Total"), yearColumn, Calculation.NOTHING), sbt.sum(quantityColumn), sbt.sum(unitPriceColumn))
				.title(Templates.createTitleComponent("GroupLayout"))
				.pageFooter(Templates.footerComponent)
				.setDataSource(createDataSource())
				.show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private class YearExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			return type.dateYearType().valueToString("orderdate", reportParameters);
		}
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("orderdate", "item", "quantity", "unitprice");
		dataSource.add(toDate(2009, 11, 1), "Tablet", 5, new BigDecimal(250));
		dataSource.add(toDate(2009, 11, 1), "Laptop", 3, new BigDecimal(480));
		dataSource.add(toDate(2009, 12, 1), "Smartphone", 1, new BigDecimal(280));
		dataSource.add(toDate(2009, 12, 1), "Tablet", 1, new BigDecimal(190));
		dataSource.add(toDate(2010, 1, 1), "Tablet", 4, new BigDecimal(230));
		dataSource.add(toDate(2010, 1, 1), "Laptop", 2, new BigDecimal(650));
		dataSource.add(toDate(2010, 2, 1), "Laptop", 3, new BigDecimal(550));
		dataSource.add(toDate(2010, 2, 1), "Smartphone", 5, new BigDecimal(210));
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
		new GroupLayoutReport();
	}
}