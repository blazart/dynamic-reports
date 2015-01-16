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

package net.sf.dynamicreports.design.transformation;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.util.Locale;

import net.sf.dynamicreports.design.base.style.DRDesignStyle;
import net.sf.dynamicreports.design.definition.style.DRIDesignStyle;
import net.sf.dynamicreports.report.base.style.DRFont;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.definition.style.DRIReportStyle;
import net.sf.dynamicreports.report.definition.style.DRIStyle;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.fonts.FontUtil;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class StyleResolver {
	private static FontRenderContext context = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).createGraphics().getFontRenderContext();

	protected static int getFontWidth(DRDesignStyle style, int columns) {
		double width = getFont(style).getStringBounds("m", context).getWidth();
		return (int) Math.ceil(width * columns) + getHorizontalPadding(style);
	}

	protected static int getFontHeight(DRDesignStyle style, int rows) {
		double height = getFont(style).getMaxCharBounds(context).getHeight();
		return (int) Math.ceil(height * rows) + getVerticalPadding(style);
	}

	public static double getFontWidth(DRFont font) {
		Font fnt = getFont(font.getFontName(), font.getBold(), font.getItalic(), font.getFontSize());
		return fnt.getStringBounds("m", context).getWidth();
	}

	public static double getFontHeight(DRFont font) {
		Font fnt = getFont(font.getFontName(), font.getBold(), font.getItalic(), font.getFontSize());
		return fnt.getMaxCharBounds(context).getHeight();
	}

	private static Font getFont(DRDesignStyle style) {
		String fontName = getFontName(style);
		Integer fontSize = getFontSize(style);
		Boolean bold = getFontBold(style);
		Boolean italic = getFontItalic(style);
		return getFont(fontName, bold, italic, fontSize);
	}

	private static Font getFont(String fontName, Boolean bold, Boolean italic, Integer fontSize) {
		if (bold == null) {
			bold = false;
		}
		if (italic == null) {
			italic = false;
		}
		int fontStyle;
		if (bold && italic) {
			fontStyle = Font.BOLD | Font.ITALIC;
		}
		else if (bold) {
			fontStyle = Font.BOLD;
		}
		else if (italic) {
			fontStyle = Font.ITALIC;
		}
		else {
			fontStyle = Font.PLAIN;
		}

		FontUtil fontUtil = FontUtil.getInstance(DefaultJasperReportsContext.getInstance());
		Font font = fontUtil.getAwtFontFromBundles(fontName, fontStyle, (float) fontSize, Locale.getDefault(), true);
		if (font == null) {
			font = new Font(fontName, fontStyle, fontSize);
		}
		return font;
	}

	protected static String getFontName(DRDesignStyle style) {
		if (style == null) {
			return null;
		}
		if (style.getFont().getFontName() != null) {
			return style.getFont().getFontName();
		}
		if (style.getParentStyle() != null) {
			return getFontName(style.getParentStyle());
		}
		return null;
	}

	protected static Integer getFontSize(DRDesignStyle style) {
		if (style == null) {
			return null;
		}
		if (style.getFont().getFontSize() != null) {
			return style.getFont().getFontSize();
		}
		if (style.getParentStyle() != null) {
			return getFontSize(style.getParentStyle());
		}
		return null;
	}

	protected static Boolean getFontBold(DRDesignStyle style) {
		if (style == null) {
			return null;
		}
		if (style.getFont().getBold() != null) {
			return style.getFont().getBold();
		}
		if (style.getParentStyle() != null) {
			return getFontBold(style.getParentStyle());
		}
		return null;
	}

	protected static Boolean getFontItalic(DRDesignStyle style) {
		if (style == null) {
			return null;
		}
		if (style.getFont().getItalic() != null) {
			return style.getFont().getItalic();
		}
		if (style.getParentStyle() != null) {
			return getFontItalic(style.getParentStyle());
		}
		return null;
	}

	protected static Boolean getFontUnderline(DRDesignStyle style) {
		if (style == null) {
			return null;
		}
		if (style.getFont().getUnderline() != null) {
			return style.getFont().getUnderline();
		}
		if (style.getParentStyle() != null) {
			return getFontUnderline(style.getParentStyle());
		}
		return null;
	}

	protected static Boolean getFontStrikeThrough(DRDesignStyle style) {
		if (style == null) {
			return null;
		}
		if (style.getFont().getStrikeThrough() != null) {
			return style.getFont().getStrikeThrough();
		}
		if (style.getParentStyle() != null) {
			return getFontStrikeThrough(style.getParentStyle());
		}
		return null;
	}

	protected static String getPdfFontName(DRDesignStyle style) {
		if (style == null) {
			return null;
		}
		if (style.getFont().getPdfFontName() != null) {
			return style.getFont().getPdfFontName();
		}
		if (style.getParentStyle() != null) {
			return getPdfFontName(style.getParentStyle());
		}
		return null;
	}

	protected static String getPdfEncoding(DRDesignStyle style) {
		if (style == null) {
			return null;
		}
		if (style.getFont().getPdfEncoding() != null) {
			return style.getFont().getPdfEncoding();
		}
		if (style.getParentStyle() != null) {
			return getPdfEncoding(style.getParentStyle());
		}
		return null;
	}

	protected static Boolean getPdfEmbedded(DRDesignStyle style) {
		if (style == null) {
			return null;
		}
		if (style.getFont().getPdfEmbedded() != null) {
			return style.getFont().getPdfEmbedded();
		}
		if (style.getParentStyle() != null) {
			return getPdfEmbedded(style.getParentStyle());
		}
		return null;
	}

	public static int getHorizontalPadding(DRIDesignStyle style) {
		return getLeftPadding(style) + getRightPadding(style) + getLeftIndent(style) + getRightIndent(style);
	}

	public static int getVerticalPadding(DRIDesignStyle style) {
		return getTopPadding(style) + getBottomPadding(style) + getFirstLineIndent(style);
	}

	private static Integer getTopPadding(DRIDesignStyle style) {
		if (style == null) {
			return 0;
		}
		if (style.getPadding() != null && style.getPadding().getTop() != null) {
			return style.getPadding().getTop();
		}
		if (style.getParentStyle() != null) {
			return getTopPadding(style.getParentStyle());
		}
		return 0;
	}

	private static Integer getBottomPadding(DRIDesignStyle style) {
		if (style == null) {
			return 0;
		}
		if (style.getPadding() != null && style.getPadding().getBottom() != null) {
			return style.getPadding().getBottom();
		}
		if (style.getParentStyle() != null) {
			return getBottomPadding(style.getParentStyle());
		}
		return 0;
	}

	private static Integer getLeftPadding(DRIDesignStyle style) {
		if (style == null) {
			return 0;
		}
		if (style.getPadding() != null && style.getPadding().getLeft() != null) {
			return style.getPadding().getLeft();
		}
		if (style.getParentStyle() != null) {
			return getLeftPadding(style.getParentStyle());
		}
		return 0;
	}

	private static Integer getRightPadding(DRIDesignStyle style) {
		if (style == null) {
			return 0;
		}
		if (style.getPadding() != null && style.getPadding().getRight() != null) {
			return style.getPadding().getRight();
		}
		if (style.getParentStyle() != null) {
			return getRightPadding(style.getParentStyle());
		}
		return 0;
	}

	private static Integer getFirstLineIndent(DRIDesignStyle style) {
		if (style == null) {
			return 0;
		}
		if (style.getParagraph() != null && style.getParagraph().getFirstLineIndent() != null) {
			return style.getParagraph().getFirstLineIndent();
		}
		if (style.getParentStyle() != null) {
			return getFirstLineIndent(style.getParentStyle());
		}
		return 0;
	}

	private static Integer getLeftIndent(DRIDesignStyle style) {
		if (style == null) {
			return 0;
		}
		if (style.getParagraph() != null && style.getParagraph().getLeftIndent() != null) {
			return style.getParagraph().getLeftIndent();
		}
		if (style.getParentStyle() != null) {
			return getLeftIndent(style.getParentStyle());
		}
		return 0;
	}

	private static Integer getRightIndent(DRIDesignStyle style) {
		if (style == null) {
			return 0;
		}
		if (style.getParagraph() != null && style.getParagraph().getRightIndent() != null) {
			return style.getParagraph().getRightIndent();
		}
		if (style.getParentStyle() != null) {
			return getRightIndent(style.getParentStyle());
		}
		return 0;
	}

	public static String getPattern(DRDesignStyle style) {
		if (style == null) {
			return null;
		}
		if (style.getPattern() != null) {
			return style.getPattern();
		}
		if (style.getParentStyle() != null) {
			return getPattern(style.getParentStyle());
		}
		return null;
	}

	public static HorizontalAlignment getHorizontalAlignment(DRDesignStyle style) {
		if (style == null) {
			return null;
		}
		if (style.getHorizontalAlignment() != null) {
			return style.getHorizontalAlignment();
		}
		if (style.getParentStyle() != null) {
			return getHorizontalAlignment(style.getParentStyle());
		}
		return null;
	}

	public static Color getBackgroundColor(DRIReportStyle reportStyle, StyleTransform transform) {
		if (reportStyle == null) {
			return null;
		}
		DRIStyle style = transform.getStyle(reportStyle);
		if (style.getBackgroundColor() != null) {
			return style.getBackgroundColor();
		}
		if (style.getParentStyle() != null) {
			return getBackgroundColor(style.getParentStyle(), transform);
		}
		return null;
	}

	public static Color mergeColors(Color color1, Color color2, float percent) {
		float amount = 1.0f - percent;
		int r = (int) (color1.getRed() * amount + color2.getRed() * percent);
		int g = (int) (color1.getGreen() * amount + color2.getGreen() * percent);
		int b = (int) (color1.getBlue() * amount + color2.getBlue() * percent);
		int a = (int) (color1.getAlpha() * amount + color2.getAlpha() * percent);
	  return new Color(r, g, b, a);
	}

}
