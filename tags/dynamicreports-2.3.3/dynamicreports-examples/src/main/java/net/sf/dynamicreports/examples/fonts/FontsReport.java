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

package net.sf.dynamicreports.examples.fonts;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 *
 * This report is working properly only when the font "FreeUniversal" is registered.
 * The font is registered in these files: customfonts.xml and jasperreports_extension.properties. The files are located in the root of the classpath.
 */
public class FontsReport {

	public FontsReport() {
		build();
	}

	private void build() {
		StyleBuilder plainStyle = stl.style()
			.setFontName("FreeUniversal");
		StyleBuilder boldStyle = stl.style(plainStyle)
			.bold();
		StyleBuilder italicStyle = stl.style(plainStyle)
			.italic();
		StyleBuilder boldItalicStyle = stl.style(plainStyle)
			.boldItalic();

		try {
			report()
			  .title(
			  	Templates.createTitleComponent("Fonts"),
			  	cmp.text("FreeUniversal font - plain").setStyle(plainStyle),
			  	cmp.text("FreeUniversal font - bold").setStyle(boldStyle),
			  	cmp.text("FreeUniversal font - italic").setStyle(italicStyle),
			  	cmp.text("FreeUniversal font - bolditalic").setStyle(boldItalicStyle))
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new FontsReport();
	}
}