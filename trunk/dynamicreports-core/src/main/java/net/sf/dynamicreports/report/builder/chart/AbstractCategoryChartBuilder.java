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

package net.sf.dynamicreports.report.builder.chart;

import net.sf.dynamicreports.report.base.chart.dataset.DRCategoryDataset;
import net.sf.dynamicreports.report.base.chart.plot.DRAxisPlot;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.HyperLinkBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
@SuppressWarnings("unchecked")
public abstract class AbstractCategoryChartBuilder<T extends AbstractCategoryChartBuilder<T, U>, U extends DRAxisPlot> extends AbstractBaseChartBuilder<T, U, DRCategoryDataset> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected AbstractCategoryChartBuilder(ChartType chartType) {
		super(chartType);
	}

	//dataset
	public T setCategory(ValueColumnBuilder<?, String> column) {
		Validate.notNull(column, "column must not be null");
		getDataset().setValueExpression(column.getColumn());
		return (T) this;
	}

	public T setCategory(String fieldName, Class<String> valueClass) {
		return setCategory(DynamicReports.field(fieldName, valueClass));
	}

	public T setCategory(FieldBuilder<String> field) {
		Validate.notNull(field, "field must not be null");
		getDataset().setValueExpression(field.build());
		return (T) this;
	}

	public T setCategory(DRIExpression<String> expression) {
		getDataset().setValueExpression(expression);
		return (T) this;
	}

	public T series(AbstractCategoryChartSerieBuilder<?, ?> ...chartSeries) {
		return addSerie(chartSeries);
	}

	public T addSerie(AbstractCategoryChartSerieBuilder<?, ?> ...chartSeries) {
		Validate.notNull(chartSeries, "chartSeries must not be null");
		Validate.noNullElements(chartSeries, "chartSeries must not contains null chartSerie");
		for (AbstractCategoryChartSerieBuilder<?, ?> chartSerie : chartSeries) {
			getDataset().addSerie(chartSerie.build());
		}
		return (T) this;
	}

	public T setUseSeriesAsCategory(Boolean useSeriesAsCategory) {
		getDataset().setUseSeriesAsCategory(useSeriesAsCategory);
		return (T) this;
	}

	public T setItemHyperLink(HyperLinkBuilder itemHyperLink) {
		Validate.notNull(itemHyperLink, "itemHyperLink must not be null");
		getDataset().setItemHyperLink(itemHyperLink.build());
		return (T) this;
	}

	//plot
	public T setCategoryAxisFormat(AxisFormatBuilder categoryAxisFormat) {
		Validate.notNull(categoryAxisFormat, "categoryAxisFormat must not be null");
		getPlot().setXAxisFormat(categoryAxisFormat.build());
		return (T) this;
	}

	public T setValueAxisFormat(AxisFormatBuilder valueAxisFormat) {
		Validate.notNull(valueAxisFormat, "valueAxisFormat must not be null");
		getPlot().setYAxisFormat(valueAxisFormat.build());
		return (T) this;
	}

	public T setShowPercentages(Boolean showPercentages) {
		getPlot().setShowPercentages(showPercentages);
		return (T) this;
	}
}
