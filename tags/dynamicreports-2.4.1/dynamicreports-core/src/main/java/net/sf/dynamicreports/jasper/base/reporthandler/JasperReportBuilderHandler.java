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

package net.sf.dynamicreports.jasper.base.reporthandler;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.definition.JasperReportHandler;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperReportBuilderHandler implements JasperReportHandler {
	private List<JasperReportBuilder> jasperReportBuilders;
	private boolean continuousPageNumbering;

	public JasperReportBuilderHandler() {
		jasperReportBuilders = new ArrayList<JasperReportBuilder>();
		continuousPageNumbering = false;
	}

	public void concatenate(JasperReportBuilder... jasperReportBuilders) {
		for (JasperReportBuilder jasperReportBuilder : jasperReportBuilders) {
			this.jasperReportBuilders.add(jasperReportBuilder);
		}
	}

	public void setContinuousPageNumbering(boolean continuousPageNumbering) {
		this.continuousPageNumbering = continuousPageNumbering;
	}

	public List<JasperPrint> getPrintList() throws DRException {
		List<JasperPrint> printList = new ArrayList<JasperPrint>();
		int pageNumber = 1;
		for (JasperReportBuilder jasperReportBuilder : jasperReportBuilders) {
			if (continuousPageNumbering) {
				jasperReportBuilder.setStartPageNumber(pageNumber);
			}
			else {
				jasperReportBuilder.setStartPageNumber(null);
			}
			JasperPrint jasperPrint = jasperReportBuilder.toJasperPrint();
			printList.add(jasperPrint);
			pageNumber += jasperPrint.getPages().size();
			jasperReportBuilder.rebuild();
		}
		return printList;
	}

}
