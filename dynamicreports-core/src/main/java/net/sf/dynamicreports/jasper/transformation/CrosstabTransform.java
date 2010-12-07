/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 Ricardo Mariaca
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

package net.sf.dynamicreports.jasper.transformation;

import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstab;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabBucket;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabCell;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabCellContent;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabColumnGroup;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabGroup;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabMeasure;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabRowGroup;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabBucket;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabMeasure;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class CrosstabTransform {
	private JasperTransformAccessor accessor;
	
	public CrosstabTransform(JasperTransformAccessor accessor) {
		this.accessor = accessor;
	}
	
	protected JRDesignElement transform(DRIDesignCrosstab crosstab) {		
		JRDesignCrosstab jrCrosstab = new JRDesignCrosstab();
		
		jrCrosstab.setRepeatColumnHeaders(crosstab.isRepeatColumnHeaders());
		jrCrosstab.setRepeatRowHeaders(crosstab.isRepeatRowHeaders());
		jrCrosstab.setColumnBreakOffset(crosstab.getColumnBreakOffset());
		jrCrosstab.setIgnoreWidth(crosstab.getIgnoreWidth());
		jrCrosstab.setRunDirection(ConstantTransform.runDirection(crosstab.getRunDirection()));
		jrCrosstab.setWhenNoDataCell(cellContent(crosstab.getWhenNoDataCell()));
		jrCrosstab.setHeaderCell(cellContent(crosstab.getHeaderCell()));
		for (DRIDesignCrosstabColumnGroup columnGroup : crosstab.getColumnGroups()) {
			addColumnGroup(jrCrosstab, columnGroup);
		}
		for (DRIDesignCrosstabRowGroup rowGroup : crosstab.getRowGroups()) {
			addRowGroup(jrCrosstab, rowGroup);
		}
		for (DRIDesignCrosstabCell cell : crosstab.getCells()) {
			addCell(jrCrosstab, cell);
		}
		for (DRIDesignCrosstabMeasure measure : crosstab.getMeasures()) {
			addMeasure(jrCrosstab, measure);
		}
		
		//jrCrosstab.addParameter(JRCrosstabParameter parameter);//TODO
		//jrCrosstab.setParametersMapExpression(accessor.getExpressionTransform().getExpression(parametersExpression));//TODO		
		
		return jrCrosstab;
	}
	
	private JRDesignCellContents cellContent(DRIDesignCrosstabCellContent cellContent) {
		JRDesignCellContents jrCellContents = new JRDesignCellContents();
		JRDesignElement[] jrElements = accessor.getComponentTransform().component(cellContent.getComponent(), ListType.VERTICAL);
		for (JRDesignElement jrElement : jrElements) {
			jrCellContents.addElement(jrElement);
		}
		return jrCellContents;
	}

	private void group(JRDesignCrosstabGroup jrGroup, DRIDesignCrosstabGroup group) {
		jrGroup.setName(group.getName());
		jrGroup.setTotalPosition(ConstantTransform.crosstabTotalPosition(group.getTotalPosition()));
		jrGroup.setBucket(bucket(group.getBucket()));
		jrGroup.setHeader(cellContent(group.getHeader()));
		jrGroup.setTotalHeader(cellContent(group.getTotalHeader()));		
	}
	
	private JRDesignCrosstabBucket bucket(DRIDesignCrosstabBucket bucket) {		
		JRDesignCrosstabBucket jrBucket = new JRDesignCrosstabBucket();
		jrBucket.setOrder(ConstantTransform.orderType(bucket.getOrderType()));
		jrBucket.setExpression(accessor.getExpressionTransform().getExpression(bucket.getExpression()));
		jrBucket.setOrderByExpression(accessor.getExpressionTransform().getExpression(bucket.getOrderByExpression()));
		jrBucket.setComparatorExpression(accessor.getExpressionTransform().getExpression(bucket.getComparatorExpression()));
		return jrBucket;
	}
	
	private void addColumnGroup(JRDesignCrosstab jrCrosstab, DRIDesignCrosstabColumnGroup columnGroup) {
		JRDesignCrosstabColumnGroup jrColumnGroup = new JRDesignCrosstabColumnGroup();
		group(jrColumnGroup, columnGroup);
		jrColumnGroup.setHeight(columnGroup.getHeight());
		jrColumnGroup.setPosition(ConstantTransform.crosstabColumnPosition(columnGroup.getPosition()));
		
		try {
			jrCrosstab.addColumnGroup(jrColumnGroup);
		} catch (JRException e) {
			throw new JasperDesignException("Registration failed for crosstab column group \"" + columnGroup.getName() + "\"", e);
		}
	}
	
	private void addRowGroup(JRDesignCrosstab jrCrosstab, DRIDesignCrosstabRowGroup rowGroup) {
		JRDesignCrosstabRowGroup jrRowGroup = new JRDesignCrosstabRowGroup();
		group(jrRowGroup, rowGroup);
		jrRowGroup.setWidth(rowGroup.getWidth());
		jrRowGroup.setPosition(ConstantTransform.crosstabRowPosition(rowGroup.getPosition()));
		
		try {
			jrCrosstab.addRowGroup(jrRowGroup);
		} catch (JRException e) {
			throw new JasperDesignException("Registration failed for crosstab row group \"" + rowGroup.getName() + "\"", e);
		}
	}
	
	private void addCell(JRDesignCrosstab jrCrosstab, DRIDesignCrosstabCell cell) {
		JRDesignCrosstabCell jrCell = new JRDesignCrosstabCell();
		jrCell.setWidth(cell.getWidth());
		jrCell.setHeight(cell.getHeight());
		jrCell.setRowTotalGroup(cell.getRowTotalGroup());
		jrCell.setColumnTotalGroup(cell.getColumnTotalGroup());
		jrCell.setContents(cellContent(cell.getContent()));
		
		try {
			jrCrosstab.addCell(jrCell);
		} catch (JRException e) {
			throw new JasperDesignException("Registration failed for crosstab cell \"" + cell.getName() + "\"", e);
		}
	}
	
	private void addMeasure(JRDesignCrosstab jrCrosstab, DRIDesignCrosstabMeasure measure) {
		JRDesignCrosstabMeasure jrMeasure = new JRDesignCrosstabMeasure();
		jrMeasure.setName(measure.getName());
		jrMeasure.setValueExpression(accessor.getExpressionTransform().getExpression(measure.getValueExpression()));
		jrMeasure.setValueClassName(measure.getValueClass().getName());
		jrMeasure.setCalculation(ConstantTransform.calculation(measure.getCalculation()));
		jrMeasure.setPercentageType(ConstantTransform.crosstabPercentageType(measure.getPercentageType()));
		
		try {
			jrCrosstab.addMeasure(jrMeasure);
		} catch (JRException e) {
			throw new JasperDesignException("Registration failed for crosstab measure \"" + measure.getName() + "\"", e);
		}
	}
}
