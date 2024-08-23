package in.srinivasit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.srinivasit.entity.Counsellor;
@Repository
public interface CounsellorRepository extends JpaRepository<Counsellor, Integer> {
	
	//select * from counsellor where email=:email;
	public Counsellor findByEmail(String email);
	
	//select * from counsellor where email=:email and pwd=:pwd
	public Counsellor findByEmailAndPwd(String email,String pwd);
}
