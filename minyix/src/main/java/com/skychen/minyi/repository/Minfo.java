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
@Table(name="minfo")
public class Minfo {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
        @Getter @Setter
	private int id;

	@Column(name="totalnum")
        @Getter @Setter
	private Integer totalnum;
  
	@Column(name="doingnum")
        @Getter @Setter
	private Integer doingnum;  
   
	@Column(name="completenum")
        @Getter @Setter
	private Integer completenum;

	@Column(name="newgetdate")
        @Getter @Setter
	private Timestamp newgetdate;
  
	@Column(name="todayadvicenum")
        @Getter @Setter
	private Integer todayadvicenum;
  
	@Column(name="todaycompletenum")
        @Getter @Setter
	private Integer todaycompletenum;
  
}
