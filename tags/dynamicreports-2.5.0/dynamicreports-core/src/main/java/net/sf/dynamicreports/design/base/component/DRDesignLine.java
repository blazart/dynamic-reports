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

package net.sf.dynamicreports.design.base.component;

import net.sf.dynamicreports.design.base.style.DRDesignPen;
import net.sf.dynamicreports.design.definition.component.DRIDesignLine;
import net.sf.dynamicreports.report.constant.LineDirection;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignLine extends DRDesignComponent implements DRIDesignLine {
	private LineDirection direction;
	private DRDesignPen pen;

	public DRDesignLine() {
		super("line");
	}

	public LineDirection getDirection() {
		return direction;
	}

	public void setDirection(LineDirection direction) {
		this.direction = direction;
	}

	public DRDesignPen getPen() {
		return pen;
	}

	public void setPen(DRDesignPen pen) {
		this.pen = pen;
	}
}
