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
package net.sf.dynamicreports.report.builder.column;

import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class ColumnBuilders {
	
	//text
	public <T> TextColumnBuilder<T> column(String fieldName, Class<T> valueClass) {
		return Columns.column(fieldName, valueClass);
	}

	public <T> TextColumnBuilder<T> column(String title, String fieldName, Class<T> valueClass) {
		return Columns.column(title, fieldName, valueClass);
	}

	public <T> TextColumnBuilder<T> column(String fieldName, DRIDataType<? super T, T> dataType) {
		return Columns.column(fieldName, dataType);
	}

	public <T> TextColumnBuilder<T> column(String title, String fieldName, DRIDataType<? super T, T> dataType) {
		return Columns.column(title, fieldName, dataType);
	}
	
	public <T> TextColumnBuilder<T> column(FieldBuilder<T> field) {
		return Columns.column(field);
	}	

	public <T> TextColumnBuilder<T> column(String title, FieldBuilder<T> field) {
		return Columns.column(title, field);
	}
	
	//expression
	public <T> TextColumnBuilder<T> column(DRISimpleExpression<T> expression) {
		return Columns.column(expression);
	}

	public <T> TextColumnBuilder<T> column(String title, DRISimpleExpression<T> expression) {
		return Columns.column(title, expression);
	}
	
	//percentage
	public PercentageColumnBuilder percentageColumn(ColumnBuilder<?, ? extends Number> column) {
		return Columns.percentageColumn(column);
	}	
	
	public PercentageColumnBuilder percentageColumn(String title, ColumnBuilder<?, ? extends Number> column) {
		return Columns.percentageColumn(title, column);
	}	
	
	public PercentageColumnBuilder percentageColumn(String fieldName, Class<? extends Number> valueClass) {
		return Columns.percentageColumn(fieldName, valueClass);
	}

	public PercentageColumnBuilder percentageColumn(String title, String fieldName, Class<? extends Number> valueClass) {
		return Columns.percentageColumn(title, fieldName, valueClass);
	}	
	
	public PercentageColumnBuilder percentageColumn(FieldBuilder<? extends Number> field) {
		return Columns.percentageColumn(field);
	}	
	
	public PercentageColumnBuilder percentageColumn(String title, FieldBuilder<? extends Number> field) {
		return Columns.percentageColumn(title, field);
	}	
	
	/*public PercentageColumnBuilder percentageColumn(DRISimpleExpression<? extends Number> expression) {
		return Columns.percentageColumn(expression);
	}
	
	public PercentageColumnBuilder percentageColumn(String title, DRISimpleExpression<? extends Number> expression) {
		return Columns.percentageColumn(title, expression);
	}*/		
	
	//column row number
	public TextColumnBuilder<Integer> columnRowNumberColumn() {
		return Columns.columnRowNumberColumn();
	}
	
	public TextColumnBuilder<Integer> columnRowNumberColumn(String title) {
		return Columns.columnRowNumberColumn(title);
	}
	
	//page row number
	public TextColumnBuilder<Integer> pageRowNumberColumn() {
		return Columns.pageRowNumberColumn();
	}
	
	public TextColumnBuilder<Integer> pageRowNumberColumn(String title) {
		return Columns.pageRowNumberColumn(title);
	}
	
	//report row number
	public TextColumnBuilder<Integer> reportRowNumberColumn() {
		return Columns.reportRowNumberColumn();
	}
	
	public TextColumnBuilder<Integer> reportRowNumberColumn(String title) {
		return Columns.reportRowNumberColumn(title);
	}
}