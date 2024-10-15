package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nt.binding.SearchCriteria;
import com.nt.entity.StudentEnq;
import com.nt.repository.IStudentEnqRepository;

@Service
public class EnquiryServiceImpl  implements IEnquiryService{

	@Autowired
	private IStudentEnqRepository sRepo;
	
	@Override
	public boolean addEnq(StudentEnq se) {
		StudentEnq saveEnq =  sRepo.save(se);
		return saveEnq.getEnqId()!=null;
	}

	@Override
	public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria sc) {
		StudentEnq enq =  new StudentEnq();
		//setting cid
		enq.setCid(cid);
		//if mode selected  add to  query
		if(sc.getClassMode()!=null&& !sc.getClassMode().equals("")) {
			enq.setClassMode(sc.getClassMode());
		}
		if(sc.getCourseName()!=null&& !sc.getCourseName().equals("")) {
			enq.setCourseName(sc.getCourseName());
		}
		if(sc.getEnqStatus()!=null&& !sc.getEnqStatus().equals("")) {
			enq.setEnqStatus(sc.getEnqStatus());
		}
		Example<StudentEnq> of  = Example.of(enq);
		List<StudentEnq> enquiries = sRepo.findAll(of);
		
		return enquiries;
	}

}
