package com.mtk.generatereport.service.impl;

import com.mtk.generatereport.dto.ReportRequest;
import com.mtk.generatereport.factory.ReportGeneratorFactory;
import com.mtk.generatereport.service.ReportGeneratorService;
import com.mtk.generatereport.strategy.ReportGeneratorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportGeneratorServiceImpl implements ReportGeneratorService
{
	private final ReportGeneratorFactory reportGeneratorFactory;

	@Autowired
	public ReportGeneratorServiceImpl(ReportGeneratorFactory reportGeneratorFactory)
	{
		this.reportGeneratorFactory = reportGeneratorFactory;
	}

	@Override
	public String report(ReportRequest request)
	{
		ReportGeneratorStrategy strategy = reportGeneratorFactory.create(request.type());
		return strategy.generateReport(request.data().getMessage());
	}
}
