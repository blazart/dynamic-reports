/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2012 Ricardo Mariaca
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

package net.sf.dynamicreports.adhoc.transformation;

import java.awt.Color;
import java.util.List;

import net.sf.dynamicreports.adhoc.configuration.AdhocAxisFormat;
import net.sf.dynamicreports.adhoc.configuration.AdhocCalculation;
import net.sf.dynamicreports.adhoc.configuration.AdhocChart;
import net.sf.dynamicreports.adhoc.configuration.AdhocChartSerie;
import net.sf.dynamicreports.adhoc.configuration.AdhocChartType;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocComponent;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocFilter;
import net.sf.dynamicreports.adhoc.configuration.AdhocFont;
import net.sf.dynamicreports.adhoc.configuration.AdhocGroup;
import net.sf.dynamicreports.adhoc.configuration.AdhocGroupHeaderLayout;
import net.sf.dynamicreports.adhoc.configuration.AdhocHorizontalAlignment;
import net.sf.dynamicreports.adhoc.configuration.AdhocOrderType;
import net.sf.dynamicreports.adhoc.configuration.AdhocOrientation;
import net.sf.dynamicreports.adhoc.configuration.AdhocPage;
import net.sf.dynamicreports.adhoc.configuration.AdhocPageOrientation;
import net.sf.dynamicreports.adhoc.configuration.AdhocPen;
import net.sf.dynamicreports.adhoc.configuration.AdhocProperties;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.dynamicreports.adhoc.configuration.AdhocRestriction;
import net.sf.dynamicreports.adhoc.configuration.AdhocSort;
import net.sf.dynamicreports.adhoc.configuration.AdhocStyle;
import net.sf.dynamicreports.adhoc.configuration.AdhocSubtotal;
import net.sf.dynamicreports.adhoc.configuration.AdhocTextField;
import net.sf.dynamicreports.adhoc.configuration.AdhocTimePeriod;
import net.sf.dynamicreports.adhoc.configuration.AdhocValueOperator;
import net.sf.dynamicreports.adhoc.configuration.AdhocValueRestriction;
import net.sf.dynamicreports.adhoc.configuration.AdhocVerticalAlignment;
import net.sf.dynamicreports.adhoc.exception.AdhocException;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocAxisFormat;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocCalculation;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocChart;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocChartSerie;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocChartType;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocColumn;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocComponent;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocConfiguration;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocFilter;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocFont;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocGroup;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocGroupHeaderLayout;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocHorizontalAlignment;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocOrderType;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocOrientation;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocPage;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocPageOrientation;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocPen;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocProperty;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocReport;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocRestriction;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocSort;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocStyle;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocSubtotal;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocTextField;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocValueOperator;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocValueRestriction;
import net.sf.dynamicreports.adhoc.xmlconfiguration.XmlAdhocVerticalAlignment;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class AdhocToXmlTransform {

	public XmlAdhocConfiguration transform(AdhocConfiguration adhocConfiguration) {
		XmlAdhocConfiguration xmlAdhocConfiguration = new XmlAdhocConfiguration();
		xmlAdhocConfiguration.setReport(report(adhocConfiguration.getReport()));
		xmlAdhocConfiguration.setFilter(filter(adhocConfiguration.getFilter()));
		return xmlAdhocConfiguration;
	}

	protected void properties(AdhocProperties properties, List<XmlAdhocProperty> xmlAdhocProperties) {
		for (String key : properties.getProperties().keySet()) {
			XmlAdhocProperty xmlAdhocProperty = new XmlAdhocProperty();
			xmlAdhocProperty.setKey(key);
			Object propertyValue = properties.getProperty(key);
			xmlAdhocProperty.setValue(propertyValueToString(propertyValue));
			if (propertyValue != null && !(propertyValue instanceof String)) {
				xmlAdhocProperty.setValueClass(propertyValue.getClass().getName());
			}
			xmlAdhocProperties.add(xmlAdhocProperty);
		}
	}

	protected String propertyValueToString(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof String) {
			return (String) value;
		}
		if (value instanceof Boolean || value instanceof Integer) {
			return String.valueOf(value);
		}
		if (value instanceof AdhocTimePeriod) {
			return ((AdhocTimePeriod) value).name();
		}
		throw new AdhocException("Property value type " + value.getClass().getName() + " not supported");
	}

	protected XmlAdhocReport report(AdhocReport adhocReport) {
		if (adhocReport == null) {
			return null;
		}

		XmlAdhocReport xmlAdhocReport = new XmlAdhocReport();
		xmlAdhocReport.setTextStyle(style(adhocReport.getTextStyle()));
		xmlAdhocReport.setColumnStyle(style(adhocReport.getColumnStyle()));
		xmlAdhocReport.setColumnTitleStyle(style(adhocReport.getColumnTitleStyle()));
		xmlAdhocReport.setGroupStyle(style(adhocReport.getGroupStyle()));
		xmlAdhocReport.setGroupTitleStyle(style(adhocReport.getGroupTitleStyle()));
		xmlAdhocReport.setSubtotalStyle(style(adhocReport.getSubtotalStyle()));
		xmlAdhocReport.setDetailOddRowStyle(style(adhocReport.getDetailOddRowStyle()));
		xmlAdhocReport.setHighlightDetailOddRows(adhocReport.getHighlightDetailOddRows());
		xmlAdhocReport.setDetailEvenRowStyle(style(adhocReport.getDetailEvenRowStyle()));
		xmlAdhocReport.setHighlightDetailEvenRows(adhocReport.getHighlightDetailEvenRows());
		xmlAdhocReport.setIgnorePagination(adhocReport.getIgnorePagination());
		xmlAdhocReport.setTableOfContents(adhocReport.getTableOfContents());
		xmlAdhocReport.setPage(page(adhocReport.getPage()));
		if (adhocReport.getColumns() != null && !adhocReport.getColumns().isEmpty()) {
			for (AdhocColumn adhocColumn : adhocReport.getColumns()) {
				xmlAdhocReport.getColumn().add(column(adhocColumn));
			}
		}
		if (adhocReport.getGroups() != null && !adhocReport.getGroups().isEmpty()) {
			for (AdhocGroup adhocGroup : adhocReport.getGroups()) {
				xmlAdhocReport.getGroup().add(group(adhocGroup));
			}
		}
		if (adhocReport.getSorts() != null && !adhocReport.getSorts().isEmpty()) {
			for (AdhocSort adhocSort : adhocReport.getSorts()) {
				xmlAdhocReport.getSort().add(sort(adhocSort));
			}
		}
		if (adhocReport.getSubtotals() != null && !adhocReport.getSubtotals().isEmpty()) {
			for (AdhocSubtotal adhocSubtotal : adhocReport.getSubtotals()) {
				xmlAdhocReport.getSubtotal().add(subtotal(adhocSubtotal));
			}
		}
		if (adhocReport.getComponents() != null && !adhocReport.getComponents().isEmpty()) {
			for (AdhocComponent adhocComponent : adhocReport.getComponents()) {
				xmlAdhocReport.getComponent().add(component(adhocComponent));
			}
		}

		return xmlAdhocReport;
	}

	protected XmlAdhocColumn column(AdhocColumn adhocColumn) {
		XmlAdhocColumn xmlAdhocColumn = new XmlAdhocColumn();
		xmlAdhocColumn.setName(adhocColumn.getName());
		xmlAdhocColumn.setTitle(adhocColumn.getTitle());
		xmlAdhocColumn.setWidth(adhocColumn.getWidth());
		xmlAdhocColumn.setStyle(style(adhocColumn.getStyle()));
		xmlAdhocColumn.setTitleStyle(style(adhocColumn.getTitleStyle()));
		if (adhocColumn.getProperties() != null && !adhocColumn.getProperties().isEmpty()) {
			properties(adhocColumn.getProperties(), xmlAdhocColumn.getProperty());
		}
		return xmlAdhocColumn;
	}

	protected XmlAdhocGroup group(AdhocGroup adhocGroup) {
		XmlAdhocGroup xmlAdhocGroup = new XmlAdhocGroup();
		xmlAdhocGroup.setName(adhocGroup.getName());
		xmlAdhocGroup.setStartInNewPage(adhocGroup.getStartInNewPage());
		xmlAdhocGroup.setHeaderLayout(groupHeaderLayout(adhocGroup.getHeaderLayout()));
		xmlAdhocGroup.setStyle(style(adhocGroup.getStyle()));
		xmlAdhocGroup.setTitleStyle(style(adhocGroup.getTitleStyle()));
		if (adhocGroup.getProperties() != null && !adhocGroup.getProperties().isEmpty()) {
			properties(adhocGroup.getProperties(), xmlAdhocGroup.getProperty());
		}
		return xmlAdhocGroup;
	}

	protected XmlAdhocGroupHeaderLayout groupHeaderLayout(AdhocGroupHeaderLayout adhocGroupHeaderLayout) {
		if (adhocGroupHeaderLayout == null) {
			return null;
		}

		switch (adhocGroupHeaderLayout) {
		case EMPTY:
			return XmlAdhocGroupHeaderLayout.EMPTY;
		case VALUE:
			return XmlAdhocGroupHeaderLayout.VALUE;
		case TITLE_AND_VALUE:
			return XmlAdhocGroupHeaderLayout.TITLE_AND_VALUE;
		default:
			throw new AdhocException("Group header layout " + adhocGroupHeaderLayout.name() + " not supported");
		}
	}

	protected XmlAdhocSubtotal subtotal(AdhocSubtotal adhocSubtotal) {
		XmlAdhocSubtotal xmlAdhocSubtotal = new XmlAdhocSubtotal();
		xmlAdhocSubtotal.setName(adhocSubtotal.getName());
		xmlAdhocSubtotal.setLabel(adhocSubtotal.getLabel());
		xmlAdhocSubtotal.setCalculation(calculation(adhocSubtotal.getCalculation()));
		xmlAdhocSubtotal.setStyle(style(adhocSubtotal.getStyle()));
		xmlAdhocSubtotal.setLabelStyle(style(adhocSubtotal.getLabelStyle()));
		return xmlAdhocSubtotal;
	}

	protected XmlAdhocCalculation calculation(AdhocCalculation adhocCalculation) {
		if (adhocCalculation == null) {
			return null;
		}

		switch (adhocCalculation) {
		case NOTHING:
			return XmlAdhocCalculation.NOTHING;
		case COUNT:
			return XmlAdhocCalculation.COUNT;
		case SUM:
			return XmlAdhocCalculation.SUM;
		case AVERAGE:
			return XmlAdhocCalculation.AVERAGE;
		case LOWEST:
			return XmlAdhocCalculation.LOWEST;
		case HIGHEST:
			return XmlAdhocCalculation.HIGHEST;
		case STANDARD_DEVIATION:
			return XmlAdhocCalculation.STANDARD_DEVIATION;
		case VARIANCE:
			return XmlAdhocCalculation.VARIANCE;
		case FIRST:
			return XmlAdhocCalculation.FIRST;
		case DISTINCT_COUNT:
			return XmlAdhocCalculation.DISTINCT_COUNT;
		default:
			throw new AdhocException("Calculation " + adhocCalculation.name() + " not supported");
		}
	}

	protected XmlAdhocSort sort(AdhocSort adhocSort) {
		XmlAdhocSort xmlAdhocSort = new XmlAdhocSort();
		xmlAdhocSort.setName(adhocSort.getName());
		xmlAdhocSort.setOrderType(orderType(adhocSort.getOrderType()));
		return xmlAdhocSort;
	}

	protected XmlAdhocOrderType orderType(AdhocOrderType adhocOrderType) {
		if (adhocOrderType == null) {
			return null;
		}

		switch (adhocOrderType) {
		case ASCENDING:
			return XmlAdhocOrderType.ASCENDING;
		case DESCENDING:
			return XmlAdhocOrderType.DESCENDING;
		default:
			throw new AdhocException("Order type " + adhocOrderType.name() + " not supported");
		}
	}

	protected XmlAdhocStyle style(AdhocStyle adhocStyle) {
		if (adhocStyle == null) {
			return null;
		}

		XmlAdhocStyle xmlAdhocStyle = new XmlAdhocStyle();
		xmlAdhocStyle.setFont(font(adhocStyle.getFont()));
		xmlAdhocStyle.setTopBorder(pen(adhocStyle.getTopBorder()));
		xmlAdhocStyle.setLeftBorder(pen(adhocStyle.getLeftBorder()));
		xmlAdhocStyle.setBottomBorder(pen(adhocStyle.getBottomBorder()));
		xmlAdhocStyle.setRightBorder(pen(adhocStyle.getRightBorder()));
		xmlAdhocStyle.setForegroundColor(color(adhocStyle.getForegroundColor()));
		xmlAdhocStyle.setBackgroundColor(color(adhocStyle.getBackgroundColor()));
		xmlAdhocStyle.setHorizontalAlignment(horizontalAlignment(adhocStyle.getHorizontalAlignment()));
		xmlAdhocStyle.setVerticalAlignment(verticalAlignment(adhocStyle.getVerticalAlignment()));
		xmlAdhocStyle.setPattern(adhocStyle.getPattern());
		return xmlAdhocStyle;
	}

	protected String color(Color color) {
		if (color == null) {
			return null;
		}
    int colorMask = Integer.parseInt("FFFFFF", 16);
    int rgb = color.getRGB();
    String hex = Integer.toHexString(rgb & colorMask).toUpperCase();
    return "#" + ("000000" + hex).substring(hex.length());
	}

	protected XmlAdhocFont font(AdhocFont adhocFont) {
		if (adhocFont == null) {
			return null;
		}

		XmlAdhocFont xmlAdhocFont = new XmlAdhocFont();
		xmlAdhocFont.setFontName(adhocFont.getFontName());
		xmlAdhocFont.setFontSize(adhocFont.getFontSize());
		xmlAdhocFont.setBold(adhocFont.getBold());
		xmlAdhocFont.setItalic(adhocFont.getItalic());
		xmlAdhocFont.setUnderline(adhocFont.getUnderline());
		xmlAdhocFont.setStrikeThrough(adhocFont.getStrikeThrough());
		return xmlAdhocFont;
	}

	protected XmlAdhocPen pen(AdhocPen adhocPen) {
		if (adhocPen == null) {
			return null;
		}

		XmlAdhocPen xmlAdhocPen = new XmlAdhocPen();
		xmlAdhocPen.setLineWidth(adhocPen.getLineWidth());
		xmlAdhocPen.setLineColor(color(adhocPen.getLineColor()));
		return xmlAdhocPen;
	}

	protected XmlAdhocHorizontalAlignment horizontalAlignment(AdhocHorizontalAlignment adhocHorizontalAlignment) {
		if (adhocHorizontalAlignment == null) {
			return null;
		}

		switch (adhocHorizontalAlignment) {
		case LEFT:
			return XmlAdhocHorizontalAlignment.LEFT;
		case CENTER:
			return XmlAdhocHorizontalAlignment.CENTER;
		case RIGHT:
			return XmlAdhocHorizontalAlignment.RIGHT;
		case JUSTIFIED:
			return XmlAdhocHorizontalAlignment.JUSTIFIED;
		default:
			throw new AdhocException("Horizontal alignment " + adhocHorizontalAlignment.name() + " not supported");
		}
	}

	protected XmlAdhocVerticalAlignment verticalAlignment(AdhocVerticalAlignment adhocVerticalAlignment) {
		if (adhocVerticalAlignment == null) {
			return null;
		}

		switch (adhocVerticalAlignment) {
		case TOP:
			return XmlAdhocVerticalAlignment.TOP;
		case MIDDLE:
			return XmlAdhocVerticalAlignment.MIDDLE;
		case BOTTOM:
			return XmlAdhocVerticalAlignment.BOTTOM;
		case JUSTIFIED:
			return XmlAdhocVerticalAlignment.JUSTIFIED;
		default:
			throw new AdhocException("Vertical alignment " + adhocVerticalAlignment.name() + " not supported");
		}
	}

	protected XmlAdhocPage page(AdhocPage adhocPage) {
		if (adhocPage == null) {
			return null;
		}

		XmlAdhocPage xmlAdhocPage = new XmlAdhocPage();
		xmlAdhocPage.setWidth(adhocPage.getWidth());
		xmlAdhocPage.setHeight(adhocPage.getHeight());
		xmlAdhocPage.setOrientation(pageOrientation(adhocPage.getOrientation()));
		xmlAdhocPage.setTopMargin(adhocPage.getTopMargin());
		xmlAdhocPage.setBottomMargin(adhocPage.getBottomMargin());
		xmlAdhocPage.setLeftMargin(adhocPage.getLeftMargin());
		xmlAdhocPage.setRightMargin(adhocPage.getRightMargin());
		xmlAdhocPage.setIgnorePageWidth(adhocPage.getIgnorePageWidth());
		return xmlAdhocPage;
	}

	protected XmlAdhocPageOrientation pageOrientation(AdhocPageOrientation adhocPageOrientation) {
		if (adhocPageOrientation == null) {
			return null;
		}

		switch (adhocPageOrientation) {
		case PORTRAIT:
			return XmlAdhocPageOrientation.PORTRAIT;
		case LANDSCAPE:
			return XmlAdhocPageOrientation.LANDSCAPE;
		default:
			throw new AdhocException("Page orientation " + adhocPageOrientation.name() + " not supported");
		}
	}

	protected XmlAdhocComponent component(AdhocComponent adhocComponent) {
		if (adhocComponent instanceof AdhocTextField) {
			XmlAdhocTextField xmlAdhocComponent = new XmlAdhocTextField();
			textField((AdhocTextField) adhocComponent, xmlAdhocComponent);
			return xmlAdhocComponent;
		}
		if (adhocComponent instanceof AdhocChart) {
			XmlAdhocChart xmlAdhocComponent = new XmlAdhocChart();
			chart((AdhocChart) adhocComponent, xmlAdhocComponent);
			return xmlAdhocComponent;
		}
		else {
			XmlAdhocComponent xmlAdhocComponent = new XmlAdhocComponent();
			component(adhocComponent, xmlAdhocComponent);
			return xmlAdhocComponent;
		}
	}

	protected void component(AdhocComponent adhocComponent, XmlAdhocComponent xmlAdhocComponent) {
		xmlAdhocComponent.setKey(adhocComponent.getKey());
		xmlAdhocComponent.setStyle(style(adhocComponent.getStyle()));
		xmlAdhocComponent.setWidth(adhocComponent.getWidth());
		xmlAdhocComponent.setHeight(adhocComponent.getHeight());
		if (adhocComponent.getProperties() != null && !adhocComponent.getProperties().isEmpty()) {
			properties(adhocComponent.getProperties(), xmlAdhocComponent.getProperty());
		}
	}

	protected void textField(AdhocTextField adhocTextField, XmlAdhocTextField xmlAdhocTextField) {
		component(adhocTextField, xmlAdhocTextField);
		xmlAdhocTextField.setText(adhocTextField.getText());
	}

	protected void chart(AdhocChart adhocChart, XmlAdhocChart xmlAdhocChart) {
		component(adhocChart, xmlAdhocChart);
		xmlAdhocChart.setType(chartType(adhocChart.getType()));
		xmlAdhocChart.setTitle(adhocChart.getTitle());
		xmlAdhocChart.setTitleFont(font(adhocChart.getTitleFont()));
		xmlAdhocChart.setTitleColor(color(adhocChart.getTitleColor()));
		xmlAdhocChart.setShowLegend(adhocChart.getShowLegend());
		xmlAdhocChart.setXValue(adhocChart.getXValue());
		if (adhocChart.getSeries() != null && !adhocChart.getSeries().isEmpty()) {
			for (AdhocChartSerie adhocChartSerie : adhocChart.getSeries()) {
				xmlAdhocChart.getSerie().add(chartSerie(adhocChartSerie));
			}
		}
		if (adhocChart.getSeriesColors() != null && !adhocChart.getSeriesColors().isEmpty()) {
			for (Color adhocSeriesColor : adhocChart.getSeriesColors()) {
				xmlAdhocChart.getSeriesColors().add(color(adhocSeriesColor));
			}
		}
		xmlAdhocChart.setXAxisFormat(axisFormat(adhocChart.getXAxisFormat()));
		xmlAdhocChart.setYAxisFormat(axisFormat(adhocChart.getYAxisFormat()));
		xmlAdhocChart.setOrientation(orientation(adhocChart.getOrientation()));
	}

	protected XmlAdhocOrientation orientation(AdhocOrientation adhocOrientation) {
		if (adhocOrientation == null) {
			return null;
		}

		switch (adhocOrientation) {
		case HORIZONTAL:
			return XmlAdhocOrientation.HORIZONTAL;
		case VERTICAL:
			return XmlAdhocOrientation.VERTICAL;
		default:
			throw new AdhocException("Orientation " + adhocOrientation.name() + " not supported");
		}
	}

	protected XmlAdhocAxisFormat axisFormat(AdhocAxisFormat adhocAxisFormat) {
		if (adhocAxisFormat == null) {
			return null;
		}

		XmlAdhocAxisFormat xmlAdhocAxisFormat = new XmlAdhocAxisFormat();
		xmlAdhocAxisFormat.setLabel(adhocAxisFormat.getLabel());
		xmlAdhocAxisFormat.setLabelFont(font(adhocAxisFormat.getLabelFont()));
		xmlAdhocAxisFormat.setLabelColor(color(adhocAxisFormat.getLabelColor()));
		return xmlAdhocAxisFormat;
	}

	protected XmlAdhocChartSerie chartSerie(AdhocChartSerie adhocChartSerie) {
		XmlAdhocChartSerie xmlAdhocChartSerie = new XmlAdhocChartSerie();
		xmlAdhocChartSerie.setSeries(adhocChartSerie.getSeries());
		xmlAdhocChartSerie.setXValue(adhocChartSerie.getXValue());
		xmlAdhocChartSerie.setYValue(adhocChartSerie.getYValue());
		xmlAdhocChartSerie.setZValue(adhocChartSerie.getZValue());
		xmlAdhocChartSerie.setLabel(adhocChartSerie.getLabel());
		return xmlAdhocChartSerie;
	}

	protected XmlAdhocChartType chartType(AdhocChartType adhocChartType) {
		if (adhocChartType == null) {
			return null;
		}

		switch (adhocChartType) {
		case AREA:
			return XmlAdhocChartType.AREA;
		case STACKEDAREA:
			return XmlAdhocChartType.STACKEDAREA;
		case BAR:
			return XmlAdhocChartType.BAR;
		case STACKEDBAR:
			return XmlAdhocChartType.STACKEDBAR;
		case BAR3D:
			return XmlAdhocChartType.BAR_3_D;
		case STACKEDBAR3D:
			return XmlAdhocChartType.STACKEDBAR_3_D;
		case LINE:
			return XmlAdhocChartType.LINE;
		case LAYEREDBAR:
			return XmlAdhocChartType.LAYEREDBAR;
		case DIFFERENCE:
			return XmlAdhocChartType.DIFFERENCE;
		case PIE:
			return XmlAdhocChartType.PIE;
		case PIE3D:
			return XmlAdhocChartType.PIE_3_D;
		case SCATTER:
			return XmlAdhocChartType.SCATTER;
		case SPIDER:
			return XmlAdhocChartType.SPIDER;
		case TIMESERIES:
			return XmlAdhocChartType.TIMESERIES;
		case XYAREA:
			return XmlAdhocChartType.XYAREA;
		case XYBAR:
			return XmlAdhocChartType.XYBAR;
		case XYLINE:
			return XmlAdhocChartType.XYLINE;
		case BUBBLE:
			return XmlAdhocChartType.BUBBLE;
		default:
			throw new AdhocException("Chart type " + adhocChartType.name() + " not supported");
		}
	}

	protected XmlAdhocFilter filter(AdhocFilter adhocFilter) {
		if (adhocFilter == null) {
			return null;
		}

		XmlAdhocFilter xmlAdhocFilter = new XmlAdhocFilter();
		if (adhocFilter.getRestrictions() != null && !adhocFilter.getRestrictions().isEmpty()) {
			for (AdhocRestriction adhocRestriction : adhocFilter.getRestrictions()) {
				xmlAdhocFilter.getRestriction().add(restriction(adhocRestriction));
			}
		}
		return xmlAdhocFilter;
	}

	protected XmlAdhocRestriction restriction(AdhocRestriction adhocRestriction) {
		if (adhocRestriction instanceof AdhocValueRestriction) {
			XmlAdhocValueRestriction xmlAdhocRestriction = new XmlAdhocValueRestriction();
			valueRestriction((AdhocValueRestriction) adhocRestriction, xmlAdhocRestriction);
			return xmlAdhocRestriction;
		}
		else {
			XmlAdhocRestriction xmlAdhocRestriction = new XmlAdhocRestriction();
			restriction(adhocRestriction, xmlAdhocRestriction);
			return xmlAdhocRestriction;
		}
	}

	protected void restriction(AdhocRestriction adhocRestriction, XmlAdhocRestriction xmlAdhocRestriction) {
		xmlAdhocRestriction.setKey(adhocRestriction.getKey());
		if (adhocRestriction.getProperties() != null && !adhocRestriction.getProperties().isEmpty()) {
			properties(adhocRestriction.getProperties(), xmlAdhocRestriction.getProperty());
		}
	}

	protected void valueRestriction(AdhocValueRestriction adhocValueRestriction, XmlAdhocValueRestriction xmlAdhocValueRestriction) {
		restriction(adhocValueRestriction, xmlAdhocValueRestriction);
		xmlAdhocValueRestriction.setName(adhocValueRestriction.getName());
		xmlAdhocValueRestriction.setOperator(valueOperator(adhocValueRestriction.getOperator()));
		if (adhocValueRestriction.getValues() != null && !adhocValueRestriction.getValues().isEmpty()) {
			for (String value : adhocValueRestriction.getValues()) {
				xmlAdhocValueRestriction.getValue().add(value);
			}
		}
	}

	protected XmlAdhocValueOperator valueOperator(AdhocValueOperator adhocValueOperator) {
		if (adhocValueOperator == null) {
			return null;
		}

		switch (adhocValueOperator) {
		case EQUAL:
			return XmlAdhocValueOperator.EQUAL;
		case UNEQUAL:
			return XmlAdhocValueOperator.UNEQUAL;
		case IN:
			return XmlAdhocValueOperator.IN;
		case NOT_IN:
			return XmlAdhocValueOperator.NOT_IN;
		case LIKE:
			return XmlAdhocValueOperator.LIKE;
		case NOT_LIKE:
			return XmlAdhocValueOperator.NOT_LIKE;
		case START_WITH:
			return XmlAdhocValueOperator.START_WITH;
		case NOT_START_WITH:
			return XmlAdhocValueOperator.NOT_START_WITH;
		case END_WITH:
			return XmlAdhocValueOperator.END_WITH;
		case NOT_END_WITH:
			return XmlAdhocValueOperator.NOT_END_WITH;
		case GREATER:
			return XmlAdhocValueOperator.GREATER;
		case GREATER_OR_EQUAL:
			return XmlAdhocValueOperator.GREATER_OR_EQUAL;
		case SMALLER:
			return XmlAdhocValueOperator.SMALLER;
		case SMALLER_OR_EQUAL:
			return XmlAdhocValueOperator.SMALLER_OR_EQUAL;
		case BETWEEN:
			return XmlAdhocValueOperator.BETWEEN;
		case NOT_BETWEEN:
			return XmlAdhocValueOperator.NOT_BETWEEN;
		case IS_NOT_NULL:
			return XmlAdhocValueOperator.IS_NOT_NULL;
		case IS_NULL:
			return XmlAdhocValueOperator.IS_NULL;
		default:
			throw new AdhocException("Value operator " + adhocValueOperator.name() + " not supported");
		}
	}

}
