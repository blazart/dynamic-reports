/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 Ricardo Mariaca
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

package net.sf.dynamicreports.test.jasper.component;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Arrays;

import junit.framework.Assert;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.test.jasper.AbstractJasperTest;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRRenderable;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.util.JRImageLoader;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class ImageTest extends AbstractJasperTest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Image image;
	
	@Override
	protected void configureReport(JasperReportBuilder rb) {
		rb.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
			.setImageStyle(stl.style().setImageScale(ImageScale.NO_RESIZE))
			.title(cmp.image(image = new TestImage()));
	}
	
	@Override
	public void test() {
		super.test();
		
		numberOfPagesTest(1);
		
		try {
			byte[] imageData = JRImageLoader.loadImageDataFromAWTImage(image, JRRenderable.IMAGE_TYPE_JPEG);
			JRPrintImage jrImage = (JRPrintImage) getElementAt("title.image1", 0);
			Assert.assertTrue("image data", Arrays.equals(imageData, jrImage.getRenderer().getImageData()));
			Assert.assertEquals("scale image", ScaleImageEnum.CLIP, jrImage.getScaleImageValue());
		} catch (JRException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}		
	}
	
	private class TestImage extends BufferedImage implements Serializable {
		private static final long serialVersionUID = 1L;

		public TestImage() {
			super(100, 100, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = createGraphics();  
			g2d.setColor(Color.BLUE); 
			g2d.setComposite(AlphaComposite.Src); 
			g2d.fill(new Rectangle2D.Float(5, 5, 90, 90)); 
		}		
	}
}
