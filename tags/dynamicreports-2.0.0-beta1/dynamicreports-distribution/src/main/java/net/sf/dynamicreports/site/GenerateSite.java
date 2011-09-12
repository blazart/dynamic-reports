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

package net.sf.dynamicreports.site;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.Project;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.AbstractJasperExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.Exporters;
import net.sf.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperImageExporterBuilder;
import net.sf.dynamicreports.jasper.constant.ImageType;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class GenerateSite {
	private static final String examples_classpath = "../dynamicreports-examples/target/classes/";
	private static final String examples_source = "../dynamicreports-examples/src/main/java/";
	private static final String templates_path = "src/site/templates/";
	private static final String pages_path = "src/site/pages/";
	
	private static Template temp;
	private static StringTemplateLoader loader;
	private static List<Example> examples;
	private static boolean runExamples;
	private static String site_path;	
	private static String examples_path;
	private static Project project;
	
	static {		
		runExamples = new Boolean(System.getenv("runExamples"));
		site_path = System.getenv("outputDirectory") + "/";	
		examples_path = site_path + "examples/";
		
		Configuration cfg = new Configuration();		
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		
		TemplateLoader[] loaders = new TemplateLoader[2];
		loaders[0] = cfg.getTemplateLoader();
		loader = new StringTemplateLoader();
		loaders[1] = loader;
		cfg.setTemplateLoader(new MultiTemplateLoader(loaders));		

		try {			
			project = new Project();
			temp = cfg.getTemplate(templates_path + "site.ftl");			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		examples = new ArrayList<Example>();
	}
	
	public GenerateSite() throws Exception {			
		generatePages();
		if (runExamples) {
			generateExamples();
		}
	}
	
	private void generatePages() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(pages_path + "pages.txt"));
    String line = reader.readLine();
    while (line != null) {
    	String[] p = line.split("\\|");
    	line = reader.readLine();
    	String file = p[0] + ".html";
    	
			Page page = new Page(file, pages_path + file, loadFile(new FileReader(pages_path + file)));
			page.setTitle(p[1]);
			if (p.length > 2) {
				page.setDescription(p[2]);
			}
			if (p.length > 3) {
				page.setKeywords(p[3]);
			}
			
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("project", project);			
			root.put("page", page);
			
			Writer out = new FileWriter(site_path + file);
			temp.process(root, out);
			out.flush();
		}
	}
	
	private void generateExamples() throws Exception {	
		runExamples(new File(examples_classpath));
		generateExamplesHtml();
		generateExampleHtml("Templates", "", "", Templates.class);
		generateExampleHtml("dynamicreports-defaults", "", "", GenerateSite.class.getResourceAsStream("/dynamicreports-defaults.xml"), "xml");	
	}

	private void runExamples(File dir) throws Exception {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				runExamples(new File(dir, children[i]));
			}
		}
		else {
			if (dir.getName().endsWith(".class")) {
				String name = Templates.class.getPackage().getName().replaceAll("\\.", "\\\\");
				int index = dir.getPath().indexOf(name);
				try {
					Class<?> classs = Class.forName(dir.getPath().substring(index).replaceAll("\\\\", ".").replaceAll("\\.class", ""));
					if (classs.getMethod("main", String[].class) != null) {
						classs.newInstance();
					}
				} catch (NoSuchMethodException e) {
				} catch (Exception e) {
					throw e;
				}
			}
		}
	} 
	
	private void generateExamplesHtml() throws Exception {
		String name = "examples";
		String content = "";
		
		int index = 0;
		int count = 0;
		String text1 = "";
		String text2 = "";
		for (Example example : examples) {						
			Example previous = null;
			Example next = null;
			if (index - 1 > -1) {
				previous = examples.get(index - 1);
			}
			if (index + 1 < examples.size()) {
				next = examples.get(index + 1);
			}
			
			if (previous == null || !previous.getPath().equals(example.getPath())) {
				content += "<a name=\"" + example.getPath() + "\"></a><h3>" + example.getPath() + "</h2><br/>\r\n";
				content += "<table class=\"example\">\r\n";
			}
			text1 += "<@example_link id=\"" + example.getName() + "\"/>\r\n";
			text2 += "<@example_preview id=\"" + example.getName() + "\" file=\"" + getFileType(example.getName()) + "\" file_ext=\"" + getFileExt(example.getName()) + "\"/>\r\n";
			if (count == 2 || next == null || !next.getPath().equals(example.getPath())) {	
				content += "<tr>\r\n";
				content += text1;
				content += "</tr><tr>\r\n";
				content += text2;
				content += "</tr>\r\n";
				count = 0;
				text1 = "";
				text2 = "";
			}
			else {
				count++;
			}						
			if (next == null || !next.getPath().equals(example.getPath())) {
				content += "</table><br/>\r\n";
			}
			
			generateExampleHtml(example.getName(), (next != null ? next.getName() : ""), (previous != null ? previous.getName() : ""), example.getDesign());
			index++;
		}
		content += "<@example_link id=\"" + Templates.class.getSimpleName() + "\" table=false/><br/>\r\n";
		
    loader.putTemplate(name, content);
    
		Map<String, Object> root = new HashMap<String, Object>();
		Page page = new Page("examples/" + name.toLowerCase() + ".html", name, content);
		page.setPath("../");
		page.setExamples("");
		page.setTitle("Examples");
		root.put("project", project);
		root.put("page", page);

		Writer out = new FileWriter(examples_path + name.toLowerCase() + ".html");
		temp.process(root, out);
		out.flush();
	}
	
	public static void generateExampleImage(String name, JasperReportBuilder reportBuilder) throws Exception {
		generateExampleImage(name, reportBuilder, Exporters.pdfExporter(""));
	}

	public static void generateExampleImage(String name, JasperReportBuilder reportBuilder, AbstractJasperExporterBuilder<?, ?> jasperExporterBuilder) throws Exception {
		JasperImageExporterBuilder imageExporter = Exporters.imageExporter(new FileOutputStream(examples_path + name.toLowerCase() + "_s.png"), ImageType.PNG);
		imageExporter.setOffsetX(1);
		imageExporter.setOffsetY(1);
		imageExporter.setPageGap(1);
		imageExporter.setZoom(0.08f);
		reportBuilder.toImage(imageExporter);
		imageExporter = Exporters.imageExporter(new FileOutputStream(examples_path + name.toLowerCase() + "_m.png"), ImageType.PNG);
		imageExporter.setOffsetX(1);
		imageExporter.setOffsetY(1);
		imageExporter.setPageGap(1);
		imageExporter.setZoom(0.15f);
		reportBuilder.toImage(imageExporter);
		imageExporter = Exporters.imageExporter(new FileOutputStream(examples_path + name.toLowerCase() + ".png"), ImageType.PNG);
		imageExporter.setOffsetX(1);
		imageExporter.setOffsetY(1);
		imageExporter.setPageGap(1);
		imageExporter.setZoom(1.1f);
		reportBuilder.toImage(imageExporter);
		Method method = reportBuilder.getClass().getDeclaredMethod("export", AbstractJasperExporterBuilder.class);
		method.setAccessible(true);
		if (jasperExporterBuilder instanceof JasperHtmlExporterBuilder) {
			((JasperHtmlExporterBuilder) jasperExporterBuilder).getExporter().setImagesDirName(examples_path + "/images");			
		}
		jasperExporterBuilder.getExporter().setOutputFileName(examples_path + name.toLowerCase() + getFileExt(name) + "." + getFileType(name));
		method.invoke(reportBuilder, jasperExporterBuilder);
	}
	
	public static void generateExampleImage(String name, JasperConcatenatedReportBuilder reportBuilder, AbstractJasperExporterBuilder<?, ?> jasperExporterBuilder) throws Throwable {
		reportBuilder.toPng(new FileOutputStream(examples_path + name.toLowerCase() + "_s.png"), 0.08f);
		reportBuilder.toPng(new FileOutputStream(examples_path + name.toLowerCase() + "_m.png"), 0.15f);
		reportBuilder.toPng(new FileOutputStream(examples_path + name.toLowerCase() + ".png"), 1.1f);
		Method method = reportBuilder.getClass().getDeclaredMethod("export", AbstractJasperExporterBuilder.class);
		method.setAccessible(true);
		jasperExporterBuilder.getExporter().setOutputFileName(examples_path + name.toLowerCase() + ".pdf");
		method.invoke(reportBuilder, jasperExporterBuilder);
	}
	
	private static void generateExampleHtml(String name, String next, String previous, Class<?> design) throws Exception {
		generateExampleHtml(name, next, previous, new FileInputStream(examples_source + design.getName().replaceAll("\\.", "/") + ".java"), "java");
	}
	
	private static String getFileType(String name) {		
		if (name.toLowerCase().indexOf("html") > -1) {
			return "html";
		}
		if (name.toLowerCase().indexOf("excel") > -1) {
			return "xls";
		}
		return "pdf";
	}
	
	private static String getFileExt(String name) {		
		if (name.toLowerCase().indexOf("html") > -1) {
			return "html";
		}
		return "";
	}
	
	private static void generateExampleHtml(String name, String next, String previous, InputStream file, String type) throws Exception {
		String content = "";
		if (!next.equals("") || !previous.equals("")) {
			content += "<@examples previous=\"" + previous + "\" next=\"" + next + "\"/>\r\n";
			content += "<@example id=\"" + name + "\" title=true source_code=false file=\"" + getFileType(name) + "\" file_ext=\"" + getFileExt(name) + "\"/>\r\n";
		}
		content += "<@" + type + "_code>\r\n";
    content += loadFile(new InputStreamReader(file)).replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    content += "\r\n</@" + type + "_code>";
    loader.putTemplate(name, content);
    
		Map<String, Object> root = new HashMap<String, Object>();
		Page page = new Page("examples/" + name.toLowerCase() + ".html", name, content);
		page.setPath("../");
		page.setExamples("");
		page.setSideBar(false);
		page.setTitle(name);
		root.put("project", project);
		root.put("page", page);

		Writer out = new FileWriter(examples_path + name.toLowerCase() + ".html");
		temp.process(root, out);
		out.flush();
	}

	public static void addExample(String name, String path, Class<? extends Object> design) {
		examples.add(new Example(name, path, design));
	}
	
	private static String loadFile(Reader fRead) throws Exception {		
		String content = "";
    BufferedReader reader = new BufferedReader(fRead);
    String line = reader.readLine();
    while (line != null) {
    	content += "\r\n" + line;
    	line = reader.readLine();
		}
    if (content.length() > 0) {
    	content = content.substring(2);
    }
    return content;
	}
	
	public static void main(String[] args) throws Exception {
		new GenerateSite();
	}
} 