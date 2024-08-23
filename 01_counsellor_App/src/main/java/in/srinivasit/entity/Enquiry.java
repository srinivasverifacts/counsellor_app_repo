package in.srinivasit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Enquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqId;
	private String studentName;
	private String studentPhno;
	private String courseName;
	private String classMode;
	@Column(name = "enq_status")
	private String enqStatus;
	@CurrentTimestamp
	private LocalDate createdDate;
	@UpdateTimestamp
	private LocalDate updatedDate;
	
//	private int counsellor_id;
	@ManyToOne
	@JoinColumn(name = "counsellor_id")
	private Counsellor counsellor;
}
