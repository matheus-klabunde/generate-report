package com.mtk.generatereport.controller;

import com.mtk.generatereport.dto.ReportRequest;
import com.mtk.generatereport.service.ReportGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@RestController
@RequestMapping("report")
public class ReportController
{

	private final ReportGeneratorService service;

	@Autowired
	public ReportController(ReportGeneratorService service)
	{
		this.service = service;
	}

	@PostMapping("/generate")
	public String generateReport(@RequestBody ReportRequest request)
	{
		return service.report(request);
	}
}
