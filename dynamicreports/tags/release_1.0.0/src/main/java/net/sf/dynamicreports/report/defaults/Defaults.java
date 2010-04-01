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
package net.sf.dynamicreports.report.defaults;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import net.sf.dynamicreports.report.defaults.xml.XmlDynamicReports;
import net.sf.dynamicreports.report.exception.DRReportException;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class Defaults {
	private static Default defaults;
	
	static {
		defaults = DefaultBinder.bind(load());
	}
	
	private static XmlDynamicReports load() {
		InputStream is = ClassLoader.getSystemResourceAsStream("dynamicreports-defaults.xml");
		if (is == null) {
			throw new DRReportException("Dynamic reports defaults xml file not found");
		}
		try {
			Unmarshaller unmarshaller = JAXBContext.newInstance(XmlDynamicReports.class).createUnmarshaller();						
			JAXBElement<XmlDynamicReports> root = unmarshaller.unmarshal(new StreamSource(is), XmlDynamicReports.class);
			return root.getValue(); 
		} catch (JAXBException e) {
			throw new DRReportException("Could not load dynamic reports defaults", e);
		}
	}
	
	public static Default getDefaults() {
		return defaults;
	}
}
