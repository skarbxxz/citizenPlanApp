package com.excelr.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.excelr.entity.CitizenPlan;
import com.excelr.repository.CitizenPlanRepo;
import com.excelr.request.SearchRequest;
import com.excelr.util.EmailUtil;
import com.excelr.util.ExcelGenerator;
import com.excelr.util.PdfGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Service
public  class ReportServiceImpl implements ReportService {
	
	@Autowired
	private CitizenPlanRepo planRepo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtil emailUtil;

	@Override
	public List<String> getPlanNames() {
		return planRepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		return planRepo.getPlanStatus();
	}
	
	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();
		// see if we directly copy like this means we will get an error(bcz in request object it is empty(empty means creates problm ) and entity contains null value thats y) so first we need check wheteher it is not null and not empty then only it will work when we click on search wthout clicking on any filter
		//BeanUtils.copyProperties(request, entity);
		if(null!=request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if(null!=request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		// if it is not null and not empty
		if(null!=request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			//covert string to localdate
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(localDate);
		}
		
		if(null!=request.getEndDate() && !"".equals(request.getEndDate())) {
			String endDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			//covert string to localdate
			LocalDate localDate = LocalDate.parse(endDate, formatter);
			entity.setPlanEndDate(localDate);
		}
		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		File f = new File("Plans.xls");
		
		List<CitizenPlan> plans = planRepo.findAll();
		excelGenerator.generate(response, plans, f);
		
		String subject = "Test mail subject";
		String body = "<h1>Test mail body </h1>";
		String to = "bhavadeeshkondeti34@gmail.com";
		
		
		emailUtil.sendEmail(subject, body, to, f);
		
		f.delete();
		
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception{
		
        File f = new File("Plans.pdf");		
		List<CitizenPlan> plans = planRepo.findAll();
		pdfGenerator.generate(response, plans, f);
		
		String subject = "Test mail subject";
		String body = "<h1>Test mail body </h1>";
		String to = "bhavadeeshkondeti34@gmail.com";
		
		emailUtil.sendEmail(subject, body, to, f);
		
		f.delete();
		
		
		
		
		
		return true;
	}

	

}
