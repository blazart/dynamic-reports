/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2014 Ricardo Mariaca
 * http://www.dynamicreports.org
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

package net.sf.dynamicreports.jasper.base;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.dynamicreports.report.base.style.DRBaseStyle;
import net.sf.dynamicreports.report.base.style.DRBorder;
import net.sf.dynamicreports.report.base.style.DRConditionalStyle;
import net.sf.dynamicreports.report.base.style.DRFont;
import net.sf.dynamicreports.report.base.style.DRPadding;
import net.sf.dynamicreports.report.base.style.DRParagraph;
import net.sf.dynamicreports.report.base.style.DRPen;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.base.style.DRTabStop;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.constant.LineSpacing;
import net.sf.dynamicreports.report.constant.LineStyle;
import net.sf.dynamicreports.report.constant.Markup;
import net.sf.dynamicreports.report.constant.Rotation;
import net.sf.dynamicreports.report.constant.TabStopAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRConditionalStyle;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRParagraph;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTemplate;
import net.sf.jasperreports.engine.TabStop;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.LineSpacingEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.TabStopAlignEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;
import net.sf.jasperreports.engine.xml.JRXmlTemplateLoader;

import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class JasperTemplateStyleLoader {

	public static DRStyle[] loadStyles(File file) {
		Validate.notNull(file, "file must not be null");
		return loadStyles(JRXmlTemplateLoader.load(file));
	}

	public static DRStyle[] loadStyles(String fileName) throws DRException {
		Validate.notNull(fileName, "fileName must not be null");
		try {
			return loadStyles(JRXmlTemplateLoader.load(fileName));
		} catch (JRException e) {
			throw new DRException(e);
		}
	}

	public static DRStyle[] loadStyles(InputStream inputStream) {
		Validate.notNull(inputStream, "inputStream must not be null");
		return loadStyles(JRXmlTemplateLoader.load(inputStream));
	}

	public static DRStyle[] loadStyles(URL url) {
		Validate.notNull(url, "url must not be null");
		return loadStyles(JRXmlTemplateLoader.load(url));
	}

	private static DRStyle[] loadStyles(JRTemplate template) {
		Validate.notNull(template, "template must not be null");
		JRStyle[] jrStyles = template.getStyles();
		DRStyle[] styles = new DRStyle[jrStyles.length];
		for (int i = 0; i < jrStyles.length; i++) {
			JRStyle jrStyle = jrStyles[i];
			styles[i] = convertStyle(jrStyle);
		}
		return styles;
	}

	private static DRStyle convertStyle(JRStyle jrStyle) {
		DRStyle style = new DRStyle();
		abstractStyle(jrStyle, style);

		style.setName(jrStyle.getName());
		JRStyle jrParentStyle = jrStyle.getStyle();
		if (jrParentStyle != null) {
			style.setParentStyle(convertStyle(jrParentStyle));
		}
		for (JRConditionalStyle jrConditionalStyle : jrStyle.getConditionalStyles()) {
			style.addConditionalStyle(conditionalStyle(jrConditionalStyle));
		}
		return style;
	}

	private static DRConditionalStyle conditionalStyle(JRConditionalStyle jrConditionalStyle) {
		DRIExpression<Boolean> expression = Expressions.jasperSyntax(jrConditionalStyle.getConditionExpression().getText(), Boolean.class);
		DRConditionalStyle conditionalStyle = new DRConditionalStyle(expression);
		abstractStyle(jrConditionalStyle, conditionalStyle);
		return conditionalStyle;
	}

	private static void abstractStyle(JRStyle jrStyle, DRBaseStyle style) {
		style.setForegroundColor(jrStyle.getOwnForecolor());
		style.setBackgroundColor(jrStyle.getOwnBackcolor());
		style.setRadius(jrStyle.getOwnRadius());
		style.setImageScale(imageScale(jrStyle.getOwnScaleImageValue()));
		style.setHorizontalAlignment(horizontalAlignment(jrStyle.getOwnHorizontalAlignmentValue()));
		style.setVerticalAlignment(verticalAlignment(jrStyle.getOwnVerticalAlignmentValue()));
		border(jrStyle.getLineBox(), style.getBorder());
		padding(jrStyle.getLineBox(), style.getPadding());
		font(jrStyle, style.getFont());
		style.setRotation(rotation(jrStyle.getOwnRotationValue()));
		style.setPattern(jrStyle.getOwnPattern());
		style.setMarkup(markup(jrStyle.getOwnMarkup()));
		paragraph(jrStyle.getParagraph(), style.getParagraph());
		pen(jrStyle.getLinePen(), style.getLinePen());
	}

	private static void paragraph(JRParagraph jrParagraph, DRParagraph paragraph) {
		paragraph.setLineSpacing(lineSpacing(jrParagraph.getOwnLineSpacing()));
		paragraph.setLineSpacingSize(jrParagraph.getOwnLineSpacingSize());
		paragraph.setFirstLineIndent(jrParagraph.getOwnFirstLineIndent());
		paragraph.setLeftIndent(jrParagraph.getOwnLeftIndent());
		paragraph.setRightIndent(jrParagraph.getOwnRightIndent());
		paragraph.setSpacingBefore(jrParagraph.getOwnSpacingBefore());
		paragraph.setSpacingAfter(jrParagraph.getOwnSpacingAfter());
		paragraph.setTabStopWidth(jrParagraph.getOwnTabStopWidth());
		if (jrParagraph.getOwnTabStops() != null) {
			for (TabStop jrTabStop : jrParagraph.getOwnTabStops()) {
				DRTabStop tabStop = new DRTabStop();
				tabStop.setPosition(jrTabStop.getPosition());
				tabStop.setAlignment(tabStopAlignment(jrTabStop.getAlignment()));
				paragraph.getTabStops().add(tabStop);
			}
		}
	}

	protected static void pen(JRPen jrPen, DRPen pen) {
		pen.setLineColor(jrPen.getOwnLineColor());
		pen.setLineStyle(lineStyle(jrPen.getOwnLineStyleValue()));
		pen.setLineWidth(jrPen.getOwnLineWidth());
	}

	private static void border(JRLineBox jrLineBox, DRBorder border) {
		pen(jrLineBox.getLeftPen(), border.getLeftPen());
		pen(jrLineBox.getRightPen(), border.getRightPen());
		pen(jrLineBox.getTopPen(), border.getTopPen());
		pen(jrLineBox.getBottomPen(), border.getBottomPen());
	}

	private static void padding(JRLineBox jrLineBox, DRPadding padding) {
		padding.setLeft(jrLineBox.getOwnLeftPadding());
		padding.setRight(jrLineBox.getOwnRightPadding());
		padding.setTop(jrLineBox.getOwnTopPadding());
		padding.setBottom(jrLineBox.getOwnBottomPadding());
	}

	@SuppressWarnings("deprecation")
	private static void font(JRStyle jrStyle, DRFont font) {
		font.setFontName(jrStyle.getOwnFontName());
		font.setBold(jrStyle.isOwnBold());
		font.setItalic(jrStyle.isOwnItalic());
		font.setFontSize(jrStyle.getOwnFontSize());
		font.setStrikeThrough(jrStyle.isOwnStrikeThrough());
		font.setUnderline(jrStyle.isOwnUnderline());
		font.setPdfFontName(jrStyle.getOwnPdfFontName());
		font.setPdfEncoding(jrStyle.getOwnPdfEncoding());
		font.setPdfEmbedded(jrStyle.isOwnPdfEmbedded());
	}

	private static LineStyle lineStyle(LineStyleEnum lineStyle) {
		if (lineStyle == null) {
			return null;
		}

		switch (lineStyle) {
		case SOLID:
			return LineStyle.SOLID;
		case DASHED:
			return LineStyle.DASHED;
		case DOTTED:
			return LineStyle.DOTTED;
		case DOUBLE:
			return LineStyle.DOUBLE;
		default:
			throw new JasperDesignException("Line style " + lineStyle.name() + " not supported");
		}
	}

	private static ImageScale imageScale(ScaleImageEnum imageScale) {
		if (imageScale == null) {
			return null;
		}

		switch (imageScale) {
		case CLIP:
			return ImageScale.CLIP;
		case FILL_FRAME:
			return ImageScale.FILL_FRAME;
		case RETAIN_SHAPE:
			return ImageScale.RETAIN_SHAPE;
		case REAL_HEIGHT:
			return ImageScale.REAL_HEIGHT;
		case REAL_SIZE:
			return ImageScale.REAL_SIZE;
		default:
			throw new JasperDesignException("Image scale " + imageScale.name() + " not supported");
		}
	}

	private static HorizontalAlignment horizontalAlignment(HorizontalAlignEnum horizontalAlignment) {
		if (horizontalAlignment == null) {
			return null;
		}

		switch (horizontalAlignment) {
		case LEFT:
			return HorizontalAlignment.LEFT;
		case CENTER:
			return HorizontalAlignment.CENTER;
		case RIGHT:
			return HorizontalAlignment.RIGHT;
		case JUSTIFIED:
			return HorizontalAlignment.JUSTIFIED;
		default:
			throw new JasperDesignException("Horizontal alignment " + horizontalAlignment.name() + " not supported");
		}
	}

	private static VerticalAlignment verticalAlignment(VerticalAlignEnum verticalAlignment) {
		if (verticalAlignment == null) {
			return null;
		}

		switch (verticalAlignment) {
		case TOP:
			return VerticalAlignment.TOP;
		case MIDDLE:
			return VerticalAlignment.MIDDLE;
		case BOTTOM:
			return VerticalAlignment.BOTTOM;
		case JUSTIFIED:
			return VerticalAlignment.JUSTIFIED;
		default:
			throw new JasperDesignException("Vertical alignment " + verticalAlignment.name() + " not supported");
		}
	}

	private static Markup markup(String markup) {
		if (markup == null) {
			return null;
		}

		if (markup.equals("none")) {
			return Markup.NONE;
		}
		if (markup.equals("styled")) {
			return Markup.STYLED;
		}
		if (markup.equals("rtf")) {
			return Markup.RTF;
		}
		if (markup.equals("html")) {
			return Markup.HTML;
		}
		throw new JasperDesignException("Markup " + markup + " not supported");
	}

	private static LineSpacing lineSpacing(LineSpacingEnum lineSpacing) {
		if (lineSpacing == null) {
			return null;
		}

		switch (lineSpacing) {
		case SINGLE:
			return LineSpacing.SINGLE;
		case ONE_AND_HALF:
			return LineSpacing.ONE_AND_HALF;
		case DOUBLE:
			return LineSpacing.DOUBLE;
		case AT_LEAST:
			return LineSpacing.AT_LEAST;
		case FIXED:
			return LineSpacing.FIXED;
		case PROPORTIONAL:
			return LineSpacing.PROPORTIONAL;
		default:
			throw new JasperDesignException("LineSpacing " + lineSpacing.name() + " not supported");
		}
	}

	protected static Rotation rotation(RotationEnum rotation) {
		if (rotation == null) {
			return null;
		}

		switch (rotation) {
		case NONE:
			return Rotation.NONE;
		case LEFT:
			return Rotation.LEFT;
		case RIGHT:
			return Rotation.RIGHT;
		case UPSIDE_DOWN:
			return Rotation.UPSIDE_DOWN;
		default:
			throw new JasperDesignException("Rotation " + rotation.name() + " not supported");
		}
	}

	private static TabStopAlignment tabStopAlignment(TabStopAlignEnum alignment) {
		switch (alignment) {
		case LEFT:
			return TabStopAlignment.LEFT;
		case CENTER:
			return TabStopAlignment.CENTER;
		case RIGHT:
			return TabStopAlignment.RIGHT;
		default:
			throw new JasperDesignException("TabStopAlignment " + alignment.name() + " not supported");
		}
	}
}
