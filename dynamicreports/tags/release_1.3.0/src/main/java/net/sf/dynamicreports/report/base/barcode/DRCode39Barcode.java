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
package net.sf.dynamicreports.report.base.barcode;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.barcode.DRICode39Barcode;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class DRCode39Barcode extends DRChecksumBarcode implements DRICode39Barcode {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private Boolean displayChecksum;
	private Boolean displayStartStop;
	private Boolean extendedCharSetEnabled;
	private Double intercharGapWidth;
	private Double wideFactor;

	public Boolean getDisplayChecksum() {
		return displayChecksum;
	}

	public void setDisplayChecksum(Boolean displayChecksum) {
		this.displayChecksum = displayChecksum;
	}

	public Boolean getDisplayStartStop() {
		return displayStartStop;
	}

	public void setDisplayStartStop(Boolean displayStartStop) {
		this.displayStartStop = displayStartStop;
	}

	public Boolean getExtendedCharSetEnabled() {
		return extendedCharSetEnabled;
	}

	public void setExtendedCharSetEnabled(Boolean extendedCharSetEnabled) {
		this.extendedCharSetEnabled = extendedCharSetEnabled;
	}

	public Double getIntercharGapWidth() {
		return intercharGapWidth;
	}

	public void setIntercharGapWidth(Double intercharGapWidth) {
		this.intercharGapWidth = intercharGapWidth;
	}

	public Double getWideFactor() {
		return wideFactor;
	}

	public void setWideFactor(Double wideFactor) {
		this.wideFactor = wideFactor;
	}	
}
