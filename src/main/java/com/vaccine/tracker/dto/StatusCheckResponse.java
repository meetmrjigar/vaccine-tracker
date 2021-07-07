package com.vaccine.tracker.dto;



import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class StatusCheckResponse {

	@Id
	private int id;
	
	@Column @Lob
	private String centers;
	
	@Column	@Lob
	private String centers86;
	
	@Column @Lob
	private String centers77;
	
	@Column @Lob
	private String now;
}
