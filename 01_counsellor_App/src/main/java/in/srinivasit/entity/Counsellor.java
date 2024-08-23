package in.srinivasit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Counsellor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer counsellorId;
	private String name;
	@Column(unique = true)
	private String email;
	private String pwd;
	private String phno;
	@CurrentTimestamp
	private LocalDate created_date;
	@UpdateTimestamp
	private LocalDate updated_date;
}
