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

package net.sf.dynamicreports.test.jasper.component;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.report.exception.DRException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class PageNumber6Test {

	@Test
	public void test() {
		try {
			report().summary(cmp.pageXofY().setFormatExpression("{1} {0}")).toJasperPrint();
			Assert.fail("Wrong page number");
		} catch (DRException e) {
		}

		try {
			report().summary(cmp.pageXofY().setFormatExpression("{1} {1}")).toJasperPrint();
			Assert.fail("Wrong page number");
		} catch (DRException e) {
		}

		try {
			report().summary(cmp.pageXofY().setFormatExpression("{0} {0}")).toJasperPrint();
			Assert.fail("Wrong page number");
		} catch (DRException e) {
		}

		try {
			report().summary(cmp.pageXofY().setFormatExpression("{0}")).toJasperPrint();
			Assert.fail("Wrong page number");
		} catch (DRException e) {
		}

		try {
			report().summary(cmp.pageXofY().setFormatExpression("{1}")).toJasperPrint();
			Assert.fail("Wrong page number");
		} catch (DRException e) {
		}
	}
}
