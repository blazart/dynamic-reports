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

package net.sf.dynamicreports.test.jasper.chart;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.Serializable;

import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class PieChartTest extends AbstractJasperChartTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		TextColumnBuilder<String> column1;
		TextColumnBuilder<Integer> column2;

		rb.addProperty("net.sf.jasperreports.chart.pie.ignore.duplicated.key", "true")
			.columns(
				column1 = col.column("Column1", "field1", String.class),
				column2 = col.column("Column2", "field2", Integer.class))
			.summary(
					cht.pieChart()
						.setKey(column1)
						.series(cht.serie(column2))
						.setCircular(false)
						.setLabelFormat("label {0}")
						.setLegendLabelFormat("legend label {0}"),
					cht.pieChart()
						.setKey(column1)
						.series(cht.serie(column2))
						.setShowLabels(false));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		JFreeChart chart = getChart("summary.chart1", 0);
		Plot plot = chart.getPlot();
		Assert.assertEquals("plot", PiePlot.class, plot.getClass());
		Assert.assertFalse("circular", ((PiePlot) plot).isCircular());
		Assert.assertEquals("label format", "label {0}", ((StandardPieSectionLabelGenerator) ((PiePlot) plot).getLabelGenerator()).getLabelFormat());
		Assert.assertEquals("legend label format", "legend label {0}", ((StandardPieSectionLabelGenerator) ((PiePlot) plot).getLegendLabelGenerator()).getLabelFormat());

		chart = getChart("summary.chart2", 0);
		plot = chart.getPlot();
		Assert.assertNull("label format", ((PiePlot) plot).getLabelGenerator());
	}

	@Override
	protected JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("field1", "field2");
		for (int i = 0; i < 4; i++) {
			dataSource.add("value" + (i + 1), i + 1);
			dataSource.add("value" + (i + 1), i + 1);
		}
		return dataSource;
	}
}
