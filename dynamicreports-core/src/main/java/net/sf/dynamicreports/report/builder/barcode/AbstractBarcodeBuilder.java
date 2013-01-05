/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2013 Ricardo Mariaca
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

import net.sf.dynamicreports.report.base.barcode.DRBarcode;
import net.sf.dynamicreports.report.builder.component.DimensionComponentBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.BarcodeOrientation;
import net.sf.dynamicreports.report.constant.BarcodeTextPosition;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
@SuppressWarnings("unchecked")
public abstract class AbstractBarcodeBuilder<T extends AbstractBarcodeBuilder<T, U>, U extends DRBarcode> extends DimensionComponentBuilder<T, U> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected AbstractBarcodeBuilder(String code, U barcode) {
		super(barcode);
		setCode(code);
	}

	protected AbstractBarcodeBuilder(DRIExpression<String> codeExpression, U barcode) {
		super(barcode);
		setCode(codeExpression);
	}

	public T setCode(String code) {
		getObject().setCodeExpression(Expressions.text(code));
		return (T) this;
	}

	public T setCode(DRIExpression<String> codeExpression) {
		getObject().setCodeExpression(codeExpression);
		return (T) this;
	}

	public T setPattern(String pattern) {
		getObject().setPatternExpression(Expressions.text(pattern));
		return (T) this;
	}

	public T setPattern(DRIExpression<String> patternExpression) {
		getObject().setPatternExpression(patternExpression);
		return (T) this;
	}

	public T setModuleWidth(Double moduleWidth) {
		getObject().setModuleWidth(moduleWidth);
		return (T) this;
	}

	public T setOrientation(BarcodeOrientation orientation) {
		getObject().setOrientation(orientation);
		return (T) this;
	}

	public T setTextPosition(BarcodeTextPosition textPosition) {
		getObject().setTextPosition(textPosition);
		return (T) this;
	}

	public T setQuietZone(Double quietZone) {
		getObject().setQuietZone(quietZone);
		return (T) this;
	}

	public T setVerticalQuietZone(Double verticalQuietZone) {
		getObject().setVerticalQuietZone(verticalQuietZone);
		return (T) this;
	}
}
