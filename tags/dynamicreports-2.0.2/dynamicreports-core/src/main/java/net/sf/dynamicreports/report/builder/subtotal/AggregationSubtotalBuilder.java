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

package net.sf.dynamicreports.report.builder.subtotal;

import net.sf.dynamicreports.report.base.DRVariable;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.Evaluation;
import net.sf.dynamicreports.report.constant.SubtotalPosition;
import net.sf.dynamicreports.report.definition.DRIValue;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;
import net.sf.dynamicreports.report.exception.DRReportException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class AggregationSubtotalBuilder<T> extends SubtotalBuilder<AggregationSubtotalBuilder<T>, T> implements DRIValue<T> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private DRIExpression<?> expression;
	private Calculation calculation;
	
	//column
	protected AggregationSubtotalBuilder(ValueColumnBuilder<?, ?> column, Calculation calculation) {
		this(column.getColumn(), column, calculation);
		if (calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT)) {
			setDataType(DataTypes.longType());
		}
		else if (calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION) ||
				calculation.equals(Calculation.VARIANCE)) {
			setDataType(DataTypes.doubleType());
		}
		else {
			setDataType(column.getColumn().getComponent().getDataType());
			setPattern(column.getColumn().getComponent().getPattern());
		}
	}
	
	//simple expression
	protected AggregationSubtotalBuilder(DRISimpleExpression<?> expression, ColumnBuilder<?, ?> showInColumn, Calculation calculation) {
		this((DRIExpression<?>) expression, showInColumn, calculation);
	}
	
	//field	
	protected AggregationSubtotalBuilder(FieldBuilder<?> field, ColumnBuilder<?, ?> showInColumn, Calculation calculation) {
		this(field.build(), showInColumn, calculation);
		if (calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT)) {
			setDataType(DataTypes.longType());
		}
		else if (calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION) ||
				calculation.equals(Calculation.VARIANCE)) {
			setDataType(DataTypes.doubleType());
		}
		else {
			setDataType(field.getField().getDataType());
		}
	}

	private AggregationSubtotalBuilder(DRIExpression<?> expression, ColumnBuilder<?, ?> showInColumn, Calculation calculation) {
		super(showInColumn);
		this.expression = expression;
		this.calculation = calculation;
	}
		
	@Override
	protected void configure() {
		DRVariable<T> subtotalVariable = new DRVariable<T>(expression, calculation);
		Evaluation resetType = subtotalPositionToEvaluation(getObject().getPosition());
		subtotalVariable.setResetType(resetType);			
		subtotalVariable.setResetGroup(getObject().getGroup());		
		setValueExpression(subtotalVariable);
		
		super.configure();
	}
		
	private static Evaluation subtotalPositionToEvaluation(SubtotalPosition position) {		
		switch (position) {
		case PAGE_HEADER:			
		case PAGE_FOOTER:			
			return Evaluation.PAGE;
		case COLUMN_HEADER:			
		case COLUMN_FOOTER:			
			return Evaluation.COLUMN;
		case GROUP_HEADER:			
		case GROUP_FOOTER:			
			return Evaluation.GROUP;
		case FIRST_GROUP_HEADER:			
		case FIRST_GROUP_FOOTER:			
			return Evaluation.FIRST_GROUP;
		case LAST_GROUP_HEADER:			
		case LAST_GROUP_FOOTER:			
			return Evaluation.LAST_GROUP;
		case TITLE:			
		case LAST_PAGE_FOOTER:			
		case SUMMARY:			
			return Evaluation.REPORT;
		default:
			throw new DRReportException("Subtotal position " + position.name() + " not supported");
		}
	}
	
	public String getName() {
		return getSubtotal().getName();
	}
}
