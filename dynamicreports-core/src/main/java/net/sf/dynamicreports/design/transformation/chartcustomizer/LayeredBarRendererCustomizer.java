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

package net.sf.dynamicreports.design.transformation.chartcustomizer;

import java.awt.Paint;
import java.io.Serializable;
import java.util.List;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LayeredBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.GradientPaintTransformer;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class LayeredBarRendererCustomizer implements DRIChartCustomizer, Serializable {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private List<Double> seriesBarWidths;

	public LayeredBarRendererCustomizer(List<Double> seriesBarWidths) {
		this.seriesBarWidths = seriesBarWidths;
	}

	@Override
	public void customize(JFreeChart chart, ReportParameters reportParameters) {
		BarRenderer categoryRenderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
    LayeredBarRenderer renderer = new LayeredBarRenderer();

    renderer.setBaseItemLabelsVisible(categoryRenderer.getBaseItemLabelsVisible());
    renderer.setBaseItemLabelFont(categoryRenderer.getBaseItemLabelFont());
    renderer.setBaseItemLabelPaint(categoryRenderer.getBaseItemLabelPaint());
    renderer.setBaseItemLabelGenerator(categoryRenderer.getBaseItemLabelGenerator());
		renderer.setShadowVisible(categoryRenderer.getShadowsVisible());
		CategoryDataset categoryDataset = chart.getCategoryPlot().getDataset();
		if(categoryDataset != null)	{
			for(int i = 0; i < categoryDataset.getRowCount(); i++) {
				Paint seriesOutlinePaint = categoryRenderer.getSeriesOutlinePaint(i);
				if (seriesOutlinePaint != null) {
					renderer.setSeriesOutlinePaint(i, seriesOutlinePaint);
				}
				Paint seriesPaint = categoryRenderer.getSeriesPaint(i);
				if (seriesPaint != null) {
					renderer.setSeriesPaint(i, seriesPaint);
				}
			}
		}
		renderer.setItemMargin(categoryRenderer.getItemMargin());
		GradientPaintTransformer gradientPaintTransformer = categoryRenderer.getGradientPaintTransformer();
		if (gradientPaintTransformer != null) {
			renderer.setGradientPaintTransformer(gradientPaintTransformer);
		}

    if (seriesBarWidths != null) {
    	for (int i = 0; i < seriesBarWidths.size(); i++) {
    		renderer.setSeriesBarWidth(i, seriesBarWidths.get(i));
			}
    }

    chart.getCategoryPlot().setRenderer(renderer);
  }
}
