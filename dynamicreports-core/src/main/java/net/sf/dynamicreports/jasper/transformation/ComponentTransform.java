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

package net.sf.dynamicreports.jasper.transformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.design.base.DRDesignReport;
import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.base.component.DRDesignTextField;
import net.sf.dynamicreports.design.base.expression.AbstractDesignComplexExpression;
import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.definition.DRIDesignHyperLink;
import net.sf.dynamicreports.design.definition.DRIDesignTableOfContentsHeading;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignBarbecue;
import net.sf.dynamicreports.design.definition.barcode.DRIDesignBarcode;
import net.sf.dynamicreports.design.definition.chart.DRIDesignChart;
import net.sf.dynamicreports.design.definition.component.DRIDesignBreak;
import net.sf.dynamicreports.design.definition.component.DRIDesignComponent;
import net.sf.dynamicreports.design.definition.component.DRIDesignEllipse;
import net.sf.dynamicreports.design.definition.component.DRIDesignFiller;
import net.sf.dynamicreports.design.definition.component.DRIDesignGenericElement;
import net.sf.dynamicreports.design.definition.component.DRIDesignImage;
import net.sf.dynamicreports.design.definition.component.DRIDesignLine;
import net.sf.dynamicreports.design.definition.component.DRIDesignList;
import net.sf.dynamicreports.design.definition.component.DRIDesignMap;
import net.sf.dynamicreports.design.definition.component.DRIDesignRectangle;
import net.sf.dynamicreports.design.definition.component.DRIDesignSubreport;
import net.sf.dynamicreports.design.definition.component.DRIDesignTextField;
import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstab;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignParameterExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignPropertyExpression;
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.jasper.base.JasperReportDesign;
import net.sf.dynamicreports.jasper.base.JasperReportParameters;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.component.CustomComponentTransform;
import net.sf.dynamicreports.report.component.CustomComponents;
import net.sf.dynamicreports.report.component.DRIDesignCustomComponent;
import net.sf.dynamicreports.report.constant.ComponentPositionType;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.dynamicreports.report.constant.StretchType;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.components.ComponentsExtensionsRegistryFactory;
import net.sf.jasperreports.components.map.StandardMapComponent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRGenericElementType;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignBreak;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignEllipse;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JRDesignGenericElement;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignLine;
import net.sf.jasperreports.engine.design.JRDesignRectangle;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;
import net.sf.jasperreports.engine.type.OnErrorTypeEnum;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ComponentTransform {
	private static final Log log = LogFactory.getLog(SubreportExpression.class);

	private JasperTransformAccessor accessor;

	public ComponentTransform(JasperTransformAccessor accessor) {
		this.accessor = accessor;
	}

	protected JRDesignElement[] component(DRIDesignComponent component, ListType listType) {
		JRDesignElement[] jrElements;
		if (component instanceof DRIDesignChart) {
			JRDesignElement jrElement = accessor.getChartTransform().transform((DRIDesignChart) component);
			jrElements = component(jrElement, component, listType);
		}
		else if (component instanceof DRIDesignBarcode) {
			JRDesignElement jrElement = accessor.getBarcodeTransform().transform((DRIDesignBarcode) component);
			jrElements = component(jrElement, component, listType);
		}
		else if (component instanceof DRIDesignBarbecue) {
			JRDesignElement jrElement = accessor.getBarcodeTransform().transform((DRIDesignBarbecue) component);
			jrElements = component(jrElement, component, listType);
		}
		else if (component instanceof DRIDesignList) {
			jrElements = list((DRIDesignList) component);
		}
		else if (component instanceof DRIDesignTextField) {
			JRDesignElement jrElement = textField((DRIDesignTextField) component);
			jrElements = component(jrElement, component, listType);
		}
		else if (component instanceof DRIDesignFiller) {
			JRDesignElement jrElement = filler((DRIDesignFiller) component);
			jrElements = component(jrElement, component, listType);
		}
		else if (component instanceof DRIDesignImage) {
			JRDesignElement jrElement = image((DRIDesignImage) component);
			jrElements = component(jrElement, component, listType);
		}
		else if (component instanceof DRIDesignSubreport) {
			JRDesignElement jrElement = subreport((DRIDesignSubreport) component, component.getWidth());
			jrElements = component(jrElement, component, listType);
		}
		else if (component instanceof DRIDesignLine) {
			JRDesignElement jrElement = line((DRIDesignLine) component);
			jrElements = component(jrElement, component, listType);
		}
		else if (component instanceof DRIDesignEllipse) {
			JRDesignElement jrElement = ellipse((DRIDesignEllipse) component);
			jrElements = component(jrElement, component, listType);
		}
		else if (component instanceof DRIDesignRectangle) {
			JRDesignElement jrElement = rectangle((DRIDesignRectangle) component);
			jrElements = component(jrElement, component, listType);
		}
		else if (component instanceof DRIDesignBreak) {
			JRDesignElement jrElement = breakComponent((DRIDesignBreak) component);
			jrElements = component(jrElement, component, listType);
		}
		else if (component instanceof DRIDesignGenericElement) {
			JRDesignElement jrElement = genericElement((DRIDesignGenericElement) component);
			jrElements = component(jrElement, component, listType);
		}
		else if (component instanceof DRIDesignCrosstab) {
			JRDesignElement jrElement = accessor.getCrosstabTransform().transform((DRIDesignCrosstab) component);
			jrElements = component(jrElement, component, listType);
		}
		else if (component instanceof DRIDesignMap) {
			JRDesignElement jrElement = map((DRIDesignMap) component);
			jrElements = component(jrElement, component, listType);
		}
		else if (component instanceof DRIDesignCustomComponent) {
			JRDesignElement jrElement = customComponent(component);
			jrElements = component(jrElement, component, listType);
		}
		else {
			throw new JasperDesignException("Component " + component.getClass().getName() + " not supported");
		}

		return jrElements;
	}

	private JRDesignElement[] component(JRDesignElement jrElement, DRIDesignComponent component, ListType listType) {
		StretchType stretchType = component.getStretchType();
		if (stretchType == null) {
			if (component instanceof DRIDesignSubreport ||
					component instanceof DRIDesignLine ||
					component instanceof DRIDesignBreak ||
					component instanceof DRIDesignCrosstab ||
					component instanceof DRIDesignList) {
				stretchType = StretchType.NO_STRETCH;
			}
			else {
				stretchType = detectStretchType(listType);
			}
		}

		ComponentPositionType positionType = component.getPositionType();
		if (positionType == null) {
			positionType = ComponentPositionType.FLOAT;
		}

		jrElement.setPositionType(ConstantTransform.componentPositionType(positionType));
		jrElement.setStretchType(ConstantTransform.stretchType(stretchType));
		jrElement.setPrintInFirstWholeBand(component.isPrintInFirstWholeBand());
		jrElement.setPrintWhenDetailOverflows(component.isPrintWhenDetailOverflows());
		if (component.getPrintWhenGroupChanges() != null) {
			jrElement.setPrintWhenGroupChanges(accessor.getGroupTransform().getGroup(component.getPrintWhenGroupChanges()));
		}
		jrElement.setKey(component.getUniqueName());
		jrElement.setX(component.getX());
		jrElement.setY(component.getY());
		jrElement.setWidth(component.getWidth());
		jrElement.setHeight(component.getHeight());

		if (component.getStyle() != null) {
			jrElement.setStyle(accessor.getStyleTransform().getStyle(component.getStyle()));
		}
		jrElement.setPrintWhenExpression(accessor.getExpressionTransform().getExpression(component.getPrintWhenExpression()));
		jrElement.setRemoveLineWhenBlank(component.isRemoveLineWhenBlank());

		for (DRIDesignPropertyExpression propertyExpression : component.getPropertyExpressions()) {
			jrElement.addPropertyExpression(accessor.getExpressionTransform().getPropertyExpression(propertyExpression));
		}

		DRIDesignTableOfContentsHeading tocHeading = component.getTableOfContentsHeading();
		if (tocHeading != null) {
			DRDesignTextField referenceField = (DRDesignTextField) tocHeading.getReferenceField();
			referenceField.setX(component.getX());
			referenceField.setY(component.getY());
			JRDesignElement jrReferenceElement = textField(referenceField);
			component(jrReferenceElement, referenceField, listType);
			return new JRDesignElement[] {jrReferenceElement, jrElement};
		}
		else {
			return new JRDesignElement[] {jrElement};
		}
	}

	private StretchType detectStretchType(ListType listType) {
		if (listType.equals(ListType.VERTICAL)) {
			return StretchType.NO_STRETCH;
		}

		return StretchType.RELATIVE_TO_TALLEST_OBJECT;
	}

	//list
	private JRDesignElement[] list(DRIDesignList list) {
		switch (list.getComponentGroupType()) {
		case FRAME:
			JRDesignFrame frame = new JRDesignFrame();
			JRDesignElement[] jrElems = component(frame, list, list.getType());

			DRDesignComponent background = (DRDesignComponent) list.getBackgroundComponent();
			if (background != null) {
				JRDesignElement jrBackground = component(background, list.getType())[0];
				frame.addElement(jrBackground);
			}

			for (DRIDesignComponent element : list.getComponents()) {
				JRDesignElement[] jrElements = component(element, list.getType());
				for (JRDesignElement jrElement : jrElements) {
					frame.addElement(jrElement);
				}
			}
			return jrElems;
		case NONE:
			List<JRDesignElement> jrElementList = new ArrayList<JRDesignElement>();
			for (DRIDesignComponent element : list.getComponents()) {
				JRDesignElement[] jrElements = component(element, list.getType());
				for (JRDesignElement jrElement : jrElements) {
					jrElementList.add(jrElement);
				}
			}
			return jrElementList.toArray(new JRDesignElement[jrElementList.size()]);
		default:
			throw new JasperDesignException("ComponentGroupType " + list.getComponentGroupType().getClass().getName() + " not supported");
		}
	}

	//textField
	private JRDesignElement textField(DRIDesignTextField textField) {
		JRDesignTextField jrTextField = new JRDesignTextField();

		DRIDesignHyperLink hyperLink = textField.getHyperLink();
		if (hyperLink != null) {
			jrTextField.setAnchorNameExpression(accessor.getExpressionTransform().getExpression(hyperLink.getAnchorNameExpression()));
			jrTextField.setHyperlinkAnchorExpression(accessor.getExpressionTransform().getExpression(hyperLink.getAnchorExpression()));
			jrTextField.setHyperlinkPageExpression(accessor.getExpressionTransform().getExpression(hyperLink.getPageExpression()));
			jrTextField.setHyperlinkReferenceExpression(accessor.getExpressionTransform().getExpression(hyperLink.getReferenceExpression()));
			jrTextField.setHyperlinkTooltipExpression(accessor.getExpressionTransform().getExpression(hyperLink.getTooltipExpression()));
			HyperlinkTypeEnum hyperLinkType = ConstantTransform.hyperLinkType(hyperLink.getType());
			if (hyperLinkType != null) {
				jrTextField.setHyperlinkType(hyperLinkType);
			}
		}

		EvaluationTime evaluationTime = textField.getEvaluationTime();
		jrTextField.setEvaluationTime(ConstantTransform.evaluationTime(evaluationTime));
		if (evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP) && textField.getEvaluationGroup() != null) {
			jrTextField.setEvaluationGroup(accessor.getGroupTransform().getGroup(textField.getEvaluationGroup()));
		}

		jrTextField.setStretchWithOverflow(textField.isStretchWithOverflow());

		String pattern = textField.getPattern();
		if (!StringUtils.isBlank(pattern)) {
			jrTextField.setPattern(pattern);
		}
		jrTextField.setPatternExpression(accessor.getExpressionTransform().getExpression(textField.getPatternExpression()));
		jrTextField.setHorizontalAlignment(ConstantTransform.horizontalAlignment(textField.getHorizontalAlignment()));
		jrTextField.setExpression(accessor.getExpressionTransform().getExpression(textField.getValueExpression()));
		jrTextField.setPrintRepeatedValues(textField.isPrintRepeatedValues());
		jrTextField.setMarkup(ConstantTransform.markup(textField.getMarkup()));
		jrTextField.setBlankWhenNull(true);

		return jrTextField;
	}

	//filler
	private JRDesignElement filler(DRIDesignFiller filler) {
		JRDesignStaticText jrDesignStaticText = new JRDesignStaticText();
		return jrDesignStaticText;
	}

	//image
	private JRDesignElement image(DRIDesignImage image) {
		JRDesignImage jrImage = new JRDesignImage(new JRDesignStyle().getDefaultStyleProvider());

		DRIDesignHyperLink hyperLink = image.getHyperLink();
		if (hyperLink != null) {
			jrImage.setAnchorNameExpression(accessor.getExpressionTransform().getExpression(hyperLink.getAnchorNameExpression()));
			jrImage.setHyperlinkAnchorExpression(accessor.getExpressionTransform().getExpression(hyperLink.getAnchorExpression()));
			jrImage.setHyperlinkPageExpression(accessor.getExpressionTransform().getExpression(hyperLink.getPageExpression()));
			jrImage.setHyperlinkReferenceExpression(accessor.getExpressionTransform().getExpression(hyperLink.getReferenceExpression()));
			jrImage.setHyperlinkTooltipExpression(accessor.getExpressionTransform().getExpression(hyperLink.getTooltipExpression()));
			HyperlinkTypeEnum hyperLinkType = ConstantTransform.hyperLinkType(hyperLink.getType());
			if (hyperLinkType != null) {
				jrImage.setHyperlinkType(hyperLinkType);
			}
		}

		jrImage.setOnErrorType(OnErrorTypeEnum.BLANK);
		jrImage.setScaleImage(ConstantTransform.imageScale(image.getImageScale()));
		jrImage.setHorizontalAlignment(ConstantTransform.horizontalAlignment(image.getHorizontalAlignment()));
		jrImage.setExpression(accessor.getExpressionTransform().getExpression(image.getImageExpression()));
		if (image.getUsingCache() != null) {
			jrImage.setUsingCache(image.getUsingCache());
		}
		if (image.getLazy() != null) {
			jrImage.setLazy(image.getLazy());
		}

		return jrImage;
	}

	//subreport
	private JRDesignElement subreport(DRIDesignSubreport subreport, Integer width) {
		JRDesignSubreport jrSubreport = new JRDesignSubreport(new JRDesignStyle().getDefaultStyleProvider());
		jrSubreport.setConnectionExpression(accessor.getExpressionTransform().getExpression(subreport.getConnectionExpression()));
		jrSubreport.setDataSourceExpression(accessor.getExpressionTransform().getExpression(subreport.getDataSourceExpression()));
		jrSubreport.setRunToBottom(subreport.getRunToBottom());

		if (ReportBuilder.class.isAssignableFrom(subreport.getReportExpression().getValueClass())) {
			SubreportExpression subreportExpression = new SubreportExpression(subreport.getReportExpression(), width);
			accessor.getExpressionTransform().addComplexExpression(subreportExpression);
			jrSubreport.setExpression(accessor.getExpressionTransform().getExpression(subreportExpression));

			SubreportParametersExpression parametersExpression = new SubreportParametersExpression(subreportExpression, subreport.getParametersExpression());
			accessor.getExpressionTransform().addComplexExpression(parametersExpression);
			jrSubreport.setParametersMapExpression(accessor.getExpressionTransform().getExpression(parametersExpression));
		}
		else {
			jrSubreport.setExpression(accessor.getExpressionTransform().getExpression(subreport.getReportExpression()));

			JasperSubreportParametersExpression parametersExpression = new JasperSubreportParametersExpression(subreport.getParametersExpression());
			accessor.getExpressionTransform().addComplexExpression(parametersExpression);
			jrSubreport.setParametersMapExpression(accessor.getExpressionTransform().getExpression(parametersExpression));
		}

		return jrSubreport;
	}

	//line
	private JRDesignElement line(DRIDesignLine line) {
		JRDesignLine jrDesignLine = new JRDesignLine();
		jrDesignLine.setDirection(ConstantTransform.lineDirection(line.getDirection()));
		accessor.getStyleTransform().pen(jrDesignLine.getLinePen(), line.getPen());
		return jrDesignLine;
	}

	//ellipse
	private JRDesignElement ellipse(DRIDesignEllipse ellipse) {
		JRDesignEllipse jrDesignEllipse= new JRDesignEllipse(new JRDesignStyle().getDefaultStyleProvider());
		accessor.getStyleTransform().pen(jrDesignEllipse.getLinePen(), ellipse.getPen());
		return jrDesignEllipse;
	}

	//rectangle
	private JRDesignElement rectangle(DRIDesignRectangle rectangle) {
		JRDesignRectangle jrDesignRectangle = new JRDesignRectangle();
		jrDesignRectangle.setRadius(rectangle.getRadius());
		accessor.getStyleTransform().pen(jrDesignRectangle.getLinePen(), rectangle.getPen());
		return jrDesignRectangle;
	}

	//break
	private JRDesignElement breakComponent(DRIDesignBreak breakComponent) {
		JRDesignBreak jrDesignBreak = new JRDesignBreak();
		jrDesignBreak.setType(ConstantTransform.breakType(breakComponent.getType()));
		return jrDesignBreak;
	}

	//generic element
	private JRDesignElement genericElement(DRIDesignGenericElement genericElement) {
		JRDesignGenericElement jrDesignGenericElement = new JRDesignGenericElement(new JRDesignStyle().getDefaultStyleProvider());
		JRGenericElementType genericType = new JRGenericElementType(genericElement.getGenericElementNamespace(), genericElement.getGenericElementName());
		jrDesignGenericElement.setGenericType(genericType);
		EvaluationTime evaluationTime = genericElement.getEvaluationTime();
		jrDesignGenericElement.setEvaluationTime(ConstantTransform.evaluationTime(evaluationTime));
		if (evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP) && genericElement.getEvaluationGroup() != null) {
			jrDesignGenericElement.setEvaluationGroupName(accessor.getGroupTransform().getGroup(genericElement.getEvaluationGroup()).getName());
		}
		for (DRIDesignParameterExpression parameterExpression : genericElement.getParameterExpressions()) {
			jrDesignGenericElement.addParameter(accessor.getExpressionTransform().getGenericElementParameterExpression(parameterExpression));
		}
		return jrDesignGenericElement;
	}

	//map
	private JRDesignElement map(DRIDesignMap map) {
		StandardMapComponent jrMap = new StandardMapComponent();
		EvaluationTime evaluationTime = map.getEvaluationTime();
		jrMap.setEvaluationTime(ConstantTransform.evaluationTime(evaluationTime));
		if (evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP) && map.getEvaluationGroup() != null) {
			jrMap.setEvaluationGroup(accessor.getGroupTransform().getGroup(map.getEvaluationGroup()).getName());
		}
		jrMap.setLatitudeExpression(accessor.getExpressionTransform().getExpression(map.getLatitudeExpression()));
		jrMap.setLongitudeExpression(accessor.getExpressionTransform().getExpression(map.getLongitudeExpression()));
		jrMap.setZoomExpression(accessor.getExpressionTransform().getExpression(map.getZoomExpression()));

		JRDesignComponentElement jrComponent = new JRDesignComponentElement();
		jrComponent.setComponent(jrMap);
		jrComponent.setComponentKey(new ComponentKey(ComponentsExtensionsRegistryFactory.NAMESPACE, "jr", "map"));

		return jrComponent;
	}

	//custom component
	private JRDesignElement customComponent(DRIDesignComponent component) {
		@SuppressWarnings("rawtypes")
		CustomComponentTransform componentTransfom = CustomComponents.getComponentTransform(component);
		if (componentTransfom == null) {
			throw new DRDesignReportException("Component " + component.getClass().getName() + " not supported");
		}
		@SuppressWarnings("unchecked")
		JRDesignComponentElement jrComponent = (JRDesignComponentElement) componentTransfom.jasperComponent(accessor, component);
		return jrComponent;
	}

	private class SubreportExpression extends AbstractDesignComplexExpression {
		private String name;
		private Integer pageWidth;
		private ReportBuilder<?> reportBuilder;
		private Map<ReportBuilder<?>, JasperReportDesign> reportDesigns;
		private Map<ReportBuilder<?>, JasperReport> jasperReports;

		public SubreportExpression(DRIDesignExpression reportExpression, Integer pageWidth) {
			addExpression(reportExpression);
			this.pageWidth = pageWidth;
			this.name = ReportUtils.generateUniqueName("subreportExpression");
			reportDesigns = new HashMap<ReportBuilder<?>, JasperReportDesign>();
			jasperReports = new HashMap<ReportBuilder<?>, JasperReport>();
		}

		public Object evaluate(List<?> values, ReportParameters reportParameters) {
			reportBuilder = (ReportBuilder<?>) values.get(0);
			if (jasperReports.containsKey(reportBuilder)) {
				return jasperReports.get(reportBuilder);
			}
			try {
				JasperReportDesign reportDesign = new JasperReportDesign(new DRDesignReport(reportBuilder.build(), pageWidth), reportParameters, null);
				JasperReport jasperReport = JasperCompileManager.compileReport(reportDesign.getDesign());
				reportDesigns.put(reportBuilder, reportDesign);
				jasperReports.put(reportBuilder, jasperReport);
				return jasperReport;
			} catch (JRException e) {
				if (log.isErrorEnabled()) {
					log.error("Error encountered while creating subreport design", e);
				}
			} catch (DRException e) {
				if (log.isErrorEnabled()) {
					log.error("Error encountered while creating subreport design", e);
				}
			}
			return null;
		}

		public JasperReportDesign getReportDesign() {
			return reportDesigns.get(reportBuilder);
		}

		@Override
		public String getName() {
			return name;
		}

		public Class<?> getValueClass() {
			return JasperReport.class;
		}
	}

	private class SubreportParametersExpression extends AbstractDesignComplexExpression {
		private SubreportExpression subreportExpression;

		public SubreportParametersExpression(SubreportExpression subreportExpression, DRIDesignExpression parametersExpression) {
			super(ReportUtils.generateUniqueName("subreportParametersExpression"));
			this.subreportExpression = subreportExpression;
			if (parametersExpression != null) {
				addExpression(parametersExpression);
			}
		}

		@SuppressWarnings("unchecked")
		public Object evaluate(List<?> values, ReportParameters reportParameters) {
			Map<String, Object> parameters = null;
			if (!values.isEmpty()) {
				parameters = (Map<String, Object>) values.get(0);
			}
			else {
				parameters = new HashMap<String, Object>();
			}
			parameters.putAll(subreportExpression.getReportDesign().getParameters());
			if (subreportExpression.getReportDesign().getParameterValues() != null) {
				parameters.putAll(subreportExpression.getReportDesign().getParameterValues());
			}
			parameters.put(JasperReportParameters.MASTER_REPORT_PARAMETERS, reportParameters);
			return parameters;
		}

		public Class<?> getValueClass() {
			return Map.class;
		}
	}

	private class JasperSubreportParametersExpression extends AbstractDesignComplexExpression {

		public JasperSubreportParametersExpression(DRIDesignExpression parametersExpression) {
			super(ReportUtils.generateUniqueName("jasperSubreportParametersExpression"));
			if (parametersExpression != null) {
				addExpression(parametersExpression);
			}
		}

		@SuppressWarnings("unchecked")
		public Object evaluate(List<?> values, ReportParameters reportParameters) {
			Map<String, Object> parameters = null;
			if (!values.isEmpty()) {
				parameters = (Map<String, Object>) values.get(0);
			}
			else {
				parameters = new HashMap<String, Object>();
			}
			parameters.put(JasperReportParameters.MASTER_REPORT_PARAMETERS, reportParameters);
			return parameters;
		}

		public Class<?> getValueClass() {
			return Map.class;
		}
	}
}
