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

package net.sf.dynamicreports.examples.chart;

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
public class StackedBar3DChartReport {
		
	public StackedBar3DChartReport() {
		build();
	}
	
	private void build() {
		FontBuilder  boldFont = stl.fontArialBold().setFontSize(12);
		
		TextColumnBuilder<String>  itemColumn   = col.column("Item",    "item",   type.stringType());
		TextColumnBuilder<Integer> stock1Column = col.column("Stock 1", "stock1", type.integerType());
		TextColumnBuilder<Integer> stock2Column = col.column("Stock 2", "stock2", type.integerType());
		TextColumnBuilder<Integer> stock3Column = col.column("Stock 3", "stock3", type.integerType());
		
		try {
			report()
			  .setTemplate(Templates.reportTemplate)
			  .columns(itemColumn, stock1Column, stock2Column, stock3Column)
			  .title(Templates.createTitleComponent("StackedBar3DChart"))
			  .summary(
			  	cht.stackedBar3DChart()
			  	   .setTitle("Stacked bar 3D chart")
			  	   .setTitleFont(boldFont)
			  	   .setCategory(itemColumn)
			  	   .series(
			  	  		 cht.serie(stock1Column), cht.serie(stock2Column), cht.serie(stock3Column))
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
		DRDataSource dataSource = new DRDataSource("item", "stock1", "stock2", "stock3");		
		dataSource.add("Notebook", 90, 85, 40);
		dataSource.add("Book", 170, 100, 150);
		dataSource.add("PDA", 120, 80, 60);
		return dataSource;
	}
	
	public static void main(String[] args) {
		new StackedBar3DChartReport();
	}
}
