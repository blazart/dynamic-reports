/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2011 Ricardo Mariaca
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

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.report.ReportUtils;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
abstract class AbstractDesignComplexExpression implements DRIDesignComplexExpression {
	private String name;
	private List<DRIDesignExpression> expressions;

	protected AbstractDesignComplexExpression() {
		this.name = ReportUtils.generateUniqueName("complexExpression");
		this.expressions = new ArrayList<DRIDesignExpression>();
	}

	public String getName() {
		return name;
	}

	public void setExpressions(List<DRIDesignExpression> expressions) {
		this.expressions = expressions;
	}

	public void addExpression(DRIDesignExpression expression) {
		this.expressions.add(expression);
	}

	public List<DRIDesignExpression> getExpressions() {
		return expressions;
	}
}
