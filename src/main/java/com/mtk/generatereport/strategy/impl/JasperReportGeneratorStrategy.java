package com.mtk.generatereport.strategy.impl;

import com.mtk.generatereport.dto.Data;
import com.mtk.generatereport.strategy.ReportGeneratorStrategy;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

@Component("jasperReportGeneratorStrategy")
public class JasperReportGeneratorStrategy implements ReportGeneratorStrategy
{

	@Override
	public String generateReport(String message)
	{
		try
		{
			// Carrega o template do classpath
			InputStream templateStream = getClass().getResourceAsStream(
				"/templates/jasper_template.jrxml");
			if (templateStream == null)
			{
				throw new RuntimeException("Template não encontrado: /templates/jasper_template.jrxml");
			}

			// Compila o template
			JasperReport jasperReport = JasperCompileManager.compileReport(templateStream);

			// Cria a lista de dados para o relatório
			List<Data> dataList = List.of(new Data(message));

			// Cria o DataSource
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

			// Preenche o relatório
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(),
				dataSource);

			// Gera o nome do arquivo
			String filename = LocalDateTime.now().toString().replace(":", "-") + ".pdf";

			// Exporta para PDF
			try (FileOutputStream outputStream = new FileOutputStream(filename))
			{
				JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			}

			return "Relatório gerado: " + filename;
		}
		catch (Exception e)
		{
			throw new RuntimeException("Erro ao gerar o relatório: " + e.getMessage(), e);
		}
	}
}

