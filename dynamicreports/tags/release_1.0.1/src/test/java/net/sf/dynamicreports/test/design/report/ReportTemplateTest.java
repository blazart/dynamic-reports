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
package net.sf.dynamicreports.test.design.report;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.util.Locale;

import junit.framework.Assert;
import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.base.DRDesignReport;
import net.sf.dynamicreports.design.base.chart.DRDesignChart;
import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.base.component.DRDesignList;
import net.sf.dynamicreports.design.base.component.DRDesignTextField;
import net.sf.dynamicreports.design.base.style.DRDesignStyle;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.exception.DRException;

import org.junit.Test;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ReportTemplateTest {

	public void configureReport(ReportBuilder<?> rb) {
		TextColumnBuilder<Integer> column1;
		
		rb.columns(column1 = col.column("Column1", "field1", Integer.class))
			.groupBy(
					grp.group(column1)
						.header(
								cmp.horizontalList(
									cmp.hListCell(cmp.text("")).widthFixed())))
			.title(
				cmp.horizontalList(
					cmp.hListCell(cmp.image("")).widthFixed().heightFixedOnTop(),
					cmp.hListCell(cht.barChart()).widthFixed().heightFixedOnTop()))
			.setTemplate(
					template()
						.setLocale(Locale.ENGLISH)
						.setShowColumnTitle(false)
						.setIgnorePagination(true)
						.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
						.setTitleOnANewPage(true)
						.setSummaryOnANewPage(true)
						.setFloatColumnFooter(true)
						
						.setHighlightDetailOddRows(true)
						.setDetailOddRowStyle(stl.simpleStyle().setBackgroundColor(Color.BLUE))
						.setHighlightDetailEvenRows(true)
						.setDetailEvenRowStyle(stl.simpleStyle().setBackgroundColor(Color.CYAN))
						.setTextStyle(stl.style().bold())
						
						.setPageFormat(PageType.A3, PageOrientation.LANDSCAPE)
						.setPageMargin(margin(3))
						.setPageColumnsPerPage(3)
						.setPageColumnSpace(20)						
						
						.setColumnPrintRepeatedDetailValues(false)
						
						.setGroupHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE)
						.setGroupHideColumn(false)
						.setGroupShowColumnHeaderAndFooter(true)
						.setGroupPadding(20)
						.setGroupStartInNewPage(true)
						.setGroupStartInNewColumn(true)
						.setGroupReprintHeaderOnEachPage(true)						
						
						.setTextFieldWidth(150)
						
						.setImageWidth(110)
						.setImageHeight(120)

						.setListgap(10)
						
						.setChartWidth(210)
						.setChartHeight(220)
						.chartSeriesColors(Color.BLUE)
						
						.setDetailSplitType(SplitType.IMMEDIATE));
	}
	
	@Test
	public void test() {
		@SuppressWarnings("unchecked")
		ReportBuilder rb = new ReportBuilder();
		configureReport(rb);
		try {
			DRDesignReport report = new DRDesignReport(rb.getReport());
			Assert.assertEquals("locale", Locale.ENGLISH, report.getLocale());
			Assert.assertNull("show column title", report.getColumnHeaderBand());
			Assert.assertTrue("ignore pagination", report.isIgnorePagination());
			Assert.assertEquals("when no data type", WhenNoDataType.ALL_SECTIONS_NO_DETAIL, report.getWhenNoDataType());
			Assert.assertTrue("title on a new page", report.isTitleOnANewPage());
			Assert.assertTrue("summary on a new page", report.isSummaryOnANewPage());
			Assert.assertTrue("float column footer", report.isFloatColumnFooter());
			
			DRDesignTextField columnTextField = (DRDesignTextField) ((DRDesignList) report.getDetailBand().getBandComponent()).getComponents().get(0);
			DRDesignStyle style = columnTextField.getStyle();
			Assert.assertEquals("detail odd row style", Color.BLUE, style.getConditionalStyles().get(0).getBackgroundColor());
			Assert.assertEquals("detail even row style", Color.CYAN, style.getConditionalStyles().get(1).getBackgroundColor());
			Assert.assertTrue("text style bold", style.getParentStyle().getFont().getBold());
			
			Assert.assertEquals("page width", 1190, report.getPage().getWidth());
			Assert.assertEquals("page height", 842, report.getPage().getHeight());
			Assert.assertEquals("page orientation", PageOrientation.LANDSCAPE, report.getPage().getOrientation());
			Assert.assertEquals("page margin", 3, report.getPage().getMargin().getLeft());
			Assert.assertEquals("page columns per page", 3, report.getPage().getColumnsPerPage());
			Assert.assertEquals("page columns spac", 20, report.getPage().getColumnSpace());
			
			Assert.assertFalse("column print repeated detail values", columnTextField.isPrintRepeatedValues());
			
			DRDesignGroup group = (DRDesignGroup) report.getGroups().toArray()[0];
			DRDesignComponent textField = group.getHeaderBands().get(1).getBandComponent();
			Assert.assertEquals("group header layout", 2, ((DRDesignList) group.getHeaderBands().get(0).getBandComponent()).getComponents().size());		
			Assert.assertEquals("group header layout", "groupHeader.textField1", textField.getName());
			Assert.assertEquals("group padding", new Integer(20), columnTextField.getX());
			Assert.assertTrue("group start in new page", group.isStartInNewPage());
			Assert.assertTrue("group start in new column", group.isStartInNewColumn());
			Assert.assertTrue("group reprint header on each page", group.isReprintHeaderOnEachPage());
			
			Assert.assertEquals("text field width", new Integer(150), textField.getWidth());
			
			DRDesignList titleList = (DRDesignList) report.getTitleBand().getBandComponent();
			Assert.assertEquals("list gap", 10, titleList.getGap());
			
			DRDesignComponent image = titleList.getComponents().get(0);
			Assert.assertEquals("image width", new Integer(110), image.getWidth());
			Assert.assertEquals("image height", new Integer(120), image.getHeight());
					
			DRDesignComponent chart = titleList.getComponents().get(1);
			Assert.assertEquals("chart width", new Integer(210), chart.getWidth());
			Assert.assertEquals("chart height", new Integer(220), chart.getHeight());
			Assert.assertEquals("chart colors", Color.BLUE, ((DRDesignChart) chart).getPlot().getSeriesColors().get(0));
			
			Assert.assertEquals("detail split type", SplitType.IMMEDIATE, report.getDetailBand().getSplitType());
		} catch (DRException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}