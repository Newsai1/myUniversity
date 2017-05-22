package mytest;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ls.user.serviceImpl.StudentServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class) // ��ʾ�̳���SpringJUnit4ClassRunner��
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class MyTest {

	// private ApplicationContext ac = null;
	@Resource
	private StudentServiceImpl studentService;

	@Test
	public void test1() {
		System.out.println("********************" + this.studentService.getStudentCount());
		// logger.info("ֵ��"+user.getUserName());
	}
}
