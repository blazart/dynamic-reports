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

package net.sf.dynamicreports.report.definition.grid;

import java.util.List;

import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRIPropertyExpression;
import net.sf.dynamicreports.report.definition.style.DRIReportStyle;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public interface DRIColumnTitleGroup extends DRIColumnGridComponent {

	public DRIColumnGridList getList();

	public DRIExpression<?> getTitleExpression();

	public DRIReportStyle getTitleStyle();

	public Integer getTitleWidth();

	public ComponentDimensionType getTitleWidthType();

	public Integer getTitleColumns();

	public Integer getTitleHeight();

	public ComponentDimensionType getTitleHeightType();

	public Integer getTitleRows();

	public Boolean getTitleStretchWithOverflow();

	public List<DRIPropertyExpression> getTitlePropertyExpressions();
}
