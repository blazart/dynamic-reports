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
package net.sf.dynamicreports.design.base.barcode;

import net.sf.dynamicreports.design.definition.barcode.DRIDesignPostnetBarcode;
import net.sf.dynamicreports.report.constant.BarcodeBaselinePosition;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRDesignPostnetBarcode extends DRDesignChecksumBarcode implements DRIDesignPostnetBarcode {
	private Boolean displayChecksum;
	private Double shortBarHeight;
	private BarcodeBaselinePosition baselinePosition;
	private Double intercharGapWidth;
	
	public DRDesignPostnetBarcode() {
		super("POSTNET");
	}

	public Boolean getDisplayChecksum() {
		return displayChecksum;
	}

	public void setDisplayChecksum(Boolean displayChecksum) {
		this.displayChecksum = displayChecksum;
	}

	public Double getShortBarHeight() {
		return shortBarHeight;
	}

	public void setShortBarHeight(Double shortBarHeight) {
		this.shortBarHeight = shortBarHeight;
	}

	public BarcodeBaselinePosition getBaselinePosition() {
		return baselinePosition;
	}

	public void setBaselinePosition(BarcodeBaselinePosition baselinePosition) {
		this.baselinePosition = baselinePosition;
	}

	public Double getIntercharGapWidth() {
		return intercharGapWidth;
	}

	public void setIntercharGapWidth(Double intercharGapWidth) {
		this.intercharGapWidth = intercharGapWidth;
	}		
}
