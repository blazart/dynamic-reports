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

import java.math.BigDecimal;

import net.sf.dynamicreports.examples.DataSource;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.export.Exporters;
import net.sf.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class EncryptedPdfReport {
	
	public EncryptedPdfReport() {
		build();
	}
	
	private void build() {
		try {
			JasperPdfExporterBuilder pdfExporter = Exporters.pdfExporter("c:/report.pdf")
			                                                .encrypted("1234");
			
			report()
			  .setTemplate(Templates.reportTemplate)
			  .columns(
			  	col.column("Item",       "item",      type.stringType()),
			  	col.column("Quantity",   "quantity",  type.integerType()),
			  	col.column("Unit price", "unitprice", type.bigDecimalType()))
			  .title(Templates.createTitleComponent("EncryptedPdfReport"))
			  .pageFooter(Templates.footerComponent)
			  .setDataSource(createDataSource())
			  .toPdf(pdfExporter);
		} catch (DRException e) {
			e.printStackTrace();	
		}
	}
	
	private JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("item", "quantity", "unitprice");
		for (int i = 0; i < 20; i++) {
			dataSource.add("Book", (int) (Math.random() * 10) + 1, new BigDecimal(Math.random() * 100 + 1));
		}
		return dataSource;
	}
	
	public static void main(String[] args) {
		new EncryptedPdfReport();
	}
}