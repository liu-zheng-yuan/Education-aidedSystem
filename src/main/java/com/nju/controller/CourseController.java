package com.nju.controller;

import com.nju.bean.*;
import com.nju.service.StudentService;
import com.nju.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    /*
    * 获得登录用户所有已经选的课
    * */
    @ResponseBody
    @RequestMapping(value = "/myCourses",method = RequestMethod.GET)
    public List<Course> getMyCourses(HttpSession session) {
        Student student = (Student) session.getAttribute("loginUser");
        if (student == null){
            return null;
        }
        try {
            List<Course> courses = studentService.getSelectedCourse(student.getsId());
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /*
     * 获得登录用户所有没有选的课
     * */
    @ResponseBody
    @RequestMapping(value = "/notSelectCourses",method = RequestMethod.GET)
    public List<Course> notSelectCourses(HttpSession session) {
        Student student = (Student) session.getAttribute("loginUser");
        if (student == null){
            return null;
        }
        try {
            List<Course> courses = studentService.getNotSelectCourses(student.getsId());
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @ResponseBody
    @RequestMapping(value = "/getAllCourses",method = RequestMethod.GET)
    public List<Course> getAllCourses(HttpSession session) {
        Student student = (Student) session.getAttribute("loginUser");
        if (student == null){
            return null;
        }
        try {
            List<Course> allCourses = new ArrayList<>();
            List<Course> selectedCourses = studentService.getSelectedCourse(student.getsId());
            List<Course> unselectedCourses = studentService.getNotSelectCourses(student.getsId());
            for (Course c : selectedCourses) {
                c.setSelected(1);
                allCourses.add(c);
            }
            for (Course c : unselectedCourses) {
                c.setSelected(0);
                allCourses.add(c);
            }
            return allCourses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /*
     * 获得登录用户某门课的成绩
     * */
    @ResponseBody
    @RequestMapping(value = "/getCourseScore",method = RequestMethod.GET)
    public Integer getCourseScore(Integer cId,HttpSession session) {
        Student student = (Student) session.getAttribute("loginUser");
        if (student == null){
            return null;
        }
        try {
            return studentService.getCourseScore(student.getsId(),cId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /*
     * 获得某门课所有的课堂问题
     * */
    @ResponseBody
    @RequestMapping(value = "/getQuestions",method = RequestMethod.GET)
    public List<Homework> getQuestions(Integer cId,HttpSession session) {
        Student student = (Student) session.getAttribute("loginUser");
        if (student == null){
            return null;
        }
        try {
            List<Homework> homeworks = studentService.getQuestions(cId);
            List<Homework> answeredHomeworks = studentService.getAnsweredQuestion(cId,student.getsId());
            for (Homework h : homeworks){
                for (Homework ah : answeredHomeworks) {
                    if (h.gethId() == ah.gethId()) {
                        h.setAnswered(true);
                    }
                }
            }
            return homeworks;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /*
     * 获得某个学生的某个课程所有的课（包括课堂评价）
     * */
    @ResponseBody
    @RequestMapping(value = "/getLessons",method = RequestMethod.POST)
    public List<Lesson> getLessons(Integer cId,HttpSession session ) {
        Student student = (Student) session.getAttribute("loginUser");
        if (student == null){
            return null;
        }
        try {
            return studentService.getLessons(student.getsId(),cId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /*
     * 获得某个学生对某个课程的评价
     * */
    @ResponseBody
    @RequestMapping(value = "/getCourseComment", method = RequestMethod.POST)
    public CourseComment commentCourse(Integer cId,HttpSession session) {
        Student student = (Student) session.getAttribute("loginUser");
        if (student == null ) {
            return null;
        }
        try {
            return studentService.getCourseComment(student.getsId(),cId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
