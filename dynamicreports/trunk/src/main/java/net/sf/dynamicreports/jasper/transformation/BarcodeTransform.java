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
package net.sf.dynamicreports.jasper.transformation;

import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignBarcode;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignEAN128Barcode;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.jasperreports.components.ComponentsExtensionsRegistryFactory;
import net.sf.jasperreports.components.barcode4j.BarcodeComponent;
import net.sf.jasperreports.components.barcode4j.EAN128Component;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class BarcodeTransform {
	private JasperTransformAccessor accessor;
	
	public BarcodeTransform(JasperTransformAccessor accessor) {
		this.accessor = accessor;
	}
	
	protected JRDesignElement transform(DRIDesignBarcode barcode) {		
		JRDesignComponentElement jrComponent = new JRDesignComponentElement();
		jrComponent.setComponent(barcodeComponent(barcode));
		jrComponent.setComponentKey(new ComponentKey(ComponentsExtensionsRegistryFactory.NAMESPACE, "jr", barcode.getName()));
		jrComponent.setPositionType(JRElement.POSITION_TYPE_FLOAT);
		jrComponent.setStretchType(JRElement.STRETCH_TYPE_RELATIVE_TO_TALLEST_OBJECT);
		return jrComponent;
	}
	
	private BarcodeComponent barcodeComponent(DRIDesignBarcode barcode) {
		if (barcode instanceof DRIDesignEAN128Barcode) {
			return ean128Barcode((DRIDesignEAN128Barcode) barcode);
		}
		else {
			throw new JasperDesignException("Barcode " + barcode.getClass().getName() + " not supported");
		}
	}
	
	private void barcode(BarcodeComponent jrBarcode, DRIDesignBarcode barcode) {
		EvaluationTime evaluationTime = barcode.getEvaluationTime();
		jrBarcode.setEvaluationTime(ConstantTransform.evaluationTime(evaluationTime));
		if (evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP) && barcode.getEvaluationGroup() != null) {
			jrBarcode.setEvaluationGroup(accessor.getGroupTransform().getGroup(barcode.getEvaluationGroup()).getName());
		}	
		jrBarcode.setCodeExpression(accessor.getExpressionTransform().getExpression(barcode.getCodeExpression()));
		jrBarcode.setPatternExpression(accessor.getExpressionTransform().getExpression(barcode.getPatternExpression()));
		jrBarcode.setModuleWidth(barcode.getModuleWidth());
		if (barcode.getOrientation() != null) {
			jrBarcode.setOrientation(ConstantTransform.barcodeOrientation(barcode.getOrientation()));
		}
		jrBarcode.setTextPosition(ConstantTransform.barcodeTextPosition(barcode.getTextPosition()));
		jrBarcode.setQuietZone(barcode.getQuietZone());
		jrBarcode.setVerticalQuietZone(barcode.getVerticalQuietZone());		
	}
	
	private BarcodeComponent ean128Barcode(DRIDesignEAN128Barcode barcode) {
		EAN128Component jrEAN128Barcode = new EAN128Component();
		barcode(jrEAN128Barcode, barcode);
		jrEAN128Barcode.setChecksumMode(ConstantTransform.barcodeChecksumMode(barcode.getChecksumMode()));
		return jrEAN128Barcode;
	}
}
