package in.srinivasit.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.srinivasit.dto.ViewEnqFilterRequest;
import in.srinivasit.entity.Counsellor;
import in.srinivasit.entity.Enquiry;
import in.srinivasit.repository.CounsellorRepository;
import in.srinivasit.repository.EnquiryRepo;
import io.micrometer.common.util.StringUtils;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	private CounsellorRepository counsellorRepository;
	private EnquiryRepo enquiryRepo;

	EnquiryServiceImpl(EnquiryRepo enquiryRepo, CounsellorRepository counsellorRepository) {
		this.enquiryRepo = enquiryRepo;
		this.counsellorRepository = counsellorRepository;

	}

	public boolean addEnquiry(Enquiry enquiry, Integer counsellorId) throws Exception {
		// TODO Auto-generated method stub
		Counsellor counsellor = counsellorRepository.findById(counsellorId).orElse(null);
		if (counsellor == null) {
			throw new Exception("No Counsellor Found");
		}
		enquiry.setCounsellor(counsellor);
		Enquiry enq = enquiryRepo.save(enquiry);
		if (enq.getEnqId() != null) {
			return true;
		}
		return false;
	}

	public List<Enquiry> getAllEnquiry(Integer counsellorId) {
		List<Enquiry> enqList = enquiryRepo.getEnquriesByCounsellorId(counsellorId);
		return enqList;
	}

	public List<Enquiry> getEnquiresWithFilter(ViewEnqFilterRequest filterRequest, Integer counsellorId) {
		// QBE Query By Example (Dynamic Query Preparation)

		Enquiry enq = new Enquiry();

		if (StringUtils.isNotEmpty(filterRequest.getClassMode())) {
			enq.setClassMode(filterRequest.getClassMode());
		}

		if (StringUtils.isNotEmpty(filterRequest.getCourseName())) {

			enq.setCourseName(filterRequest.getCourseName());
		}

		if (StringUtils.isNotEmpty(filterRequest.getEnqStatus())) {
			enq.setEnqStatus(filterRequest.getEnqStatus());
		}

		Counsellor c = counsellorRepository.findById(counsellorId).orElse(null);

		enq.setCounsellor(c);

		Example<Enquiry> of = Example.of(enq);
		List<Enquiry> enqList = enquiryRepo.findAll(of);

		return enqList;
	}

	public Enquiry getEnaquiryById(Integer enqId) {
		// TODO Auto-generated method stub
		
		Enquiry enquiry = enquiryRepo.findById(enqId).orElse(null);
		return enquiry;
	}

}
