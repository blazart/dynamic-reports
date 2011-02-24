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
package net.sf.dynamicreports.design.base.component;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.design.definition.component.DRIDesignList;
import net.sf.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.dynamicreports.report.constant.VerticalCellComponentAlignment;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignList extends DRDesignComponent implements DRIDesignList {
	private List<DRDesignListCell> listCells;
	private List<DRDesignComponent> components;
	private ListType type;
	private int gap;
	
	public DRDesignList() {
		this(ListType.HORIZONTAL);
	}

	public DRDesignList(ListType type) {
		super("list");
		this.type = type;
	}
	
	@Override
	protected void init() {
		super.init();
		this.listCells = new ArrayList<DRDesignListCell>();
		this.components = new ArrayList<DRDesignComponent>();
	}
	
	public List<DRDesignComponent> getComponents() {
		return components;
	}
	
	public void addComponent(DRDesignComponent component) {
		components.add(component);
		listCells.add(new DRDesignListCell(component));
	}

	public void addComponent(int index, DRDesignComponent component) {
		components.add(index, component);
		listCells.add(index, new DRDesignListCell(component));
	}
	
	public void addComponent(int index, HorizontalCellComponentAlignment horizontalAlignment, VerticalCellComponentAlignment verticalAlignment, DRDesignComponent component) {
		components.add(index, component);
		listCells.add(index, new DRDesignListCell(horizontalAlignment, verticalAlignment, component));
	}
	
	public void addComponent(HorizontalCellComponentAlignment horizontalAlignment, VerticalCellComponentAlignment verticalAlignment, DRDesignComponent component) {
		components.add(component);
		listCells.add(new DRDesignListCell(horizontalAlignment, verticalAlignment, component));
	}
	
	public List<DRDesignListCell> getListCells() {
		return listCells;
	}
	
	public boolean isEmpty() {
		return components.isEmpty();
	}
	
	public void setType(ListType type) {
		this.type = type;
	}
	
	public ListType getType() {
		return type;
	}

	public int getGap() {
		return gap;
	}

	public void setGap(int gap) {
		this.gap = gap;
	}
}