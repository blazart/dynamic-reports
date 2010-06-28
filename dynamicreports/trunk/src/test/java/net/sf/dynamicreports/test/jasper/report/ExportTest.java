/* Dynamic reports - Free Java reporting library for creating reports dynamically
 *
 * (C) Copyright 2010 Ricardo Mariaca
 *
 * http://dynamicreports.sourceforge.net
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 3 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA. 
 */
package net.sf.dynamicreports.test.jasper.report;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.ByteArrayOutputStream;

import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRDataSource;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ExportTest {
	JasperReportBuilder report;
	JasperConcatenatedReportBuilder concatenatedReport;
	
	@Before
	public void init() {		
		report = report();
		report.columns(col.column("Column1", "field1", String.class));
		report.setDataSource(createDataSource());
		
		concatenatedReport = concatenatedReport();
		concatenatedReport.concatenate(report, report, report);
	}

	@Test
	public void exportTest() {
		try {
			report.toPng(new ByteArrayOutputStream(), -1);
			report.toCsv(new ByteArrayOutputStream());
			report.toDocx(new ByteArrayOutputStream());
			report.toHtml(new ByteArrayOutputStream());
			report.toOds(new ByteArrayOutputStream());
			report.toOdt(new ByteArrayOutputStream());
			report.toPdf(new ByteArrayOutputStream());
			report.toRtf(new ByteArrayOutputStream());
			report.toText(new ByteArrayOutputStream());
			report.toXhtml(new ByteArrayOutputStream());
			report.toExcelApiXls(new ByteArrayOutputStream());
			report.toXls(new ByteArrayOutputStream());
			report.toXlsx(new ByteArrayOutputStream());
			report.toXml(new ByteArrayOutputStream());
			report.toXmlss(new ByteArrayOutputStream());
		} catch (DRException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void concatenatedExportTest() {
		try {
			concatenatedReport.toPng(new ByteArrayOutputStream());
			concatenatedReport.toCsv(new ByteArrayOutputStream());
			concatenatedReport.toDocx(new ByteArrayOutputStream());
			concatenatedReport.toHtml(new ByteArrayOutputStream());
			concatenatedReport.toOds(new ByteArrayOutputStream());
			concatenatedReport.toOdt(new ByteArrayOutputStream());
			concatenatedReport.toPdf(new ByteArrayOutputStream());
			concatenatedReport.toRtf(new ByteArrayOutputStream());
			concatenatedReport.toText(new ByteArrayOutputStream());
			concatenatedReport.toXhtml(new ByteArrayOutputStream());
			concatenatedReport.toExcelApiXls(new ByteArrayOutputStream());
			concatenatedReport.toXls(new ByteArrayOutputStream());
			concatenatedReport.toXlsx(new ByteArrayOutputStream());
			concatenatedReport.toXml(new ByteArrayOutputStream());
			concatenatedReport.toXmlss(new ByteArrayOutputStream());
		} catch (DRException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	private JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1");
		dataSource.add("field1");	
		return dataSource;
	}
}