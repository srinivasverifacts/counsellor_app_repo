package in.srinivasit.service;



import in.srinivasit.dto.DashBoardResponse;
import in.srinivasit.entity.Counsellor;

public interface CounsellorService {

	public Counsellor findByEmail(String email);
	public boolean register(Counsellor counsellor);
	public Counsellor login(String email,String pwd);
	public DashBoardResponse getDashBoardInfo(Integer counsellorId);
}
