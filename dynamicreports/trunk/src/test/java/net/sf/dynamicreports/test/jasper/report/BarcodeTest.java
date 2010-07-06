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

import java.lang.reflect.Field;

import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.constant.BarcodeOrientation;
import net.sf.dynamicreports.report.constant.BarcodeTextPosition;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.renderers.BatikRenderer;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class BarcodeTest extends AbstractJasperValueTest {
	
	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
			.title(
					bcode.EAN128("12345678"),
					bcode.EAN128("12345678").setPattern("(1)"),
					bcode.EAN128("12345678").setOrientation(BarcodeOrientation.LEFT),
					bcode.EAN128("12345678").setTextPosition(BarcodeTextPosition.TOP),
					bcode.EAN128("12345678").setModuleWidth(2d),
					bcode.EAN128("12345678").setQuietZone(100d));
	}

	@Override
	public void test() {
		super.test();
		
		numberOfPagesTest(1);
		
		String barcode = getBarcode(0);
		Assert.assertTrue("EAN128Barcode code", barcode.matches(".*>\\(12\\)345678<.*"));
		barcode = getBarcode(1);
		Assert.assertTrue("EAN128Barcode pattern", barcode.matches(".*>\\(1\\)\\(12\\)345678<.*"));
		barcode = getBarcode(2);
		Assert.assertTrue("EAN128Barcode orientation", barcode.matches(".*x=\"11.55\" y=\"201.7762\">\\(12\\)345678<.*"));
		barcode = getBarcode(3);
		Assert.assertTrue("EAN128Barcode text position", barcode.matches(".*x=\"11.55\" y=\"2.6228\">\\(12\\)345678<.*"));
		barcode = getBarcode(4);
		Assert.assertTrue("EAN128Barcode module width", barcode.matches(".*x=\"33.846\" y=\"34.2277\">\\(12\\)345678<.*"));
		barcode = getBarcode(5);
		Assert.assertTrue("EAN128Barcode quite zone", barcode.matches(".*x=\"44.7234\" y=\"34.2277\">\\(12\\)345678<.*"));
	}
	
	private String getBarcode(int index) {
		JRPrintImage image = (JRPrintImage) ((JRPrintFrame) ((JRPrintPage) getJasperPrint().getPages().get(0)).getElements().get(0)).getElements().get(index);	
		BatikRenderer renderer = (BatikRenderer) image.getRenderer();
		try {
			Field field = renderer.getClass().getDeclaredField("svgText");
			field.setAccessible(true);
			String string = (String) field.get(renderer);
			return string;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		return null;
	}
}
