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
package net.sf.dynamicreports.report.base.style;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.style.DRIStyle;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRStyle extends DRBaseStyle implements DRIStyle {	
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private DRStyle parentStyle;
	private List<DRConditionalStyle> conditionalStyles;
	
	@Override
	protected void init() {		
		super.init();
		conditionalStyles = new ArrayList<DRConditionalStyle>();
	}

	public DRStyle getParentStyle() {
		return parentStyle;
	}

	public void setParentStyle(DRStyle parentStyle) {
		this.parentStyle = parentStyle;
	}

	public List<DRConditionalStyle> getConditionalStyles() {
		return conditionalStyles;
	}

	@SuppressWarnings("ucd")
	public void setConditionalStyles(List<DRConditionalStyle> conditionalStyles) {
		Validate.notNull(conditionalStyles, "conditionalStyles must not be null");
		Validate.noNullElements(conditionalStyles, "conditionalStyles must not contains null conditionalStyle");
		this.conditionalStyles = conditionalStyles;
	}

	public void addConditionalStyle(DRConditionalStyle conditionalStyle) {
		Validate.notNull(conditionalStyle, "conditionalStyle must not be null");
		this.conditionalStyles.add(conditionalStyle);
	}
}
