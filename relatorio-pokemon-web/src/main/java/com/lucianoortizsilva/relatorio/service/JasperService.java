package com.lucianoortizsilva.relatorio.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.HtmlResourceHandler;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import net.sf.jasperreports.web.util.WebHtmlResourceHandler;

@Service
public class JasperService {

	private static final String JASPER_DIRETORIO = "classpath:jasper/";
	private static final String JASPER_SUFIXO = ".jasper";

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private Connection connection;

	private Map<String, Object> params = new HashMap<>();

	public JasperService() {
		this.params.put("IMAGEM_DIRETORIO", JASPER_DIRETORIO);
	}

	public void addParams(final String key, final Object value) {
		this.params.put(key, value);
	}

	public byte[] exportarPDF(String filename) throws JRException, IOException {
		byte[] bytes = null;
		Resource resource = resourceLoader.getResource(JASPER_DIRETORIO + filename + JASPER_SUFIXO);
		InputStream is = resource.getInputStream();
		JasperPrint print = JasperFillManager.fillReport(is, params, connection);
		bytes = JasperExportManager.exportReportToPdf(print);
		return bytes;
	}
	

	public HtmlExporter exportarHTML(String filename, HttpServletRequest request, HttpServletResponse response) {
		HtmlExporter htmlExporter = null;
		try {
			final Resource resource = resourceLoader.getResource(JASPER_DIRETORIO + filename+ JASPER_SUFIXO);
			final InputStream is = resource.getInputStream();
			final JasperPrint print = JasperFillManager.fillReport(is, params, connection);
			htmlExporter = new HtmlExporter();
			htmlExporter.setExporterInput(new SimpleExporterInput(print));
			final SimpleHtmlExporterOutput htmlExporterOutput = new SimpleHtmlExporterOutput(response.getWriter());
			final HtmlResourceHandler resourceHandler = new WebHtmlResourceHandler(request.getContextPath() + "/image/servlet?image={0}");
			htmlExporterOutput.setImageHandler(resourceHandler);
			htmlExporter.setExporterOutput(htmlExporterOutput);
			request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, print);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htmlExporter;
	}

}