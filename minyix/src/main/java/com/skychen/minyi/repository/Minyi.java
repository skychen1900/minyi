package com.skychen.minyi.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the minyi database table.
 * 
 */
@Entity
@Table(name="main")
public class Minyi {
	
	@Id
	@Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="cdate")
	private String cdate;
	
	@Column(name="ctime")
	private String ctime;
	
	@Column(name="status")
	private String status;
	
	@Column(name="title")
	private String title;
	
	@Column(name="author")
	private String author;

	@Column(name="content")
	private String content;
	
	@Column(name="replycontent")
	private String replycontent;
	
	@Column(name="replybumen")
	private String replybumen;

	@Column(name="replydate")
	private String replydate;

	@Column(name="replytime")
	private String replytime;

	@Column(name="href")
	private String href;
	
	@Column(name="mdate")
	private String mdate;
	
	@Column(name="mtime")
	private String mtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReplycontent() {
		return replycontent;
	}

	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}

	public String getReplybumen() {
		return replybumen;
	}

	public void setReplybumen(String replybumen) {
		this.replybumen = replybumen;
	}

	public String getReplydate() {
		return replydate;
	}

	public void setReplydate(String replydate) {
		this.replydate = replydate;
	}

	public String getReplytime() {
		return replytime;
	}

	public void setReplytime(String replytime) {
		this.replytime = replytime;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
	
}