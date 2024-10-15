package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.Counsellor;

public interface ICounsellorRepository  extends JpaRepository<Counsellor, Integer>{

	public Counsellor  findByEmail(String  email);
	public Counsellor  findByEmailAndPwd(String  email,String  pwd);
}
