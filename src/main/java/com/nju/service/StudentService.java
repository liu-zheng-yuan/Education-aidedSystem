package com.nju.service;

import com.nju.bean.Course;
import com.nju.bean.Courseware;
import com.nju.bean.Homework;
import com.nju.bean.Lesson;
import com.nju.bean.Student;
import com.nju.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    public Student getStudent4Login(Student student){
        return studentDao.getStudent4Login(student);
    }

    public void selectCourse(Integer sId, Integer cId) {
        studentDao.selectCourse(sId,cId);
    }

    public void unselectCourse(Integer sId, Integer cId) {
        studentDao.unselectCourse(sId, cId);
    }

    public List<Course> getSelectedCourse(Integer sId) {
        return studentDao.getSelectedCourse(sId);
    }

    public List<Course> getNotSelectCourses(Integer sId) {
        return studentDao.notSelectCourses(sId);
    }

    public String getCourseToken(Integer cId) {
        return studentDao.getCourseToken(cId);
    }

    public void signOn(Integer sId, Integer lId) {
        if (studentDao.countSigned(sId, lId) > 0){
            return;
        }
        studentDao.signOn(sId, lId);
    }

    public Integer getCourseScore(Integer sId,Integer cId) {
        return studentDao.getCourseScore(sId,cId);
    }

    public void answerLessonQuestion(Integer sId, Integer hId, String answer) {
        studentDao.answerLessonQuestion(sId, hId, answer);
        return;

    }

    public List<Homework> getQuestions(Integer cId) {
        return studentDao.getQuestions(cId);
    }

    public List<Lesson> getLessons(Integer sId, Integer cId) {
        return studentDao.getLessons(sId, cId);
    }

    public void commentLesson(Integer sId, Integer lId, String comment) {
        studentDao.commentLesson(sId,lId,comment);
    }

    public void commentCourse(Integer sId, Integer cId, String comment) {
        studentDao.commentCourse(sId,cId,comment);
    }
    
    public Courseware getCourseware(Integer cwId) {
    	return studentDao.getCourseware(cwId);  	
    }
    
    public void updateCoursewareDownCount(Integer cwId) {
    	studentDao.updateCoursewareDownCount(cwId);
    }
    
    public void updateCoursewareViewCount(Integer cwId) {
    	studentDao.updateCoursewareViewCount(cwId);
    }
    
    public List<Courseware> getCoursewareByCId(Integer cId) {
    	return studentDao.getCoursewareByCId(cId);
    }

    public List<Homework> getAnsweredQuestion(Integer cId,Integer sId) {
        return studentDao.getAnsweredQuestion(cId,sId);
    }

    public String getCourseComment(Integer sId, Integer cId) {
        return studentDao.getCourseComment(sId,cId);
    }
}
