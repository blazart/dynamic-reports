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

package net.sf.dynamicreports.report.base.barcode;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.barcode.DRIPdf417Barcode;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRPdf417Barcode extends DRBarcode implements DRIPdf417Barcode {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private Integer minColumns;
	private Integer maxColumns;
	private Integer minRows;
	private Integer maxRows;
	private Double widthToHeightRatio;
	private Integer errorCorrectionLevel;

	public Integer getMinColumns() {
		return minColumns;
	}

	public void setMinColumns(Integer minColumns) {
		this.minColumns = minColumns;
	}

	public Integer getMaxColumns() {
		return maxColumns;
	}

	public void setMaxColumns(Integer maxColumns) {
		this.maxColumns = maxColumns;
	}

	public Integer getMinRows() {
		return minRows;
	}

	public void setMinRows(Integer minRows) {
		this.minRows = minRows;
	}

	public Integer getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(Integer maxRows) {
		this.maxRows = maxRows;
	}

	public Double getWidthToHeightRatio() {
		return widthToHeightRatio;
	}

	public void setWidthToHeightRatio(Double widthToHeightRatio) {
		this.widthToHeightRatio = widthToHeightRatio;
	}

	public Integer getErrorCorrectionLevel() {
		return errorCorrectionLevel;
	}

	public void setErrorCorrectionLevel(Integer errorCorrectionLevel) {
		this.errorCorrectionLevel = errorCorrectionLevel;
	}	
}
