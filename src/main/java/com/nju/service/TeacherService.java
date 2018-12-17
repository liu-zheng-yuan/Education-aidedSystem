package com.nju.service;

import com.nju.bean.Teacher;
import com.nju.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    TeacherDao teacherDao;
    public Teacher getTeacher4Login(Teacher teacher) {
        return teacherDao.getTeacher4Login(teacher);
    }
}
