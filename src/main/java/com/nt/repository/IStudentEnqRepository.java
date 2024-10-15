package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.StudentEnq;

public interface IStudentEnqRepository extends JpaRepository<StudentEnq, Integer> {

	public List<StudentEnq> findByCid(Integer  cid);
}
