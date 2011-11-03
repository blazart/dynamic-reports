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

package net.sf.dynamicreports.examples.miscellaneous;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.util.HashMap;
import java.util.Map;

import net.sf.dynamicreports.examples.DataSource;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.base.AbstractScriptlet;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.CustomGroupBuilder;
import net.sf.dynamicreports.report.builder.subtotal.CustomSubtotalBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import org.apache.commons.lang.StringUtils;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ScriptletReport {
	private Map<String, Integer> itemsCount;

	public ScriptletReport() {
		build();
	}

	private void build() {
		itemsCount = new HashMap<String, Integer>();

		TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType());
		CustomSubtotalBuilder<String> itemSbt = sbt.customValue(new ItemSubtotal(), itemColumn);
		CustomGroupBuilder group = grp.group("country", String.class);

		try {
			report()
			  .setTemplate(Templates.reportTemplate)
			  .scriptlets(new ReportScriptlet())
			  .columns(itemColumn)
			  .groupBy(group)
			  .subtotalsAtGroupFooter(group, itemSbt)
			  .title(Templates.createTitleComponent("Scriptlet"))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .show();
		} catch (DRException e) {
			e.printStackTrace();
		}
	}

	private class ItemSubtotal extends AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		public String evaluate(ReportParameters reportParameters) {
			String result = "";
			for (String item : itemsCount.keySet()) {
				result += item + " = " + itemsCount.get(item) + "\n";
			}
			return StringUtils.removeEnd(result, "\n");
		}
	}

	private class ReportScriptlet extends AbstractScriptlet {

		@Override
		public void afterDetailEval(ReportParameters reportParameters) {
			super.afterDetailEval(reportParameters);
			String item = reportParameters.getValue("item");
			Integer count;
			if (itemsCount.containsKey(item)) {
				count = itemsCount.get(item);
			}
			else {
				count = 0;
			}
			itemsCount.put(item, ++count);
		}

		@Override
		public void afterGroupInit(String groupName, ReportParameters reportParameters) {
			super.afterGroupInit(groupName, reportParameters);
			itemsCount.clear();
		}
	}

	private JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("country", "item");
		dataSource.add("USA", "Book");
		dataSource.add("USA", "DVD");
		dataSource.add("USA", "Book");
		dataSource.add("USA", "Book");
		dataSource.add("USA", "DVD");
		dataSource.add("USA", "Book");
		dataSource.add("USA", "DVD");

		dataSource.add("Canada", "Book");
		dataSource.add("Canada", "Book");
		dataSource.add("Canada", "DVD");
		dataSource.add("Canada", "Book");
		dataSource.add("Canada", "DVD");
		dataSource.add("Canada", "Phone");
		return dataSource;
	}

	public static void main(String[] args) {
		new ScriptletReport();
	}
}