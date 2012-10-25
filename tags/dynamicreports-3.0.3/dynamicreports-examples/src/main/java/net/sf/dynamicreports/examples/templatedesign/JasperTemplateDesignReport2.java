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

package net.sf.dynamicreports.examples.templatedesign;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.InputStream;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperTemplateDesignReport2 {

	public JasperTemplateDesignReport2() {
		build();
	}

	private void build() {
		InputStream is = JasperTemplateDesignReport2.class.getResourceAsStream("templatedesign2.jrxml");

		try {
			report()
			  .setTemplate(Templates.reportTemplate)
			  .setTemplateDesign(is)
			  .columns(
			  	col.column("Item",       "item",      type.stringType()),
			  	col.column("Quantity",   "quantity",  type.integerType()),
			  	col.column("Unit price", "unitprice", type.integerType()))
			  .title(
			  	Templates.createTitleComponent("JasperTemplateDesign2"),
			  	cmp.subreport(createDynamicSubreport()),
			  	cmp.subreport(createStaticSubreport()),
			  	cmp.subreport(createStaticAndDynamicSubreport()))
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private JasperReportBuilder createDynamicSubreport() {
		return createSubreport("Subreport - dynamic design");
	}

	private JasperReportBuilder createStaticAndDynamicSubreport() throws DRException {
		InputStream is = JasperTemplateDesignReport2.class.getResourceAsStream("subreporttemplatedesign.jrxml");
		JasperReportBuilder report = createSubreport("Subreport - static and dynamic design");
		report.setTemplateDesign(is);
		return report;
	}

	private JasperReportBuilder createSubreport(String title) {
		StyleBuilder style = stl.style()
		                        .setHorizontalAlignment(HorizontalAlignment.CENTER)
		                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
		                        .setBorder(stl.pen1Point());

		JasperReportBuilder report = report();
		report
		  .setTemplate(Templates.reportTemplate)
		  .title(
		  	cmp.horizontalList(
		  		cmp.gap(30, 47),
		  		cmp.text(title).setStyle(style),
		  		cmp.gap(30, 47)));

		return report;
	}

	private JasperReport createStaticSubreport() throws JRException {
		InputStream is = JasperTemplateDesignReport2.class.getResourceAsStream("subreport.jrxml");
		return JasperCompileManager.compileReport(is);
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("item", "quantity", "unitprice");
		for (int i = 0; i < 5; i++) {
			dataSource.add("Book", (int) (Math.random() * 10) + 1, (int) (Math.random() * 100) + 1);
		}
		return dataSource;
	}

	public static void main(String[] args) {
		new JasperTemplateDesignReport2();
	}
}