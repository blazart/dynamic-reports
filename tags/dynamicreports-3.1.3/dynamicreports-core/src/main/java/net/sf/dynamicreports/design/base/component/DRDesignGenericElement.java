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

package net.sf.dynamicreports.design.base.component;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.definition.component.DRIDesignGenericElement;
import net.sf.dynamicreports.design.definition.expression.DRIDesignParameterExpression;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DRDesignGenericElement extends DRDesignComponent implements DRIDesignGenericElement {
	private String genericElementNamespace;	
	private String genericElementName;	
	private EvaluationTime evaluationTime;
	private DRDesignGroup evaluationGroup;	
	private List<DRIDesignParameterExpression> parameterExpressions;
	
	public DRDesignGenericElement() {
		super("genericElement");
	}
	
	@Override
	protected void init() {
		super.init();
		parameterExpressions = new ArrayList<DRIDesignParameterExpression>();
	}

	@Override
	public String getGenericElementNamespace() {
		return genericElementNamespace;
	}

	public void setGenericElementNamespace(String genericElementNamespace) {
		this.genericElementNamespace = genericElementNamespace;
	}

	@Override
	public String getGenericElementName() {
		return genericElementName;
	}

	public void setGenericElementName(String genericElementName) {
		this.genericElementName = genericElementName;
	}

	@Override
	public EvaluationTime getEvaluationTime() {
		return evaluationTime;
	}

	public void setEvaluationTime(EvaluationTime evaluationTime) {
		this.evaluationTime = evaluationTime;
	}

	@Override
	public DRDesignGroup getEvaluationGroup() {
		return evaluationGroup;
	}

	public void setEvaluationGroup(DRDesignGroup evaluationGroup) {
		this.evaluationGroup = evaluationGroup;
	}

	@Override
	public List<DRIDesignParameterExpression> getParameterExpressions() {
		return parameterExpressions;
	}

	public void setParameterExpressions(List<DRIDesignParameterExpression> parameterExpressions) {
		this.parameterExpressions = parameterExpressions;
	}
}
