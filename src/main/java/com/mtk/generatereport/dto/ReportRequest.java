package com.mtk.generatereport.dto;

import com.mtk.generatereport.enums.ReportGeneratorType;

public record ReportRequest(Data data, ReportGeneratorType type)
{
}
