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

package net.sf.dynamicreports.design.definition.component;

import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.definition.DRIDesignGroup;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.Markup;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public interface DRIDesignTextField extends DRIDesignHyperLinkComponent {

	public String getPattern();

	public DRIDesignExpression getPatternExpression();

	public HorizontalTextAlignment getHorizontalTextAlignment();

	public DRIDesignExpression getValueExpression();

	public boolean isPrintRepeatedValues();

	public EvaluationTime getEvaluationTime();

	public DRIDesignGroup getEvaluationGroup();

	public Markup getMarkup();

	public boolean isStretchWithOverflow();
}
