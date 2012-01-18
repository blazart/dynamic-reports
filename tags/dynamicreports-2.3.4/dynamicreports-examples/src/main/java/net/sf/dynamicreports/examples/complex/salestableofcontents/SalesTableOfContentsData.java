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

package net.sf.dynamicreports.examples.complex.salestableofcontents;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import net.sf.dynamicreports.examples.DataSource;
import net.sf.dynamicreports.examples.complex.ReportData;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class SalesTableOfContentsData implements ReportData {

	public JRDataSource createDataSource() {
		String[] countries = new String[]{"USA", "Canada", "Mexico", "Australia", "France", "Spain", "Germany", "China"};
		String[] items = new String[]{"Book", "Notebook", "PDA"};

		DataSource dataSource = new DataSource("country", "item", "orderdate", "quantity", "unitprice");

		for (String country : countries) {
			for (String item : items) {
				Calendar c = Calendar.getInstance();
				c.add(Calendar.MONTH, -1);
				c.set(Calendar.DAY_OF_MONTH, 1);
				for (int i = 0; i < 15; i++) {
					Date date = c.getTime();
					dataSource.add(country, item, date, (int) (Math.random() * 10) + 1, new BigDecimal(Math.random() * 100 + 1));
					c.add(Calendar.DAY_OF_MONTH, 1);
				}
			}
		}

		return dataSource;
	}
}
