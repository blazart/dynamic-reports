/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2015 Ricardo Mariaca
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

package net.sf.dynamicreports.examples.subreport;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.InputStream;
import java.math.BigDecimal;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DetailJasperSubreport {

	public DetailJasperSubreport() {
		build();
	}

	private void build() {
		try {
			SubreportBuilder subreport = cmp.subreport(getJasperSubreport())
			                                .setDataSource(new SubreportDataSourceExpression());

			report()
			  .title(Templates.createTitleComponent("DetailJasperSubreport"))
			  .detail(
			  	subreport,
			  	cmp.verticalGap(20))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		return new JREmptyDataSource(3);
	}

	private JasperReport getJasperSubreport() throws JRException {
		InputStream is = DetailJasperSubreport.class.getResourceAsStream("subreport.jrxml");
		return JasperCompileManager.compileReport(is);
	}

	private class SubreportDataSourceExpression extends AbstractSimpleExpression<JRDataSource> {
		private static final long serialVersionUID = 1L;

		@Override
		public JRDataSource evaluate(ReportParameters reportParameters) {
			DRDataSource dataSource = new DRDataSource("item", "quantity", "unitprice");
			for (int i = 0; i < 5; i++) {
				dataSource.add("Book", (int) (Math.random() * 10) + 1, new BigDecimal(Math.random() * 100 + 1));
			}
			return dataSource;
		}
	}

	public static void main(String[] args) {
		new DetailJasperSubreport();
	}
}