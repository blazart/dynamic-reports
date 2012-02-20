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

package net.sf.dynamicreports.report.base.component;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.dynamicreports.report.constant.VerticalCellComponentAlignment;
import net.sf.dynamicreports.report.definition.component.DRIList;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRList extends DRDimensionComponent implements DRIList {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private List<DRListCell> listCells;
	private ListType type;
	private Integer gap;
	private DRComponent backgroundComponent;

	public DRList() {
		this(ListType.HORIZONTAL);
	}

	public DRList(ListType type) {
		setType(type);
	}

	@Override
	protected void init() {
		super.init();
		this.listCells = new ArrayList<DRListCell>();
	}

	public List<DRListCell> getListCells() {
		return listCells;
	}

	public void addComponent(DRComponent component) {
		listCells.add(new DRListCell(component));
	}

	public void addCell(DRListCell cell) {
		Validate.notNull(cell, "cell must not be null");
		listCells.add(cell);
	}

	public void addComponent(HorizontalCellComponentAlignment horizontalAlignment, VerticalCellComponentAlignment verticalAlignment, DRComponent component) {
		listCells.add(new DRListCell(horizontalAlignment, verticalAlignment, component));
	}

	public void setType(ListType type) {
		Validate.notNull(type, "type must not be null");
		this.type = type;
	}

	public ListType getType() {
		return type;
	}

	public Integer getGap() {
		return gap;
	}

	public void setGap(Integer gap) {
		if (gap != null) {
			Validate.isTrue(gap >= 0, "gap must be >= 0");
		}
		this.gap = gap;
	}

	public DRComponent getBackgroundComponent() {
		return backgroundComponent;
	}

	public void setBackgroundComponent(DRComponent backgroundComponent) {
		this.backgroundComponent = backgroundComponent;
	}


}
