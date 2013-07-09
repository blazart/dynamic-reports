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

package net.sf.dynamicreports.report.builder.chart;

import java.awt.Color;

import net.sf.dynamicreports.report.base.chart.plot.DRAxisFormat;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class AxisFormatBuilder extends AbstractBuilder<AxisFormatBuilder, DRAxisFormat> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected AxisFormatBuilder() {
		super(new DRAxisFormat());
	}

	public AxisFormatBuilder setLabel(String label) {
		getObject().setLabelExpression(Expressions.text(label));
		return this;
	}

	public AxisFormatBuilder setLabel(DRIExpression<String> labelExpression) {
		getObject().setLabelExpression(labelExpression);
		return this;
	}

	public AxisFormatBuilder setLabelFont(FontBuilder labelFont) {
		Validate.notNull(labelFont, "labelFont must not be null");
		getObject().setLabelFont(labelFont.build());
		return this;
	}

	public AxisFormatBuilder setLabelColor(Color labelColor) {
		getObject().setLabelColor(labelColor);
		return this;
	}

	public AxisFormatBuilder setTickLabelFont(FontBuilder tickLabelFont) {
		Validate.notNull(tickLabelFont, "tickLabelFont must not be null");
		getObject().setTickLabelFont(tickLabelFont.build());
		return this;
	}

	public AxisFormatBuilder setTickLabelColor(Color tickLabelColor) {
		getObject().setTickLabelColor(tickLabelColor);
		return this;
	}

	public AxisFormatBuilder setTickLabelMask(String tickLabelMask) {
		getObject().setTickLabelMask(tickLabelMask);
		return this;
	}

	public AxisFormatBuilder setVerticalTickLabels(Boolean verticalTickLabels) {
		getObject().setVerticalTickLabels(verticalTickLabels);
		return this;
	}

	public AxisFormatBuilder setTickLabelRotation(Double tickLabelRotation) {
		getObject().setTickLabelRotation(tickLabelRotation);
		return this;
	}

	public AxisFormatBuilder setLineColor(Color lineColor) {
		getObject().setLineColor(lineColor);
		return this;
	}

	public AxisFormatBuilder setRangeMinValueExpression(Number rangeMinValue) {
		getObject().setRangeMinValueExpression(Expressions.number(rangeMinValue));
		return this;
	}

	public AxisFormatBuilder setRangeMinValueExpression(DRIExpression<? extends Number> rangeMinValueExpression) {
		getObject().setRangeMinValueExpression(rangeMinValueExpression);
		return this;
	}

	public AxisFormatBuilder setRangeMaxValueExpression(Number rangeMaxValue) {
		getObject().setRangeMaxValueExpression(Expressions.number(rangeMaxValue));
		return this;
	}

	public AxisFormatBuilder setRangeMaxValueExpression(DRIExpression<? extends Number> rangeMaxValueExpression) {
		getObject().setRangeMaxValueExpression(rangeMaxValueExpression);
		return this;
	}

	public DRAxisFormat getAxisFormat() {
		return build();
	}
}
