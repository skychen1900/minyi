package com.skychen.minyi.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Time;
import java.util.Date;
import java.sql.Timestamp;

/**
 * The persistent class for the minyi database table.
 * 
 */
@Entity
@Table(name="Minyi")
public class Minyi {
	
	@Id
	@Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String author;

	private Date CDate;

	private String content;

	private Time CTime;

	private String href;

	private Date MDate;

	private int minyiID;

	private Time MTime;

	private String replyBumen;

	private String replyContent;

	private Date replyDate;

	private Time replyTime;

	private String status;

	private String title;

	@Column(name="update_at")
	private Timestamp updateAt;

	public Minyi() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCDate() {
		return this.CDate;
	}

	public void setCDate(Date CDate) {
		this.CDate = CDate;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Time getCTime() {
		return this.CTime;
	}

	public void setCTime(Time CTime) {
		this.CTime = CTime;
	}

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Date getMDate() {
		return this.MDate;
	}

	public void setMDate(Date MDate) {
		this.MDate = MDate;
	}

	public int getMinyiID() {
		return this.minyiID;
	}

	public void setMinyiID(int minyiID) {
		this.minyiID = minyiID;
	}

	public Time getMTime() {
		return this.MTime;
	}

	public void setMTime(Time MTime) {
		this.MTime = MTime;
	}

	public String getReplyBumen() {
		return this.replyBumen;
	}

	public void setReplyBumen(String replyBumen) {
		this.replyBumen = replyBumen;
	}

	public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyDate() {
		return this.replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public Time getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(Time replyTime) {
		this.replyTime = replyTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getUpdateAt() {
		return this.updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

}