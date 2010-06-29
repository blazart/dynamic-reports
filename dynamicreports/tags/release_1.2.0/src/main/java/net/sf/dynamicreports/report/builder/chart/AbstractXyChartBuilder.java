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

import net.sf.dynamicreports.report.base.chart.dataset.DRChartDataset;
import net.sf.dynamicreports.report.base.chart.plot.DRAxisPlot;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRISimpleExpression;

import org.apache.commons.lang.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings({"unchecked", "ucd"})
public abstract class AbstractXyChartBuilder<T extends AbstractXyChartBuilder<T, U>, U extends DRAxisPlot> extends AbstractChartBuilder<T> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	protected AbstractXyChartBuilder(ChartType chartType) {
		super(chartType);
	}

	//dataset
	public T setXValue(ColumnBuilder<?, ? extends Number> column) {
		Validate.notNull(column, "column must not be null");
		getDataset().setValueExpression(column.getColumn());
		return (T) this;
	}

	public T setXValue(String fieldName, Class<? extends Number> valueClass) {
		return setXValue(DynamicReports.field(fieldName, valueClass));
	}
	
	public T setXValue(FieldBuilder<? extends Number> field) {
		Validate.notNull(field, "field must not be null");
		getDataset().setValueExpression(field.build());
		return (T) this;
	}
	
	public T setXValue(DRISimpleExpression<? extends Number> expression) {
		getDataset().setValueExpression(expression);
		return (T) this;
	}

	public T series(ChartSerieBuilder ...chartSeries) {
		return addSerie(chartSeries);
	}

	public T addSerie(ChartSerieBuilder ...chartSeries) {
		Validate.notNull(chartSeries, "chartSeries must not be null");
		Validate.noNullElements(chartSeries, "chartSeries must not contains null chartSerie");
		for (ChartSerieBuilder chartSerie : chartSeries) {
			getDataset().addSerie(chartSerie.build());
		}		
		return (T) this;
	}
	
	//plot
	public T setXAxisFormat(AxisFormatBuilder xAxisFormat) {
		Validate.notNull(xAxisFormat, "xAxisFormat must not be null");
		getPlot().setXAxisFormat(xAxisFormat.build());
		return (T) this;
	}
	
	public T setYAxisFormat(AxisFormatBuilder yAxisFormat) {
		Validate.notNull(yAxisFormat, "yAxisFormat must not be null");
		getPlot().setYAxisFormat(yAxisFormat.build());
		return (T) this;
	}
	
	private DRChartDataset getDataset() {
		return getObject().getDataset();
	}
	
	protected U getPlot() {
		return (U) getObject().getPlot();
	}
}
