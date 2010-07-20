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
package net.sf.dynamicreports.jasper.base;

import java.util.HashMap;
import java.util.Map;

import net.sf.dynamicreports.design.definition.DRIDesignReport;
import net.sf.dynamicreports.jasper.transformation.BandTransform;
import net.sf.dynamicreports.jasper.transformation.BarcodeTransform;
import net.sf.dynamicreports.jasper.transformation.ChartTransform;
import net.sf.dynamicreports.jasper.transformation.ComponentTransform;
import net.sf.dynamicreports.jasper.transformation.ExpressionTransform;
import net.sf.dynamicreports.jasper.transformation.GroupTransform;
import net.sf.dynamicreports.jasper.transformation.JasperTransformAccessor;
import net.sf.dynamicreports.jasper.transformation.ReportTransform;
import net.sf.dynamicreports.jasper.transformation.StyleTransform;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperReportDesign implements JasperTransformAccessor {
	private DRIDesignReport report;
	private ReportTransform reportTransform;
	private ExpressionTransform expressionTransform;
	private BandTransform bandTransform;
	private ComponentTransform componentTransform;
	private GroupTransform groupTransform;
	private StyleTransform styleTransform;
	private ChartTransform chartTransform;
	private BarcodeTransform barcodeTransform;
	
	private JasperDesign design;
	private JasperCustomValues customValues;
	private Map<String, Object> parameters;	
	
	public JasperReportDesign(DRIDesignReport report) {		
		this.report = report;
		init();
		transform();
	}

	private void init() {		
		reportTransform = new ReportTransform(this);
		expressionTransform = new ExpressionTransform(this);
		groupTransform = new GroupTransform(this);
		bandTransform = new BandTransform(this);	
		componentTransform = new ComponentTransform(this);
		styleTransform = new StyleTransform(this);
		chartTransform = new ChartTransform(this);
		barcodeTransform = new BarcodeTransform(this);
		
		this.design = new JasperDesign();
		design.setName("Report");
		this.parameters = new HashMap<String, Object>();		
		this.customValues = new JasperCustomValues();
		
		this.design.setScriptletClass(JasperScriptlet.class.getName());
	}
	
	private void transform() {						
		reportTransform.transform();
		groupTransform.transform();
		expressionTransform.transform();		
		groupTransform.transformExpressions();
		styleTransform.transform();
		bandTransform.transform();
	}	
	
	public ChartTransform getChartTransform() {
		return chartTransform;
	}

	public BarcodeTransform getBarcodeTransform() {
		return barcodeTransform;
	}
	
	public ComponentTransform getComponentTransform() {
		return componentTransform;
	}

	public ExpressionTransform getExpressionTransform() {
		return expressionTransform;
	}

	public GroupTransform getGroupTransform() {
		return groupTransform;
	}

	public StyleTransform getStyleTransform() {
		return styleTransform;
	}	

	public DRIDesignReport getReport() {
		return report;
	}

	public JasperCustomValues getCustomValues() {
		return customValues;
	}
	
	public JasperDesign getDesign() {
		return design;
	}
	
	public Map<String, Object> getParameters() {
		return parameters;
	}
}
