/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2011 Ricardo Mariaca
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

package net.sf.dynamicreports.design.definition.barcode;

import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.definition.DRIDesignGroup;
import net.sf.dynamicreports.design.definition.component.DRIDesignComponent;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.report.constant.BarcodeOrientation;
import net.sf.dynamicreports.report.constant.BarcodeTextPosition;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public interface DRIDesignBarcode extends DRIDesignComponent {

	public DRIDesignSimpleExpression getCodeExpression();

	public DRIDesignSimpleExpression getPatternExpression();

	public Double getModuleWidth();

	public BarcodeOrientation getOrientation();

	public BarcodeTextPosition getTextPosition();

	public Double getQuietZone();

	public Double getVerticalQuietZone();
	
	public EvaluationTime getEvaluationTime();

	public DRIDesignGroup getEvaluationGroup();
}
