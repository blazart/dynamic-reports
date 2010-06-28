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
package net.sf.dynamicreports.report.builder.component;

import net.sf.dynamicreports.report.base.component.DRList;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.ListType;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class VerticalListBuilder extends ComponentBuilder<VerticalListBuilder, DRList> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
		
	protected VerticalListBuilder() {
		super(new DRList(ListType.VERTICAL));
	}
	
	public VerticalListBuilder add(ComponentBuilder<?, ?> ...components) {
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for (ComponentBuilder<?, ?> component : components) {
			getObject().addComponent(component.getComponent());
		}
		return this;
	}

	public VerticalListBuilder add(Integer gap, ComponentBuilder<?, ?> ...components) {
		Validate.notNull(components, "components must not be null");
		for (ComponentBuilder<?, ?> component : components) {
			add(Components.vListCell(Components.filler().setHeight(gap)).heightFixed());
			add(component);
		}
		return this;
	}
	
	public VerticalListBuilder add(VerticalListCellBuilder ...cells) {
		Validate.notNull(cells, "cells must not be null");
		Validate.noNullElements(cells, "cells must not contains null cell");
		for (VerticalListCellBuilder cell : cells) {
			getObject().addCell(cell.build());
		}
		return this;
	}
	
	public VerticalListBuilder add(Integer gap, VerticalListCellBuilder ...cells) {
		Validate.notNull(cells, "cells must not be null");
		for (VerticalListCellBuilder cell : cells) {
			add(Components.vListCell(Components.filler().setHeight(gap)).heightFixed(), cell);
		}
		return this;
	}
	
	public VerticalListBuilder setGap(Integer gap) {
		getObject().setGap(gap);
		return this;
	}
	
	public DRList getList() {
		return build();
	}
}