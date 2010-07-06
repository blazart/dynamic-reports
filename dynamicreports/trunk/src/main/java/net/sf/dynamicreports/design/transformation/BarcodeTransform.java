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
package net.sf.dynamicreports.design.transformation;

import net.sf.dynamicreports.design.base.barcode.DRDesignBarcode;
import net.sf.dynamicreports.design.base.barcode.DRDesignEAN128Barcode;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.report.definition.barcode.DRIBarcode;
import net.sf.dynamicreports.report.definition.barcode.DRIEAN128Barcode;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class BarcodeTransform {
	private DesignTransformAccessor accessor;
	
	public BarcodeTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
	}
	
	protected DRDesignBarcode transform(DRIBarcode barcode) throws DRException {		
		if (barcode instanceof DRIEAN128Barcode) {
			return ean128Barcode((DRIEAN128Barcode) barcode);
		}
		else {
			throw new DRDesignReportException("Barcode " + barcode.getClass().getName() + " not supported");
		}
	}
	
	private void barcode(DRDesignBarcode designBarcode, DRIBarcode barcode) throws DRException {
		designBarcode.setWidth(accessor.getTemplateTransform().getBarcodeWidth(barcode));
		designBarcode.setHeight(accessor.getTemplateTransform().getBarcodeHeight(barcode));
		designBarcode.setCodeExpression((DRIDesignSimpleExpression) accessor.getExpressionTransform().transformExpression(barcode.getCodeExpression()));
		designBarcode.setPatternExpression((DRIDesignSimpleExpression) accessor.getExpressionTransform().transformExpression(barcode.getPatternExpression()));
		designBarcode.setModuleWidth(barcode.getModuleWidth());
		designBarcode.setOrientation(barcode.getOrientation());
		designBarcode.setTextPosition(barcode.getTextPosition());
		designBarcode.setQuietZone(barcode.getQuietZone());
		designBarcode.setVerticalQuietZone(barcode.getVerticalQuietZone());		
	}
	
	private DRDesignEAN128Barcode ean128Barcode(DRIEAN128Barcode barcode) throws DRException {
		DRDesignEAN128Barcode designBarcode = new DRDesignEAN128Barcode();
		barcode(designBarcode, barcode);
		designBarcode.setChecksumMode(barcode.getChecksumMode());
		return designBarcode;
	}
}
