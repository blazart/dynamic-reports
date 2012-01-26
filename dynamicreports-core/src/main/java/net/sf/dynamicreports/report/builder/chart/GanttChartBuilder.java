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

import net.sf.dynamicreports.report.base.chart.dataset.DRSeriesDataset;
import net.sf.dynamicreports.report.base.chart.plot.DRBarPlot;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class GanttChartBuilder extends AbstractBaseChartBuilder<GanttChartBuilder, DRBarPlot, DRSeriesDataset> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected GanttChartBuilder() {
		super(ChartType.GANTT);
	}

	//dataset
	public GanttChartBuilder setTask(ValueColumnBuilder<?, String> column) {
		Validate.notNull(column, "column must not be null");
		getDataset().setValueExpression(column.getColumn());
		return this;
	}

	public GanttChartBuilder setTask(String fieldName, Class<String> valueClass) {
		return setTask(DynamicReports.field(fieldName, valueClass));
	}

	public GanttChartBuilder setTask(FieldBuilder<String> field) {
		Validate.notNull(field, "field must not be null");
		getDataset().setValueExpression(field.build());
		return this;
	}

	public GanttChartBuilder setTask(DRIExpression<String> expression) {
		getDataset().setValueExpression(expression);
		return this;
	}

	public GanttChartBuilder series(GanttChartSerieBuilder ...chartSeries) {
		return addSerie(chartSeries);
	}

	public GanttChartBuilder addSerie(GanttChartSerieBuilder ...chartSeries) {
		Validate.notNull(chartSeries, "chartSeries must not be null");
		Validate.noNullElements(chartSeries, "chartSeries must not contains null chartSerie");
		for (GanttChartSerieBuilder chartSerie : chartSeries) {
			getDataset().addSerie(chartSerie.build());
		}
		return this;
	}

	//plot
	public GanttChartBuilder setTimeAxisFormat(AxisFormatBuilder timeAxisFormat) {
		Validate.notNull(timeAxisFormat, "timeAxisFormat must not be null");
		getPlot().setXAxisFormat(timeAxisFormat.build());
		return this;
	}

	public GanttChartBuilder setTaskAxisFormat(AxisFormatBuilder taskAxisFormat) {
		Validate.notNull(taskAxisFormat, "taskAxisFormat must not be null");
		getPlot().setYAxisFormat(taskAxisFormat.build());
		return this;
	}

	public GanttChartBuilder setShowLabels(Boolean showLabels) {
		getPlot().setShowLabels(showLabels);
		return this;
	}

	public GanttChartBuilder setShowTickLabels(Boolean showTickLabels) {
		getPlot().setShowTickLabels(showTickLabels);
		return this;
	}

	public GanttChartBuilder setShowTickMarks(Boolean showTickMarks) {
		getPlot().setShowTickMarks(showTickMarks);
		return this;
	}
}
