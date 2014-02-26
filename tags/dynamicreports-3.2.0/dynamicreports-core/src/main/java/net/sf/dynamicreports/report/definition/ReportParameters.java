/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2014 Ricardo Mariaca
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

package net.sf.dynamicreports.report.definition;

import java.sql.Connection;
import java.util.Locale;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public interface ReportParameters {
	public static final String CROSSTAB_ROW_COUNTER = "CROSSTAB_ROW_NUMBER";

	public <T> T getValue(String name);

	public <T> T getValue(DRIValue<T> value);

	public <T> T getFieldValue(String name);

	public <T> T getVariableValue(String name);

	public <T> T getParameterValue(String name);

	public Integer getPageNumber();

	public Integer getColumnNumber();

	public Integer getReportRowNumber();

	public Integer getPageRowNumber();

	public Integer getColumnRowNumber();

	public Integer getCrosstabRowNumber();
	
	public Integer getGroupCount(String groupName);

	public Connection getConnection();

	public Locale getLocale();

	public DRIScriptlet getScriptlet(String name);

	public String getMessage(String key);

	public String getMessage(String key, Object[] arguments);

	public ReportParameters getMasterParameters();
}
