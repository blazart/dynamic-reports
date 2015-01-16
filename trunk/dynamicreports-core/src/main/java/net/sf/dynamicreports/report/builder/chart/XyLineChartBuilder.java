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

package net.sf.dynamicreports.report.builder.chart;

import net.sf.dynamicreports.report.base.chart.plot.DRLinePlot;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class XyLineChartBuilder extends AbstractXyChartBuilder<XyLineChartBuilder, DRLinePlot> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected XyLineChartBuilder() {
		super(ChartType.XYLINE);
	}

	public XyLineChartBuilder setShowShapes(Boolean showShapes) {
		getPlot().setShowShapes(showShapes);
		return this;
	}

	public XyLineChartBuilder setShowLines(Boolean showLines) {
		getPlot().setShowLines(showLines);
		return this;
	}

	public XyLineChartBuilder setShowValues(Boolean showValues) {
		getPlot().setShowValues(showValues);
		return this;
	}

	public XyLineChartBuilder setValuePattern(String valuePattern) {
		getPlot().setValuePattern(valuePattern);
		return this;
	}

	/*public XyLineChartBuilder setPercentValuePattern(String percentValuePattern) {
		getPlot().setPercentValuePattern(percentValuePattern);
		return this;
	}*/
}
