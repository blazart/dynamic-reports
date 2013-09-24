/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2013 Ricardo Mariaca
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

package net.sf.dynamicreports.report.builder;

import net.sf.dynamicreports.report.base.DRSort;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.OrderType;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class SortBuilder extends AbstractBuilder<SortBuilder, DRSort> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected SortBuilder(TextColumnBuilder<?> column) {
		super(new DRSort());
		Validate.notNull(column, "column must not be null");
		getObject().setExpression(column.build());
	}

	protected SortBuilder(FieldBuilder<?> field) {
		super(new DRSort());
		Validate.notNull(field, "field must not be null");
		getObject().setExpression(field.build());
	}

	protected SortBuilder(VariableBuilder<?> variable) {
		super(new DRSort());
		Validate.notNull(variable, "variable must not be null");
		getObject().setExpression(variable.build());
	}

	protected SortBuilder(DRIExpression<?> expression) {
		super(new DRSort());
		getObject().setExpression(expression);
	}

	public SortBuilder setOrderType(OrderType orderType) {
		getObject().setOrderType(orderType);
		return this;
	}
}
