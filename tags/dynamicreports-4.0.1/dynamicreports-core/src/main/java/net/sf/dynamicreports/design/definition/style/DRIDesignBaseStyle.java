/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2015 Ricardo Mariaca
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

package net.sf.dynamicreports.design.definition.style;

import java.awt.Color;

import net.sf.dynamicreports.report.constant.HorizontalImageAlignment;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.constant.Markup;
import net.sf.dynamicreports.report.constant.Rotation;
import net.sf.dynamicreports.report.constant.VerticalImageAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public interface DRIDesignBaseStyle {

	public Color getForegroundColor();

	public Color getBackgroundColor();

	public Integer getRadius();

	public ImageScale getImageScale();

	public HorizontalTextAlignment getHorizontalTextAlignment();

	public VerticalTextAlignment getVerticalTextAlignment();

	public HorizontalImageAlignment getHorizontalImageAlignment();

	public VerticalImageAlignment getVerticalImageAlignment();

	public DRIDesignBorder getBorder();

	public DRIDesignPadding getPadding();

	public DRIDesignFont getFont();

	public Rotation getRotation();

	public String getPattern();

	public Markup getMarkup();

	public DRIDesignParagraph getParagraph();

	public DRIDesignPen getLinePen();

}
