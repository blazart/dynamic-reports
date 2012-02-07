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

package net.sf.dynamicreports.jasper.base;

import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.jasper.definition.JasperPrintList;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class JasperSimplePrintList implements JasperPrintList {
	private List<JasperPrint> printList;

	public JasperSimplePrintList() {
		printList = new ArrayList<JasperPrint>();
	}

	public List<JasperPrint> getPrintList() {
		return printList;
	}

	public void add(JasperPrint jasperPrint) {
		printList.add(jasperPrint);
	}

}
