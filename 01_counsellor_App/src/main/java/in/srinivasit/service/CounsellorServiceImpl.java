package in.srinivasit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import in.srinivasit.dto.DashBoardResponse;
import in.srinivasit.entity.Counsellor;
import in.srinivasit.entity.Enquiry;
import in.srinivasit.repository.CounsellorRepository;
import in.srinivasit.repository.EnquiryRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	private CounsellorRepository counsellorRepository;
	private EnquiryRepo enquiryRepo;
	
	public CounsellorServiceImpl(CounsellorRepository counsellorRepository,EnquiryRepo enquiryRepo) {
		this.counsellorRepository = counsellorRepository;
		this.enquiryRepo = enquiryRepo;
	}
	
	
	@Override
	public Counsellor findByEmail(String email) {
		// TODO Auto-generated method stub
		return counsellorRepository.findByEmail(email);
	}

	@Override
	public boolean register(Counsellor counsellor) {
		
		Counsellor saveCounsellor =counsellorRepository.save(counsellor);
		if(saveCounsellor.getCounsellorId()!=null) {
			
			return true;
		}
		
		
		return false;
	}

	@Override
	public Counsellor login(String email, String pwd) {
		// TODO Auto-generated method stub
		return counsellorRepository.findByEmailAndPwd(email, pwd);
	}


	@Override
	public DashBoardResponse getDashBoardInfo(Integer counsellerId) {
		// TODO Auto-generated method stub
		
		DashBoardResponse response = new DashBoardResponse();
		List<Enquiry> enqList = enquiryRepo.getEnquriesByCounsellorId(counsellerId);

		int totalEnquiries = enqList.size();
		System.out.println("enqList :" + enqList);
		int openEnquiries = enqList.stream().filter(e -> e.getEnqStatus().equals("Open")).collect(Collectors.toList())
				.size();

		int enrollEnquires = enqList.stream().filter(e -> e.getEnqStatus().equals("Enrolled"))
				.collect(Collectors.toList()).size();

		int lostEnquires = enqList.stream().filter(e -> e.getEnqStatus().equals("Lost")).collect(Collectors.toList())
				.size();

		response.setTotalEnquiries(totalEnquiries);
		response.setOpenEnquiries(openEnquiries);
		response.setEnrollEnquires(enrollEnquires);
		response.setLostEnquires(lostEnquires);

		return response;
	}

}
