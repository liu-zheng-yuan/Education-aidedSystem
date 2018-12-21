package com.nju.dao;

import com.nju.bean.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface StudentDao {
    Student getStudent4Login(Student student);

    void selectCourse(@Param("sId") Integer sId, @Param("cId") Integer cId);

    void unselectCourse(@Param("sId")Integer sId, @Param("cId") Integer cId);

    List<Course> getSelectedCourse(Integer sId);

    List<Course> notSelectCourses(Integer sId);

    String getCourseToken(Integer cId);

    void signOn(@Param("sId")Integer sId, @Param("lId")Integer lId);

    //找出此人此节课签到的次数 大于0说明签到过了
    Integer countSigned(@Param("sId")Integer sId, @Param("lId")Integer lId);

    Integer getCourseScore(@Param("sId")Integer sId,@Param("cId")Integer cId);

    void answerLessonQuestion(@Param("sId")Integer sId, @Param("hId")Integer hId, @Param("answer")String answer);

    List<Homework> getQuestions(Integer cId);

    List<Homework> getAnsweredQuestion(@Param("cId")Integer cId,@Param("sId")Integer sId);

    List<Lesson> getLessons(@Param("sId")Integer sId, @Param("cId")Integer cId);

    void commentLesson(@Param("sId") Integer sId, @Param("lId") Integer lId, @Param("comment") String comment, @Param("now")Date now);

    void commentCourse(@Param("sId") Integer sId, @Param("cId") Integer cId, @Param("comment") String comment, @Param("now")Date now);

    Courseware getCourseware(@Param("cwId")Integer cwId);

    void updateCoursewareDownCount(@Param("cwId")Integer cwId);

    void updateCoursewareViewCount(@Param("cwId")Integer cwId);

    List<Courseware> getCoursewareByCId(@Param("cId")Integer cId);

    CourseComment getCourseComment(@Param("sId")Integer sId, @Param("cId")Integer cId);

    Student getStudentByWxid(String wxid);

    void register(@Param("maxId")int maxId,@Param("sName")String sName, @Param("wxId")String wxId);

    int getMaxSId();

    List<Integer> getLessonsBycId(Integer cId);

    void addEmptyLessonCommentRecord(@Param("sId")Integer sId, @Param("lids")List<Integer> lessonIds);

    void delLessonCommentRecord(@Param("sId")Integer sId, @Param("lids")List<Integer> lessonIds);
}

