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

package net.sf.dynamicreports.report.builder;

import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.DRIValue;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class FieldBuilder<T> extends AbstractBuilder<FieldBuilder<T>, DRField<T>> implements DRIValue<T> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected FieldBuilder(String name, Class<? super T> valueClass) {
		super(new DRField<T>(name, valueClass));
	}
	
	public FieldBuilder<T> setDataType(DRIDataType<? super T, T> dataType) {
		getObject().setDataType(dataType);
		return this;
	}	
	
	public DRField<T> getField() {
		return build();
	}
	
	public String getName() {
		return getField().getName();
	}
}
