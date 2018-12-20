package com.nju.dao;

import com.nju.bean.Course;
import com.nju.bean.Courseware;
import com.nju.bean.Homework;
import com.nju.bean.Lesson;
import com.nju.bean.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    void commentLesson(@Param("sId")Integer sId, @Param("lId")Integer lId, @Param("comment")String comment);

    void commentCourse(@Param("sId")Integer sId, @Param("cId")Integer cId, @Param("comment")String comment);

    Courseware getCourseware(@Param("cwId")Integer cwId);

    void updateCoursewareDownCount(@Param("cwId")Integer cwId);

    void updateCoursewareViewCount(@Param("cwId")Integer cwId);

    List<Courseware> getCoursewareByCId(@Param("cId")Integer cId);

    String getCourseComment(@Param("sId")Integer sId, @Param("cId")Integer cId);
}

