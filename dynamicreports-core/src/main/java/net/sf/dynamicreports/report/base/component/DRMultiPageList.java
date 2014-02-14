/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2014 Ricardo Mariaca
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

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.definition.component.DRIComponent;
import net.sf.dynamicreports.report.definition.component.DRIMultiPageList;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DRMultiPageList extends DRDimensionComponent implements DRIMultiPageList {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private List<DRIComponent> components;
	private SplitType splitType;

	public DRMultiPageList() {
		components = new ArrayList<DRIComponent>();
	}

	@Override
	public List<DRIComponent> getComponents() {
		return components;
	}

	public void addComponent(DRIComponent component) {
		components.add(component);
	}

	public void setComponents(List<DRIComponent> components) {
		this.components = components;
	}

	@Override
	public SplitType getSplitType() {
		return splitType;
	}

	public void setSplitType(SplitType splitType) {
		this.splitType = splitType;
	}

}
