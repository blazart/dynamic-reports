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

package net.sf.dynamicreports.examples.datasource;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.chart.BarChartBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRXmlDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class XmlDatasourceReport {

	public XmlDatasourceReport() {
		build();
	}

	private void build() {
		try {
			JRXmlDataSource dataSource = new JRXmlDataSource(XmlDatasourceReport.class.getResourceAsStream("sales.xml"), "/sales/item");
			JRXmlDataSource chartDataSource = dataSource.dataSource("/sales/chart/item");

			FieldBuilder<Integer> idField = field("id", type.integerType())
				.setDescription("@id");
			FieldBuilder<String> itemField = field("item", type.stringType());
			FieldBuilder<Integer> quantityField = field("quantity", type.integerType());
			FieldBuilder<BigDecimal> unitPriceField = field("unitprice", type.bigDecimalType());

			BarChartBuilder barChart = cht.barChart()
				.setDataSource(chartDataSource)
				.setCategory(itemField)
				.series(
					cht.serie(quantityField).setLabel("Quantity"));

			report()
			  .setTemplate(Templates.reportTemplate)
			  .columns(
			  	col.column("Id", idField),
			  	col.column("Item", itemField),
			  	col.column("Quantity", quantityField),
			  	col.column("Unit price", unitPriceField))
			  .title(Templates.createTitleComponent("XmlDatasource"))
			  .summary(barChart)
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(dataSource)
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new XmlDatasourceReport();
	}
}