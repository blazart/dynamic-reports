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

package net.sf.dynamicreports.adhoc.configuration;

import java.io.Serializable;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class AdhocConfiguration implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	private AdhocReport report;
	private AdhocFilter filter;

	public AdhocReport getReport() {
		return report;
	}

	public void setReport(AdhocReport report) {
		this.report = report;
	}

	public AdhocFilter getFilter() {
		return filter;
	}

	public void setFilter(AdhocFilter filter) {
		this.filter = filter;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null)
			return false;
		if (!(obj instanceof AdhocConfiguration))
			return false;

		AdhocConfiguration object = (AdhocConfiguration) obj;
		if (!(report == null ? object.getReport() == null : report.equals(object.getReport()))) {
			return false;
		}
		if (!(filter == null ? object.getFilter() == null : filter.equals(object.getFilter()))) {
			return false;
		}

		return true;
	}

	@Override
	public AdhocConfiguration clone() {
		AdhocConfiguration clone;
		try {
			clone = (AdhocConfiguration) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}

		if (report != null) {
			clone.report = report.clone();
		}
		if (filter != null) {
			clone.filter = filter.clone();
		}

		return clone;
	}

}
