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

package net.sf.dynamicreports.examples.chart;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class TimeSeriesChartReport {

	public TimeSeriesChartReport() {
		build();
	}

	private void build() {
		FontBuilder boldFont = stl.fontArialBold().setFontSize(12);

		TextColumnBuilder<Date> orderDateColumn = col.column("Order date", "orderdate", type.dateYearToMonthType());
		TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType());
		TextColumnBuilder<BigDecimal> priceColumn = col.column("Price", "price", type.bigDecimalType());

		try {
			report()
				.setTemplate(Templates.reportTemplate)
				.columns(orderDateColumn, quantityColumn, priceColumn)
				.title(Templates.createTitleComponent("TimeSeriesChart"))
				.summary(
					cht.timeSeriesChart()
						.setTitle("Time series chart")
						.setTitleFont(boldFont)
						.setTimePeriod(orderDateColumn)
						.setTimePeriodType(TimePeriod.MONTH)
						.series(
							cht.serie(quantityColumn), cht.serie(priceColumn))
						.setTimeAxisFormat(
							cht.axisFormat().setLabel("Date")))
				.pageFooter(Templates.footerComponent)
				.setDataSource(createDataSource())
				.show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("orderdate", "quantity", "price");
		dataSource.add(toDate(2010, 1), 50, new BigDecimal(200));
		dataSource.add(toDate(2010, 2), 110, new BigDecimal(450));
		dataSource.add(toDate(2010, 3), 70, new BigDecimal(280));
		dataSource.add(toDate(2010, 4), 250, new BigDecimal(620));
		dataSource.add(toDate(2010, 5), 100, new BigDecimal(400));
		dataSource.add(toDate(2010, 6), 80, new BigDecimal(320));
		dataSource.add(toDate(2010, 7), 180, new BigDecimal(490));
		return dataSource;
	}

	private Date toDate(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		return c.getTime();
	}

	public static void main(String[] args) {
		new TimeSeriesChartReport();
	}
}
