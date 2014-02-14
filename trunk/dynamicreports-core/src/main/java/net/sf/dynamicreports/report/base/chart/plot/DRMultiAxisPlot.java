/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2014 Ricardo Mariaca
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

package net.sf.dynamicreports.report.base.chart.plot;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.report.constant.AxisPosition;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.chart.DRIChart;
import net.sf.dynamicreports.report.definition.chart.plot.DRIChartAxis;
import net.sf.dynamicreports.report.definition.chart.plot.DRIMultiAxisPlot;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DRMultiAxisPlot extends DRAxisPlot implements DRIMultiAxisPlot {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private List<DRIChartAxis> axes;

	public DRMultiAxisPlot() {
		axes = new ArrayList<DRIChartAxis>();
	}

	@Override
	public List<DRIChartAxis> getAxes() {
		return axes;
	}

	public void setAxes(List<DRIChartAxis> axes) {
		this.axes = axes;
	}

	public void addChart(DRIChart chart) {
		DRChartAxis axis = new DRChartAxis();
		axis.setChart(chart);
		axes.add(axis);
	}

	public void addChart(DRIChart chart, AxisPosition position) {
		DRChartAxis axis = new DRChartAxis();
		axis.setChart(chart);
		axis.setPosition(position);
		axes.add(axis);
	}
}
