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
package net.sf.dynamicreports.design.transformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.design.base.DRDesignBand;
import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.base.component.DRDesignFiller;
import net.sf.dynamicreports.design.base.component.DRDesignList;
import net.sf.dynamicreports.design.constant.DefaultStyleType;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.definition.DRIBand;
import net.sf.dynamicreports.report.definition.DRIReport;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class BandTransform {
	private DesignTransformAccessor accessor;
	private int maxWidth;
	private int maxColumnWidth;
	
	private DRDesignBand titleBand;
	private DRDesignBand pageHeaderBand;
	private DRDesignBand pageFooterBand;
	private DRDesignBand columnHeaderBand;
	private DRDesignBand columnFooterBand;
	private DRDesignBand detailBand;
	private DRDesignBand lastPageFooterBand;
	private DRDesignBand summaryBand;
	private DRDesignBand noDataBand;
	private DRDesignBand backgroundBand;	
	
	private Map<String, Integer> componentNames;
	
	public BandTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
		init();
	}
	
	private void init() {		
		componentNames = new HashMap<String, Integer>();
		TemplateTransform templateTransform = accessor.getTemplateTransform();
		maxWidth = templateTransform.getPageWidth() - templateTransform.getPageMargin().getLeft() - templateTransform.getPageMargin().getRight();
		maxColumnWidth = maxWidth;
		int columnsPerPage = templateTransform.getPageColumnsPerPage();
		maxColumnWidth = (maxColumnWidth - templateTransform.getPageColumnSpace() * (columnsPerPage - 1)) / columnsPerPage;
	}
	
	public void transform() throws DRException {
		DRIReport report = accessor.getReport();
		TemplateTransform templateTransform = accessor.getTemplateTransform();
		
		DRIBand band = report.getTitleBand();		
		titleBand = band("title", band, templateTransform.getTitleSplitType(band), ResetType.REPORT, null);
		
		band = report.getPageHeaderBand();
		pageHeaderBand = band("pageHeader", band, templateTransform.getPageHeaderSplitType(band), ResetType.PAGE, null);
		
		band = report.getPageFooterBand();
		pageFooterBand = band("pageFooter", band, templateTransform.getPageFooterSplitType(band), ResetType.PAGE, null);
		
		band = report.getColumnHeaderBand();
		columnHeaderBand = band("columnHeader", band, templateTransform.getColumnHeaderSplitType(band), ResetType.COLUMN, null);
		
		band = report.getColumnFooterBand();
		columnFooterBand = band("columnFooter", band, templateTransform.getColumnFooterSplitType(band), ResetType.COLUMN, null);
		
		band = report.getDetailBand();
		detailBand = band("detail", band, templateTransform.getDetailSplitType(band), ResetType.REPORT, null);
		
		band = report.getLastPageFooterBand();
		lastPageFooterBand = band("lastPageFooter", band, templateTransform.getLastPageFooterSplitType(band), ResetType.PAGE, null);
		
		band = report.getSummaryBand();
		summaryBand = band("summary", band, templateTransform.getSummarySplitType(band), ResetType.REPORT, null);
		
		band = report.getNoDataBand();
		noDataBand = band("noData", band, templateTransform.getNoDataSplitType(band), ResetType.REPORT, null);
		
		band = report.getBackgroundBand();
		backgroundBand = band("background", band, templateTransform.getBackgroundSplitType(band), ResetType.REPORT, null);	
	}
	
	public void prepareBands() throws DRException {
		titleBand = prepareBand(titleBand, maxWidth);
		pageHeaderBand = prepareBand(pageHeaderBand, maxWidth);
		pageFooterBand = prepareBand(pageFooterBand, maxWidth);
		columnHeaderBand = prepareBand(columnHeaderBand, maxColumnWidth);
		columnFooterBand = prepareBand(columnFooterBand, maxColumnWidth);
		detailBand = prepareBand(detailBand, maxColumnWidth);
		lastPageFooterBand = prepareBand(lastPageFooterBand, maxWidth);
		summaryBand = prepareBand(summaryBand, maxWidth);
		noDataBand = prepareBand(noDataBand, maxWidth);
		backgroundBand = prepareBand(backgroundBand, maxWidth);
		for (DRDesignGroup group : accessor.getGroupTransform().getGroups()) {
			List<DRDesignBand> bands = new ArrayList<DRDesignBand>();
			for (DRDesignBand band : group.getHeaderBands()) {
				DRDesignBand newBand = prepareBand(band, maxColumnWidth);
				if (newBand != null) {
					bands.add(newBand);
				}
			}
			group.setHeaderBands(bands);
			bands = new ArrayList<DRDesignBand>();
			for (DRDesignBand band : group.getFooterBands()) {
				DRDesignBand newBand = prepareBand(band, maxColumnWidth);
				if (newBand != null) {
					bands.add(newBand);
				}
			}
			group.setFooterBands(bands);
		}
	}
	
	private DRDesignBand prepareBand(DRDesignBand band, int maxWidth) throws DRException {
		if (band.getBandComponent() != null) {
			return band;
		}
		if (band.getList().isEmpty()) {
			return null;
		}

		ComponentPosition.component(band.getName(), band.getList(), maxWidth);
		
		DRDesignComponent component = removeEmptyComponents(band.getList());
		if (component == null) {
			return null;
		}
		generateComponentNames(component, band.getName());		
		band.setBandComponent(component);
		
		return band;
	}
	
	private void generateComponentNames(DRDesignComponent component, String bandName) {
		String componentName = bandName + "." + component.getName();
		if (!componentNames.containsKey(componentName)) {
			componentNames.put(componentName, new Integer(1));
		}
		else {
			componentNames.put(componentName, componentNames.get(componentName) + 1);
		}
		component.setName(componentName + componentNames.get(componentName));
		if (component instanceof DRDesignList) {
			for (DRDesignComponent lComponent : ((DRDesignList) component).getComponents()) {
				generateComponentNames(lComponent, bandName);
			}
		}
	}

	private DRDesignComponent removeEmptyComponents(DRDesignComponent component) {
		if (component instanceof DRDesignList) {
			DRDesignList list = (DRDesignList) component;
			if (list.getComponents().isEmpty()) {
				return null;
			}
			else if (list.getComponents().size() == 1) {
				DRDesignComponent lComponent = list.getComponents().get(0);
				DRDesignComponent elm = removeEmptyComponents(lComponent);
				if (elm == null) {
					return null;
				}
				elm.setX(lComponent.getX() + elm.getX());
				elm.setY(lComponent.getY() + elm.getY());

				if (list.getStyle() == null && list.getPrintWhenExpression() == null) {
					elm.setX(list.getX() + elm.getX());
					elm.setY(list.getY() + elm.getY());	
					return elm;
				}
				else {
					list.getComponents().clear();
					list.getComponents().add(elm);
					return list;
				}
			}
			else {
				List<DRDesignComponent> components = new ArrayList<DRDesignComponent>();
				for (DRDesignComponent listComponent : list.getComponents()) {
					DRDesignComponent comp = removeEmptyComponents(listComponent);
					if (comp != null) {
						components.add(comp);
					}
				}
				if (components.isEmpty()) {
					return null;
				}
				list.getComponents().clear();
				list.getComponents().addAll(components);
				return list;
			}
		}
		else if (component instanceof DRDesignFiller && component.getStyle() == null) {
			return null;
		}
		return component;
	}
	
	//band
	protected DRDesignBand band(String bandName, DRIBand band, SplitType splitType, ResetType resetType, DRDesignGroup resetGroup) throws DRException {
		DRDesignBand designBand = new DRDesignBand(bandName);
		designBand.setSplitType(splitType);
		designBand.setList(accessor.getComponentTransform().list(band.getList(), DefaultStyleType.TEXT, resetType, resetGroup));		
		return designBand;
	}
	
	protected DRDesignBand band(String bandName, DRIBand band, SplitType splitType) throws DRException {
		DRDesignBand designBand = new DRDesignBand(bandName);
		designBand.setSplitType(splitType);
		DRDesignList list = new DRDesignList();
		list.setType(band.getList().getType());
		list.setGap(accessor.getTemplateTransform().getListGap(band.getList()));
		list.setPrintWhenExpression((DRIDesignSimpleExpression) accessor.getExpressionTransform().transformExpression(band.getList().getPrintWhenExpression()));
		designBand.setList(list);		
		return designBand;
	}
	
	protected int getMaxWidth() {
		return maxWidth;
	}
	
	protected int getMaxColumnWidth() {
		return maxColumnWidth;
	}
	
	public DRDesignBand getTitleBand() {
		return titleBand;
	}

	public DRDesignBand getPageHeaderBand() {
		return pageHeaderBand;
	}
	
	public DRDesignBand getPageFooterBand() {
		return pageFooterBand;
	}
	
	public DRDesignBand getColumnHeaderBand() {
		return columnHeaderBand;
	}
	
	public DRDesignBand getColumnFooterBand() {
		return columnFooterBand;
	}
	
	public DRDesignBand getDetailBand() {
		return detailBand;
	}
	
	public DRDesignBand getLastPageFooterBand() {
		return lastPageFooterBand;
	}
	
	public DRDesignBand getSummaryBand() {
		return summaryBand;
	}
	
	public DRDesignBand getNoDataBand() {
		return noDataBand;
	}
	
	public DRDesignBand getBackgroundBand() {
		return backgroundBand;
	}
}