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
package net.sf.dynamicreports.design.transformation;

import net.sf.dynamicreports.design.base.DRDesignMargin;
import net.sf.dynamicreports.design.base.DRDesignPage;
import net.sf.dynamicreports.report.definition.DRIMargin;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class PageTransform {
	private TemplateTransform templateTransform;
	private DRDesignPage page;
	
	public PageTransform(DesignTransformAccessor accessor) {		
		this.templateTransform = accessor.getTemplateTransform();
		this.page = new DRDesignPage();
	}
	
	public void transform() {
		this.page.setWidth(templateTransform.getPageWidth());
		this.page.setHeight(templateTransform.getPageHeight());
		this.page.setOrientation(templateTransform.getPageOrientation());
		this.page.setMargin(margin(templateTransform.getPageMargin()));
		this.page.setColumnsPerPage(templateTransform.getPageColumnsPerPage());
		this.page.setColumnSpace(templateTransform.getPageColumnSpace());		
		this.page.setColumnWidth((this.page.getWidth() - this.page.getMargin().getLeft() - this.page.getMargin().getRight() - this.page.getColumnSpace() * (this.page.getColumnsPerPage() - 1)) / this.page.getColumnsPerPage());
	}
	
	private DRDesignMargin margin(DRIMargin margin) {
		DRDesignMargin designMargin = new DRDesignMargin();
		designMargin.setTop(margin.getTop());
		designMargin.setLeft(margin.getLeft());
		designMargin.setBottom(margin.getBottom());
		designMargin.setRight(margin.getRight());
		return designMargin;
	}
	
	public DRDesignPage getPage() {
		return page;
	}	
}
