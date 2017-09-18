package com.skychen.minyi.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.skychen.minyi.batch.AdviceAction;
import com.skychen.minyi.batch.PageAdvice;
import com.skychen.minyi.setting.CronSetting;
import com.skychen.minyi.setting.DruidConfiguration;

@Component
public class ScheduledTasks {

	private static final SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy/MM/dd HH:MM:SS");
	private static final Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);

	@Autowired
	CronSetting cron;
	
	@Autowired
	PageAdvice pageAdvice;

//	@Scheduled(fixedRate = 10000)
//	public void reportCurrentTime() {
//		System.out.println("Rate " + sdf.format(new Date()));
//		try {
//			TimeUnit.SECONDS.sleep(2L);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Scheduled(initialDelay = 15000, fixedDelay = 10000)
//	public void report() {
//		System.out.println("Delay " + sdf.format(new Date()));
//		try {
//			TimeUnit.SECONDS.sleep(2L);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

//	@Scheduled(cron = "0  */1  *  *  *  *", zone = "Asia/Tokyo")
	@Scheduled(cron = "${cron.cron1}", zone = "Asia/Tokyo")
	public void cron() {
		String today;
		String yesterday;
		
		logger.info("[Cron]Batch执行  {}",sdfTime.format(new Date()));
		
		//Today
		today = sdfYMD.format(new Date()).toString();
		
		//Yesterday
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1); 
		yesterday = sdfYMD.format(calendar.getTime());

//		PageAdvice pageAdvice = new PageAdvice();
		pageAdvice.setToday(today);
		pageAdvice.setYesterday(yesterday);
		pageAdvice.exec();
		
		//初期化
		AdviceAction.updateCnt = 0;
	}

//	@Scheduled(cron = "${cron.cron2}")
//	public void cron2() {
//		System.out.println("Cron2 " + sdf.format(new Date()));
//		try {
//			TimeUnit.SECONDS.sleep(2L);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

}