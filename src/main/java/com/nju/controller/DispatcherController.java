package com.nju.controller;

import com.nju.bean.Student;
import com.nju.bean.Teacher;
import com.nju.service.StudentService;
import com.nju.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class DispatcherController {

    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    /*
    * 学生登陆
    * */
    @ResponseBody
    @RequestMapping("/doSLogin")
    public String doSLogin(Student student, HttpSession session){
        Student s = studentService.getStudent4Login(student);
        if (s != null) {
            session.setAttribute("loginUser",s);
            return "success";
        } else {
            return "failed";
        }
    }
    /*
    * 老师登陆
    * */
    @ResponseBody
    @RequestMapping("/doTLogin")
    public String doTLogin(Teacher teacher, HttpSession session){
        Teacher t = teacherService.getTeacher4Login(teacher);
        if (t != null) {
            session.setAttribute("loginUser",t);
            return "success";
        } else {
            return "failed";
        }
    }
    /*
    * 学生选课
    * */
    @ResponseBody
    @RequestMapping("/selectCourse")
    public String selectCourse(Integer cId,String token,HttpSession session){
        Student student = (Student) session.getAttribute("loginUser");
        String realToken = studentService.getCourseToken(cId);
        if (student == null || !realToken.equals(token)){
            return "failed";
        }
        try {
            studentService.selectCourse(student.getsId(), cId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    /*
     * 学生退选课
     * */
    @ResponseBody
    @RequestMapping("/unselectCourse")
    public String unselectCourse(Integer cId, HttpSession session) {
        Student student = (Student) session.getAttribute("loginUser");
        if (student == null) {
            return "failed";
        }
        try {
            studentService.unselectCourse(student.getsId(), cId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    /*
     * 学生课堂上签到
     * 需要老师提供一个token，即lId，当堂课的ID号
     * */
    @ResponseBody
    @RequestMapping(value = "/signOn", method = RequestMethod.GET)
    public String signOn(Integer lId, HttpSession session) {
        Student student = (Student) session.getAttribute("loginUser" );
        if (student == null) {
            return "failed";
        }
        try {
            studentService.signOn(student.getsId(), lId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    /*
     * 学生回答问题
     * */
    @ResponseBody
    @RequestMapping(value = "/answerLessonQuestion", method = RequestMethod.POST)
    public String answerLessonQuestion(Integer hId, String answer, HttpSession session) {
        Student student = (Student) session.getAttribute("loginUser");
        if (student == null || answer == null || answer.length() > 500) {
            return "failed";
        }
        try {
            studentService.answerLessonQuestion(student.getsId(), hId,answer);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }
    /*
     * 学生对某堂课进行课堂评价
     * */
    @ResponseBody
    @RequestMapping(value = "/commentLesson", method = RequestMethod.POST)
    public String commentLesson(Integer lId, String comment,HttpSession session) {
        Student student = (Student) session.getAttribute("loginUser");
        //comment 为空或太长则不行
        if (student == null || comment == null || comment.length() > 500) {
            return "failed";
        }
        try {
            studentService.commentLesson(student.getsId(),lId,comment);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }
    /*
     * 学生对某个课程进行评价
     * */
    @ResponseBody
    @RequestMapping(value = "/commentCourse", method = RequestMethod.POST)
    public String commentCourse(Integer cId, String comment,HttpSession session) {
        Student student = (Student) session.getAttribute("loginUser");
        //comment 为空或太长则不行
        if (student == null || comment == null || comment.length() > 500) {
            return "failed";
        }
        try {
            studentService.commentCourse(student.getsId(),cId,comment);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

}
