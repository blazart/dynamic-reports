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
package net.sf.dynamicreports.report.builder.group;

import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class Groups {

	//column
	public static ColumnGroupBuilder group(ColumnBuilder<?, ?> groupColumn) {
		return new ColumnGroupBuilder(groupColumn);	
	}

	public static ColumnGroupBuilder group(String name, ColumnBuilder<?, ?> groupColumn) {
		return new ColumnGroupBuilder(name, groupColumn);	
	}
	
	//custom
	public static CustomGroupBuilder group(String fieldName, Class<?> valueClass) {
		return group(DynamicReports.field(fieldName, valueClass));
	}

	public static CustomGroupBuilder group(String name, String fieldName, Class<?> valueClass) {
		return group(name, DynamicReports.field(fieldName, valueClass));
	}
	
	public static CustomGroupBuilder group(FieldBuilder<?> field) {
		return new CustomGroupBuilder(field);
	}

	public static CustomGroupBuilder group(String name, FieldBuilder<?> field) {
		return new CustomGroupBuilder(name, field);
	}
	
	public static CustomGroupBuilder group(DRISimpleExpression<?> expression) {
		return new CustomGroupBuilder(expression);
	}
	
	public static CustomGroupBuilder group(String name, DRISimpleExpression<?> expression) {
		return new CustomGroupBuilder(name, expression);
	}
}
