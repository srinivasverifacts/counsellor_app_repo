package in.srinivasit.controller;

import java.net.http.HttpRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.srinivasit.dto.DashBoardResponse;
import in.srinivasit.entity.Counsellor;
import in.srinivasit.service.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	
	static Logger logger = LoggerFactory.getLogger(CounsellorController.class);
	
	
	private CounsellorService counsellorService; 
	
	CounsellorController(CounsellorService counsellorService)
	{
		this.counsellorService=counsellorService;
		
	}
	
	
	@GetMapping("/")
	public String index(Model model) {
		
		Counsellor cobj = new Counsellor();
		//sending data from controller to UI
		model.addAttribute("counsellor", cobj);
		
		return "index";
	}
	
	@PostMapping("/login")
	public String login(Counsellor counsellor,HttpServletRequest request, Model model) {
		
		Counsellor c =counsellorService.login(counsellor.getEmail(), counsellor.getPwd());
		
		if(c == null) {
			model.addAttribute("emsg", "Invalid Credntials");
			return "index";
		}else {
			//valid login  store counsellorid in session for feature purpose
			HttpSession session =request.getSession(true);
			session.setAttribute("counsellorId", c.getCounsellorId());
			System.out.println("c.getCounsellorId() :"+c.getCounsellorId());
			
			//write a dash board logic is pending
			System.out.println("I am need to dash board logic is pending ");
			
		DashBoardResponse dbResponse=counsellorService.getDashBoardInfo(c.getCounsellorId());
		model.addAttribute("dbresponse", dbResponse);
		return"dashboard";
		
		}
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpServletRequest request,Model model) {
		HttpSession session=request.getSession(false);
		Integer counsellorId = (Integer)session.getAttribute("counsellorId");
		
		DashBoardResponse dbResponse=counsellorService.getDashBoardInfo(counsellorId);
		model.addAttribute("dbresponse", dbResponse);
		return"dashboard";
	}
	
	
	
	
	@GetMapping("/register")
	public String register(Model model) {
		Counsellor cobj = new Counsellor();
		model.addAttribute("counsellor", cobj);
		return"register";
	}
	
	@PostMapping("/register")
	public String addCounsellor(Counsellor counsellor,Model model) {
	Counsellor counsellorEmail =counsellorService.findByEmail(counsellor.getEmail());
	logger.info("counsellorEmail ",counsellorEmail);
	
		if(counsellorEmail!=null) {
			model.addAttribute("emsg", "Email is duplicate");
			return "register";
		}else {
			counsellorService.register(counsellor);
			model.addAttribute("smsg", "Counsellor Added Successfull");
			return "register";
		}
	}
	
	
	
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		//get existing session and invalidate it
	HttpSession session	=request.getSession(false);
	session.invalidate();
	//redirect to Login page
	return "redirect:/";	
	}
	
	
	
}
