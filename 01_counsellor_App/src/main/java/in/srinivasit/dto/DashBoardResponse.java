package in.srinivasit.dto;

import lombok.Data;

@Data
public class DashBoardResponse {
	private Integer totalEnquiries;
	private Integer openEnquiries;
	private Integer enrollEnquires;
	private Integer lostEnquires;
}
