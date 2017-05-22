package com.ls.user.dao;

import org.apache.ibatis.annotations.Param;

import com.ls.user.bo.StudentBo;

public interface StudentDao {

	int getStudentCount();
	
	StudentBo queryById(@Param("id") Integer id);
}
