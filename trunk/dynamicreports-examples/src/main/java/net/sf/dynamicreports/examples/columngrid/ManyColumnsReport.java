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

package net.sf.dynamicreports.examples.columngrid;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;
import java.util.Date;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class ManyColumnsReport {

	public ManyColumnsReport() {
		build();
	}

	private void build() {
		StyleBuilder textStyle = stl.style(Templates.columnStyle)
		                            .setBorder(stl.pen1Point());

		try {
			report()
			  .setTemplate(Templates.reportTemplate)
			  .setColumnStyle(textStyle)
			  .columnGrid(ListType.HORIZONTAL_FLOW)
			  .columns(
			  	col.column("ID",          "id",        type.integerType()),
			  	col.column("Item",        "item",      type.stringType()),
			  	col.column("Quantity",    "quantity",  type.integerType()),
			  	col.column("Unit price",  "unitprice", type.bigDecimalType()),
			  	col.column("Order date",  "orderdate", type.dateType()),
			  	col.column("Order date",  "orderdate", type.dateYearToFractionType()),
			  	col.column("Order year",  "orderdate", type.dateYearType()),
			  	col.column("Order month", "orderdate", type.dateMonthType()),
			  	col.column("Order day",   "orderdate", type.dateDayType()))
			  .title(Templates.createTitleComponent("ManyColumns"))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("id", "item", "orderdate", "quantity", "unitprice");
		dataSource.add(5, "Notebook", new Date(), 1, new BigDecimal(500));
		dataSource.add(8, "Book", new Date(), 7, new BigDecimal(300));
		dataSource.add(15, "PDA", new Date(), 2, new BigDecimal(250));
		return dataSource;
	}

	public static void main(String[] args) {
		new ManyColumnsReport();
	}
}