package com.ls.user.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ls.user.bo.StudentBo;
import com.ls.user.serviceImpl.StudentServiceImpl;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentServiceImpl studentService;
	
	@RequestMapping("/showStudent")  
    public String toIndex(HttpServletRequest request,Model model){  
        int id = Integer.parseInt(request.getParameter("id"));  
        StudentBo stu = this.studentService.queryById(id);  
//        StudentBo stu = new StudentBo();
//        stu.setName("wangbadan");
        model.addAttribute("stu", stu);  
        return "showS";  
    }  
	
}
