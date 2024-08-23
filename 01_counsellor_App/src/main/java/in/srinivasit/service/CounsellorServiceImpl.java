package in.srinivasit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.srinivasit.dto.DashBoardResponse;
import in.srinivasit.dto.DashboardResponse;
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
	List<Enquiry> enqList=enquiryRepo.getEnquriesByCounsellorId(counsellerId);
	
	int totalEnquiries = enqList.size();
	System.out.println("enqList :"+enqList);
	enqList.stream().forEach(e->e.getEnqStatus());
	 
	 response.setTotalEnquiries(totalEnquiries);
	
	return response;
	}

}
