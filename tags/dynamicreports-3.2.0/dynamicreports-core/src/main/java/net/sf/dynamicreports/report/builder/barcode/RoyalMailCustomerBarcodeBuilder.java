/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2014 Ricardo Mariaca
 * http://www.dynamicreports.org
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

import net.sf.dynamicreports.report.base.barcode.DRRoyalMailCustomerBarcode;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class RoyalMailCustomerBarcodeBuilder extends AbstractChecksumBarcodeBuilder<RoyalMailCustomerBarcodeBuilder, DRRoyalMailCustomerBarcode> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected RoyalMailCustomerBarcodeBuilder(String code) {
		super(code, new DRRoyalMailCustomerBarcode());
	}

	protected RoyalMailCustomerBarcodeBuilder(DRIExpression<String> codeExpression) {
		super(codeExpression, new DRRoyalMailCustomerBarcode());
	}

	public RoyalMailCustomerBarcodeBuilder setAscenderHeight(Double ascenderHeight) {
		getObject().setAscenderHeight(ascenderHeight);
		return this;
	}

	public RoyalMailCustomerBarcodeBuilder setIntercharGapWidth(Double intercharGapWidth) {
		getObject().setIntercharGapWidth(intercharGapWidth);
		return this;
	}

	public RoyalMailCustomerBarcodeBuilder setTrackHeight(Double trackHeight) {
		getObject().setTrackHeight(trackHeight);
		return this;
	}
}
