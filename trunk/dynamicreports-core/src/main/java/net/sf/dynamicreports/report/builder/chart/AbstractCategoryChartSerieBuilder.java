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

import net.sf.dynamicreports.report.base.chart.dataset.DRCategoryChartSerie;
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
@SuppressWarnings("unchecked")
public class AbstractCategoryChartSerieBuilder<T extends AbstractCategoryChartSerieBuilder<T, U>, U extends DRCategoryChartSerie> extends AbstractChartSerieBuilder<T, U> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected AbstractCategoryChartSerieBuilder(U categoryChartSerie, ValueColumnBuilder<?, ? extends Number> column) {
		super(categoryChartSerie);
		Validate.notNull(column, "column must not be null");
		getObject().setValueExpression(column.getColumn());
		getObject().setLabelExpression(column.getColumn().getTitleExpression());
	}

	protected AbstractCategoryChartSerieBuilder(U categoryChartSerie, FieldBuilder<? extends Number> field) {
		super(categoryChartSerie);
		Validate.notNull(field, "field must not be null");
		getObject().setValueExpression(field.build());
	}

	protected AbstractCategoryChartSerieBuilder(U categoryChartSerie, DRIExpression<? extends Number> valueExpression) {
		super(categoryChartSerie);
		getObject().setValueExpression(valueExpression);
	}

	protected AbstractCategoryChartSerieBuilder(U categoryChartSerie, VariableBuilder<? extends Number> variable) {
		super(categoryChartSerie);
		Validate.notNull(variable, "variable must not be null");
		getObject().setValueExpression(variable.build());
	}

	public T setLabel(String label) {
		getObject().setLabelExpression(Expressions.text(label));
		return (T) this;
	}

	public T setLabel(DRIExpression<String> labelExpression) {
		getObject().setLabelExpression(labelExpression);
		return (T) this;
	}

	public U getChartSerie() {
		return build();
	}
}
