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

package net.sf.dynamicreports.report.base.component;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.component.DRIXyListCell;

import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DRXyListCell implements DRIXyListCell {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private Integer x;
	private Integer y;
	private DRComponent component;

	public DRXyListCell(Integer x, Integer y, DRComponent component) {
		Validate.notNull(x, "x must not be null");
		Validate.notNull(y, "y must not be null");
		Validate.notNull(component, "component must not be null");
		this.x = x;
		this.y = y;
		this.component = component;
	}

	@Override
	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	@Override
	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	@Override
	public DRComponent getComponent() {
		return component;
	}
}
