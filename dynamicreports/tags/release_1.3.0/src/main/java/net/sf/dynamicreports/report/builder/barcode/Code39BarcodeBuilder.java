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
package net.sf.dynamicreports.report.builder.barcode;

import net.sf.dynamicreports.report.base.barcode.DRCode39Barcode;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class Code39BarcodeBuilder extends AbstractChecksumBarcodeBuilder<Code39BarcodeBuilder, DRCode39Barcode> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	protected Code39BarcodeBuilder(String code) {
		super(code, new DRCode39Barcode());
	}

	protected Code39BarcodeBuilder(DRISimpleExpression<String> codeExpression) {
		super(codeExpression, new DRCode39Barcode());
	}

	public Code39BarcodeBuilder setDisplayChecksum(Boolean displayChecksum) {
		getObject().setDisplayChecksum(displayChecksum);
		return this;
	}

	public Code39BarcodeBuilder setDisplayStartStop(Boolean displayStartStop) {
		getObject().setDisplayStartStop(displayStartStop);
		return this;
	}

	public Code39BarcodeBuilder setExtendedCharSetEnabled(Boolean extendedCharSetEnabled) {
		getObject().setExtendedCharSetEnabled(extendedCharSetEnabled);
		return this;
	}

	public Code39BarcodeBuilder setIntercharGapWidth(Double intercharGapWidth) {
		getObject().setIntercharGapWidth(intercharGapWidth);
		return this;
	}

	public Code39BarcodeBuilder setWideFactor(Double wideFactor) {
		getObject().setWideFactor(wideFactor);
		return this;
	}	
}
