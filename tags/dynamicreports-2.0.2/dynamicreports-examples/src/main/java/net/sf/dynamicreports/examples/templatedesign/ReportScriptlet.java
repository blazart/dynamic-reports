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

package net.sf.dynamicreports.examples.templatedesign;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.util.Map;

import net.sf.dynamicreports.examples.DataSource;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.JasperReport;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ReportScriptlet extends JRDefaultScriptlet {
	private JasperReportBuilder dynamicSubreport;

	@Override
	public void beforeDetailEval() throws JRScriptletException {
		super.beforeDetailEval();
		dynamicSubreport = report();
		dynamicSubreport
		  .setTemplate(Templates.reportTemplate)
		  .setPageFormat(515, PageType.A4.getHeight(), PageOrientation.PORTRAIT)
		  .setPageMargin(margin(0))
		  .columns(
		  	col.column("Item",        "item",      type.stringType()),
		  	col.column("Quantity",    "quantity",  type.integerType()),
		  	col.column("Unit price",  "unitprice", type.integerType()))
		  .title(cmp.text("Dynamic subreport").setStyle(Templates.bold12CenteredStyle));
	}

	private JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("item", "quantity", "unitprice");
		for (int i = 0; i < 5; i++) {
			dataSource.add("Book", (int) (Math.random() * 10) + 1, (int) (Math.random() * 100) + 1);
		}
		return dataSource;
	}

	public JasperReport getDynamicSubreport() throws DRException {
		return dynamicSubreport.toJasperReport();
	}

	public Map<String, Object> getDynamicSubreportParameters() throws DRException {
		return dynamicSubreport.getJasperParameters();
	}

	public JRDataSource getDynamicSubreportDataSource() {
		return createDataSource();
	}
}
