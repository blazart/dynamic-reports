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

package net.sf.dynamicreports.report;

import java.lang.reflect.ParameterizedType;

import net.sf.dynamicreports.report.constant.Calculation;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ReportUtils {
	private static int counter = 0;

	public static String generateUniqueName(String name) {
		if (counter == Integer.MAX_VALUE) {
			counter = 0;
		}
		return name + "_" + counter++ + "_";
	}

	public static Class<?> getVariableValueClass(Calculation calculation, Class<?> valueClass) {
		if (calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT)) {
			return Long.class;
		}
		if (calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION) ||
				calculation.equals(Calculation.VARIANCE)) {
			return Number.class;
		}
		return valueClass;
	}

	public static Class<?> getGenericClass(Object object, int index) {
		ParameterizedType genericSuperclass = getParameterizedType(object.getClass());
		if (genericSuperclass == null) {
			return String.class;
		}
		Class<?> rawType = getRawType(genericSuperclass.getActualTypeArguments()[index]);
		if (rawType == null) {
			return String.class;
		}
		return rawType;
	}

	private static ParameterizedType getParameterizedType(Class<?> classs) {
		if (classs == null) {
			return null;
		}
		if (classs.getGenericSuperclass() instanceof ParameterizedType) {
			return (ParameterizedType) classs.getGenericSuperclass();
		}
		return getParameterizedType((Class<?>) classs.getGenericSuperclass());
	}

	private static Class<?> getRawType(Object typeArgument) {
		if (typeArgument instanceof ParameterizedType) {
			return getRawType(((ParameterizedType) typeArgument).getRawType());
		} else {
			if (typeArgument instanceof Class<?>) {
				return (Class<?>) typeArgument;
			}
			else {
				return null;
			}
		}
	}
}
