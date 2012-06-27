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

package net.sf.dynamicreports.report.builder.crosstab;

import net.sf.dynamicreports.report.base.crosstab.DRCrosstabVariable;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.CrosstabPercentageType;
import net.sf.dynamicreports.report.definition.DRICrosstabValue;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class CrosstabVariableBuilder<T> extends AbstractBuilder<CrosstabVariableBuilder<T>, DRCrosstabVariable<T>> implements DRICrosstabValue<T> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected CrosstabVariableBuilder(ValueColumnBuilder<?, ?> column, Calculation calculation) {
		super(new DRCrosstabVariable<T>(column.build(), calculation));
	}

	protected CrosstabVariableBuilder(FieldBuilder<?> field, Calculation calculation) {
		super(new DRCrosstabVariable<T>(field.getField(), calculation));
	}

	protected CrosstabVariableBuilder(DRIExpression<?> expression, Calculation calculation) {
		super(new DRCrosstabVariable<T>(expression, calculation));
	}

	public CrosstabVariableBuilder<T> setPercentageType(CrosstabPercentageType percentageType) {
		getObject().setPercentageType(percentageType);
		return this;
	}

	@Override
	public String getName() {
		return getObject().getName();
	}
}
