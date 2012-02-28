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

package net.sf.dynamicreports.design.definition.component;

import java.util.List;

import net.sf.dynamicreports.design.definition.DRIDesignGroup;
import net.sf.dynamicreports.design.definition.DRIDesignTableOfContentsHeading;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignPropertyExpression;
import net.sf.dynamicreports.design.definition.style.DRIDesignStyle;
import net.sf.dynamicreports.report.constant.ComponentPositionType;
import net.sf.dynamicreports.report.constant.StretchType;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public interface DRIDesignComponent {
	public String getName();

	public String getUniqueName();

	public DRIDesignStyle getStyle();

	public Integer getX();

	public Integer getY();

	public Integer getWidth();

	public Integer getHeight();

	public DRIDesignExpression getPrintWhenExpression();

	public boolean isRemoveLineWhenBlank();

	public List<DRIDesignPropertyExpression> getPropertyExpressions();

	public ComponentPositionType getPositionType();

	public StretchType getStretchType();

	public boolean isPrintInFirstWholeBand();

	public boolean isPrintWhenDetailOverflows();

	public DRIDesignGroup getPrintWhenGroupChanges();

	public DRIDesignTableOfContentsHeading getTableOfContentsHeading();
}
