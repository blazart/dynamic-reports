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

import net.sf.dynamicreports.report.base.crosstab.DRCrosstabGroup;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.CrosstabTotalPosition;
import net.sf.dynamicreports.report.constant.OrderType;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("unchecked")
public abstract class AbstractCrosstabGroupBuilder<T extends AbstractCrosstabGroupBuilder<T, U>, U extends DRCrosstabGroup> extends AbstractBuilder<T, U> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected AbstractCrosstabGroupBuilder(U crosstabGroup) {
		super(crosstabGroup);
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

	public T setExpression(DRIExpression<?> expression) {
		getObject().setExpression(expression);
		return (T) this;
	}

	public T setOrderType(OrderType orderType) {
		getObject().setOrderType(orderType);
		return (T) this;
	}

	public T setOrderByExpression(DRISimpleExpression<?> orderByExpression) {
		getObject().setOrderByExpression(orderByExpression);
		return (T) this;
	}

	public T setComparatorExpression(DRISimpleExpression<?> comparatorExpression) {
		getObject().setComparatorExpression(comparatorExpression);
		return (T) this;
	}
}
