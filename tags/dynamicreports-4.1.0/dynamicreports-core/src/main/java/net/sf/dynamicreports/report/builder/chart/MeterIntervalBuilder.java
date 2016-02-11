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

package net.sf.dynamicreports.report.builder.chart;

import java.awt.Color;

import net.sf.dynamicreports.report.base.chart.plot.DRMeterInterval;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class MeterIntervalBuilder extends AbstractBuilder<MeterIntervalBuilder, DRMeterInterval> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected MeterIntervalBuilder() {
		super(new DRMeterInterval());
	}

	public MeterIntervalBuilder setLabel(String label) {
		getObject().setLabel(label);
		return this;
	}

	public MeterIntervalBuilder setBackgroundColor(Color backgroundColor) {
		getObject().setBackgroundColor(backgroundColor);
		return this;
	}

	public MeterIntervalBuilder setAlpha(Double alpha) {
		getObject().setAlpha(alpha);
		return this;
	}

	public MeterIntervalBuilder setDataRangeLowExpression(Number dataRangeLowValue) {
		getObject().setDataRangeLowExpression(Expressions.number(dataRangeLowValue));
		return this;
	}

	public MeterIntervalBuilder setDataRangeLowExpression(DRIExpression<? extends Number> dataRangeLowExpression) {
		getObject().setDataRangeLowExpression(dataRangeLowExpression);
		return this;
	}

	public MeterIntervalBuilder setDataRangeHighExpression(Number dataRangeHighValue) {
		getObject().setDataRangeHighExpression(Expressions.number(dataRangeHighValue));
		return this;
	}

	public MeterIntervalBuilder setDataRangeHighExpression(DRIExpression<? extends Number> dataRangeHighExpression) {
		getObject().setDataRangeHighExpression(dataRangeHighExpression);
		return this;
	}

}
