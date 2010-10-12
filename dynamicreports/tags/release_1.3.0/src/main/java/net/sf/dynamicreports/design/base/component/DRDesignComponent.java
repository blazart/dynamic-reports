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

import net.sf.dynamicreports.design.base.style.DRDesignStyle;
import net.sf.dynamicreports.design.definition.component.DRIDesignComponent;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class DRDesignComponent implements DRIDesignComponent {	
	private String name;
	private String uniqueName;
	private DRDesignStyle style;
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;
	private DRIDesignSimpleExpression printWhenExpression;	

	protected DRDesignComponent(String name) {
		this.name = name;
		this.uniqueName = name;
		init();
	}
	
	protected void init() {
	}
		
	public String getName() {
		return name;
	}

	public String getUniqueName() {
		return uniqueName;
	}
	
	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}
	
	public DRDesignStyle getStyle() {
		return style;
	}
	
	public void setStyle(DRDesignStyle style) {
		this.style = style;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}
	
	public Integer getWidth() {
		return width;
	}
	
	public void setWidth(Integer width) {
		this.width = width;
	}
	
	public Integer getHeight() {
		return height;
	}
	
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	public DRIDesignSimpleExpression getPrintWhenExpression() {
		return printWhenExpression;
	}
	
	public void setPrintWhenExpression(DRIDesignSimpleExpression printWhenExpression) {
		this.printWhenExpression = printWhenExpression;
	}
}