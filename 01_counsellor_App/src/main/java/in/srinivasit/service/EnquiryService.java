package in.srinivasit.service;

import java.util.List;

import in.srinivasit.dto.ViewEnqFilterRequest;
import in.srinivasit.entity.Enquiry;

public interface EnquiryService {
	
	public boolean addEnquiry(Enquiry enquiry,Integer counsellorId ) throws Exception;
	public List<Enquiry> getAllEnquiry(Integer counsellorId);
	public List<Enquiry> getEnquiresWithFilter(ViewEnqFilterRequest filterRequest, Integer counsellorId);
	public Enquiry getEnaquiryById(Integer enqId);

}
