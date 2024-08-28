package in.srinivasit.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import in.srinivasit.dto.ViewEnqFilterRequest;
import in.srinivasit.entity.Enquiry;
import in.srinivasit.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EnquiryController {
	
	
	private EnquiryService enquiryService ;
	
	public EnquiryController(EnquiryService enquiryService){
		this.enquiryService=enquiryService;
	}
	
	
	@GetMapping("/enq")
	public String addEnquiry(Model model) {
		Enquiry enquiry = new Enquiry();
		model.addAttribute("enquiry", enquiry);
		return "add-enq";
	}
	
	@GetMapping("/editEnq")
	public String editEnquiryForm(@RequestParam("enqId") Integer enqId,Model model) {
		
		Enquiry enquiry=enquiryService.getEnaquiryById(enqId);
		
		System.out.println("enquiry id :"+enquiry.getEnqId());
		
		model.addAttribute("enquiry", enquiry);
		return "add-enq";
	}
	
	
	@PostMapping("/addenq")
	public String saveEnquiry(Enquiry enquiry,HttpServletRequest request,Model model) throws Exception {
		
		
		HttpSession session=request.getSession(false);
		Integer counsellorId = (Integer)session.getAttribute("counsellorId");
	boolean enq=enquiryService.addEnquiry(enquiry, counsellorId);
		if(enq==false) {
			
			model.addAttribute("emsg", "Record not inserted");
			return "add-enq";
		}else {
			model.addAttribute("smsg", "Recored inserted successfull");
		return "add-enq";
		}
	}
	
	
	@GetMapping("/viewEnq")
	public String viewForm(HttpServletRequest request ,Model model) {
		//get existing session Object
		HttpSession session=request.getSession(false);
		Integer counsellorId = (Integer)session.getAttribute("counsellorId");
	
		List<Enquiry> enqList=enquiryService.getAllEnquiry(counsellorId);
		model.addAttribute("enquiries", enqList);
		
		ViewEnqFilterRequest viewEnqFilterRequest =new ViewEnqFilterRequest();
		model.addAttribute("viewEnqFilterRequest", viewEnqFilterRequest);
		
		return "view-enq";
	}
	
	@PostMapping("/filter-enqs")
	public String searchEnquiry(ViewEnqFilterRequest viewEnqFilterRequest,HttpServletRequest request,Model model) {
		
		HttpSession session=request.getSession(false);
		Integer counsellorId = (Integer)session.getAttribute("counsellorId");
		
		List<Enquiry>enqList=enquiryService.getEnquiresWithFilter(viewEnqFilterRequest, counsellorId);	
		System.out.println("enqList :"+enqList);
		model.addAttribute("enquiries", enqList);
		return "view-enq";
	}
	
	
}
