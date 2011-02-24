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
package net.sf.dynamicreports.design.base.style;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.design.definition.style.DRIDesignStyle;
import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignStyle extends DRDesignBaseStyle implements DRIDesignStyle {	
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private String name;
	private DRDesignStyle parentStyle;
	private List<DRDesignConditionalStyle> conditionalStyles;
	
	public DRDesignStyle() {
		this.name = ReportUtils.generateUniqueName("style");
		this.conditionalStyles = new ArrayList<DRDesignConditionalStyle>();
	}
	
	public String getName() {
		return name;
	}

	public DRDesignStyle getParentStyle() {
		return parentStyle;
	}

	public void setParentStyle(DRDesignStyle parentStyle) {
		this.parentStyle = parentStyle;
	}

	public List<DRDesignConditionalStyle> getConditionalStyles() {
		return conditionalStyles;
	}

	@SuppressWarnings("ucd")
	public void setConditionalStyles(List<DRDesignConditionalStyle> conditionalStyles) {
		this.conditionalStyles = conditionalStyles;
	}

	public void addConditionalStyle(DRDesignConditionalStyle conditionalStyle) {
		this.conditionalStyles.add(conditionalStyle);
	}	
}