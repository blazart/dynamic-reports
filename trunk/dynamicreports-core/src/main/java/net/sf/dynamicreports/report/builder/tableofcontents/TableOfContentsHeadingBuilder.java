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

package net.sf.dynamicreports.report.builder.tableofcontents;

import net.sf.dynamicreports.report.base.DRTableOfContentsHeading;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class TableOfContentsHeadingBuilder extends AbstractBuilder<TableOfContentsHeadingBuilder, DRTableOfContentsHeading> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	public TableOfContentsHeadingBuilder() {
		super(new DRTableOfContentsHeading());
	}

	public TableOfContentsHeadingBuilder setParentHeading(TableOfContentsHeadingBuilder parentHeading) {
		if (parentHeading != null) {
			getObject().setParentHeading(parentHeading.build());
		}
		else {
			getObject().setParentHeading(null);
		}
		return this;
	}

	public TableOfContentsHeadingBuilder setLabel(String label) {
		this.getObject().setLabelExpression(Expressions.text(label));
		return this;
	}

	public TableOfContentsHeadingBuilder setLabel(DRIExpression<String> labelExpression) {
		this.getObject().setLabelExpression(labelExpression);
		return this;
	}

	public TableOfContentsHeadingBuilder setCustomValue(Object customValue) {
		this.getObject().setCustomValueExpression(Expressions.value(customValue));
		return this;
	}

	public TableOfContentsHeadingBuilder setCustomValue(DRIExpression<?> customValueExpression) {
		this.getObject().setCustomValueExpression(customValueExpression);
		return this;
	}

	public DRTableOfContentsHeading getTableOfContentsHeading() {
		return build();
	}
}
