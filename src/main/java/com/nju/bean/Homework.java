package com.nju.bean;

import java.sql.Date;

public class Homework {
    private int hId;
    private int cId;
    private int lId;
    private String hTitle;
    private String hContent;
    private Date releaseTime;
    private Boolean answered = false;//标识有没有

    public Boolean getAnswered() {
        return answered;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int gethId() {
        return hId;
    }

    public void sethId(int hId) {
        this.hId = hId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getlId() {
        return lId;
    }

    public void setlId(int lId) {
        this.lId = lId;
    }

    public String gethTitle() {
        return hTitle;
    }

    public void sethTitle(String hTitle) {
        this.hTitle = hTitle;
    }

    public String gethContent() {
        return hContent;
    }

    public void sethContent(String hContent) {
        this.hContent = hContent;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "hId=" + hId +
                ", cId=" + cId +
                ", lId=" + lId +
                ", hTitle='" + hTitle + '\'' +
                ", hContent='" + hContent + '\'' +
                ", releaseTime=" + releaseTime +
                ", answered=" + answered +
                '}';
    }
}
