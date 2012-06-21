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

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.chart.plot.DRIPiePlot;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRPiePlot extends AbstractBasePlot implements DRIPiePlot {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private Boolean circular;
	private Boolean showLabels;
	private Boolean showValues;
	private Boolean showPercentages;
	private String labelFormat;
	private String legendLabelFormat;

	public Boolean getCircular() {
		return circular;
	}

	public void setCircular(Boolean circular) {
		this.circular = circular;
	}

	public void setShowLabels(Boolean showLabels) {
		this.showLabels = showLabels;
	}

	public Boolean getShowLabels() {
		return showLabels;
	}

	public Boolean getShowValues() {
		return showValues;
	}

	public void setShowValues(Boolean showValues) {
		this.showValues = showValues;
	}

	public Boolean getShowPercentages() {
		return showPercentages;
	}

	public void setShowPercentages(Boolean showPercentages) {
		this.showPercentages = showPercentages;
	}

	public String getLabelFormat() {
		return labelFormat;
	}

	public void setLabelFormat(String labelFormat) {
		this.labelFormat = labelFormat;
	}

	public String getLegendLabelFormat() {
		return legendLabelFormat;
	}

	public void setLegendLabelFormat(String legendLabelFormat) {
		this.legendLabelFormat = legendLabelFormat;
	}
}
