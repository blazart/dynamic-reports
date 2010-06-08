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
package net.sf.dynamicreports.test.jasper.style;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.io.Serializable;
import java.util.Date;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.test.jasper.AbstractJasperStyleTest;
import net.sf.dynamicreports.test.jasper.DataSource;
import net.sf.jasperreports.engine.JRAlignment;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class Style4Test extends AbstractJasperStyleTest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private TextColumnBuilder<Date> column1;
	private ColumnGroupBuilder group1;
	
	@Override
	protected void configureReport(JasperReportBuilder rb) {
		StyleBuilder groupStyle = stl.style()
		                             .bold()
                                 .setHorizontalAlignment(HorizontalAlignment.LEFT);

		column1  = col.column("field1", type.dateYearType());

		group1  = grp.group(column1)
		             .setHideColumn(false)
                 .groupByDataType()
                 .setStyle(groupStyle);
		
		rb.columns(column1)
		  .groupBy(group1);
	}

	@Override
	public void test() {
		super.test();
		
		numberOfPagesTest(1);
		
		//column1		
		columnDetailStyleTest(column1, 0, Color.BLACK, null, "Arial", 10, null, null);
		columnDetailAlignmentTest(column1, 0, JRAlignment.HORIZONTAL_ALIGN_RIGHT);
		
		//group1
		groupHeaderStyleTest(group1, 0, null, null, "Arial", 10, true, null);
		groupHeaderAlignmentTest(group1, 0, JRAlignment.HORIZONTAL_ALIGN_LEFT);
	}
	
	@Override
	protected JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("field1");
		dataSource.add(new Date());
		return dataSource;
	}
}
