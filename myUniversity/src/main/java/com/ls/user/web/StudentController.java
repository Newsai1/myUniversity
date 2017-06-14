package com.ls.user.web;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ls.user.bo.StudentBo;
import com.ls.user.serviceImpl.StudentServiceImpl;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentServiceImpl studentService;

	@RequestMapping("/showStudent")
	public String toIndex(HttpServletRequest request, Model model) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
//		StudentBo stu = this.studentService.queryById(id);
		 StudentBo stu = new StudentBo();
		 stu.setName("wangbadan");
		model.addAttribute("stu", stu);

//		JenkinsServer jenkinsServer = new JenkinsServer(new URI("http://192.168.214.108:8080"), "admin", "root");
//		Map<String, Job> jobs = jenkinsServer.getJobs();
//		Job  j1 = jobs.get("test1");
//		JobWithDetails jobWithDetails = j1.details();
//		jobWithDetails.getBuilds();
//		jobWithDetails.build();
//		j1.build();
		return "showStudent";
	}
	
	@RequestMapping("/testJenkins")
	public String getConfigure(HttpServletRequest request, Model model){
		// Credentials
		String username = "admin";
		String password = "root";

		// Jenkins url
		String jenkinsUrl = "http://localhost:8080";
		
		String urlString = jenkinsUrl +"/job/"+request.getParameter("jobName")+"/configure";
		
		URI uri = URI.create(urlString);
		HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
		
		//凭证提供者
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		// UsernamePasswordCredentials 是明文用户名、密码认证
		credsProvider.setCredentials(
				new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(username, password));
		// Create AuthCache instance
		//基础认证缓存
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(host, basicAuth);
		
		
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		HttpPost httpPost = new HttpPost(uri);
		
//		FileEntity entity = new FileEntity(new File("doc/config.xml"));
//		httpPost.setEntity(entity);
		
		httpPost.setHeader("Content-Type", "application/xml;charset=UTF-8"); 
		//httpPost.setEntity(reqEntity);
		// Add AuthCache to the execution context
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setAuthCache(authCache);
		HttpResponse response;
		try {
			response = httpClient.execute(host, httpPost, localContext);
			String result = EntityUtils.toString(response.getEntity());
			model.addAttribute("result", result);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "showS";
	}
	
	@RequestMapping("/testHttpClient")
	public String learnHttpClient(HttpServletRequest request,Model model){
		
		String url = "http://localhost:8090/myUniversity/student/showStudent?id=1";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/xml;charset=UTF-8");
		HttpResponse response;
		try {
			response = httpClient.execute(post);
			model.addAttribute("result", EntityUtils.toString(response.getEntity()));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "showS";
	}

}
