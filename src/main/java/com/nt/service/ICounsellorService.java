package com.nt.service;

import com.nt.binding.DashboardResponse;
import com.nt.entity.Counsellor;

public interface ICounsellorService {

	public String  saveCounsellor(Counsellor  c);
	
	public Counsellor  loginCheck(String email,String pwd);
	
	public boolean  recoverPwd(String email);
	
	public DashboardResponse  getDashboardInfo(Integer  cid);
}
