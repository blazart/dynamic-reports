/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2014 Ricardo Mariaca
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

package net.sf.dynamicreports.examples.chartcustomization;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class SeriesBarChartReport {

	public SeriesBarChartReport() {
		build();
	}

	private void build() {
		FontBuilder  boldFont = stl.fontArialBold().setFontSize(12);

		TextColumnBuilder<String> stateColumn = col.column("State", "state", type.stringType());
		TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType());
		TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType());

		try {
			report()
				.setTemplate(Templates.reportTemplate)
				.columns(stateColumn, itemColumn, quantityColumn)
				.title(Templates.createTitleComponent("SeriesBarChart"))
				.summary(
					cht.barChart()
						.setTitle("Bar chart")
						.setTitleFont(boldFont)
						.setCategory(stateColumn)
						.series(
							cht.serie(quantityColumn).setSeries(itemColumn))
						.setCategoryAxisFormat(
							cht.axisFormat().setLabel("Item")))
				.pageFooter(Templates.footerComponent)
				.setDataSource(createDataSource())
				.show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("state", "item", "quantity");
		dataSource.add("New York", "Tablet", 170);
		dataSource.add("New York", "Laptop", 100);
		dataSource.add("New York", "Smartphone", 120);
		dataSource.add("Washington", "Tablet", 110);
		dataSource.add("Washington", "Laptop", 90);
		dataSource.add("Washington", "Smartphone", 160);
		return dataSource;
	}

	public static void main(String[] args) {
		new SeriesBarChartReport();
	}
}
