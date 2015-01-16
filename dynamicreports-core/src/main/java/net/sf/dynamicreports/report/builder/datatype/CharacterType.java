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

package net.sf.dynamicreports.report.builder.datatype;

import java.util.Locale;

import net.sf.dynamicreports.report.base.datatype.AbstractDataType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.defaults.Defaults;
import net.sf.dynamicreports.report.exception.DRException;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class CharacterType extends AbstractDataType<Character, Character> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	@Override
	public String getPattern() {
		return Defaults.getDefaults().getCharacterType().getPattern();
	}

	@Override
	public HorizontalAlignment getHorizontalAlignment() {
		return Defaults.getDefaults().getCharacterType().getHorizontalAlignment();
	}

	@Override
	public Character stringToValue(String value, Locale locale) throws DRException {
		if (StringUtils.isNotEmpty(value)) {
			return new Character(value.charAt(0));
		}
		return null;
	}
}