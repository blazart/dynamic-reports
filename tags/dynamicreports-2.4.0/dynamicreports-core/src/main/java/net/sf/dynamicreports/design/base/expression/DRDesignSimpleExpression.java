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

package net.sf.dynamicreports.design.base.expression;

import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignSimpleExpression extends AbstractDesignSimpleExpression {
	private DRISimpleExpression<?> simpleExpression;
	private String parameterName;

	public DRDesignSimpleExpression(DRISimpleExpression<?> simpleExpression, String parameterName) {
		this.simpleExpression = simpleExpression;
		this.parameterName = parameterName;
	}

	public Object evaluate(ReportParameters reportParameters) {
		return simpleExpression.evaluate(reportParameters);
	}

	public Class<?> getValueClass() {
		return simpleExpression.getValueClass();
	}

	@Override
	public String getName() {
		return simpleExpression.getName();
	}

	@Override
	public String getParameterName() {
		return parameterName;
	}
}
