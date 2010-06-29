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
package net.sf.dynamicreports.report.builder.chart;

import java.awt.Color;

import net.sf.dynamicreports.report.base.chart.DRChart;
import net.sf.dynamicreports.report.builder.component.HyperLinkComponentBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.Position;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings({"unchecked", "ucd"})
public abstract class AbstractChartBuilder<T extends AbstractChartBuilder<T>> extends HyperLinkComponentBuilder<T, DRChart> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	protected AbstractChartBuilder(ChartType chartType) {
		super(new DRChart(chartType));
	}

	public T setCustomizer(DRIChartCustomizer customizer) {
		getObject().setCustomizer(customizer);
		return (T) this;
	}

	//title
	public T setTitle(String title) {
		getObject().getTitle().setTitle(Expressions.text(title));
		return (T) this;
	}
	
	public T setTitle(DRISimpleExpression<String> titleExpression) {
		getObject().getTitle().setTitle(titleExpression);
		return (T) this;
	}
	
	public T setTitleColor(Color titleColor) {
		getObject().getTitle().setColor(titleColor);
		return (T) this;
	}

	public T setTitleFont(FontBuilder titleFont) {
		Validate.notNull(titleFont, "titleFont must not be null");
		getObject().getTitle().setFont(titleFont.build());
		return (T) this;
	}
	
	public T setTitlePosition(Position titlePosition) {
		getObject().getTitle().setPosition(titlePosition);
		return (T) this;
	}
	
	//subtitle
	public T setSubtitle(String subtitle) {
		getObject().getSubtitle().setTitle(Expressions.text(subtitle));
		return (T) this;
	}
	
	public T setSubtitle(DRISimpleExpression<String> subtitleExpression) {
		getObject().getSubtitle().setTitle(subtitleExpression);
		return (T) this;
	}
	
	public T setSubtitleColor(Color subtitleColor) {
		getObject().getSubtitle().setColor(subtitleColor);
		return (T) this;
	}

	public T setSubtitleFont(FontBuilder subtitleFont) {
		Validate.notNull(subtitleFont, "subtitleFont must not be null");
		getObject().getSubtitle().setFont(subtitleFont.build());
		return (T) this;
	}

	//legend
	public T setLegendColor(Color legendColor) {
		getObject().getLegend().setColor(legendColor);
		return (T) this;
	}

	public T setLegendBackgroundColor(Color legendBackgroundColor) {
		getObject().getLegend().setBackgroundColor(legendBackgroundColor);
		return (T) this;
	}
	
	public T setShowLegend(Boolean showLegend) {
		getObject().getLegend().setShowLegend(showLegend);
		return (T) this;
	}
	
	public T setLegendFont(FontBuilder legendFont) {
		Validate.notNull(legendFont, "legendFont must not be null");
		getObject().getLegend().setFont(legendFont.build());
		return (T) this;
	}
	
	public T setLegendPosition(Position legendPosition) {
		getObject().getLegend().setPosition(legendPosition);
		return (T) this;
	}
	
	//plot
	public T setOrientation(Orientation orientation) {
		getObject().getPlot().setOrientation(orientation);
		return (T) this;
	}

	public T seriesColors(Color ...seriesColors) {
		return addSeriesColor(seriesColors);
	}
	
	public T addSeriesColor(Color ...seriesColors) {
		Validate.notNull(seriesColors, "seriesColors must not be null");
		Validate.noNullElements(seriesColors, "seriesColors must not contains null seriesColor");
		for (Color seriesColor : seriesColors) {
			getObject().getPlot().addSeriesColor(seriesColor);
		}		
		return (T) this;
	}
	
	public DRChart getChart() {
		return build();
	}
}
