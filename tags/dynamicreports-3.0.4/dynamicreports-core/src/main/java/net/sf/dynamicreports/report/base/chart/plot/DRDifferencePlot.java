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

package net.sf.dynamicreports.report.base.chart.plot;

import java.awt.Color;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.chart.plot.DRIDifferencePlot;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDifferencePlot extends DRAxisPlot implements DRIDifferencePlot {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private Color positiveColor;
	private Color negativeColor;
	private Boolean showShapes;

	@Override
	public Color getPositiveColor() {
		return positiveColor;
	}

	public void setPositiveColor(Color positiveColor) {
		this.positiveColor = positiveColor;
	}

	@Override
	public Color getNegativeColor() {
		return negativeColor;
	}

	public void setNegativeColor(Color negativeColor) {
		this.negativeColor = negativeColor;
	}

	@Override
	public Boolean getShowShapes() {
		return showShapes;
	}

	public void setShowShapes(Boolean showShapes) {
		this.showShapes = showShapes;
	}

}
