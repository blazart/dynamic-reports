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
package net.sf.dynamicreports.jasper.transformation;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import net.sf.dynamicreports.design.definition.DRIDesignMargin;
import net.sf.dynamicreports.design.definition.DRIDesignPage;
import net.sf.dynamicreports.design.definition.DRIDesignParameter;
import net.sf.dynamicreports.design.definition.DRIDesignQuery;
import net.sf.dynamicreports.design.definition.DRIDesignReport;
import net.sf.dynamicreports.jasper.base.CustomScriptlet;
import net.sf.dynamicreports.jasper.base.JasperCustomValues;
import net.sf.dynamicreports.jasper.base.JasperReportParameters;
import net.sf.dynamicreports.jasper.constant.ValueType;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.dynamicreports.report.definition.DRIScriptlet;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRAbstractScriptlet;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignScriptlet;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ReportTransform {		
	private JasperTransformAccessor accessor;
	
	public ReportTransform(JasperTransformAccessor accessor) {
		this.accessor = accessor;
	}
	
	public void transform() {
		DRIDesignReport report = accessor.getReport();
		JasperDesign design = accessor.getDesign();		
		Map<String, Object> parameters = accessor.getParameters();
		
		addParameter(JasperCustomValues.CUSTOM_VALUES, JasperCustomValues.class, accessor.getCustomValues());
		addParameter(JasperReportParameters.MASTER_REPORT_PARAMETERS, ReportParameters.class, accessor.getMasterReportParameters());
		
		parameters.put(JRParameter.REPORT_LOCALE, report.getLocale());
		parameters.put(JRParameter.REPORT_RESOURCE_BUNDLE, report.getResourceBundle());
		design.setResourceBundle(report.getResourceBundleName());
		design.setIgnorePagination(report.isIgnorePagination());
		setProperties(report.getProperties());
		if (report.getQuery() != null) { 
			design.setQuery(query(report.getQuery()));
		}
		page();
		design.setWhenNoDataType(ConstantTransform.whenNoDataType(report.getWhenNoDataType()));
		design.setTitleNewPage(report.isTitleOnANewPage());
		design.setSummaryNewPage(report.isSummaryOnANewPage());
		design.setFloatColumnFooter(report.isFloatColumnFooter());
		
		for (DRIDesignParameter parameter : report.getParameters()) {
			addParameter(parameter);
		}
		for (DRIScriptlet scriptlet : report.getScriptlets()) {
			addScriptlet(scriptlet);
		}
	}
	
	private <T> void addParameter(String name, Class<T> parameterClass, T value) {
		JRDesignParameter jrParameter = new JRDesignParameter();
		jrParameter.setName(name);
		jrParameter.setValueClass(parameterClass);
		try {
			accessor.getDesign().addParameter(jrParameter);
		} catch (JRException e) {
			throw new JasperDesignException("Registration failed for parameter \"" + name + "\"", e);
		}		
		accessor.getParameters().put(jrParameter.getName(), value);
	}
	
	private void addParameter(DRIDesignParameter parameter) {
		try {
			accessor.getDesign().addParameter(parameter(parameter));		
			accessor.getCustomValues().addValueType(parameter.getName(), ValueType.PARAMETER);
			accessor.getParameters().put(parameter.getName(), parameter.getValue());
		} catch (JRException e) {
			throw new JasperDesignException("Registration failed for parameter \"" + parameter.getName() + "\"", e);
		}	
	}

	private void setProperties(Properties properties) {
		for (Iterator<Object> iterator = properties.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			accessor.getDesign().setProperty(key, properties.getProperty(key));
		}
	}

	private void addScriptlet(DRIScriptlet scriptlet) {
		try {
			accessor.getDesign().addScriptlet(scriptlet(scriptlet));	
			accessor.getParameters().put(scriptlet.getName() + "_SCRIPTLET", new CustomScriptlet(scriptlet));
		} catch (JRException e) {
			throw new JasperDesignException("Registration failed for scriptlet \"" + scriptlet.getName() + "\"", e);
		}	
	}
	
	//page
	private void page() {
		DRIDesignPage page = accessor.getReport().getPage();
		JasperDesign design = accessor.getDesign();
		
		design.setPageWidth(page.getWidth());
		design.setPageHeight(page.getHeight());
		design.setOrientation(ConstantTransform.pageOrientation(page.getOrientation()));
		DRIDesignMargin margin = page.getMargin();
		design.setLeftMargin(margin.getLeft());
		design.setRightMargin(margin.getRight());
		design.setTopMargin(margin.getTop());
		design.setBottomMargin(margin.getBottom());
		design.setColumnCount(page.getColumnsPerPage());
		design.setColumnSpacing(page.getColumnSpace());
		design.setColumnWidth(page.getColumnWidth());
	}
	
	//parameter
	private JRDesignParameter parameter(DRIDesignParameter parameter) {
		JRDesignParameter jrParameter = new JRDesignParameter();
		jrParameter.setName(parameter.getName());
		jrParameter.setValueClass(parameter.getValue().getClass());
		return jrParameter;
	}
	
	//scriptlet
	private JRDesignScriptlet scriptlet(DRIScriptlet scriptlet) {
		JRDesignScriptlet jrScriptlet = new JRDesignScriptlet();
		jrScriptlet.setName(scriptlet.getName());
		jrScriptlet.setValueClass(JRAbstractScriptlet.class);
		return jrScriptlet;
	}
	
	//query
	private JRDesignQuery query(DRIDesignQuery query) {
		JRDesignQuery jrQuery = new JRDesignQuery();
		jrQuery.setText(query.getText());
		jrQuery.setLanguage(ConstantTransform.queryLanguage(query.getLanguage()));
		return jrQuery;
	}
}