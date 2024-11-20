package com.mtk.generatereport.factory;

import com.mtk.generatereport.enums.ReportGeneratorType;
import com.mtk.generatereport.strategy.ReportGeneratorStrategy;

public interface ReportGeneratorFactory
{
	ReportGeneratorStrategy create(ReportGeneratorType type);
}
