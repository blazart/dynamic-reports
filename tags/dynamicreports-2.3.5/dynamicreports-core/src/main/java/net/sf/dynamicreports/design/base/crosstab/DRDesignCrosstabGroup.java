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

package net.sf.dynamicreports.design.base.crosstab;

import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabGroup;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.report.constant.CrosstabTotalPosition;
import net.sf.dynamicreports.report.constant.OrderType;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class DRDesignCrosstabGroup implements DRIDesignCrosstabGroup {
	private String name;
	private CrosstabTotalPosition totalPosition;
	private OrderType orderType;
	private DRIDesignExpression expression;
	private DRIDesignExpression orderByExpression;
	private DRIDesignExpression comparatorExpression;
	private DRDesignCrosstabCellContent header;
	private DRDesignCrosstabCellContent totalHeader;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CrosstabTotalPosition getTotalPosition() {
		return totalPosition;
	}

	public void setTotalPosition(CrosstabTotalPosition totalPosition) {
		this.totalPosition = totalPosition;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public DRIDesignExpression getExpression() {
		return expression;
	}

	public void setExpression(DRIDesignExpression expression) {
		this.expression = expression;
	}

	public DRIDesignExpression getOrderByExpression() {
		return orderByExpression;
	}

	public void setOrderByExpression(DRIDesignExpression orderByExpression) {
		this.orderByExpression = orderByExpression;
	}

	public DRIDesignExpression getComparatorExpression() {
		return comparatorExpression;
	}

	public void setComparatorExpression(DRIDesignExpression comparatorExpression) {
		this.comparatorExpression = comparatorExpression;
	}

	public DRDesignCrosstabCellContent getHeader() {
		return header;
	}

	public void setHeader(DRDesignCrosstabCellContent header) {
		this.header = header;
	}

	public DRDesignCrosstabCellContent getTotalHeader() {
		return totalHeader;
	}

	public void setTotalHeader(DRDesignCrosstabCellContent totalHeader) {
		this.totalHeader = totalHeader;
	}
}