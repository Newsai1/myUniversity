package com.ls.user.serviceImpl;

import org.apache.ibatis.annotations.Param;

import com.ls.user.bo.StudentBo;

public interface StudentServiceImpl {

	int getStudentCount();
	
	StudentBo queryById(Integer id);
}
