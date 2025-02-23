package com.example.stock.service.noti;

import com.example.stock.domain.market.MarketPrice;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TelegramService {

    private final String TELEGRAM_TOKEN = "-";
    private final String TELEGRAM_CHAT_ID = "-";

    /**
     * 텔레그램 메시지 전송
     */
    public void sendMessage(String type) throws IOException {
        LocalDate today = LocalDate.now();
        if (today.getDayOfWeek() != DayOfWeek.SATURDAY && today.getDayOfWeek() != DayOfWeek.SUNDAY) { // 토,일 제외
            if ("IPO".equals(type)) {
                sendIpoUsingTelegram(today);
            } else if ("Subscription".equals(type)) {
                sendSubscriptionUsingTelegram(today);
            } else if ("Closing".equals(type)) {
                sendSubscriptionClosingUsingTelegram(today);
            }
        }
    }

    /**
     * 상장 알리미
     */
    private void sendIpoUsingTelegram(LocalDate today) throws IOException {
        Document document = Jsoup.connect("https://www.38.co.kr/html/fund/index.htm?o=nw").get();
        Elements elements = document.select("table[summary='신규상장종목'] tr");

        boolean isEmpty = true;

        StringBuffer sb = new StringBuffer();
        sb.append("[상장 알림]").append("\n\n");

        for (Element trElement : elements) {
            Elements tdElements = trElement.select("td");
            // 헤더 제외
            if (tdElements.size() != 10) {
                continue;
            }

            if (today.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).equals(tdElements.get(1).text())) {
                sb.append("[기업명] ").append(tdElements.get(0).text()).append("\n");
                sb.append("[상장일] ").append(tdElements.get(1).text()).append("\n");
                sb.append("\n\n\n");
                isEmpty = false;
            }
        }

        if (isEmpty) {
            sb.append("상장정보가 없습니다.");
        }

        BufferedReader in = null;
        try {
            URL obj = new URL("https://api.telegram.org/bot" + TELEGRAM_TOKEN + "/sendmessage?chat_id=" + TELEGRAM_CHAT_ID + "&text=" + URLEncoder.encode(sb.toString(), "UTF-8"));
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                log.info("line={}", line);
            }
        } catch (Exception e) {
            log.error("sendIpoUsingTelegram Err", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }

    /**
     * 청약 알리미
     */
    private void sendSubscriptionUsingTelegram(LocalDate today) throws IOException {
        Document document = Jsoup.connect("https://www.38.co.kr/html/fund/index.htm?o=k").get();
        Elements elements = document.select("table[summary='공모주 청약일정'] tr");

        boolean isEmpty = true;

        StringBuffer sb = new StringBuffer();
        sb.append("[청약 알림]").append("\n\n");

        for (Element trElement : elements) {
            Elements tdElements = trElement.select("td");
            // 헤더 제외
            if (tdElements.size() != 7) {
                continue;
            }

            String[] period = tdElements.get(1).text().split("~");

            if (today.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")).equals(period[0]) ||
                    today.format(DateTimeFormatter.ofPattern("MM.dd")).equals(period[1])) {
                sb.append("[공모주] ").append(tdElements.get(0).text()).append("\n");
                sb.append("[일정] ").append(tdElements.get(1).text()).append("\n");
                sb.append("[희망공모가] ").append(tdElements.get(3).text()).append("\n");
                sb.append("[주간사] ").append(tdElements.get(5).text()).append("\n");
                sb.append("\n\n\n");
                isEmpty = false;
            }
        }

        if (isEmpty) {
            sb.append("청약정보가 없습니다.");
        }

        BufferedReader in = null;
        try {
            URL obj = new URL("https://api.telegram.org/bot" + TELEGRAM_TOKEN + "/sendmessage?chat_id=" + TELEGRAM_CHAT_ID + "&text=" + URLEncoder.encode(sb.toString(), "UTF-8"));
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                log.info("line={}", line);
            }
        } catch (Exception e) {
            log.error("sendSubscriptionUsingTelegram Err", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }

    /**
     * 청약 마감 알리미
     */
    private void sendSubscriptionClosingUsingTelegram(LocalDate today) throws IOException {
        Document document = Jsoup.connect("https://www.38.co.kr/html/fund/index.htm?o=k").get();
        Elements elements = document.select("table[summary='공모주 청약일정'] tr");

        boolean isEmpty = true;

        StringBuffer sb = new StringBuffer();
        sb.append("[청약 마감 알림]").append("\n\n");

        for (Element trElement : elements) {
            Elements tdElements = trElement.select("td");
            // 헤더 제외
            if (tdElements.size() != 7) {
                continue;
            }

            String[] period = tdElements.get(1).text().split("~");

            if (today.format(DateTimeFormatter.ofPattern("MM.dd")).equals(period[1])) {
                sb.append("[공모주] ").append(tdElements.get(0).text()).append("\n");
                sb.append("[일정] ").append(tdElements.get(1).text()).append("\n");
                sb.append("[희망공모가] ").append(tdElements.get(3).text()).append("\n");
                sb.append("[주간사] ").append(tdElements.get(5).text()).append("\n");
                sb.append("\n\n\n");
                isEmpty = false;
            }
        }

        if (!isEmpty) {
            BufferedReader in = null;
            try {
                URL obj = new URL("https://api.telegram.org/bot" + TELEGRAM_TOKEN + "/sendmessage?chat_id=" + TELEGRAM_CHAT_ID + "&text=" + URLEncoder.encode(sb.toString(), "UTF-8"));
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String line;
                while ((line = in.readLine()) != null) {
                    log.info("line={}", line);
                }
            } catch (Exception e) {
                log.error("sendSubscriptionClosingUsingTelegram Err", e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Exception e) {
                        // ignore
                    }
                }
            }
        }
    }

    /**
     * 체결 알리미
     */
    public void sendExecutionCompleted(List<MarketPrice> recentPrice) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        recentPrice.sort(Comparator.comparing(MarketPrice::getMarketPrice));

        // Set price info.
        StringBuffer sb = new StringBuffer();
        sb.append("[체결 알림]").append("\n\n");
        sb.append("금액 변동: ").append(recentPrice.stream().map(marketPrice -> decimalFormat.format(marketPrice.getMarketPrice())).collect(Collectors.joining("  >  ")));

        // Set price change percentage.
        BigDecimal lastPrice = recentPrice.get(recentPrice.size() - 1).getMarketPrice();
        BigDecimal prevLastPrice = recentPrice.get(recentPrice.size() - 2).getMarketPrice();

        BigDecimal percentChange = lastPrice.subtract(prevLastPrice)
                .divide(prevLastPrice, 10, RoundingMode.HALF_EVEN) // 10자리까지 유지
                .multiply(BigDecimal.valueOf(100))
                .setScale(4, RoundingMode.HALF_UP);

        sb.append("\n등락율: ").append(percentChange).append("%");

        BufferedReader in = null;
        try {
            URL obj = new URL("https://api.telegram.org/bot" + TELEGRAM_TOKEN + "/sendmessage?chat_id=" + TELEGRAM_CHAT_ID + "&text=" + URLEncoder.encode(sb.toString(), "UTF-8"));
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                log.info("line={}", line);
            }
        } catch (Exception e) {
            log.error("sendExecutionCompleted Err", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }

}
