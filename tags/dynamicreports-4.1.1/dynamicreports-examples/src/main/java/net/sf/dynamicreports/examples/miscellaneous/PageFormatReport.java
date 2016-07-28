/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2016 Ricardo Mariaca
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

package net.sf.dynamicreports.examples.miscellaneous;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class PageFormatReport {
	
	public PageFormatReport() {
		build();
	}
	
	private void build() {
		TextColumnBuilder<String>     itemColumn     = col.column("Item",       "item",      type.stringType());
		TextColumnBuilder<Integer>    quantityColumn = col.column("Quantity",   "quantity",  type.integerType());
		TextColumnBuilder<BigDecimal> priceColumn    = col.column("Unit price", "unitprice", type.bigDecimalType());
		
		try {
			report()
			  .setTemplate(Templates.reportTemplate)
			  .setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
			  .setPageColumnsPerPage(3)
			  .setPageColumnSpace(15)
			  .setPageMargin(margin(20))
			  .columns(
			  	itemColumn, quantityColumn, priceColumn)
			  .title(Templates.createTitleComponent("PageFormat"))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
	
	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("item", "quantity", "unitprice");
		for (int i = 0; i < 40; i++) {
			dataSource.add("Book", (int) (Math.random() * 10) + 1, new BigDecimal(Math.random() * 100 + 1));
		}		
		return dataSource;
	}
	
	public static void main(String[] args) {
		new PageFormatReport();
	}
}