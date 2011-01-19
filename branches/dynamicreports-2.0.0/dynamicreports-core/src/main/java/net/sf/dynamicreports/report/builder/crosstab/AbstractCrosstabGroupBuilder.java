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

package net.sf.dynamicreports.report.builder.crosstab;

import net.sf.dynamicreports.report.base.DRValueColumn;
import net.sf.dynamicreports.report.base.crosstab.DRCrosstabGroup;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.CrosstabTotalPosition;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.OrderType;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("unchecked")
public abstract class AbstractCrosstabGroupBuilder<T extends AbstractCrosstabGroupBuilder<T, U, V>, U extends DRCrosstabGroup<V>, V> extends AbstractBuilder<T, U> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected AbstractCrosstabGroupBuilder(ValueColumnBuilder<?, V> column, U crosstabGroup) {
		super(crosstabGroup);
		Validate.notNull(column, "column must not be null");
		DRValueColumn<V> col = column.getColumn();
		getObject().setExpression(col);
		getObject().setDataType(col.getComponent().getDataType());
		getObject().setHeaderPattern(col.getComponent().getPattern());
		getObject().setHeaderHorizontalAlignment(col.getComponent().getHorizontalAlignment());
		getObject().setHeaderValueFormatter(col.getComponent().getValueFormatter());
		getObject().setHeaderStretchWithOverflow(col.getComponent().getStretchWithOverflow());
		getObject().setHeaderStyle(col.getComponent().getStyle());
	}

	protected AbstractCrosstabGroupBuilder(FieldBuilder<V> field, U crosstabGroup) {
		super(crosstabGroup);
		Validate.notNull(field, "field must not be null");
		getObject().setExpression(field.getField());
		getObject().setDataType(field.getField().getDataType());
	}

	protected AbstractCrosstabGroupBuilder(DRISimpleExpression<V> expression, U crosstabGroup) {
		super(crosstabGroup);
		getObject().setExpression(expression);
	}

	public T setHeaderPattern(String pattern) {
		getObject().setHeaderPattern(pattern);
		return (T) this;
	}

	public T setHeaderHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		getObject().setHeaderHorizontalAlignment(horizontalAlignment);
		return (T) this;
	}

	public T setHeaderValueFormatter(DRIValueFormatter<?, ? super V> valueFormatter) {
		getObject().setHeaderValueFormatter(valueFormatter);
		return (T) this;
	}

	public T setHeaderStretchWithOverflow(Boolean stretchWithOverflow) {
		getObject().setHeaderStretchWithOverflow(stretchWithOverflow);
		return (T) this;
	}

	public T setHeaderStyle(StyleBuilder style) {
		if (style != null) {
			getObject().setHeaderStyle(style.getStyle());
		}
		else {
			getObject().setHeaderStyle(null);
		}
		return (T) this;
	}

	public T setShowTotal(Boolean showTotal) {
		getObject().setShowTotal(showTotal);
		return (T) this;
	}

	public T setTotalPosition(CrosstabTotalPosition totalPosition) {
		getObject().setTotalPosition(totalPosition);
		return (T) this;
	}

	public T setTotalHeader(DRISimpleExpression<?> totalHeaderExpression) {
		getObject().setTotalHeaderExpression(totalHeaderExpression);
		return (T) this;
	}

	public T setTotalHeader(String totalHeader) {
		getObject().setTotalHeaderExpression(Expressions.text(totalHeader));
		return (T) this;
	}

	public T setTotalHeaderStyle(StyleBuilder style) {
		if (style != null) {
			getObject().setTotalHeaderStyle(style.getStyle());
		}
		else {
			getObject().setTotalHeaderStyle(null);
		}
		return (T) this;
	}

	public T setDataType(DRIDataType<? super V, V> dataType) {
		getObject().setDataType(dataType);
		return (T) this;
	}

	public T setOrderType(OrderType orderType) {
		getObject().setOrderType(orderType);
		return (T) this;
	}

	public T setOrderByExpression(DRISimpleExpression<V> orderByExpression) {
		getObject().setOrderByExpression(orderByExpression);
		return (T) this;
	}

	public T setComparatorExpression(DRISimpleExpression<V> comparatorExpression) {
		getObject().setComparatorExpression(comparatorExpression);
		return (T) this;
	}
}
