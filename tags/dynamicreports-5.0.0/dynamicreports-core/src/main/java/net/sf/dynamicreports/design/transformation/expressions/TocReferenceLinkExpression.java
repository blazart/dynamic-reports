/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2016 Ricardo Mariaca
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

package net.sf.dynamicreports.design.transformation.expressions;

import java.util.List;

import net.sf.dynamicreports.report.builder.expression.AbstractComplexExpression;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class TocReferenceLinkExpression extends AbstractComplexExpression<String> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private String expressionName;
	private boolean customId;

	public TocReferenceLinkExpression(String expressionName, DRIExpression<String> anchorNameExpression) {
		this.expressionName = expressionName;
		customId = anchorNameExpression != null;
		if (anchorNameExpression != null) {
			addExpression(anchorNameExpression);
		}
	}

	@Override
	public String evaluate(List<?> values, ReportParameters reportParameters) {
		String id;
		if (customId) {
			id = (String) values.get(0);
		}
		else {
			id = expressionName + "_" + reportParameters.getReportRowNumber();
		}
		return id;
	}
}
