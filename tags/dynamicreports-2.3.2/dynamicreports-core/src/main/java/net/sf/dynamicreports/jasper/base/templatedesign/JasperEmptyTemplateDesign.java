/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2011 Ricardo Mariaca
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

package net.sf.dynamicreports.jasper.base.templatedesign;

import net.sf.dynamicreports.report.base.AbstractTemplateDesign;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperEmptyTemplateDesign extends AbstractTemplateDesign<JasperDesign> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	@Override
	public JasperDesign getDesign() throws DRException {
		JasperDesign design = new JasperDesign();
		design.setName("Report");
		return design;
	}
}
