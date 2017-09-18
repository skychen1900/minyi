package com.skychen.minyi.repository;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

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
@Component
@Table(name="minyi")
public class Minyi {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="minyiid", nullable=false)
	@Getter @Setter
	private int minyiid;	

	@Column(name="cdate")
	private Timestamp cdate;
	
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
	private Timestamp replydate;

	@Column(name="href")
	private String href;
	
	@Column(name="mdate")
	private Timestamp mdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCdate() {
		return cdate;
	}

	public void setCdate(Timestamp cdate) {
		this.cdate = cdate;
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

	public Timestamp getReplydate() {
		return replydate;
	}

	public void setReplydate(Timestamp replydate) {
		this.replydate = replydate;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Timestamp getMdate() {
		return mdate;
	}

	public void setMdate(Timestamp mdate) {
		this.mdate = mdate;
	}

}