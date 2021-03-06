package com.nju.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

//封装了某个学生的某门课程的课堂信息包括课堂评价
public class Lesson {
    private int lId;
    private int cId;
    private Date lTime;
    private String comment;
    private Date cTime;

    @Override
    public String toString() {
        return "Lesson{" +
                "lId=" + lId +
                ", cId=" + cId +
                ", lTime=" + lTime +
                ", comment='" + comment + '\'' +
                ", cTime=" + cTime +
                '}';
    }

    public int getlId() {
        return lId;
    }

    public void setlId(int lId) {
        this.lId = lId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public Date getlTime() {
        return lTime;
    }

    public void setlTime(Date lTime) {
        this.lTime = lTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }
}
