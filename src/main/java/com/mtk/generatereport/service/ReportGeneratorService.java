package com.mtk.generatereport.service;

import com.mtk.generatereport.dto.ReportRequest;

public interface ReportGeneratorService
{
	String report(ReportRequest request);
}
