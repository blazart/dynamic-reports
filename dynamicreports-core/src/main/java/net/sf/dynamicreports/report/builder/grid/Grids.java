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

package net.sf.dynamicreports.report.builder.grid;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class Grids {

	//horizontal
	public static HorizontalColumnGridListBuilder horizontalColumnGridList() {
		return new HorizontalColumnGridListBuilder();
	}

	public static HorizontalColumnGridListBuilder horizontalColumnGridList(ColumnGridComponentBuilder ...components) {
		return new HorizontalColumnGridListBuilder().add(components);
	}

	public static HorizontalColumnGridListBuilder horizontalColumnGridList(HorizontalColumnGridListCellBuilder ...cells) {
		return new HorizontalColumnGridListBuilder().add(cells);
	}

	public static HorizontalColumnGridListCellBuilder hColumnGridListCell(ColumnGridComponentBuilder component) {
		Validate.notNull(component, "component must not be null");
		return new HorizontalColumnGridListCellBuilder(component);
	}

	//horizontal flow
	public static HorizontalColumnGridListBuilder horizontalFlowColumnGridList() {
		return new HorizontalFlowColumnGridListBuilder();
	}

	public static HorizontalColumnGridListBuilder horizontalFlowColumnGridList(ColumnGridComponentBuilder ...components) {
		return new HorizontalFlowColumnGridListBuilder().add(components);
	}

	public static HorizontalColumnGridListBuilder horizontalFlowColumnGridList(HorizontalColumnGridListCellBuilder ...cells) {
		return new HorizontalFlowColumnGridListBuilder().add(cells);
	}

	//vertical
	public static VerticalColumnGridListBuilder verticalColumnGridList() {
		return new VerticalColumnGridListBuilder();
	}

	public static VerticalColumnGridListBuilder verticalColumnGridList(ColumnGridComponentBuilder ...components) {
		return new VerticalColumnGridListBuilder().add(components);
	}

	public static VerticalColumnGridListBuilder verticalColumnGridList(VerticalColumnGridListCellBuilder ...cells) {
		return new VerticalColumnGridListBuilder().add(cells);
	}

	public static VerticalColumnGridListCellBuilder vColumnGridListCell(ColumnGridComponentBuilder component) {
		Validate.notNull(component, "component must not be null");
		return new VerticalColumnGridListCellBuilder(component);
	}

	//title group
	public static ColumnTitleGroupBuilder titleGroup() {
		return new ColumnTitleGroupBuilder();
	}

	public static ColumnTitleGroupBuilder titleGroup(ColumnGridComponentBuilder ...components) {
		return new ColumnTitleGroupBuilder().add(components);
	}

	public static ColumnTitleGroupBuilder titleGroup(String title, ColumnGridComponentBuilder ...components) {
		return titleGroup(components).setTitle(title);
	}
}
