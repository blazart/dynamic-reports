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
import net.sf.dynamicreports.report.definition.chart.plot.DRIPiePlot;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRPiePlot extends AbstractPlot implements DRIPiePlot {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private Boolean circular;
	private String labelFormat;
	private String legendLabelFormat;

	public Boolean getCircular() {
		return circular;
	}

	public void setCircular(Boolean circular) {
		this.circular = circular;
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
