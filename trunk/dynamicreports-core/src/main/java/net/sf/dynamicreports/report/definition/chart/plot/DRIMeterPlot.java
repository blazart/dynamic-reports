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

package net.sf.dynamicreports.report.definition.chart.plot;

import java.awt.Color;
import java.util.List;

import net.sf.dynamicreports.report.constant.MeterShape;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.style.DRIFont;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public interface DRIMeterPlot extends DRIPlot {

	public DRIExpression<? extends Number> getDataRangeLowExpression();

	public DRIExpression<? extends Number> getDataRangeHighExpression();

	public Color getValueColor();

	public String getValueMask();

	public DRIFont getValueFont();

	public MeterShape getShape();

	public List<DRIMeterInterval> getIntervals();

	public Integer getMeterAngle();

	public String getUnits();

	public Double getTickInterval();

	public Color getMeterBackgroundColor();

	public Color getNeedleColor();

	public Color getTickColor();

	public DRIFont getTickLabelFont();
}
