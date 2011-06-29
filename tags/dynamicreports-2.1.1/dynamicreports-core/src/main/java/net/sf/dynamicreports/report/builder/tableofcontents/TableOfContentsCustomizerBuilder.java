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

package net.sf.dynamicreports.report.builder.tableofcontents;

import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class TableOfContentsCustomizerBuilder extends AbstractBuilder<TableOfContentsCustomizerBuilder, TableOfContentsCustomizer> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	public TableOfContentsCustomizerBuilder() {
		super(new TableOfContentsCustomizer());
	}

	public TableOfContentsCustomizer getTableOfContents() {
		return build();
	}

	public TableOfContentsCustomizerBuilder setTitleStyle(StyleBuilder titleStyle) {
		this.getObject().setTitleStyle(titleStyle);
		return this;
	}

	public TableOfContentsCustomizerBuilder setHeadingStyle(StyleBuilder headingStyle) {
		this.getObject().setHeadingStyle(headingStyle);
		return this;
	}

	public TableOfContentsCustomizerBuilder setHeadingStyle(int level, StyleBuilder headingStyle) {
		this.getObject().setHeadingStyle(level, headingStyle);
		return this;
	}

	public TableOfContentsCustomizerBuilder setTextFixedWidth(Integer textFixedWidth) {
		this.getObject().setTextFixedWidth(textFixedWidth);
		return this;
	}

	public TableOfContentsCustomizerBuilder setDotsFixedWidth(Integer dotsFixedWidth) {
		this.getObject().setDotsFixedWidth(dotsFixedWidth);
		return this;
	}

	public TableOfContentsCustomizerBuilder setPageIndexFixedWidth(Integer pageIndexFixedWidth) {
		this.getObject().setPageIndexFixedWidth(pageIndexFixedWidth);
		return this;
	}
}
