/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 Ricardo Mariaca
 * http://dynamicreports.sourceforge.net
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

package net.sf.dynamicreports.report.builder.barcode;

import net.sf.dynamicreports.report.base.barcode.DRPdf417Barcode;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class Pdf417BarcodeBuilder extends AbstractBarcodeBuilder<Pdf417BarcodeBuilder, DRPdf417Barcode> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	protected Pdf417BarcodeBuilder(String code) {
		super(code, new DRPdf417Barcode());
	}

	protected Pdf417BarcodeBuilder(DRISimpleExpression<String> codeExpression) {
		super(codeExpression, new DRPdf417Barcode());
	}

	public Pdf417BarcodeBuilder setMinColumns(Integer minColumns) {
		getObject().setMinColumns(minColumns);
		return this;
	}

	public Pdf417BarcodeBuilder setMaxColumns(Integer maxColumns) {
		getObject().setMaxColumns(maxColumns);
		return this;
	}

	public Pdf417BarcodeBuilder setMinRows(Integer minRows) {
		getObject().setMinRows(minRows);
		return this;
	}

	public Pdf417BarcodeBuilder setMaxRows(Integer maxRows) {
		getObject().setMaxRows(maxRows);
		return this;
	}

	public Pdf417BarcodeBuilder setWidthToHeightRatio(Double widthToHeightRatio) {
		getObject().setWidthToHeightRatio(widthToHeightRatio);
		return this;
	}

	public Pdf417BarcodeBuilder setErrorCorrectionLevel(Integer errorCorrectionLevel) {
		getObject().setErrorCorrectionLevel(errorCorrectionLevel);
		return this;
	}	
}
