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

package net.sf.dynamicreports.examples.component;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.net.URL;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ComponentsReport {

	public ComponentsReport() {
		build();
	}

	private void build() {
		URL image = Templates.class.getResource("images/pda.png");

		try {
			report()
			  .setTemplate(template().setBarcodeHeight(50))
			  .setTextStyle(stl.style(stl.pen1Point()))
			  .title(
			  	Templates.createTitleComponent("Components"),
			  	components(
			  		"rectangle", cmp.rectangle(),
			  		"round rectangle", cmp.roundRectangle(10),
			  		"ellipse", cmp.ellipse()),
			  	components(
			  		"text field", cmp.text("text"),
			  		"image", cmp.image(image).setFixedDimension(30, 30),
			  		"line", cmp.line()),
			  	components(
			  		"boolean field", cmp.booleanField(true),
			  		"center horizontal", cmp.centerHorizontal(cmp.image(image).setFixedDimension(50, 50)),
			  		"center vertical", cmp.centerVertical(cmp.text("text").setFixedRows(1))),
			  		components(
			  		"text field", cmp.text("text"),
			  		"empty space", cmp.filler(),
			  		"text field", cmp.text("text")),
			  	cmp.text("text"),
			  	cmp.verticalGap(50),
			  	cmp.horizontalList(cmp.text("text"), cmp.horizontalGap(100), cmp.text("text")))
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private ComponentBuilder<?, ?> components(String label1, ComponentBuilder<?, ?> component1, String label2, ComponentBuilder<?, ?> component2, String label3, ComponentBuilder<?, ?> component3) {
		HorizontalListBuilder list = cmp.horizontalList()
			.setGap(10);
		list.add(component(label1, component1));
		list.add(component(label2, component2));
		list.add(component(label3, component3));
		return list;
	}

	private ComponentBuilder<?, ?> component(String label, ComponentBuilder<?, ?> component) {
		TextFieldBuilder<String> labelField = cmp.text(label)
			.setFixedRows(1)
			.setStyle(Templates.bold12CenteredStyle);
		return cmp.verticalList(labelField, component);
	}

	public static void main(String[] args) {
		new ComponentsReport();
	}
}