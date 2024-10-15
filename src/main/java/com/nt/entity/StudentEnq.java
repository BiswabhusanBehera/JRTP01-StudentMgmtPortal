package com.nt.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class StudentEnq {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  enqId;
	private String  name;
	private Long  phno;
	private String  classMode;
	private String  courseName;
	private String  enqStatus;
	
	@CreationTimestamp
	private LocalDate  createdDate;
	private Integer  cid;
	
}
