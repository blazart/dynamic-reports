/* Dynamic reports - Free Java reporting library for creating reports dynamically
 *
 * (C) Copyright 2010 Ricardo Mariaca
 *
 * http://dynamicreports.sourceforge.net
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 3 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA. 
 */
package net.sf.dynamicreports.report.builder.grid;

import net.sf.dynamicreports.report.base.grid.DRColumnGridList;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.ListType;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class VerticalColumnGridListBuilder extends AbstractBuilder<VerticalColumnGridListBuilder, DRColumnGridList> implements ColumnGridComponentBuilder {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
		
	protected VerticalColumnGridListBuilder() {
		super(new DRColumnGridList(ListType.VERTICAL));
	}
	
	public VerticalColumnGridListBuilder add(ColumnGridComponentBuilder ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for (ColumnGridComponentBuilder component : components) {
			getObject().addComponent(component.build());
		}
		return this;
	}
	
	public VerticalColumnGridListBuilder add(VerticalColumnGridListCellBuilder ...cells) {
		Validate.notNull(cells, "cells must not be null");
		Validate.noNullElements(cells, "cells must not contains null cell");
		for (VerticalColumnGridListCellBuilder cell : cells) {
			getObject().addCell(cell.build());
		}
		return this;
	}
	
	public VerticalColumnGridListBuilder setGap(int gap) {
		getObject().setGap(gap);
		return this;
	}
	
	public DRColumnGridList getColumnGridList() {
		return build();
	}
}
