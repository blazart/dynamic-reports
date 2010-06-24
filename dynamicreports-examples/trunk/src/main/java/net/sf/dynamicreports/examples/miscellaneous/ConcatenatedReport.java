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
package net.sf.dynamicreports.examples.miscellaneous;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import net.sf.dynamicreports.examples.complex.ReportData;
import net.sf.dynamicreports.examples.complex.ReportDesign;
import net.sf.dynamicreports.examples.complex.invoice.InvoiceData;
import net.sf.dynamicreports.examples.complex.invoice.InvoiceDesign;
import net.sf.dynamicreports.examples.complex.sales.SalesData;
import net.sf.dynamicreports.examples.complex.sales.SalesDesign;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ConcatenatedReport {
	
	public ConcatenatedReport() {
		build();
	}
	
	private void build() {				
		try {									
			concatenatedReport()
			  .concatenate(
			  		invoiceReport(), salesReport())
			  .toPdf(new FileOutputStream("c:/report.pdf"));
		} catch (DRException e) {
			e.printStackTrace();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	private JasperReportBuilder invoiceReport() {	
		return createReport(new InvoiceDesign(), new InvoiceData());
	}

	private JasperReportBuilder salesReport() {	
		return createReport(new SalesDesign(), new SalesData());
	}
	
	private <T extends ReportData> JasperReportBuilder createReport(ReportDesign<T> design, T data) {
		JasperReportBuilder reportBuilder = DynamicReports.report();
		if (data != null) {
			reportBuilder.setDataSource(data.createDataSource());
		}
		design.configureReport(reportBuilder, data);	
		return reportBuilder;
	}
	
	public static void main(String[] args) {
		new ConcatenatedReport();
	}
}