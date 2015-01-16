/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2015 Ricardo Mariaca
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

import java.io.Serializable;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYStepRenderer;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class XyStepRendererCustomizer implements DRIChartCustomizer, Serializable {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private Double stepPoint;

	public XyStepRendererCustomizer(Double stepPoint) {
		this.stepPoint = stepPoint;
	}

	@Override
	public void customize(JFreeChart chart, ReportParameters reportParameters) {
		XYLineAndShapeRenderer lineRenderer = (XYLineAndShapeRenderer) chart.getXYPlot().getRenderer();
		XYStepRenderer renderer = new XYStepRenderer();

    renderer.setBaseItemLabelsVisible(lineRenderer.getBaseItemLabelsVisible());
    renderer.setBaseItemLabelFont(lineRenderer.getBaseItemLabelFont());
    renderer.setBaseItemLabelPaint(lineRenderer.getBaseItemLabelPaint());
    renderer.setBaseItemLabelGenerator(lineRenderer.getBaseItemLabelGenerator());
    renderer.setBaseShapesVisible(lineRenderer.getBaseShapesVisible());
    renderer.setBaseLinesVisible(lineRenderer.getBaseLinesVisible());

    if (stepPoint != null) {
    	renderer.setStepPoint(stepPoint);
    }
    chart.getXYPlot().setRenderer(renderer);
	}
}
