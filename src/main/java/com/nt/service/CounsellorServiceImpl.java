package com.nt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.DashboardResponse;
import com.nt.entity.Counsellor;
import com.nt.entity.StudentEnq;
import com.nt.repository.ICounsellorRepository;
import com.nt.repository.IStudentEnqRepository;
import com.nt.util.EmailUtils;

@Service
public class CounsellorServiceImpl  implements ICounsellorService{

	@Autowired
	private ICounsellorRepository cRepo;
	
	@Autowired
	private IStudentEnqRepository sRepo;
	
	@Autowired
	private EmailUtils  emailUtials;
	
	@Override
	public String saveCounsellor(Counsellor c) {
		// verify the duplicate email
		Counsellor obj = cRepo.findByEmail(c.getEmail());
		if(obj!=null) {
			return "Duplicate Emails";
		}
		Counsellor saveObj =  cRepo.save(c);
		if(saveObj.getCid()!=null) {
			return "Registration Success";
		}
		return "Registration Faild";
	}

	@Override
	public Counsellor loginCheck(String email, String pwd) {
		return cRepo.findByEmailAndPwd(email, pwd);
	}

	@Override
	public boolean recoverPwd(String email) {
		Counsellor c =  cRepo.findByEmail(email);
		if(c==null) {
			return false;
		}
		String  subject = "Recover Password - Ashok IT";
		String  body = "<h1>Your Password::"+c.getPwd()+"</h1>";
		return emailUtials.sendEmail(subject, body, email);
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer cid) {
		List<StudentEnq> allEnqs = sRepo.findByCid(cid);
		int enrolledEnqs =  allEnqs.stream()
										  .filter(e-> e.getEnqStatus().equals("Enrolled"))
										  .collect(Collectors.toList())
										  .size();
		DashboardResponse resp = new DashboardResponse();
		resp.setTotalEnq(allEnqs.size());
		resp.setEnrolledEnq(enrolledEnqs);
		resp.setLostEnq(allEnqs.size()-enrolledEnqs);
		return resp;
	}

}
