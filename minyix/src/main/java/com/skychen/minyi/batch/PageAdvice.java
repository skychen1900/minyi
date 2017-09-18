package com.skychen.minyi.batch;

import org.apache.commons.lang.StringUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skychen.minyi.web.MinyiController;

import lombok.Getter;
import lombok.Setter;

@Component
public class PageAdvice {

	final static Logger logger = LoggerFactory.getLogger(MinyiController.class);

	@Getter
	@Setter
	private String today;

	@Getter
	@Setter
	private String yesterday;

	@Autowired
	AdviceAction adviceAction;

	public void exec() {

		// 从昨天到今天的投诉
		String urlDate = Constant.FROMDATE + yesterday + Constant.TODATE + today;
//		String urlDate = Constant.FROMDATE + "20170915" + Constant.TODATE + "20170916";

		// 只是今天的投诉
		// String urlDate = Constant.FROMDATE + today + Constant.TODATE + today;
		
		Integer pagesNum = getTotalPageNum(urlDate);
		if (pagesNum > 0) {
			getAdvices(pagesNum, urlDate);
		}

	}

	/* 获取总页数 */
	private Integer getTotalPageNum(String urlDate) {
		Integer pageNum = 0;

		logger.debug("请求URL={}", Constant.详细页 + urlDate);

		try {
			// URL paseUrl = new URL(Constant.详细页 + urlDate);
			// Document document = Jsoup.parse(paseUrl, 1000*60);

			// 解析URL,设置连接超时的时间
			Document document = Jsoup.connect(Constant.详细页 + urlDate).timeout(60 * 1000)
				.userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) "
					+ "AppleWebKit/537.36 (KHTML, like Gecko) "
					+ "Chrome/42.0.2311.152 Safari/537.36")
				.get();

			Elements spans = document.getElementsByTag("span");

			if (spans.size() <= 0) {
				logger.info("远程服务器访问拒绝， 抓取页面Span元素为0，当时间段抓取取消 !", document);
			}

			for (Element span : spans) {
				if (StringUtils.containsIgnoreCase(span.text(), "共")
					& StringUtils.containsIgnoreCase(span.text(), "页")) {
					pageNum = Integer.parseInt(span.text().replaceAll(".*共([0-9]*)页.*", "$1"));
					break;
				}
			}

		} catch (Exception e) {
			logger.error("远程网站访问异常   !!!", e);
		}
		return pageNum;
	}

	/**
	 * 获取每页内容并写入DB
	 * 
	 * @param tagLists
	 */
	private void getAdvices(Integer pageNum, String urlDate) {
		
		logger.debug("总页数={}", pageNum);

		try {
			for (int i = 1; i <= pageNum; i++) {
				String url = Constant.详细页 + urlDate + Constant.分页信息 + i;
				
//				AdviceAction adviceAction = new AdviceAction();
				adviceAction.setUrl(url);
				adviceAction.run();
				Thread.sleep(5);
				
				if(pageNum%100 == 0){
					logger.info("处理完成 {}页", pageNum);
				}
			}
		} catch (Exception e) {
			logger.error("未知异常", e);
		}
		
		logger.debug("更新件数{}", AdviceAction.updateCnt);
	}
}
