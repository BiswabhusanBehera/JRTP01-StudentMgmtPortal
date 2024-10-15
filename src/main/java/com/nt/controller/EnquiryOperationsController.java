package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nt.binding.SearchCriteria;
import com.nt.entity.StudentEnq;
import com.nt.service.IEnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryOperationsController {

	@Autowired
	private  IEnquiryService enqService;
	
	@GetMapping("/enquiry")
	public  String  enqPage(Model model) {
		model.addAttribute("enq",new StudentEnq());
		return "addEnqView";
	}
	
	@PostMapping("enquiry")
	public String  addEnquiry(@ModelAttribute("enq")StudentEnq enq, HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		Integer  cid =(Integer) session.getAttribute("CID");
		
		if(cid==null) {
			return "redirect:/logout";
		}
		enq.setCid(cid);
		
		boolean addEnq = enqService.addEnq(enq);
		if(addEnq) {
			model.addAttribute("successMsg","Enquiry Added");
		}
		else {
			model.addAttribute("errMsg","Enquiry Failed To Added");
		}
		return "addedEnqView";
	}
	
	@GetMapping("enquiries")
	public String viewEnquiries(HttpServletRequest req,Model model) {
		HttpSession session =  req.getSession(false);
		Integer cid = (Integer)session.getAttribute("CID");
		
		if(cid==null) {
			return "redirect:/logout";
		}
		model.addAttribute("sc",new SearchCriteria());
		List<StudentEnq>  enquiriesList = enqService.getEnquiries(cid, new SearchCriteria());
		model.addAttribute("enquiries",enquiriesList);
		return "displayEnqView";
	}
	
	@PostMapping("filter-enquires")
	public String filterEnquiries(@ModelAttribute("sc")SearchCriteria sc,HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("CID");
		
		if(cid==null) {
			return "redirect:/";
		}
		List<StudentEnq>  enquiriesList =  enqService.getEnquiries(cid, sc);
		model.addAttribute("enquiries",enquiriesList);
		return "filterEnqView";
	}
	
	
	
}
