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

package net.sf.dynamicreports.test.jasper.component;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.Serializable;

import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.test.jasper.AbstractJasperChartTest;
import net.sf.jasperreports.engine.fill.JRTemplateGenericPrintElement;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class MapTest extends AbstractJasperChartTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.title(
			cmp.map(40.7f, -74f, 12));
	}

	@Override
	public void test() {
		super.test();

		numberOfPagesTest(1);

		JRTemplateGenericPrintElement map = (JRTemplateGenericPrintElement) getJasperPrint().getPages().get(0).getElements().get(0);
		Assert.assertEquals("latitude", map.getParameterValue("latitude"), 40.7f);
		Assert.assertEquals("longitude", map.getParameterValue("longitude"), -74f);
		Assert.assertEquals("zoom", map.getParameterValue("zoom"), 12);
	}
}
