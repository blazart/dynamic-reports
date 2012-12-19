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

package net.sf.dynamicreports.examples.chart;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class WaterfallBarChartReport {

	public WaterfallBarChartReport() {
		build();
	}

	private void build() {
		FontBuilder  boldFont = stl.fontArialBold().setFontSize(12);

		TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType());
		TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType());

		try {
			report()
				.setTemplate(Templates.reportTemplate)
				.columns(itemColumn, quantityColumn)
				.title(Templates.createTitleComponent("WaterfallBarChart"))
				.summary(
					cht.waterfallBarChart()
						.setTitle("Waterfall bar chart")
						.setTitleFont(boldFont)
						.setCategory(itemColumn)
						.series(cht.serie(quantityColumn))
						.setCategoryAxisFormat(
							cht.axisFormat()
								.setLabel("Item")))
						.pageFooter(Templates.footerComponent)
				.setDataSource(createDataSource())
				.show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("item", "quantity");
		dataSource.add("Book", 170);
		dataSource.add("Notebook", 90);
		dataSource.add("PDA", 120);
		dataSource.add("Total", 380);
		return dataSource;
	}

	public static void main(String[] args) {
		new WaterfallBarChartReport();
	}
}
