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
package net.sf.dynamicreports.examples;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class AbstractReportMain<T extends ReportDesign<U>, U extends ReportData> {
	
	public AbstractReportMain() {
		build();
	}
	
	protected void build() {
		try {
			JasperReportBuilder reportBuilder = DynamicReports.report();			
			U data = getReportData();
			if (data != null) {
				reportBuilder.setDataSource(data.createDataSource());
			}
			getReportDesign().configureReport(reportBuilder, data);
			reportBuilder.show();						
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	protected U getReportData() {
		return null;
	}
	
	protected abstract T getReportDesign();	
}
