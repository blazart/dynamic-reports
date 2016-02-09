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

package net.sf.dynamicreports.test.jasper.report;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.ByteArrayOutputStream;

import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class ConcatenatedReport2Test {
	JasperConcatenatedReportBuilder concatenatedReport;

	@Before
	public void init() {
		JasperReportBuilder report1 = report()
			.columns(col.column("field1", String.class))
			.pageFooter(cmp.pageNumber())
			.setDataSource(createDataSource("text1"));
		JasperReportBuilder report2 = report()
			.columns(col.column("field1", String.class))
			.pageFooter(cmp.pageNumber())
			.setDataSource(createDataSource("text2"));
		JasperReportBuilder report3 = report()
			.columns(col.column("field1", String.class))
			.pageFooter(cmp.pageNumber())
			.setDataSource(createDataSource("text3"));

		concatenatedReport = concatenatedReport();
		concatenatedReport.concatenate(report1, report2, report3);
	}

	@Test
	public void test() {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			concatenatedReport.toCsv(bos);
			Assert.assertEquals("concatenated report ", "text1\n1\ntext2\n1\ntext3\n1\n", new String(bos.toByteArray()));

			concatenatedReport.continuousPageNumbering();
			bos = new ByteArrayOutputStream();
			concatenatedReport.toCsv(bos);
			Assert.assertEquals("concatenated report ", "text1\n1\ntext2\n2\ntext3\n3\n", new String(bos.toByteArray()));

			concatenatedReport.setContinuousPageNumbering(false);
			bos = new ByteArrayOutputStream();
			concatenatedReport.toCsv(bos);
			Assert.assertEquals("concatenated report ", "text1\n1\ntext2\n1\ntext3\n1\n", new String(bos.toByteArray()));
		} catch (DRException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	private JRDataSource createDataSource(String text) {
		DRDataSource dataSource = new DRDataSource("field1");
		dataSource.add(text);
		return dataSource;
	}
}
