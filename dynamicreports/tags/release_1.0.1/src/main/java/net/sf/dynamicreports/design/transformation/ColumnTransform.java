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
import java.util.List;

import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.base.component.DRDesignTextField;
import net.sf.dynamicreports.design.constant.DefaultStyleType;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.base.style.DRBorder;
import net.sf.dynamicreports.report.base.style.DRConditionalStyle;
import net.sf.dynamicreports.report.base.style.DRFont;
import net.sf.dynamicreports.report.base.style.DRPadding;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.definition.DRIColumn;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;
import net.sf.dynamicreports.report.definition.style.DRIConditionalStyle;
import net.sf.dynamicreports.report.definition.style.DRISimpleStyle;
import net.sf.dynamicreports.report.definition.style.DRIStyle;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ColumnTransform {
	private DesignTransformAccessor accessor;
	
	public ColumnTransform(DesignTransformAccessor accessor) {
		this.accessor = accessor;
	}
	
	//columns
	public void transform() throws DRException {
		ColumnGrid columnTitle = accessor.getColumnGridTransform().createColumnGrid(accessor.getStyleTransform().getDefaultStyle(DefaultStyleType.COLUMN_TITLE));
		ColumnGrid detail = accessor.getColumnGridTransform().createColumnGrid();
		
		for (DRIColumn<?> column : accessor.getReport().getColumns()) {
			if (!accessor.getGroupTransform().getHideGroupColumns().contains(column)) {
				if (column.getTitleExpression() != null) {
					columnTitle.addComponent(column, titleComponent(column));
				}
				detail.addComponent(column, valueComponent(column));
			}
		}		
		
		
		if (!columnTitle.isEmpty() && accessor.getTemplateTransform().isShowColumnTitle()) {
			accessor.getBandTransform().getColumnHeaderBand().addComponent(0, columnTitle.getList());
		}
		accessor.getBandTransform().getDetailBand().addComponent(detail.getList());
	}
	
	//title
	@SuppressWarnings("unchecked")
	private DRDesignComponent titleComponent(DRIColumn<?> column) throws DRException {
		DRTextField titleField = new DRTextField();
		titleField.setValueExpression(column.getTitleExpression());
		titleField.setStyle((DRStyle) column.getTitleStyle());
		titleField.setWidth(accessor.getTemplateTransform().getColumnWidth(column, accessor.getStyleTransform().getDefaultStyle(DefaultStyleType.COLUMN)));
		titleField.setHeight(column.getTitleHeight());
		titleField.setHeightType(column.getTitleHeightType());
		titleField.setRows(column.getTitleRows());
		DRDesignTextField designTitleField = accessor.getComponentTransform().textField(titleField, DefaultStyleType.COLUMN_TITLE);
		designTitleField.setName("column_" + column.getName() + ".title");		
		return designTitleField;	
	}
	
	//value
	private DRDesignComponent valueComponent(DRIColumn<?> column) throws DRException {
		DRDesignTextField designValueField = accessor.getComponentTransform().textField(column.getValueField(), DefaultStyleType.COLUMN);	
		designValueField.setName("column_" + column.getName());
		designValueField.setPrintRepeatedValues(accessor.getTemplateTransform().isColumnPrintRepeatedDetailValues(column));

		List<DRIConditionalStyle> rowHighlighters = new ArrayList<DRIConditionalStyle>();
		rowHighlighters.addAll(getDetailRowHighlighters());
		DRISimpleStyle detailOddRowStyle = accessor.getTemplateTransform().getDetailOddRowStyle();
		if (detailOddRowStyle != null) {			
			rowHighlighters.add(detailRowConditionalStyle(detailOddRowStyle, Expressions.printInOddRow()));
		}
		DRISimpleStyle detailEvenRowStyle = accessor.getTemplateTransform().getDetailEvenRowStyle();
		if (detailEvenRowStyle != null) {			
			rowHighlighters.add(detailRowConditionalStyle(detailEvenRowStyle, Expressions.printInEvenRow()));
		}
		if (!rowHighlighters.isEmpty()) {
			DRIStyle style = column.getValueField().getStyle();
			if (style == null) {
				style = accessor.getTemplateTransform().getColumnStyle();
			}
			DRStyle newStyle = new DRStyle();
			newStyle.setParentStyle((DRStyle) style);			
			for (DRIConditionalStyle conditionalStyle : style.getConditionalStyles()) {
				newStyle.addConditionalStyle((DRConditionalStyle) conditionalStyle);
			}
			for (DRIConditionalStyle conditionalStyle : rowHighlighters) {
				newStyle.addConditionalStyle((DRConditionalStyle) conditionalStyle);
			}			
			designValueField.setStyle(accessor.getStyleTransform().transformStyle(newStyle, true, DefaultStyleType.COLUMN));
		}
		
		return designValueField;
	}
	
	private List<? extends DRIConditionalStyle> getDetailRowHighlighters() {
		return accessor.getReport().getDetailRowHighlighters();
	}
	
	private DRConditionalStyle detailRowConditionalStyle(DRISimpleStyle style, DRISimpleExpression<Boolean> expression) {
		DRConditionalStyle conditionalStyle = new DRConditionalStyle(expression);
		conditionalStyle.setBackgroundColor(style.getBackgroundColor());
		conditionalStyle.setBorder((DRBorder) style.getBorder());
		conditionalStyle.setFont((DRFont) style.getFont());
		conditionalStyle.setForegroundColor(style.getForegroundColor());
		conditionalStyle.setHorizontalAlignment(style.getHorizontalAlignment());
		conditionalStyle.setImageScale(style.getImageScale());
		conditionalStyle.setPadding((DRPadding) style.getPadding());
		conditionalStyle.setPattern(style.getPattern());
		conditionalStyle.setRadius(style.getRadius());
		conditionalStyle.setRotation(style.getRotation());
		conditionalStyle.setVerticalAlignment(style.getVerticalAlignment());
		return conditionalStyle;
	}
}
