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

package net.sf.dynamicreports.jasper.base.export;

import net.sf.dynamicreports.jasper.definition.export.JasperIDocxExporter;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperDocxExporter extends AbstractJasperExporter implements JasperIDocxExporter {	
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private Boolean framesAsNestedTables;
	private Boolean flexibleRowHeight;
	
	@Override
	public Boolean getFramesAsNestedTables() {
		return framesAsNestedTables;
	}
	
	public void setFramesAsNestedTables(Boolean framesAsNestedTables) {
		this.framesAsNestedTables = framesAsNestedTables;
	}
	
	@Override
	public Boolean getFlexibleRowHeight() {
		return flexibleRowHeight;
	}
	
	public void setFlexibleRowHeight(Boolean flexibleRowHeight) {
		this.flexibleRowHeight = flexibleRowHeight;
	}	
}
