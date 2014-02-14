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

import java.util.List;

import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.definition.DRIDesignDataset;
import net.sf.dynamicreports.report.definition.DRIDataset;
import net.sf.dynamicreports.report.definition.DRIField;
import net.sf.dynamicreports.report.definition.DRISort;
import net.sf.dynamicreports.report.definition.DRIVariable;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DatasetExpressionTransform extends AbstractExpressionTransform {
	private DRIDataset dataset;

	public DatasetExpressionTransform(DesignTransformAccessor accessor, DRIDataset dataset) {
		super(accessor);
		this.dataset = dataset;
	}

	@Override
	protected ResetType getVariableResetType(DRIVariable<?> variable) {
		return ResetType.REPORT;
	}

	@Override
	protected List<? extends DRIField<?>> transformFields() {
		return dataset.getFields();
	}

	@Override
	protected List<? extends DRIVariable<?>> transformVariables() {
		return dataset.getVariables();
	}

	@Override
	protected List<? extends DRISort> transformSorts() {
		return dataset.getSorts();
	}

	@Override
	protected DRIDesignDataset getDataset() {
		return accessor.getDatasetTransform().getDesignDataset(dataset);
	}
}
