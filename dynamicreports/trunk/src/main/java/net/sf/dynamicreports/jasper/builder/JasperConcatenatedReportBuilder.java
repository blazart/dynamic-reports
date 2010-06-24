package net.sf.dynamicreports.jasper.builder;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import net.sf.dynamicreports.design.transformation.StyleResolver;
import net.sf.dynamicreports.report.base.style.DRFont;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.defaults.Defaults;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.export.xmlss.JRXmlssExporter;

import org.apache.commons.lang.Validate;

public class JasperConcatenatedReportBuilder implements Serializable {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private List<JasperReportBuilder> jasperReportBuilders;
	
	public JasperConcatenatedReportBuilder() {
		this.jasperReportBuilders = new ArrayList<JasperReportBuilder>();
	}
	
	public JasperConcatenatedReportBuilder concatenate(JasperReportBuilder ...jasperReportBuilders) {
		Validate.notNull(jasperReportBuilders, "jasperReportBuilders must not be null");
		Validate.noNullElements(jasperReportBuilders, "jasperReportBuilders must not contains null jasperReportBuilder");
		for (JasperReportBuilder jasperReportBuilder : jasperReportBuilders) {
			this.jasperReportBuilders.add(jasperReportBuilder);
		}		
		return this;
	}
	
	public JasperConcatenatedReportBuilder toPng(OutputStream outputStream) throws DRException {
		return toPng(outputStream, 1);
	}
	
	public JasperConcatenatedReportBuilder toPng(OutputStream outputStream, float zoom) throws DRException {
		Validate.notNull(outputStream, "outputStream must not be null");
		Validate.isTrue(zoom > 0, "zoom must be > 0");
		
		int maxWidth = 0;
		int maxHeight = 0;
		
		for (JasperReportBuilder jasperReportBuilder : jasperReportBuilders) {
			JasperPrint jasperPrint = jasperReportBuilder.toJasperPrint();
			int pages = jasperPrint.getPages().size();
			int pageWidth = (int) (jasperPrint.getPageWidth() * zoom);
			maxWidth += pageWidth * pages + (pages - 1) + 2;
			int height = (int) (jasperPrint.getPageHeight() * zoom) + 2;	
			if (height > maxHeight) {
				maxHeight = height;
			}
		}
				
		Image pageImage = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_RGB);
		
		int offset = 1;
		for (JasperReportBuilder jasperReportBuilder : jasperReportBuilders) {
			JasperPrint jasperPrint = jasperReportBuilder.toJasperPrint();
			int pageWidth = (int) (jasperPrint.getPageWidth() * zoom);
			for (int i = 0; i < jasperPrint.getPages().size(); i++) {
				try {
					JRGraphics2DExporter exporter = new JRGraphics2DExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, pageImage.getGraphics());
					exporter.setParameter(JRGraphics2DExporterParameter.OFFSET_X, offset);
					exporter.setParameter(JRGraphics2DExporterParameter.OFFSET_Y, 1);
					exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(i));
					exporter.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO, new Float(zoom));
					exporter.exportReport();
					offset += pageWidth + 1;
				} catch (JRException e) {
					throw new DRException(e);
				}
			}
		}
		try {
			ImageIO.write((RenderedImage) pageImage, "png", outputStream);
		} catch (IOException e) {
			throw new DRException(e);
		}
		return this;
	}
	
	public JasperConcatenatedReportBuilder toCsv(OutputStream outputStream) throws DRException {
		return export(new JRCsvExporter(), outputStream);
	}
	
	public JasperConcatenatedReportBuilder toDocx(OutputStream outputStream) throws DRException {
		return export(new JRDocxExporter(), outputStream);
	}

	public JasperConcatenatedReportBuilder toHtml(OutputStream outputStream) throws DRException {
		return export(new JRHtmlExporter(), outputStream);
	}
	
	public JasperConcatenatedReportBuilder toOds(OutputStream outputStream) throws DRException {
		return export(new JROdsExporter(), outputStream);
	}
	
	public JasperConcatenatedReportBuilder toOdt(OutputStream outputStream) throws DRException {
		return export(new JROdtExporter(), outputStream);
	}
	
	public JasperConcatenatedReportBuilder toPdf(OutputStream outputStream) throws DRException {
		return export(new JRPdfExporter(), outputStream);
	}
	
	public JasperConcatenatedReportBuilder toRtf(OutputStream outputStream) throws DRException {
		return export(new JRRtfExporter(), outputStream);
	}
	
	public JasperConcatenatedReportBuilder toText(OutputStream outputStream) throws DRException {		
		DRFont font = Defaults.getDefaults().getFont();
		JRTextExporter exporter = new JRTextExporter();
		exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Float(StyleResolver.getFontWidth(font)));
		exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Float(StyleResolver.getFontHeight(font)));
		return export(exporter, outputStream);
	}
	
	public JasperConcatenatedReportBuilder toXhtml(OutputStream outputStream) throws DRException {
		return export(new JRXhtmlExporter(), outputStream);
	}
	
	public JasperConcatenatedReportBuilder toExcelApiXls(OutputStream outputStream) throws DRException {
		return export(new JExcelApiExporter(), outputStream);
	}
	
	public JasperConcatenatedReportBuilder toXls(OutputStream outputStream) throws DRException {
		return export(new JRXlsExporter(), outputStream);
	}
	
	public JasperConcatenatedReportBuilder toXlsx(OutputStream outputStream) throws DRException {
		return export(new JRXlsxExporter(), outputStream);
	}
	
	public JasperConcatenatedReportBuilder toXml(OutputStream outputStream) throws DRException {
		return export(new JRXmlExporter(), outputStream);
	}

	public JasperConcatenatedReportBuilder toXmlss(OutputStream outputStream) throws DRException {
		return export(new JRXmlssExporter(), outputStream);
	}
	
	private JasperConcatenatedReportBuilder export(JRExporter exporter, OutputStream outputStream) throws DRException {
		Validate.notNull(outputStream, "outputStream must not be null");
		try {
			List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
			for (JasperReportBuilder jasperReportBuilder : jasperReportBuilders) {
				jasperPrints.add(jasperReportBuilder.toJasperPrint());
			}
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			exporter.exportReport();
		} catch (JRException e) {
			throw new DRException(e);
		}
		return this;
	}
}
