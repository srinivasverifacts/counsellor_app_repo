package in.srinivasit.repository;

import java.util.List;

import org.hibernate.annotations.QueryCacheLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

import in.srinivasit.entity.Enquiry;
@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {
@Query(value="select * from enquiry where counsellor_id=:counsellerId",
nativeQuery = true)
	public List<Enquiry> getEnquriesByCounsellorId(Integer counsellerId);

}
