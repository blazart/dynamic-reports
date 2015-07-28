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

package net.sf.dynamicreports.report.base.component;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import net.sf.dynamicreports.report.constant.VerticalCellComponentAlignment;
import net.sf.dynamicreports.report.definition.component.DRIListCell;

import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DRListCell implements DRIListCell {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private HorizontalCellComponentAlignment horizontalAlignment;
	private VerticalCellComponentAlignment verticalAlignment;
	private DRComponent component;

	public DRListCell(DRComponent component) {
		Validate.notNull(component, "component must not be null");
		this.component = component;
	}

	public DRListCell(HorizontalCellComponentAlignment horizontalAlignment, VerticalCellComponentAlignment verticalAlignment, DRComponent component) {
		this(component);
		this.horizontalAlignment = horizontalAlignment;
		this.verticalAlignment = verticalAlignment;
	}

	public void setHorizontalAlignment(HorizontalCellComponentAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	@Override
	public HorizontalCellComponentAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public void setVerticalAlignment(VerticalCellComponentAlignment verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	@Override
	public VerticalCellComponentAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	@Override
	public DRComponent getComponent() {
		return component;
	}
}
