package com.nju.service;

import com.nju.bean.*;
import com.nju.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    public Student getStudent4Login(Student student){
        return studentDao.getStudent4Login(student);
    }

    public void selectCourse(Integer sId, Integer cId) {
        // 选课的时候要把该门课对应的lesson加入lesson_comment_record表里，不然后面不能返回所有课程的评价，会少。更新评价的时候也会缺失
        studentDao.selectCourse(sId,cId);
        List<Integer> lessonIds = studentDao.getLessonsBycId(cId);
        studentDao.addEmptyLessonCommentRecord(sId,lessonIds);
    }

    public void unselectCourse(Integer sId, Integer cId) {
        //退课的时候也要把对应课程的对应lesson的对应lesson_comment_record表删除相关课程评价
        List<Integer> lessonIds = studentDao.getLessonsBycId(cId);
        studentDao.delLessonCommentRecord(sId,lessonIds);
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

    public void commentLesson(Integer sId, Integer lId, String comment, Date now) {
        studentDao.commentLesson(sId,lId,comment,now);
    }

    public void commentCourse(Integer sId, Integer cId, String comment, Date now) {
        studentDao.commentCourse(sId,cId,comment,now);
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

    public CourseComment getCourseComment(Integer sId, Integer cId) {
        return studentDao.getCourseComment(sId,cId);
    }

    public Student getStudentByWxid(String wxid) {
        return studentDao.getStudentByWxid(wxid);
    }

    public void register(String sName, String wxId) {
        int maxId = studentDao.getMaxSId();
        studentDao.register(maxId+1,sName,wxId);
    }
}
