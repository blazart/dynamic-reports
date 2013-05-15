/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2013 Ricardo Mariaca
 * http://www.dynamicreports.org
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

package net.sf.dynamicreports.examples.genericelement.openflashchart;

import net.sf.jasperreports.engine.JRGenericPrintElement;
import net.sf.jasperreports.engine.export.JRHtmlExporterContext;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class OpenFlashChartXhtmlHandler extends OpenFlashChartHtmlHandler {

	@Override
	public String getHtmlFragment(JRHtmlExporterContext exporterContext, JRGenericPrintElement element)	{
		StringBuilder result = new StringBuilder();
		result.append("<div style=\"position: absolute; left: ");
		result.append(element.getX() + "px; top: ");
		result.append(element.getY() + "px; width: ");
		result.append(element.getWidth() + "px; height: ");
		result.append(element.getHeight() + "px;\">");
		result.append(super.getHtmlFragment(exporterContext, element));
		result.append("</div>");
		return result.toString();
	}
}
