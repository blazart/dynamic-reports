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

package net.sf.dynamicreports.test.jasper.chart;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.Serializable;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class PercentageChartDataTest extends AbstractJasperChartTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		TextColumnBuilder<String> column1;
		TextColumnBuilder<Integer> column2;
		TextColumnBuilder<Integer> column3;

		rb.setPageFormat(PageType.A2, PageOrientation.PORTRAIT)
			.columns(
				column1 = col.column("Column1", "field1", String.class),
				column2 = col.column("Column2", "field2", Integer.class),
				column3 = col.column("Column3", "field3", Integer.class))
			.summary(
					cht.barChart()
						.setShowPercentages(true)
						.setCategory(column1)
						.series(cht.serie(column2), cht.serie(column3)),
					cht.bar3DChart()
						.setShowPercentages(true)
						.setCategory(column1)
						.series(cht.serie(column2), cht.serie(column3)),
					cht.stackedBarChart()
						.setShowPercentages(true)
						.setCategory(column1)
						.series(cht.serie(column2), cht.serie(column3)),
					cht.stackedBar3DChart()
						.setShowPercentages(true)
						.setCategory(column1)
						.series(cht.serie(column2), cht.serie(column3)),
					cht.areaChart()
						.setShowPercentages(true)
						.setCategory(column1)
						.series(cht.serie(column2), cht.serie(column3)),
					cht.stackedAreaChart()
						.setShowPercentages(true)
						.setCategory(column1)
						.series(cht.serie(column2), cht.serie(column3)),
					cht.layeredBarChart()
						.setShowPercentages(true)
						.setCategory(column1)
						.series(cht.serie(column2), cht.serie(column3)),
					cht.lineChart()
						.setShowPercentages(true)
						.setCategory(column1)
						.series(cht.serie(column2), cht.serie(column3)));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(2);

		String[] categories = new String[]{"value1", "value2", "value3"};
		String[] series = new String[]{"Column2", "Column3"};
		Number[][] values =  new Number[][]{{2d/6 * 100, 4d/6 * 100}, {4d/10 * 100, 6d/10 * 100}, {6d/14 * 100, 8d/14 * 100}, {8d/18 * 100, 10d/18 * 100}};

		chartCountTest("summary.chart1", 1);
		chartCategoryCountTest("summary.chart1", 0, 4);
		chartSeriesCountTest("summary.chart1", 0, 2);
		chartDataTest("summary.chart1", 0, categories, series, values);

		chartCountTest("summary.chart2", 1);
		chartCategoryCountTest("summary.chart2", 0, 4);
		chartSeriesCountTest("summary.chart2", 0, 2);
		chartDataTest("summary.chart2", 0, categories, series, values);

		chartCountTest("summary.chart3", 1);
		chartCategoryCountTest("summary.chart3", 0, 4);
		chartSeriesCountTest("summary.chart3", 0, 2);
		chartDataTest("summary.chart3", 0, categories, series, values);

		chartCountTest("summary.chart4", 1);
		chartCategoryCountTest("summary.chart4", 0, 4);
		chartSeriesCountTest("summary.chart4", 0, 2);
		chartDataTest("summary.chart4", 0, categories, series, values);

		chartCountTest("summary.chart5", 1);
		chartCategoryCountTest("summary.chart5", 0, 4);
		chartSeriesCountTest("summary.chart5", 0, 2);
		chartDataTest("summary.chart5", 0, categories, series, values);

		chartCountTest("summary.chart6", 1);
		chartCategoryCountTest("summary.chart6", 0, 4);
		chartSeriesCountTest("summary.chart6", 0, 2);
		chartDataTest("summary.chart6", 0, categories, series, values);

		chartCountTest("summary.chart7", 1);
		chartCategoryCountTest("summary.chart7", 0, 4);
		chartSeriesCountTest("summary.chart7", 0, 2);
		chartDataTest("summary.chart7", 0, categories, series, values);

		chartCountTest("summary.chart8", 1);
		chartCategoryCountTest("summary.chart8", 0, 4);
		chartSeriesCountTest("summary.chart8", 0, 2);
		chartDataTest("summary.chart8", 0, categories, series, values);
	}

	@Override
	protected JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		for (int i = 0; i < 4; i++) {
			dataSource.add("value" + (i + 1), i + 1, i + 2);
			dataSource.add("value" + (i + 1), i + 1, i + 2);
		}
		dataSource.add("value3", null, null);
		return dataSource;
	}
}
