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

package net.sf.dynamicreports.design.transformation.chartcustomizer;

import java.awt.Color;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;

import org.apache.commons.lang3.StringUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class SeriesColorsByNameCustomizer implements DRIChartCustomizer {
	private Map<String, Color> seriesColorsByName;

	public SeriesColorsByNameCustomizer(Map<String, Color> seriesColorsByName) {
		this.seriesColorsByName = seriesColorsByName;
	}

	@Override
	public void customize(JFreeChart chart, ReportParameters reportParameters) {
		if (chart.getPlot() instanceof CategoryPlot) {
			CategoryItemRenderer renderer = chart.getCategoryPlot().getRenderer();
			CategoryDataset dataset = chart.getCategoryPlot().getDataset();
			Set<String> legend = new LinkedHashSet<String>();
			for (int i = 0; i < dataset.getRowCount(); i++) {
				String key = (String) dataset.getRowKey(i);
				if (renderer instanceof GroupedStackedBarRenderer) {
					key = StringUtils.substringAfter(key, GroupedStackedBarRendererCustomizer.GROUP_SERIES_KEY);
					legend.add(key);
				}
				renderer.setSeriesPaint(i, seriesColorsByName.get(key));
			}
			if (!legend.isEmpty()) {
				LegendItemCollection legendItems = new LegendItemCollection();
				for (String item : legend) {
					legendItems.add(new LegendItem(item, seriesColorsByName.get(item)));
				}
				chart.getCategoryPlot().setFixedLegendItems(legendItems);
			}
		}
		else if (chart.getPlot() instanceof PiePlot) {
			PiePlot plot = (PiePlot) chart.getPlot();
			PieDataset dataset = plot.getDataset();
			for (int i = 0; i < dataset.getItemCount(); i++) {
				String key = (String) dataset.getKey(i);
				plot.setSectionPaint(key, seriesColorsByName.get(key));
			}
		}
		else if (chart.getPlot() instanceof XYPlot) {
			XYItemRenderer renderer = chart.getXYPlot().getRenderer();
			XYDataset dataset = chart.getXYPlot().getDataset();
			for (int i = 0; i < dataset.getSeriesCount(); i++) {
				String key = (String) dataset.getSeriesKey(i);
				renderer.setSeriesPaint(i, seriesColorsByName.get(key));
			}
		}
	}

}
