package com.nju.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nju.bean.Courseware;
import com.nju.bean.Student;
import com.nju.service.StudentService;
import com.nju.service.TeacherService;

	@Controller
	public class FileController{
	    @Autowired
	    StudentService studentService;
	    @Autowired
	    TeacherService teacherService;
	    @ResponseBody
	@RequestMapping(value = "/download",method = RequestMethod.GET)
	public String download(Integer cwId,HttpServletResponse response,HttpSession session){
	    	 Student student = (Student) session.getAttribute("loginUser");
	         if (student == null){
	             return null;
	         }
	    	try {
	    	   //1.获取要下载的文件的绝对路径
				//获取绝对路径

	           Courseware cw=studentService.getCourseware(cwId);
	            String realPath=cw.getUrl();
	    		//String realPath = "D:\\\\test.docx";
	           
	           //2.获取要下载的文件名
	           String fileName = realPath.substring(realPath.lastIndexOf("\\")+1);
	             response.reset();
	             response.setContentType("application/octet-stream");
	           //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
	            response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(fileName, "UTF-8"));
	            InputStream in = new FileInputStream(realPath);//获取文件输入流
	            int len = 0;
	            byte[] buffer = new byte[1024];
	            OutputStream out = response.getOutputStream();
	            while ((len = in.read(buffer)) > 0) {
	                 out.write(buffer,0,len);//将缓冲区的数据输出到客户端浏览器
	             }
	            in.close();
	            studentService.updateCoursewareDownCount(cwId);
	            return "succeed";
	            }
	    	catch( Exception e) {
	    		e.printStackTrace();
	    		return "failed";
	    	}
		
	}
	/*
	 * 查看课件信息
	 */
    @ResponseBody
    @RequestMapping(value = "/getCoursewareInfo",method = RequestMethod.GET)
	public Courseware getCoursewareInfo(Integer cwId,HttpSession session) {
    	 Student student = (Student) session.getAttribute("loginUser");
         if (student == null){
             return null;
         }
		try {
		Courseware cw=studentService.getCourseware(cwId);
		studentService.updateCoursewareViewCount(cwId);
		return cw;
		}
		
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

}
