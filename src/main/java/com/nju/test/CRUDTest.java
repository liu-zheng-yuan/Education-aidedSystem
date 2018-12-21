package com.nju.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/* 
 * 使用springtest模块提供的测试请求功能，得到返回值，测试curd请求的正确性，
 * 坑：SPring4测试的时候需要servlet3.0的支持
 * */
@RunWith(value = SpringJUnit4ClassRunner.class)
//一般自动装配的是bean，如果需要装配ioc容器本身，要用这个注解
@WebAppConfiguration
//还需要传入springmvc的配置文件，因为不在webinf文件夹下，所以直接以file：开头写全路径名
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml", "classpath:spring/spring-mvc.xml"})
public class CRUDTest {
    //  传入springmvc的iod容器本身
    @Autowired
    WebApplicationContext context;
    //  虚拟mvc请求，获取到处理结果
    private MockMvc mockMvc;
    //
    private MockHttpSession session;
    /*
     * 模拟登录之后信息保存在session
     * */
    
    
    private HttpSession getLoginSession() throws Exception {
        //模拟请求之后 登录信息保存在session中
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/doSLogin")
                .param("wxId","aaa111")).andReturn();
        return result.getRequest().getSession();
    }

    @Before//每次测试之前都需要初始化，用before标注
    public void initMokcMcx() {
//      要初始化mockmvc对象，需要mvc的ioc容器本身
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//      要使用mocksession模拟登陆之后的操作 先new一个对象
        this.session = new MockHttpSession();
          
    }
    @Test
    public void testLogin() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/doSLogin")
                        .param("wxId","bbb222")).andReturn();
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders.get("/selectCourse")
                .param("cId","1")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        System.out.println(result1.getResponse().getContentAsString());
    }

    @Test
    public void testSelectCourse() throws Exception{
        this.mockMvc.perform(post("/selectCourse").param("cId", "5").param("token","111")
                .session((MockHttpSession) getLoginSession()))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void testUnselectCourse() throws Exception {
        this.mockMvc.perform(post("/unselectCourse").param("cId","1")
                .session((MockHttpSession)getLoginSession()))
                .andDo(print());
    }
    @Test
    public void testGetSelectedCourse() throws Exception {
        this.mockMvc.perform(get("/myCourses")
                .session((MockHttpSession)getLoginSession()))
                .andDo(print());
    }
    @Test
    public void testNotSelectCourses() throws Exception {
        this.mockMvc.perform(get("/notSelectCourses")
                .session((MockHttpSession)getLoginSession()))
                .andDo(print());
    }
    @Test
    public void testSignOn() throws Exception {
        this.mockMvc.perform(get("/signOn").param("lId","1")
                .session((MockHttpSession)getLoginSession()))
                .andDo(print());
    }
    @Test
    public void testGetCourseScore() throws Exception {
        this.mockMvc.perform(get("/getCourseScore").param("cId","2")
                .session((MockHttpSession)getLoginSession()))
                .andDo(print());
    }
    @Test
    public void testAnswerLessonQuestion() throws Exception {
        this.mockMvc.perform(post("/answerLessonQuestion")
                .param("hId","1")
                .param("answer","2")
                .session((MockHttpSession)getLoginSession()))
                .andDo(print());
    }
    @Test
    public void testGetQuestions() throws Exception {
        this.mockMvc.perform(get("/getQuestions")
                .param("cId","1")
                .session((MockHttpSession)getLoginSession()))
                .andDo(print());
    }
    @Test
    public void testGetLessons() throws Exception {
        this.mockMvc.perform(post("/getLessons")
                .param("cId","2")
                .session((MockHttpSession)getLoginSession()))
                .andDo(print());
    }

    @Test
    public void testCommentLesson() throws Exception {
        this.mockMvc.perform(post("/commentLesson")
                .param("lId","5")
                .param("comment","1号用户上第3类课的第5号课2")
                .session((MockHttpSession)getLoginSession()))
                .andDo(print());
    }
    @Test
    public void testCommentCourse() throws Exception {
        this.mockMvc.perform(post("/commentCourse")
                .param("cId","1")
                .param("comment","1号用户对1号课程的评价")
                .session((MockHttpSession)getLoginSession()))
                .andDo(print());
    }
    
    
    @Test
    public void testViewCourseware() throws Exception {
    	this.mockMvc.perform(get("/getCoursewareInfoByCwId")
    			.param("cwId","3").session((MockHttpSession) getLoginSession())).andDo(print());
    }
  
    /*
     * 测试下载功能
     * 测试时把FileController.download(Integer cwId,HttpServletResponse response,HttpSession session)
     * 方法中的realPath修改为测试路径
     */
    @Test
    public void testDownloadCourseware() throws Exception {
    	this.mockMvc.perform(get("/download")
    			.param("cwId", "7").session((MockHttpSession) getLoginSession())).andDo(print());
    }
    
    @Test
    public void testViewCourwareByCId() throws Exception {
    	this.mockMvc.perform(get("/getCoursewareInfoByCId")
    			.param("cId", "1").session((MockHttpSession) getLoginSession())).andDo(print());
    }
    @Test
    public void testGetAllCourses() throws Exception{
        this.mockMvc.perform(get("/getAllCourses")
                .session((MockHttpSession) getLoginSession())).andDo(print());
    }
    @Test
    public void testGetCourseComment() throws Exception{
        this.mockMvc.perform(post("/getCourseComment").param("cId","2")
                .session((MockHttpSession) getLoginSession())).andDo(print());
    }
    @Test
    public void testRegister() throws Exception{
        this.mockMvc.perform(post("/register").param("sName","stu6").param("wxId","fff666"))
                .andDo(print());
    }
}

