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

package net.sf.dynamicreports.examples.complex.salestableofcontents;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.util.List;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.HyperLinkBuilder;
import net.sf.dynamicreports.report.builder.VariableBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.expression.AbstractComplexExpression;
import net.sf.dynamicreports.report.builder.group.CustomGroupBuilder;
import net.sf.dynamicreports.report.builder.style.ConditionalStyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizer;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.Evaluation;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.HyperLinkType;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class SalesTableOfContentsDesign {
	private SalesTableOfContentsData data = new SalesTableOfContentsData();

	public JasperReportBuilder build() throws DRException {
		JasperReportBuilder report = report();

		TextColumnBuilder<String> countryColumn = col.column("Country", "country", type.stringType());
		TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType());

		StyleBuilder headingToc1Style = stl.style(Templates.rootStyle)
			.italic();

		CustomTableOfContentsCustomizer tableOfContentsCustomizer = new CustomTableOfContentsCustomizer();
		tableOfContentsCustomizer.setHeadingStyle(1, headingToc1Style);
		tableOfContentsCustomizer.setTextFixedWidth(100);
		tableOfContentsCustomizer.setPageIndexFixedWidth(30);

		report
			.setPageFormat(PageType.A5, PageOrientation.LANDSCAPE)
			.setTemplate(Templates.reportTemplate)
			.setTableOfContents(tableOfContentsCustomizer)
			.columns(
				countryColumn,
				itemColumn,
				col.column("Order date", "orderdate", type.dateType()),
				col.column("Quantity",   "quantity",  type.integerType()),
				col.column("Unit price", "unitprice", type.bigDecimalType()))
			.groupBy(countryColumn, itemColumn)
			.title(
				Templates.createTitleComponent("SalesTableOfContents"))
			.pageFooter(
				Templates.footerComponent)
			.setDataSource(data.createDataSource());

		return report;
	}

	private class CustomTableOfContentsCustomizer extends TableOfContentsCustomizer {
		private static final long serialVersionUID = 1L;

		@Override
		public void customize() {
			super.customize();

			CustomGroupBuilder countryGroup = grp.group(new CountryExpression(textField))
				.setHeaderLayout(GroupHeaderLayout.EMPTY)
				.header(countryHeadingComponent())
				.footer(cmp.verticalGap(5));

			report
				.setPageColumnsPerPage(2)
				.setPageColumnSpace(10)
				.groupBy(countryGroup);
		}

		private ComponentBuilder<?, ?> countryHeadingComponent() {
			HorizontalListBuilder headingComponent = cmp.horizontalList();

			HyperLinkBuilder countryReferenceHyperLink = hyperLink();
			countryReferenceHyperLink.setAnchor(new CountryExpression(referenceField));
			countryReferenceHyperLink.setType(HyperLinkType.LOCAL_ANCHOR);

			StyleBuilder style = stl.style(Templates.rootStyle)
				.setFontSize(12)
				.bold()
				.setBackgroundColor(Color.LIGHT_GRAY);

			TextFieldBuilder<String> textComponent = cmp.text(textField)
  			.setHyperLink(countryReferenceHyperLink)
  			.setStyle(style);
			headingComponent.add(textComponent);

			TextFieldBuilder<String> pageIndexComponent = cmp.text(new CountryHeadingExpression())
				.setHyperLink(countryReferenceHyperLink)
				.setStyle(style)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT);
			headingComponent.add(pageIndexComponent);

			return headingComponent;
		}

		@Override
		protected ComponentBuilder<?, ?> headingComponent(int level) {
			if (level == 0) {
				return cmp.filler();
			}

			ComponentBuilder<?, ?> headingComponent = super.headingComponent(level);

			ConditionalStyleBuilder conditionalStyle = stl.conditionalStyle(exp.printInOddRow())
				.setBackgroundColor(new Color(245, 245, 245));
			StyleBuilder rowStyle = stl.style()
				.conditionalStyles(conditionalStyle);
			headingComponent.setStyle(rowStyle);

			return headingComponent;
		}

		private class CountryExpression extends AbstractComplexExpression<String> {
			private static final long serialVersionUID = 1L;

			private String value;

			private CountryExpression(FieldBuilder<String> field) {
				addExpression(levelField);
				addExpression(field);
			}

			@Override
			public String evaluate(List<?> values, ReportParameters reportParameters) {
				int level = (Integer) values.get(0);
				if (level == 0) {
					value = (String) values.get(1);
				}
				return value;
			}
		}

		private class CountryHeadingExpression extends AbstractComplexExpression<String> {
			private static final long serialVersionUID = 1L;

			private CountryHeadingExpression() {
				VariableBuilder<Integer> minCountryPage = variable(pageIndexField, Calculation.LOWEST)
					.setResetType(Evaluation.FIRST_GROUP);
				addExpression(minCountryPage);

				VariableBuilder<Integer> maxCountryPage = variable(pageIndexField, Calculation.HIGHEST)
					.setResetType(Evaluation.FIRST_GROUP);
				addExpression(maxCountryPage);
			}

			@Override
			public String evaluate(List<?> values, ReportParameters reportParameters) {
				return values.get(0) + " - " + values.get(1);
			}
		}
	}

	public static void main(String[] args) {
		SalesTableOfContentsDesign design = new SalesTableOfContentsDesign();
		try {
			JasperReportBuilder report = design.build();
			report.show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}
}