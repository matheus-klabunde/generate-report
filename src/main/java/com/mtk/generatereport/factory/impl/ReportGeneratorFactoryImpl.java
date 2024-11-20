package com.mtk.generatereport.factory.impl;

import com.mtk.generatereport.enums.ReportGeneratorType;
import com.mtk.generatereport.factory.ReportGeneratorFactory;
import com.mtk.generatereport.strategy.ReportGeneratorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class ReportGeneratorFactoryImpl implements ReportGeneratorFactory
{
	private final ReportGeneratorStrategy jasperReportGeneratorStrategy;
	private final ReportGeneratorStrategy openPdfReportGeneratorStrategy;

	@Autowired
	public ReportGeneratorFactoryImpl(
		@Qualifier("jasperReportGeneratorStrategy") ReportGeneratorStrategy jasperReportGeneratorStrategy,
		@Qualifier("openPdfReportGeneratorStrategy") ReportGeneratorStrategy openPdfReportGeneratorStrategy)
	{
		this.jasperReportGeneratorStrategy = jasperReportGeneratorStrategy;
		this.openPdfReportGeneratorStrategy = openPdfReportGeneratorStrategy;
	}

	@Override
	public ReportGeneratorStrategy create(ReportGeneratorType type)
	{
		if (type == ReportGeneratorType.JASPER)
		{
			return jasperReportGeneratorStrategy;
		}
		else if (type == ReportGeneratorType.OPEN_PDF)
		{
			return openPdfReportGeneratorStrategy;
		}

		throw new IllegalArgumentException("Unsupported report generator type: " + type);
	}
}
