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

package net.sf.dynamicreports.examples.complex.salescrosstab;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import net.sf.dynamicreports.report.builder.style.ConditionalStyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class SalesCrosstabDesign {
	private SalesCrosstabData data = new SalesCrosstabData();

	public JasperReportBuilder build() throws DRException {
		JasperReportBuilder report = report();

		CrosstabRowGroupBuilder<String> rowStateGroup = ctab.rowGroup("state", String.class)
			.setHeaderWidth(80);
		CrosstabRowGroupBuilder<String> rowItemGroup = ctab.rowGroup("item", String.class)
			.setHeaderWidth(80);

		CrosstabColumnGroupBuilder<Integer> columnYearGroup = ctab.columnGroup(new YearExpression());
		CrosstabColumnGroupBuilder<String> columnQuarterGroup = ctab.columnGroup(new QuarterExpression());

		CrosstabMeasureBuilder<Integer> quantityMeasure = ctab.measure("Quantity", "quantity", Integer.class, Calculation.SUM);
		CrosstabMeasureBuilder<BigDecimal> unitPriceMeasure = ctab.measure("Unit price", "unitprice", BigDecimal.class, Calculation.SUM);
		unitPriceMeasure.setPattern("#,###");

		rowStateGroup.orderBy(quantityMeasure);

		ConditionalStyleBuilder condition1 = stl.conditionalStyle(cnd.greater(unitPriceMeasure, 50000))
			.setForegroundColor(Color.GREEN);
		ConditionalStyleBuilder condition2 = stl.conditionalStyle(cnd.smaller(unitPriceMeasure, 300))
			.setForegroundColor(Color.RED);

		StyleBuilder unitPriceStyle = stl.style()
			.setBorder(stl.pen1Point().setLineColor(Color.BLACK))
			.conditionalStyles(condition1, condition2);
		unitPriceMeasure.setStyle(unitPriceStyle);

		CrosstabBuilder crosstab = ctab.crosstab()
			.setCellWidth(110)
			.headerCell(cmp.text("State / Date").setStyle(Templates.boldCenteredStyle))
			.rowGroups(
				rowStateGroup, rowItemGroup)
			.columnGroups(
				columnYearGroup, columnQuarterGroup)
			.measures(
				quantityMeasure, unitPriceMeasure);

		report
			.fields(field("orderdate", Date.class))
			.setPageFormat(PageType.A3, PageOrientation.LANDSCAPE)
			.setTemplate(Templates.reportTemplate)
			.title(
				Templates.createTitleComponent("SalesCrosstab"))
			.summary(crosstab)
			.pageFooter(
				Templates.footerComponent)
			.setDataSource(data.createDataSource());

		return report;
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

	public static void main(String[] args) {
		SalesCrosstabDesign design = new SalesCrosstabDesign();
		try {
			JasperReportBuilder report = design.build();
			report.show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
}