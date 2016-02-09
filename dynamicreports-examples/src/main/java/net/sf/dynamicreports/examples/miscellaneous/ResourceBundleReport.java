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
import java.util.List;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.expression.AbstractComplexExpression;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class ResourceBundleReport {

	public ResourceBundleReport() {
		build();
	}

	private void build() {
		try {
			TextColumnBuilder<String>     itemColumn     = col.column("item",      type.stringType())
			                                                  .setTitle(exp.message("item_title"));
			TextColumnBuilder<Integer>    quantityColumn = col.column("quantity",  type.integerType())
			                                                  .setTitle(exp.message("quantity_title"));
			TextColumnBuilder<BigDecimal> priceColumn    = col.column("unitprice", type.bigDecimalType())
			                                                  .setTitle(exp.message("unitprice_title"));

			TextFieldBuilder<String> title = cmp.text(exp.message("report_title"))
			                                     .setStyle(Templates.bold12CenteredStyle);
			TextFieldBuilder<String> subtitle = cmp.text(new SubtitleExpression())
			                                       .setStyle(Templates.bold12CenteredStyle);

			report()
			  .setTemplate(Templates.reportTemplate)
			  .setResourceBundle(ResourceBundleReport.class.getName())
			  .columns(
			  	itemColumn, quantityColumn, priceColumn)
			  .title(
			  	Templates.createTitleComponent("ResourceBundle"),
			  	title, subtitle)
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("item", "quantity", "unitprice");
		for (int i = 0; i < 10; i++) {
			dataSource.add("Book", (int) (Math.random() * 10) + 1, new BigDecimal(Math.random() * 100 + 1));
		}
		return dataSource;
	}

	public static void main(String[] args) {
		new ResourceBundleReport();
	}

	public class SubtitleExpression extends AbstractComplexExpression<String> {
		private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

		public SubtitleExpression() {
			addExpression(variable("quantity", Integer.class, Calculation.LOWEST));
			addExpression(variable("quantity", Integer.class, Calculation.HIGHEST));
		}

		@Override
		public String evaluate(List<?> values, ReportParameters reportParameters) {
			return reportParameters.getMessage("report_subtitle", new Object[]{values.get(0), values.get(1)});
		}
	}
}