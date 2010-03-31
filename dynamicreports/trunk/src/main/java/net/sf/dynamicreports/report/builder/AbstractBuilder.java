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
package net.sf.dynamicreports.report.builder;

import java.io.Serializable;

import net.sf.dynamicreports.report.constant.Constants;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public abstract class AbstractBuilder<T extends AbstractBuilder<T, U>, U> implements Serializable {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private U object;
	protected boolean builded;
	
	protected AbstractBuilder(U object) {
		this.object = object;		
		this.builded = false;
	}
	
	public U build() {
		if (!builded) {
			configure();						
			builded = true;	
		}		
		return object;
	}
	
	protected U getObject() {
		return object;
	}
	
	protected void configure() {		
	}	
}
