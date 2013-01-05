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

package net.sf.dynamicreports.report.builder.style;

import java.awt.Color;

import net.sf.dynamicreports.report.base.style.DRPen;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.LineStyle;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class PenBuilder extends AbstractBuilder<PenBuilder, DRPen> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected PenBuilder() {
		super(new DRPen());
	}

	protected PenBuilder(Float lineWidth, LineStyle lineStyle) {
		super(new DRPen(lineWidth, lineStyle));
	}

	public PenBuilder setLineWidth(Float lineWidth) {
		getObject().setLineWidth(lineWidth);
		return this;
	}

	public PenBuilder setLineStyle(LineStyle lineStyle) {
		getObject().setLineStyle(lineStyle);
		return this;
	}

	public PenBuilder setLineColor(Color lineColor) {
		getObject().setLineColor(lineColor);
		return this;
	}

	public DRPen getPen() {
		return build();
	}
}
