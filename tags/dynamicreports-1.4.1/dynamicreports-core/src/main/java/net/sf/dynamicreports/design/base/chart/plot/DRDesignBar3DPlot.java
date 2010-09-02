/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 Ricardo Mariaca
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

package net.sf.dynamicreports.design.base.chart.plot;

import net.sf.dynamicreports.design.definition.chart.plot.DRIDesignBar3DPlot;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignBar3DPlot extends DRDesignAxisPlot implements DRIDesignBar3DPlot {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private Double xOffset;
	private Double yOffset;
	private Boolean showLabels;

	public void setXOffset(Double xOffset) {
		this.xOffset = xOffset;
	}

	public Double getXOffset() {
		return xOffset;
	}

	public void setYOffset(Double yOffset) {
		this.yOffset = yOffset;
	}

	public Double getYOffset() {
		return yOffset;
	}

	public void setShowLabels(Boolean showLabels) {
		this.showLabels = showLabels;
	}

	public Boolean getShowLabels() {
		return showLabels;
	}
}
