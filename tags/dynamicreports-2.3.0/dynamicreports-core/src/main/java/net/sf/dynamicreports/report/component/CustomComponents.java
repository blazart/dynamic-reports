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

package net.sf.dynamicreports.report.component;

import java.util.List;

import net.sf.jasperreports.extensions.ExtensionsEnvironment;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class CustomComponents {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static CustomComponentTransform<?, ?> getComponentTransform(Object component) {
		List<CustomComponentTransform> transforms = (List<CustomComponentTransform>) ExtensionsEnvironment.getExtensionsRegistry().getExtensions(CustomComponentTransform.class);
		for (CustomComponentTransform transform : transforms) {
			if (transform.isTransform(component)) {
				return transform;
			}
		}
		return null;
	}
}
