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

package net.sf.dynamicreports.report.builder.grid;

import net.sf.dynamicreports.report.base.grid.DRColumnGridList;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.ListType;

import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class HorizontalColumnGridListBuilder extends AbstractBuilder<HorizontalColumnGridListBuilder, DRColumnGridList> implements ColumnGridComponentBuilder {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private DRColumnGridList row;

	protected HorizontalColumnGridListBuilder() {
		super(new DRColumnGridList(ListType.VERTICAL));
		init();
	}

	protected void init() {
		newRow();
	}

	public HorizontalColumnGridListBuilder add(ColumnGridComponentBuilder ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for (ColumnGridComponentBuilder component : components) {
			row.addComponent(component.build());
		}
		return this;
	}

	public HorizontalColumnGridListBuilder add(HorizontalColumnGridListCellBuilder ...cells) {
		Validate.notNull(cells, "cells must not be null");
		Validate.noNullElements(cells, "cells must not contains null cell");
		for (HorizontalColumnGridListCellBuilder cell : cells) {
			row.addCell(cell.build());
		}
		return this;
	}

	public HorizontalColumnGridListBuilder newRow() {
		return newRow(ListType.HORIZONTAL);
	}

	public HorizontalColumnGridListBuilder newFlowRow() {
		return newRow(ListType.HORIZONTAL_FLOW);
	}

	private HorizontalColumnGridListBuilder newRow(ListType listType) {
		row = new DRColumnGridList(listType);
		getObject().addComponent(row);
		return this;
	}

	public HorizontalColumnGridListBuilder setGap(int gap) {
		row.setGap(gap);
		return this;
	}

	public HorizontalColumnGridListBuilder setVerticalGap(int gap) {
		getObject().setGap(gap);
		return this;
	}

	public DRColumnGridList getColumnGridList() {
		return build();
	}
}
