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

package net.sf.dynamicreports.report.base.style;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.style.DRIBorder;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DRBorder implements DRIBorder {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private DRPen topPen;
	private DRPen leftPen;
	private DRPen bottomPen;
	private DRPen rightPen;

	public DRBorder() {
		topPen = new DRPen();
		leftPen = new DRPen();
		bottomPen = new DRPen();
		rightPen = new DRPen();
	}

	public DRBorder(DRPen pen) {
		topPen = pen;
		leftPen = pen;
		bottomPen = pen;
		rightPen = pen;
	}

	@Override
	public DRPen getTopPen() {
		return topPen;
	}

	public void setTopPen(DRPen topPen) {
		this.topPen = topPen;
	}

	@Override
	public DRPen getLeftPen() {
		return leftPen;
	}

	public void setLeftPen(DRPen leftPen) {
		this.leftPen = leftPen;
	}

	@Override
	public DRPen getBottomPen() {
		return bottomPen;
	}

	public void setBottomPen(DRPen bottomPen) {
		this.bottomPen = bottomPen;
	}

	@Override
	public DRPen getRightPen() {
		return rightPen;
	}

	public void setRightPen(DRPen rightPen) {
		this.rightPen = rightPen;
	}
}
