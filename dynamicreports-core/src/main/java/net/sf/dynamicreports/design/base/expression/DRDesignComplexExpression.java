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

package net.sf.dynamicreports.design.base.expression;

import java.util.List;

import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.expression.DRIComplexExpression;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DRDesignComplexExpression extends AbstractDesignComplexExpression {
	private DRIComplexExpression<?> complexExpression;
	private String parameterName;

	public DRDesignComplexExpression(DRIComplexExpression<?> complexExpression, String parameterName) {
		this.complexExpression = complexExpression;
		this.parameterName = parameterName;
	}

	@Override
	public Object evaluate(List<?> values, ReportParameters reportParameters) {
		return complexExpression.evaluate(values, reportParameters);
	}

	@Override
	public Class<?> getValueClass() {
		return complexExpression.getValueClass();
	}

	@Override
	public String getName() {
		return complexExpression.getName();
	}

	@Override
	public String getParameterName() {
		return parameterName;
	}
}
