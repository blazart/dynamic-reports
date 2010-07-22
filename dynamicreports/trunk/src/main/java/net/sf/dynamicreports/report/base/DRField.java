/* Dynamic reports - Free Java reporting library for creating reports dynamically
 *
 * (C) Copyright 2010 Ricardo Mariaca
 *
 * http://dynamicreports.sourceforge.net
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 3 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA. 
 */
package net.sf.dynamicreports.report.base;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.DRIField;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRField<T> implements DRIField<T> {	
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private String name;
	private Class<? super T> valueClass;
	
	public DRField(String name, Class<? super T> valueClass) {
		Validate.notEmpty(name, "name must not be empty");
		Validate.notNull(valueClass, "valueClass must not be null");
		this.name = name;
		this.valueClass = valueClass;
	}

	public String getName() {
		return name;
	}

	public Class<? super T> getValueClass() {
		return valueClass;
	}
}
