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

package net.sf.dynamicreports.report.definition.crosstab;

import java.util.List;

import net.sf.dynamicreports.report.constant.RunDirection;
import net.sf.dynamicreports.report.definition.component.DRIDimensionComponent;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public interface DRICrosstab extends DRIDimensionComponent {

	public Boolean isRepeatColumnHeaders();

	public Boolean isRepeatRowHeaders();

	public Integer getColumnBreakOffset();

	public Boolean getIgnoreWidth();

	public RunDirection getRunDirection();

	public Integer getCellWidth();

	public Integer getCellHeight();

	public DRICrosstabCellContent getWhenNoDataCell();

	public DRICrosstabCellContent getHeaderCell();

	public List<DRICrosstabColumnGroup<?>> getColumnGroups();

	public List<DRICrosstabRowGroup<?>> getRowGroups();

	public List<DRICrosstabMeasure<?>> getMeasures();
}