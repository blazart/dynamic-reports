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
package net.sf.dynamicreports.examples;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.util.Locale;

import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.datatype.BigDecimalType;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class Templates {
	public static final StyleBuilder rootStyle;
	public static final StyleBuilder boldStyle;
	public static final StyleBuilder boldCenteredStyle;
	public static final StyleBuilder bold12CenteredStyle;
	public static final StyleBuilder bold18CenteredStyle;
	public static final StyleBuilder columnStyle;
	public static final StyleBuilder columnTitleStyle;
	public static final StyleBuilder subtotalStyle;
	
	public static final ReportTemplateBuilder reportTemplate;
	public static final CurrencyType currencyType;
	
	static {
		rootStyle           = stl.style().setPadding(2);
		boldStyle           = stl.style(rootStyle).bold();
		boldCenteredStyle   = stl.style(boldStyle)
		                         .setAlignment(HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
		bold12CenteredStyle = stl.style(boldCenteredStyle)
		                       .setFontSize(12);
		bold18CenteredStyle = stl.style(bold12CenteredStyle)
		                         .setFontSize(18);
		columnStyle         = stl.style(rootStyle).setVerticalAlignment(VerticalAlignment.MIDDLE);
		columnTitleStyle    = stl.style(columnStyle)
		                         .setBorder(stl.pen1Point())
		                         .setHorizontalAlignment(HorizontalAlignment.CENTER)
		                         .setBackgroundColor(Color.LIGHT_GRAY)
		                         .bold();		
		subtotalStyle       = stl.style(boldStyle)
		                         .setTopBorder(stl.pen1Point());
		
		reportTemplate = template()
		                   .setLocale(Locale.ENGLISH)
		                   .setColumnStyle(columnStyle)
		                   .setColumnTitleStyle(columnTitleStyle)
		                   .setSubtotalStyle(subtotalStyle)
		                   .highlightDetailEvenRows();
		currencyType = new CurrencyType();
	}
	
	/**
	 * Creates custom component which is possible to add to any report band component  
	 */
	public static ComponentBuilder<?, ?> createTitleComponent(String label) {
		return cmp.horizontalList()
		        .add(
		        	cmp.image(Templates.class.getResourceAsStream("images/dynamicreports.png")).setFixedDimension(80, 80),
		        	cmp.text("DynamicReports").setStyle(bold18CenteredStyle).setHorizontalAlignment(HorizontalAlignment.LEFT),
		        	cmp.text(label).setStyle(bold18CenteredStyle).setHorizontalAlignment(HorizontalAlignment.RIGHT))
		        .newRow()
		        .add(cmp.filler().setStyle(stl.style().setTopBorder(stl.pen2Point())).setFixedHeight(10));	         	
	}
	
	public static CurrencyValueFormatter createCurrencyValueFormatter(String label) {
		return new CurrencyValueFormatter(label);
	}	
	
	public static class CurrencyType extends BigDecimalType {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getPattern() {
			return "$ #,###.00";
		}
	}
	
	private static class CurrencyValueFormatter extends AbstractValueFormatter<String, Number> {
		private static final long serialVersionUID = 1L;
		
		private String label;

		public CurrencyValueFormatter(String label) {
			this.label = label;
		}
		
		public String format(Number value, ReportParameters reportParameters) {
			return label + currencyType.toString(value, reportParameters.getLocale());
		}	
	}
}