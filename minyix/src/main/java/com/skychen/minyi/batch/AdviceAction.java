package com.skychen.minyi.batch;

import java.net.SocketException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skychen.minyi.repository.Minyi;
import com.skychen.minyi.service.MinyiService;
import com.skychen.minyi.web.MinyiController;

import lombok.Getter;
import lombok.Setter;

@Component
public class AdviceAction {

	final static Logger logger = LoggerFactory.getLogger(MinyiController.class);
	public static int updateCnt = 0;

	@Getter
	@Setter
	private String url;
	
	@Autowired
	MinyiService minyiService;
	
	@Autowired
	Minyi minyi;

	public void run() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = null;
		String str = null;

		try {
			Document document = Jsoup.connect(url).timeout(60 * 1000)
				.userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) "
					+ "AppleWebKit/537.36 (KHTML, like Gecko) "
					+ "Chrome/42.0.2311.152 Safari/537.36")
				.get();

			Elements ol = document.select("ol");
			Iterator<Element> olIter = ol.iterator();

			while (olIter.hasNext()) {
				try {
					Element ol_x = olIter.next();
					
					// id设定
					minyi.setMinyiid(
						Integer.parseInt(aElement.attr("href").replaceAll(".*id=([0-9]*)", "$1")));
					minyi.setId(minyi.getMinyiid());

					// 发布者
					Elements authorElement = ol_x.select("ul>h5");
					minyi.setAuthor(authorElement.text());

					// 发布日期
					Elements timeElement = ol_x.select("ul>li");
					timestamp = new Timestamp(dateFormat.parse(timeElement.text().replaceAll("发表于", "") + ":00").getTime());
					minyi.setCdate(timestamp);

					// 状态
					Elements statusElement = ol_x.select("ul>div");
					minyi.setStatus(statusElement.text());

					// 发布内容
					Elements conElement = ol_x.select("dl>p");
					str = conElement.text().replaceAll("查看全文", "")
							       .replaceAll("查看回复", "")
							       .replaceAll("\\[\\]", "");
					minyi.setContent(str);

					// 标题
					Elements titleElement = ol_x.select("dl>strong>a>h5");
					minyi.setTitle(titleElement.text());

					// link
					Elements aElement = ol_x.select("dl>strong>a");
					minyi.setHref(Constant.MINYIURL + aElement.attr("href"));

					if ("已回复".equals(statusElement.text())) {
						try {
							String replyContent = "";
							Document document2 = Jsoup.connect(minyi.getHref()).timeout(60 * 1000)
								.userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) "
									+ "AppleWebKit/537.36 (KHTML, like Gecko) "
									+ "Chrome/42.0.2311.152 Safari/537.36")
								.get();

							Elements olElement2 = document2.select("ol"); 
							Element reply = olElement2.get(1);
							
							// 回复部门
							minyi.setReplybumen(reply.select("ul>h5").text());

							// 回复日期
							timestamp = new Timestamp(dateFormat.parse(reply.select("ul>li").text().replaceAll("发表于", "") + ":00").getTime());
							minyi.setReplydate(timestamp);
							
							// 回复内容
							replyContent = reply.select("dl>dd").text().replace("<br />", Constant.LF);
							if (replyContent.length() > 1000) {
								minyi.setReplycontent(replyContent.substring(1, 1000));
							} else {
								minyi.setReplycontent(replyContent);
							}
						} catch (Exception e) {
							logger.error("回复内容获取异常 URL={}", minyi.getHref(), e);
						}
					} else {
						//初期化设定
						minyi.setReplybumen("");
						minyi.setReplydate(null);
						minyi.setReplycontent("");
					}
					
					//更新日期设定
					timestamp = new Timestamp(new java.util.Date().getTime());
					minyi.setMdate(timestamp);
					
					//DB更新
					if (minyiService.count(minyi.getMinyiid()) > 0) {
						if (minyiService.findOneMinyi(minyi.getMinyiid()).getStatus() == "未回复"
							&& minyi.getStatus() == "已回复") {
							minyiService.save(minyi);
							updateCnt++;
						}
					} else {
						minyiService.save(minyi);
						updateCnt++;
					}

				} catch (Exception e) {
					logger.error("投诉内容获取异常 ", e);
				}

			}

		} catch (SocketException x) {
			logger.info("网络访问异常");
			try {
				// 30秒停止
				Thread.sleep(1000 * 30);
			} catch (InterruptedException e) {
				logger.error("未知异常１", e);
			}
		} catch (Exception e) {
			logger.error("未知异常２", e);
		}
	}

}
