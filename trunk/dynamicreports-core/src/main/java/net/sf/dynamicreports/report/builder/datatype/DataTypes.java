/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2013 Ricardo Mariaca
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

package net.sf.dynamicreports.report.builder.datatype;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;

import org.apache.commons.lang3.Validate;

/**
 * A set of build in data types
 *
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DataTypes {
	private static final BigDecimalType bigDecimalType = new BigDecimalType();
	private static final BigIntegerType bigIntegerType = new BigIntegerType();
	private static final ByteType byteType = new ByteType();
	private static final DoubleType doubleType = new DoubleType();
	private static final FloatType floatType = new FloatType();
	private static final IntegerType integerType = new IntegerType();
	private static final LongType longType = new LongType();
	private static final ShortType shortType = new ShortType();
	private static final DateType dateType = new DateType();
	private static final DateYearToMonthType dateYearToMonthType = new DateYearToMonthType();
	private static final DateYearToHourType dateYearToHourType = new DateYearToHourType();
	private static final DateYearToMinuteType dateYearToMinuteType = new DateYearToMinuteType();
	private static final DateYearToSecondType dateYearToSecondType = new DateYearToSecondType();
	private static final DateYearToFractionType dateYearToFractionType = new DateYearToFractionType();
	private static final DateYearType dateYearType = new DateYearType();
	private static final DateMonthType dateMonthType = new DateMonthType();
	private static final DateDayType dateDayType = new DateDayType();
	private static final TimeHourToMinuteType timeHourToMinuteType = new TimeHourToMinuteType();
	private static final TimeHourToSecondType timeHourToSecondType = new TimeHourToSecondType();
	private static final TimeHourToFractionType timeHourToFractionType = new TimeHourToFractionType();
	private static final PercentageType percentageType = new PercentageType();
	private static final BooleanType booleanType = new BooleanType();
	private static final CharacterType characterType = new CharacterType();
	private static final StringType stringType = new StringType();
	private static final ListType listType = new ListType();

	public static <U, T extends DRIDataType<? super U, U>> T detectType(Class<U> dataType) throws DRException {
		return detectType(dataType.getName());
	}

	@SuppressWarnings("unchecked")
	public static <T extends DRIDataType<?, ?>> T detectType(String dataType) throws DRException {
		Validate.notNull(dataType, "dataType must not be null");

		String dataTypeLC = dataType.toLowerCase().trim();
		if (dataTypeLC.equals("bigdecimal") || dataType.equals(BigDecimal.class.getName())) {
			return (T) bigDecimalType;
		}
		if (dataTypeLC.equals("biginteger") || dataType.equals(BigInteger.class.getName())) {
			return (T) bigIntegerType;
		}
		if (dataTypeLC.equals("byte") || dataType.equals(Byte.class.getName())) {
			return (T) byteType;
		}
		if (dataTypeLC.equals("double") || dataType.equals(Double.class.getName())) {
			return (T) doubleType;
		}
		if (dataTypeLC.equals("float") || dataType.equals(Float.class.getName())) {
			return (T) floatType;
		}
		if (dataTypeLC.equals("integer") || dataType.equals(Integer.class.getName())) {
			return (T) integerType;
		}
		if (dataTypeLC.equals("long") || dataType.equals(Long.class.getName())) {
			return (T) longType;
		}
		if (dataTypeLC.equals("short") || dataType.equals(Short.class.getName())) {
			return (T) shortType;
		}
		if (dataTypeLC.equals("date") || dataType.equals(Date.class.getName())) {
			return (T) dateType;
		}
		if (dataTypeLC.equals("dateyeartomonth")) {
			return (T) dateYearToMonthType;
		}
		if (dataTypeLC.equals("dateyeartohour")) {
			return (T) dateYearToHourType;
		}
		if (dataTypeLC.equals("dateyeartominute")) {
			return (T) dateYearToMinuteType;
		}
		if (dataTypeLC.equals("dateyeartosecond")) {
			return (T) dateYearToSecondType;
		}
		if (dataTypeLC.equals("dateyeartofraction")) {
			return (T) dateYearToFractionType;
		}
		if (dataTypeLC.equals("dateyear")) {
			return (T) dateYearType;
		}
		if (dataTypeLC.equals("datemonth")) {
			return (T) dateMonthType;
		}
		if (dataTypeLC.equals("dateday")) {
			return (T) dateDayType;
		}
		if (dataTypeLC.equals("timehourtominute")) {
			return (T) timeHourToMinuteType;
		}
		if (dataTypeLC.equals("timehourtosecond")) {
			return (T) timeHourToSecondType;
		}
		if (dataTypeLC.equals("timehourtofraction")) {
			return (T) timeHourToFractionType;
		}
		if (dataTypeLC.equals("percentage")) {
			return (T) percentageType;
		}
		if (dataTypeLC.equals("boolean") || dataType.equals(Boolean.class.getName())) {
			return (T) booleanType;
		}
		if (dataTypeLC.equals("character") || dataType.equals(Character.class.getName())) {
			return (T) characterType;
		}
		if (dataTypeLC.equals("string") || dataTypeLC.equals("text") || dataType.equals(String.class.getName())) {
			return (T) stringType;
		}
		if (dataTypeLC.equals("list") || dataType.equals(List.class.getName())) {
			return (T) listType;
		}

		throw new DRException("Data type \"" + dataType + "\" is not supported");
	}

	public static BigDecimalType bigDecimalType() {
		return bigDecimalType;
	}

	public static BigIntegerType bigIntegerType() {
		return bigIntegerType;
	}

	public static BooleanType booleanType() {
		return booleanType;
	}

	public static ByteType byteType() {
		return byteType;
	}

	public static DateType dateType() {
		return dateType;
	}

	public static DateYearToFractionType dateYearToFractionType() {
		return dateYearToFractionType;
	}

	public static DateYearType dateYearType() {
		return dateYearType;
	}

	public static DateMonthType dateMonthType() {
		return dateMonthType;
	}

	public static DateDayType dateDayType() {
		return dateDayType;
	}

	public static DateYearToHourType dateYearToHourType() {
		return dateYearToHourType;
	}

	public static DateYearToMinuteType dateYearToMinuteType() {
		return dateYearToMinuteType;
	}

	public static DateYearToMonthType dateYearToMonthType() {
		return dateYearToMonthType;
	}

	public static DateYearToSecondType dateYearToSecondType() {
		return dateYearToSecondType;
	}

	public static DoubleType doubleType() {
		return doubleType;
	}

	public static FloatType floatType() {
		return floatType;
	}

	public static CharacterType characterType() {
		return characterType;
	}

	public static IntegerType integerType() {
		return integerType;
	}

	public static LongType longType() {
		return longType;
	}

	public static ShortType shortType() {
		return shortType;
	}

	public static StringType stringType() {
		return stringType;
	}

	public static ListType listType() {
		return listType;
	}

	public static TimeHourToFractionType timeHourToFractionType() {
		return timeHourToFractionType;
	}

	public static TimeHourToMinuteType timeHourToMinuteType() {
		return timeHourToMinuteType;
	}

	public static TimeHourToSecondType timeHourToSecondType() {
		return timeHourToSecondType;
	}

	public static PercentageType percentageType() {
		return percentageType;
	}
}
