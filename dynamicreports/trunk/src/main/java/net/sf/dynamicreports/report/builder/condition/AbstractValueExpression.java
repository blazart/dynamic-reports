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
package net.sf.dynamicreports.report.builder.condition;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.DRIValue;
import net.sf.dynamicreports.report.definition.ReportParameters;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public abstract class AbstractValueExpression<T extends Number> extends AbstractSimpleExpression<Boolean> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
		
	private DRIValue<T> value;
	private Number number;	
	
	public AbstractValueExpression(DRIValue<T> value, Number number) {
		Validate.notNull(value, "value must not be null");
		Validate.notNull(number, "number must not be null");
		this.value = value;
		this.number = number;		
	}
	
	public Boolean evaluate(ReportParameters reportParameters) {
		Number actualValue = reportParameters.getValue(value);
		if (actualValue != null) {
			return compare(actualValue, number);
		}
		return false;
	}
	
	@Override
	public Class<Boolean> getValueClass() {
		return Boolean.class;
	}
	
	protected abstract Boolean compare(Number actualValue, Number number);
}