package com.excelr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.excelr.entity.CitizenPlan;
import com.excelr.request.SearchRequest;
import com.excelr.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		
		response.addHeader("Content-Disposition", "attachment;filename=plans.pdf");
		service.exportPdf(response);
		}
	
	
	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/octet-stream");
		
		
		response.addHeader("Content-Disposition", "attachment;filename=plans.xls");
		
		service.exportExcel(response);
	}
	
	@PostMapping("/searchData")
	// @ModelAttribute is used to maintain(store) the search criteria
	public String handleSearch(@ModelAttribute("search") SearchRequest search, Model model)
	{
		//System.out.println(request);
		List<CitizenPlan> plans = service.search(search);
		model.addAttribute("plans", plans);
		
		init(model);
		
		return "index";
	}
	
	//  this index page will load empty page
	@GetMapping("/")
	public String indexPage(Model model)
	{
		/*SearchRequest searchObj = new SearchRequest();
		model.addAttribute("search",searchObj);*/
		
		// form binding object
		model.addAttribute("search",new SearchRequest()); // when my first method is loaded at that time only send the empty object 
		init(model);
		return "index";
	}

	private void init(Model model) {
		model.addAttribute("names",service.getPlanNames());
		model.addAttribute("status",service.getPlanStatus());
		
	}

}
