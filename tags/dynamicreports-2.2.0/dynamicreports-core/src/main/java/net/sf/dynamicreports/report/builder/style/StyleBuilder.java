/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2011 Ricardo Mariaca
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

import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.constant.Constants;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class StyleBuilder extends BaseStyleBuilder<StyleBuilder, DRStyle> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected StyleBuilder() {
		super(new DRStyle());
	}

	public StyleBuilder conditionalStyles(ConditionalStyleBuilder ...conditionalStyles) {
		return addConditionalStyle(conditionalStyles);
	}

	public StyleBuilder addConditionalStyle(ConditionalStyleBuilder ...conditionalStyles) {
		Validate.notNull(conditionalStyles, "conditionalStyles must not be null");
		Validate.noNullElements(conditionalStyles, "conditionalStyles must not contains null conditionalStyle");
		for (ConditionalStyleBuilder conditionalStyle : conditionalStyles) {
			getObject().addConditionalStyle(conditionalStyle.build());
		}
		return this;
	}

	public StyleBuilder setParentStyle(StyleBuilder parentStyle) {
		if (parentStyle != null) {
			getObject().setParentStyle(parentStyle.build());
		}
		else {
			getObject().setParentStyle(null);
		}
		return this;
	}
}
