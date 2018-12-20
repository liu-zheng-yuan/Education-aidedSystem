package com.nju.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Courseware {
	private Integer cwId;
	private String cwName;
	private Integer cId;
	private String url;
	private Integer downCount;
	private Integer viewCount;
	private Date uploadTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Integer getCwId() {
		return cwId;
	}
	public void setCwId(Integer cwId) {
		this.cwId = cwId;
	}
	public String getCwName() {
		return cwName;
	}
	public void setCwName(String cwName) {
		this.cwName = cwName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getDownCount() {
		return downCount;
	}
	public void setDownCount(Integer downCount) {
		this.downCount = downCount;
	}
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	public Integer getcId() {
		return cId;
	}
	public void setcId(Integer cId) {
		this.cId = cId;
	}
	
	

}
