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

package net.sf.dynamicreports.examples.crosstab;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.util.Calendar;
import java.util.Date;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class GroupExpressionCrosstabReport {

	public GroupExpressionCrosstabReport() {
		build();
	}

	private void build() {
		CrosstabRowGroupBuilder<String> rowGroup = ctab.rowGroup("state", String.class);

		CrosstabColumnGroupBuilder<Integer> columnYearGroup = ctab.columnGroup(new YearExpression());

		CrosstabColumnGroupBuilder<String> columnQuarterGroup = ctab.columnGroup(new QuarterExpression());

		CrosstabBuilder crosstab = ctab.crosstab()
		                               .setCellWidth(50)
		                               .headerCell(cmp.text("State / Date").setStyle(Templates.boldCenteredStyle))
		                               .rowGroups(rowGroup)
		                               .columnGroups(columnYearGroup, columnQuarterGroup)
		                               .measures(
		                              		ctab.measure("quantity", Integer.class, Calculation.SUM));

		try {
			report()
			  .fields(field("orderdate", Date.class))
			  .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
			  .setTemplate(Templates.reportTemplate)
			  .title(Templates.createTitleComponent("GroupExpressionCrosstab"))
			  .summary(crosstab)
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("state", "orderdate", "quantity");
		Calendar c = Calendar.getInstance();
		for (int i = 0; i < 700; i++) {
			Date date = c.getTime();
			dataSource.add("New York", date, (int) (Math.random() * 10) + 1);
			dataSource.add("Washington", date, (int) (Math.random() * 10) + 1);
			dataSource.add("Florida", date, (int) (Math.random() * 10) + 1);
			c.add(Calendar.DAY_OF_MONTH, -1);
		}
		return dataSource;
	}

	public static void main(String[] args) {
		new GroupExpressionCrosstabReport();
	}

	private class YearExpression extends AbstractSimpleExpression<Integer> {
		private static final long serialVersionUID = 1L;

		@Override
		public Integer evaluate(ReportParameters reportParameters) {
			Calendar c = Calendar.getInstance();
			c.setTime((Date) reportParameters.getValue("orderdate"));
			return c.get(Calendar.YEAR);
		}
	}

	private class QuarterExpression extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			Calendar c = Calendar.getInstance();
			c.setTime((Date) reportParameters.getValue("orderdate"));
			return "Q" + (c.get(Calendar.MONTH) / 3 + 1);
		}
	}
}