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

package net.sf.dynamicreports.test.jasper.chart;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;

import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.Position;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.JRDataSource;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.ui.RectangleEdge;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class ChartTest extends AbstractJasperChartTest implements Serializable {
	private static final long serialVersionUID = 1L;
		
	@Override
	protected void configureReport(JasperReportBuilder rb) {
		TextColumnBuilder<String> column1;
		TextColumnBuilder<Integer> column2; 
		
		rb.columns(
				column1 = col.column("Column1", "field1", String.class),
				column2 = col.column("Column2", "field2", Integer.class))
			.summary(
					cht.barChart()
						.setTitle("title")
						.setTitleColor(Color.BLUE)
						.setTitleFont(stl.fontArialBold())
						.setTitlePosition(Position.RIGHT)
						.setSubtitle("subtitle")
						.setSubtitleColor(Color.CYAN)
						.setSubtitleFont(stl.fontArial())
						.setLegendColor(Color.BLUE)
						.setLegendBackgroundColor(Color.LIGHT_GRAY)
						.setLegendFont(stl.fontCourierNew())
						.setLegendPosition(Position.LEFT)
						.setCategory(column1)
						.series(
								cht.serie(column2)),
					cht.barChart()
						.setShowLegend(false)
						.setOrientation(Orientation.HORIZONTAL)
						.seriesColors(Color.BLUE, Color.GREEN, Color.RED)
						.setCategory(column1)
						.series(
								cht.serie(column2),
								cht.serie(column2).setLabel("2"),
								cht.serie(column2).setLabel("3")));
	}
	
	@Override
	public void test() {
		super.test();
		
		numberOfPagesTest(1);
		
		chartCountTest("summary.chart1", 1);
		JFreeChart chart = getChart("summary.chart1", 0);
		
		TextTitle title = chart.getTitle();
		Assert.assertEquals("title", "title", title.getText());
		Assert.assertEquals("title color", Color.BLUE, title.getPaint());
		Assert.assertEquals("title font", new Font("Arial", Font.BOLD, 10), title.getFont());
		Assert.assertEquals("title position", RectangleEdge.RIGHT, title.getPosition());

		TextTitle subtitle = (TextTitle) chart.getSubtitle(1);
		Assert.assertEquals("subtitle", "subtitle", subtitle.getText());
		Assert.assertEquals("subtitle color", Color.CYAN, subtitle.getPaint());
		Assert.assertEquals("subtitle font", new Font("Arial", Font.PLAIN, 10), subtitle.getFont());

		LegendTitle legend = (LegendTitle) chart.getSubtitle(0);
		Assert.assertEquals("legend color", Color.BLUE, legend.getItemPaint());
		Assert.assertEquals("legend backgroundcolor", Color.LIGHT_GRAY, legend.getBackgroundPaint());
		Assert.assertEquals("legend font", new Font("Courier New", Font.PLAIN, 10), legend.getItemFont());
		Assert.assertEquals("legend position", RectangleEdge.LEFT, legend.getPosition());
		
		chartCountTest("summary.chart2", 1);
		chart = getChart("summary.chart2", 0);
		Assert.assertNull("legend", chart.getLegend());
		Assert.assertEquals("plot orientation", PlotOrientation.HORIZONTAL, chart.getCategoryPlot().getOrientation());
		Assert.assertEquals("plot series colors", Color.BLUE, chart.getPlot().getDrawingSupplier().getNextPaint());
		Assert.assertEquals("plot series colors", Color.GREEN, chart.getPlot().getDrawingSupplier().getNextPaint());
		Assert.assertEquals("plot series colors", Color.RED, chart.getPlot().getDrawingSupplier().getNextPaint());
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
