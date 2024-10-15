package com.nt.service;

import java.util.List;

import com.nt.binding.SearchCriteria;
import com.nt.entity.StudentEnq;

public interface IEnquiryService {

	public boolean  addEnq(StudentEnq  se);
	
	public List<StudentEnq>  getEnquiries(Integer cid, SearchCriteria sc);
	
}
