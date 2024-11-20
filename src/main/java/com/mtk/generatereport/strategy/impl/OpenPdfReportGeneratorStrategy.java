package com.mtk.generatereport.strategy.impl;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.mtk.generatereport.strategy.ReportGeneratorStrategy;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component("openPdfReportGeneratorStrategy")
public class OpenPdfReportGeneratorStrategy implements ReportGeneratorStrategy
{
	@Override
	public String generateReport(String message)
	{
		String filename = LocalDateTime.now().toString().replace(":", "-") + ".pdf";
		Document document = new Document();

		try
		{
			PdfWriter.getInstance(document, new FileOutputStream(filename));
			document.open();
			document.add(new Paragraph(message));
		}
		catch (DocumentException | IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			document.close();
		}

		return "Relatório gerado: " + filename;
	}
}
