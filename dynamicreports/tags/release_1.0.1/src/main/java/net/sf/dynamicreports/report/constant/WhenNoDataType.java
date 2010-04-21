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
package net.sf.dynamicreports.report.constant;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public enum WhenNoDataType {
	/**
	 * Specifies that in case of empty datasources, there will be an empty report.
	 */
	NO_PAGES,
	/**
	 * Specifies that in case of empty datasources, there will be a report with just one blank page.
	 */
	BLANK_PAGE,
	/**
	 * Specifies that in case of empty datasources, all sections except detail will displayed.
	 */
	ALL_SECTIONS_NO_DETAIL,
	/**
	 * Specifies that in case of empty datasources, the NoData section will be displayed.
	 */
	NO_DATA_SECTION
}
