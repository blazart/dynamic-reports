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

package net.sf.dynamicreports.report.base;

import net.sf.dynamicreports.report.base.component.DRComponent;
import net.sf.dynamicreports.report.base.component.DRList;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.definition.DRIBand;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DRBand implements DRIBand {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private SplitType splitType;
	private DRList list;
	private DRIExpression<Boolean> printWhenExpression;

	public DRBand() {
		this.list = new DRList(ListType.VERTICAL);
	}

	@Override
	public SplitType getSplitType() {
		return splitType;
	}

	public void setSplitType(SplitType splitType) {
		this.splitType = splitType;
	}

	@Override
	public DRList getList() {
		return list;
	}

	public void addComponent(DRComponent component) {
		list.addComponent(component);
	}

	@Override
	public DRIExpression<Boolean> getPrintWhenExpression() {
		return printWhenExpression;
	}

	public void setPrintWhenExpression(DRIExpression<Boolean> printWhenExpression) {
		this.printWhenExpression = printWhenExpression;
	}
}
