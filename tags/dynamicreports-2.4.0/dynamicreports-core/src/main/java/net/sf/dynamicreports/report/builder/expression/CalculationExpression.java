/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2012 Ricardo Mariaca
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

package net.sf.dynamicreports.report.builder.expression;

import java.math.BigDecimal;
import java.util.List;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
abstract class CalculationExpression extends AbstractComplexExpression<BigDecimal> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected CalculationExpression(DRIExpression<? extends Number> ...expressions) {
		Validate.notNull(expressions, "expressions must not be null");
		Validate.noNullElements(expressions, "expressions must not contains null expression");
		for (DRIExpression<? extends Number> expression : expressions) {
			addExpression(expression);
		}
	}

	@Override
	public BigDecimal evaluate(List<?> values, ReportParameters reportParameters) {
		BigDecimal result = null;
		for (Object value : values) {
			BigDecimal bigDecimalValue;
			if (value instanceof BigDecimal) {
				bigDecimalValue = (BigDecimal) value;
			}
			else {
				bigDecimalValue = new BigDecimal(((Number) value).doubleValue());
			}
			if (result == null) {
				result = bigDecimalValue;
			}
			else {
				result = calculate(result, bigDecimalValue);	
			}
		}
		return result;
	}
	
	protected abstract BigDecimal calculate(BigDecimal value1, BigDecimal value2);
}
