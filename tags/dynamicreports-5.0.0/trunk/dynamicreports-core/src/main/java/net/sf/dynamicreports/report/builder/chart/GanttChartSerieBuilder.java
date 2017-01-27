/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2016 Ricardo Mariaca
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

import java.util.Date;

import net.sf.dynamicreports.report.base.chart.dataset.DRGanttChartSerie;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.VariableBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class GanttChartSerieBuilder extends AbstractChartSerieBuilder<GanttChartSerieBuilder, DRGanttChartSerie> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected GanttChartSerieBuilder() {
		super(new DRGanttChartSerie());
	}

	//start date
	public GanttChartSerieBuilder setStartDate(ValueColumnBuilder<?, Date> column) {
		Validate.notNull(column, "column must not be null");
		getObject().setStartDateExpression(column.getColumn());
		return this;
	}

	public GanttChartSerieBuilder setStartDate(FieldBuilder<Date> field) {
		Validate.notNull(field, "field must not be null");
		getObject().setStartDateExpression(field.build());
		return this;
	}

	public GanttChartSerieBuilder setStartDate(DRIExpression<Date> expression) {
		getObject().setStartDateExpression(expression);
		return this;
	}

	//end date
	public GanttChartSerieBuilder setEndDate(ValueColumnBuilder<?, Date> column) {
		Validate.notNull(column, "column must not be null");
		getObject().setEndDateExpression(column.getColumn());
		return this;
	}

	public GanttChartSerieBuilder setEndDate(FieldBuilder<Date> field) {
		Validate.notNull(field, "field must not be null");
		getObject().setEndDateExpression(field.build());
		return this;
	}

	public GanttChartSerieBuilder setEndDate(DRIExpression<Date> expression) {
		getObject().setEndDateExpression(expression);
		return this;
	}

	//percent
	public GanttChartSerieBuilder setPercent(ValueColumnBuilder<?, ? extends Number> column) {
		Validate.notNull(column, "column must not be null");
		getObject().setPercentExpression(column.getColumn());
		return this;
	}

	public GanttChartSerieBuilder setPercent(FieldBuilder<? extends Number> field) {
		Validate.notNull(field, "field must not be null");
		getObject().setPercentExpression(field.build());
		return this;
	}

	public GanttChartSerieBuilder setPercent(DRIExpression<? extends Number> expression) {
		getObject().setPercentExpression(expression);
		return this;
	}

	public GanttChartSerieBuilder setPercent(VariableBuilder<? extends Number> variable) {
		Validate.notNull(variable, "variable must not be null");
		getObject().setPercentExpression(variable.build());
		return this;
	}

	//label
	public GanttChartSerieBuilder setLabel(String label) {
		getObject().setLabelExpression(Expressions.text(label));
		return this;
	}

	public GanttChartSerieBuilder setLabel(DRIExpression<String> labelExpression) {
		getObject().setLabelExpression(labelExpression);
		return this;
	}

	public DRGanttChartSerie getChartSerie() {
		return build();
	}
}
