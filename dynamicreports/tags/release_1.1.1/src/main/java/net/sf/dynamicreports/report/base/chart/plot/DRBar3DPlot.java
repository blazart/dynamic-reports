/* Dynamic reports - Free Java reporting library for creating reports dynamically
 *
 * (C) Copyright 2010 Ricardo Mariaca
 *
 * http://dynamicreports.sourceforge.net
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 3 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA. 
 */
package net.sf.dynamicreports.report.base.chart.plot;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.chart.plot.DRIBar3DPlot;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRBar3DPlot extends DRAxisPlot implements DRIBar3DPlot {
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
