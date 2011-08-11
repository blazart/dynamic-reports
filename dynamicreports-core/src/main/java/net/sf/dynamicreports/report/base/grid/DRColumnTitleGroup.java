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

package net.sf.dynamicreports.report.base.grid;

import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.grid.DRIColumnGridComponent;
import net.sf.dynamicreports.report.definition.grid.DRIColumnTitleGroup;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRColumnTitleGroup implements DRIColumnTitleGroup {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private DRColumnGridList list;

	private DRIExpression<?> titleExpression;
	private DRStyle titleStyle;
	private Integer titleWidth;
	private ComponentDimensionType titleWidthType;
	private Integer titleColumns;
	private Integer titleHeight;
	private ComponentDimensionType titleHeightType;
	private Integer titleRows;

	public DRColumnTitleGroup() {
		this.list = new DRColumnGridList(ListType.HORIZONTAL);
	}

	public DRColumnGridList getList() {
		return list;
	}

	public void addComponent(DRIColumnGridComponent component) {
		list.addComponent(component);
	}

	public DRIExpression<?> getTitleExpression() {
		return titleExpression;
	}

	public void setTitleExpression(DRIExpression<?> titleExpression) {
		this.titleExpression = titleExpression;
	}

	public DRStyle getTitleStyle() {
		return titleStyle;
	}

	public void setTitleStyle(DRStyle titleStyle) {
		this.titleStyle = titleStyle;
	}

  /**
   * Returns the column title width.
   *
   * @return the column title width >= 1
   */
	public Integer getTitleWidth() {
		return titleWidth;
	}

  /**
   * Sets the column title width.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param titleWidth the column title width >= 1
   * @exception IllegalArgumentException if <code>titleWidth</code> is < 1
   */
	public void setTitleWidth(Integer titleWidth) {
		if (titleWidth != null) {
			Validate.isTrue(titleWidth >= 1, "titleWidth must be >= 1");
		}
		this.titleWidth = titleWidth;
	}

	public ComponentDimensionType getTitleWidthType() {
		return titleWidthType;
	}

	public void setTitleWidthType(ComponentDimensionType titleWidthType) {
		this.titleWidthType = titleWidthType;
	}

  /**
   * Returns the number of title columns.
   *
   * @return the number of title columns >= 1
   */
	public Integer getTitleColumns() {
		return titleColumns;
	}

  /**
   * This method is used to define the width of a column title.
   * The width is set to the <code>columns</code> multiplied by width of the
   * character <em>m</em> for the font used
   *
   * @param titleColumns the number of columns >= 1
   * @exception IllegalArgumentException if <code>columns</code> is < 1
   */
	public void setTitleColumns(Integer titleColumns) {
		if (titleColumns != null) {
			Validate.isTrue(titleColumns >= 1, "titleColumns must be >= 1");
		}
		this.titleColumns = titleColumns;
	}

  /**
   * Returns the column title height.
   *
   * @return the column title height >= 1
   */
	public Integer getTitleHeight() {
		return titleHeight;
	}

  /**
   * Sets the column title height.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param titleHeight the column title height >= 1
   * @exception IllegalArgumentException if <code>titleHeight</code> is < 1
   */
	public void setTitleHeight(Integer titleHeight) {
		if (titleHeight != null) {
			Validate.isTrue(titleHeight >= 1, "titleHeight must be >= 1");
		}
		this.titleHeight = titleHeight;
	}

	public ComponentDimensionType getTitleHeightType() {
		return titleHeightType;
	}

	public void setTitleHeightType(ComponentDimensionType titleHeightType) {
		this.titleHeightType = titleHeightType;
	}

  /**
   * Returns the number of title rows.
   *
   * @return the number of title rows >= 1
   */
	public Integer getTitleRows() {
		return titleRows;
	}

  /**
   * This method is used to define the height of a column title.
   * The height is set to the <code>rows</code> multiplied by height of the font
   *
   * @param titleRows the number of rows >= 1
   * @exception IllegalArgumentException if <code>rows</code> is < 1
   */
	public void setTitleRows(Integer titleRows) {
		if (titleRows != null) {
			Validate.isTrue(titleRows >= 1, "titleRows must be >= 1");
		}
		this.titleRows = titleRows;
	}
}