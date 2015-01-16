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

package net.sf.dynamicreports.examples.component;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class MapReport {

	public MapReport() {
		build();
	}

	private void build() {
		try {
			JasperHtmlExporterBuilder htmlExporter = export.htmlExporter("c:/report.html")
				.setImagesDirName("c:/images")
				.setOutputImagesToDir(true);

			report()
			  .setTemplate(template())
			  .title(
			  	Templates.createTitleComponent("Map"),
			  	cmp.map(40.7f, -74f, 12).setFixedHeight(750))
			  .toHtml(htmlExporter);
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MapReport();
	}
}