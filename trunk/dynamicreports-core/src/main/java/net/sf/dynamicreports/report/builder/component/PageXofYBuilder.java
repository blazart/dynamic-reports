/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2014 Ricardo Mariaca
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

package net.sf.dynamicreports.report.builder.component;

import net.sf.dynamicreports.report.base.component.DRPageXofY;
import net.sf.dynamicreports.report.builder.expression.SystemMessageExpression;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class PageXofYBuilder extends AbstractFormatFieldBuilder<PageXofYBuilder, DRPageXofY> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected PageXofYBuilder() {
		super(new DRPageXofY());
	}

  /**
   * Sets the pageX component preferred width.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param width the pageX component preferred width >= 0
   * @exception IllegalArgumentException if <code>width</code> is < 0
   */
	public PageXofYBuilder setPageXWidth(Integer width) {
		getObject().setPageXWidth(width);
		return this;
	}

  /**
   * Sets the pageX component fixed width.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param width the pageX component fixed width >= 0
   * @exception IllegalArgumentException if <code>width</code> is < 0
   */
	public PageXofYBuilder setPageXFixedWidth(Integer width) {
		getObject().setPageXWidth(width);
		getObject().setPageXWidthType(ComponentDimensionType.FIXED);
		return this;
	}

  /**
   * Sets the pageX component minimum width.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param width the pageX component minimum width >= 0
   * @exception IllegalArgumentException if <code>width</code> is < 0
   */
	public PageXofYBuilder setPageXMinWidth(Integer width) {
		getObject().setPageXWidth(width);
		getObject().setPageXWidthType(ComponentDimensionType.EXPAND);
		return this;
	}

  /**
   * Sets the pageY component preferred width.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param width the pageY component preferred width >= 0
   * @exception IllegalArgumentException if <code>width</code> is < 0
   */
	public PageXofYBuilder setPageYWidth(Integer width) {
		getObject().setPageYWidth(width);
		return this;
	}

  /**
   * Sets the pageY component fixed width.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param width the pageY component fixed width >= 0
   * @exception IllegalArgumentException if <code>width</code> is < 0
   */
	public PageXofYBuilder setPageYFixedWidth(Integer width) {
		getObject().setPageYWidth(width);
		getObject().setPageYWidthType(ComponentDimensionType.FIXED);
		return this;
	}

  /**
   * Sets the pageY component minimum width.
   * @see net.sf.dynamicreports.report.builder.Units
   *
   * @param width the pageY component minimum width >= 0
   * @exception IllegalArgumentException if <code>width</code> is < 0
   */
	public PageXofYBuilder setPageYMinWidth(Integer width) {
		getObject().setPageYWidth(width);
		getObject().setPageYWidthType(ComponentDimensionType.EXPAND);
		return this;
	}

	@Override
	protected void configure() {
		if (getObject().getFormatExpression() == null) {
			setFormatExpression(new SystemMessageExpression("page_x_of_y"));
		}
		super.configure();
	}
}
