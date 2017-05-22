package com.ls.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.user.bo.StudentBo;
import com.ls.user.dao.StudentDao;
import com.ls.user.serviceImpl.StudentServiceImpl;

@Service
public class StudentService implements StudentServiceImpl {

	@Autowired
	private StudentDao dao;
	public int getStudentCount() {
		return this.dao.getStudentCount();
	}
	public StudentBo queryById(Integer id) {
		return this.dao.queryById(id);
	}
}
