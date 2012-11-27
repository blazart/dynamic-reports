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

package net.sf.dynamicreports.report.builder.chart;

import net.sf.dynamicreports.report.base.chart.dataset.DRChartDataset;
import net.sf.dynamicreports.report.base.chart.plot.DRMultiAxisPlot;
import net.sf.dynamicreports.report.constant.AxisPosition;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.Constants;

import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class MultiAxisChartBuilder extends AbstractBaseChartBuilder<MultiAxisChartBuilder, DRMultiAxisPlot, DRChartDataset> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected MultiAxisChartBuilder() {
		super(ChartType.MULTI_AXIS);
	}

	public MultiAxisChartBuilder charts(AbstractBaseChartBuilder<?, ?, ?> ...charts) {
		return addChart(charts);
	}

	public MultiAxisChartBuilder addChart(AbstractBaseChartBuilder<?, ?, ?> ...charts) {
		Validate.notNull(charts, "charts must not be null");
		Validate.noNullElements(charts, "charts must not contains null chart");
		for (AbstractBaseChartBuilder<?, ?, ?> chart : charts) {
			getPlot().addChart(chart.build());
		}
		return this;
	}

	public MultiAxisChartBuilder addChart(AbstractBaseChartBuilder<?, ?, ?> chart, AxisPosition position) {
		Validate.notNull(chart, "chart must not be null");
		getPlot().addChart(chart.build(), position);
		return this;
	}

	//plot
	public MultiAxisChartBuilder setXAxisFormat(AxisFormatBuilder xAxisFormat) {
		Validate.notNull(xAxisFormat, "xAxisFormat must not be null");
		getPlot().setXAxisFormat(xAxisFormat.build());
		return this;
	}

	public MultiAxisChartBuilder setYAxisFormat(AxisFormatBuilder yAxisFormat) {
		Validate.notNull(yAxisFormat, "yAxisFormat must not be null");
		getPlot().setYAxisFormat(yAxisFormat.build());
		return this;
	}
}
