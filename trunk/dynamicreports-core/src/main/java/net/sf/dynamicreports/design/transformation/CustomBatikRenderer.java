/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2015 Ricardo Mariaca
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

package net.sf.dynamicreports.design.transformation;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.net.URL;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.renderers.BatikRenderer;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class CustomBatikRenderer extends BatikRenderer {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private Dimension dimension;

	public CustomBatikRenderer(URL svgURL, int width, int height) throws JRException {
		super(JRLoader.loadBytes(svgURL), null);
		this.dimension = new Dimension(width, height);
	}

	@Override
	public Dimension2D getDimension(JasperReportsContext jasperReportsContext) {
		return dimension;
	}

}
