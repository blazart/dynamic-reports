/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2015 Ricardo Mariaca
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

package net.sf.dynamicreports.jasper.transformation.expression;

import java.util.HashMap;
import java.util.Map;

import net.sf.dynamicreports.design.base.expression.AbstractDesignSimpleExpression;
import net.sf.dynamicreports.jasper.base.JasperReportParameters;
import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.ReportParameters;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class CrosstabParametersExpression extends AbstractDesignSimpleExpression {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private Map<String, Object> parameters;

	public CrosstabParametersExpression(Map<String, Object> parameters) {
		super(ReportUtils.generateUniqueName("crosstabParametersExpression"));
		this.parameters = parameters;
	}

	@Override
	public Object evaluate(ReportParameters reportParameters) {
		Map<String, Object> parameters = new HashMap<String, Object>(this.parameters);
		parameters.put(JasperReportParameters.MASTER_REPORT_PARAMETERS, reportParameters);
		return parameters;
	}

	@Override
	public Class<?> getValueClass() {
		return Map.class;
	}
}
