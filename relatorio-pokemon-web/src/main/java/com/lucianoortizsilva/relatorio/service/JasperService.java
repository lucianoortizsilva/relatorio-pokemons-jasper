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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

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

}