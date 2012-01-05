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

package net.sf.dynamicreports.examples.complex.salescrosstab;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import net.sf.dynamicreports.examples.DataSource;
import net.sf.dynamicreports.examples.complex.ReportData;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class SalesCrosstabData implements ReportData {

	public JRDataSource createDataSource() {
		DataSource dataSource = new DataSource("state", "item", "orderdate", "quantity", "unitprice");
		Calendar c = Calendar.getInstance();

		for (int i = 0; i < 400; i++) {
			Date date = c.getTime();

			dataSource.add("Florida", "Notebook", date, 1, new BigDecimal(500));
			dataSource.add("Florida", "DVD", date, 5, new BigDecimal(30));
			dataSource.add("Florida", "Book", date, 2, new BigDecimal(11));
			dataSource.add("Florida", "Phone", date, 1, new BigDecimal(200));

			dataSource.add("Washington", "Notebook", date, 1, new BigDecimal(610));
			dataSource.add("Washington", "DVD", date, 2, new BigDecimal(42));
			dataSource.add("Washington", "Book", date, 3, new BigDecimal(12));
			dataSource.add("Washington", "Phone", date, 1, new BigDecimal(380));

			dataSource.add("New York", "Notebook", date, 1, new BigDecimal(460));
			dataSource.add("New York", "DVD", date, 3, new BigDecimal(49));
			dataSource.add("New York", "Book", date, 4, new BigDecimal(11));
			dataSource.add("New York", "Phone", date, 2, new BigDecimal(190));

			dataSource.add("Arizona", "Notebook", date, 1, new BigDecimal(400));
			dataSource.add("Arizona", "DVD", date, 2, new BigDecimal(30));
			dataSource.add("Arizona", "Book", date, 6, new BigDecimal(18));
			dataSource.add("Arizona", "Phone", date, 2, new BigDecimal(110));

			c.add(Calendar.DAY_OF_MONTH, -1);
		}

		return dataSource;
	}
}
