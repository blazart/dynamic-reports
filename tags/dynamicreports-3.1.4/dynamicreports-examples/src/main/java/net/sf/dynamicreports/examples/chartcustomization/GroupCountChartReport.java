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

package net.sf.dynamicreports.examples.chartcustomization;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.VariableBuilder;
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.Evaluation;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class GroupCountChartReport {

	public GroupCountChartReport() {
		build();
	}

	private void build() {
		TextColumnBuilder<String>  countryColumn  = col.column("Country",  "country",  type.stringType());
		TextColumnBuilder<String>  itemColumn     = col.column("Item",     "item",     type.stringType());
		TextColumnBuilder<Integer> quantityColumn = col.column("Quantity", "quantity", type.integerType());

		Bar3DChartBuilder chart1 = cht.bar3DChart()
		                              .setFixedHeight(180)
		                              .setCategory(countryColumn)
		                              .series(cht.serie(exp.number(1)).setLabel("Items (group count)"))
		                              .setCategoryAxisFormat(
		                              	 cht.axisFormat()
		                              	   .setLabel("Country"));

		VariableBuilder<Integer> itemVariable = variable(itemColumn, Calculation.DISTINCT_COUNT);
		itemVariable.setResetType(Evaluation.FIRST_GROUP);

		Bar3DChartBuilder chart2 = cht.bar3DChart()
                                  .setFixedHeight(180)
                                  .setCategory(countryColumn)
                                  .series(cht.serie(itemVariable).setLabel("Items (group distinct count)"))
                                  .setCategoryAxisFormat(
                                  	 cht.axisFormat()
                                  	   .setLabel("Country"));

		try {
			report()
			  .setTemplate(Templates.reportTemplate)
			  .columns(countryColumn, itemColumn, quantityColumn)
			  .title(Templates.createTitleComponent("GroupCountChart"))
			  .groupBy(grp.group(countryColumn))
			  .summary(cmp.horizontalList(chart1, chart2))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("country", "item", "quantity");
		dataSource.add("USA", "Book", 170);
		dataSource.add("USA", "Book", 80);
		dataSource.add("USA", "Notebook", 90);
		dataSource.add("USA", "PDA", 120);
		dataSource.add("Canada", "Book", 120);
		dataSource.add("Canada", "Notebook", 150);
		return dataSource;
	}

	public static void main(String[] args) {
		new GroupCountChartReport();
	}
}
