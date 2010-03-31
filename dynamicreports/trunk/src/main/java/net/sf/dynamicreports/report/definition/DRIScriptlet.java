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
package net.sf.dynamicreports.report.definition;


/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public interface DRIScriptlet {
	
	public String getName();
	
	/**
	 * Called before the report is initialized.
	 */
	public void beforeReportInit(ReportParameters reportParameters);


	/**
	 * Called after the report is initialized.
	 */
	public void afterReportInit(ReportParameters reportParameters);


	/**
	 * Called before each page is initialized.
	 */
	public void beforePageInit(ReportParameters reportParameters);


	/**
	 * Called after each page is initialized.
	 */
	public void afterPageInit(ReportParameters reportParameters);


	/**
	 * Called before each column is initialized.
	 */
	public void beforeColumnInit(ReportParameters reportParameters);


	/**
	 * Called after each column is initialized.
	 */
	public void afterColumnInit(ReportParameters reportParameters);


	/**
	 * Called before a group is initialized.
	 * @param groupName the group name
	 */
	public void beforeGroupInit(String groupName, ReportParameters reportParameters);


	/**
	 * Called after a group is initialized.
	 * @param groupName the group name
	 */
	public void afterGroupInit(String groupName, ReportParameters reportParameters);


	/**
	 * Called before evaluating each detail.
	 */
	public void beforeDetailEval(ReportParameters reportParameters);


	/**
	 * Called after evaluating each detail.
	 */
	public void afterDetailEval(ReportParameters reportParameters);
}
