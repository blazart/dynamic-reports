/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 Ricardo Mariaca
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

package net.sf.dynamicreports.report.builder;

import net.sf.dynamicreports.report.base.DRMargin;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class MarginBuilder extends AbstractBuilder<MarginBuilder, DRMargin> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected MarginBuilder() {
		super(new DRMargin());
	}

	protected MarginBuilder(int margin) {
		super(new DRMargin(margin));
	}
	
	public MarginBuilder setTop(int top) {
		getObject().setTop(top);
		return this;
	}

	public MarginBuilder setLeft(int left) {
		getObject().setLeft(left);
		return this;
	}

	public MarginBuilder setBottom(int bottom) {
		getObject().setBottom(bottom);
		return this;
	}

	public MarginBuilder setRight(int right) {
		getObject().setRight(right);
		return this;
	}
	
	public DRMargin getMargin() {
		return build();
	}
}
