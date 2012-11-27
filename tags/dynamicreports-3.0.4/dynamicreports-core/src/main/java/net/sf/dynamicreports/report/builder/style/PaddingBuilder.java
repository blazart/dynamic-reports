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

package net.sf.dynamicreports.report.builder.style;

import net.sf.dynamicreports.report.base.style.DRPadding;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class PaddingBuilder extends AbstractBuilder<PaddingBuilder, DRPadding> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected PaddingBuilder() {
		super(new DRPadding());
	}

	protected PaddingBuilder(int padding) {
		super(new DRPadding(padding));
	}

	public PaddingBuilder setTop(Integer top) {
		getObject().setTop(top);
		return this;
	}

	public PaddingBuilder setLeft(Integer left) {
		getObject().setLeft(left);
		return this;
	}

	public PaddingBuilder setBottom(Integer bottom) {
		getObject().setBottom(bottom);
		return this;
	}

	public PaddingBuilder setRight(Integer right) {
		getObject().setRight(right);
		return this;
	}

	public DRPadding getPadding() {
		return build();
	}
}
