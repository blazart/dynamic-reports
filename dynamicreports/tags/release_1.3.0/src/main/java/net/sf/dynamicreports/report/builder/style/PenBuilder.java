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
package net.sf.dynamicreports.report.builder.style;

import java.awt.Color;

import net.sf.dynamicreports.report.base.style.DRPen;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.LineStyle;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("ucd")
public class PenBuilder extends AbstractBuilder<PenBuilder, DRPen> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	protected PenBuilder() {
		super(new DRPen());
	}

	protected PenBuilder(Float lineWidth, LineStyle lineStyle) {
		super(new DRPen(lineWidth, lineStyle));
	}
	
	public PenBuilder setLineWidth(Float lineWidth) {
		getObject().setLineWidth(lineWidth);
		return this;
	}
	
	public PenBuilder setLineStyle(LineStyle lineStyle) {
		getObject().setLineStyle(lineStyle);
		return this;
	}
	
	public PenBuilder setLineColor(Color lineColor) {
		getObject().setLineColor(lineColor);
		return this;
	}
	
	public DRPen getPen() {
		return build();
	}
}
