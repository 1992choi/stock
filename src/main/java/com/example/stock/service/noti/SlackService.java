package com.example.stock.service.noti;

import com.slack.api.Slack;
import com.slack.api.model.Attachment;
import com.slack.api.model.Field;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.slack.api.webhook.WebhookPayloads.payload;

@Slf4j
@Service
public class SlackService {

    @Value("${slack.url}")
    private String slackUrl;
    private final Slack slackClient = Slack.getInstance();

    /**
     * 슬랙 메시지 전송
     */
    public void sendMessage(String title, List<String> contents) {
        try {
            slackClient.send(slackUrl, payload(p -> p
                            .text(title) // 메시지 제목
                            .attachments(
                                    List.of(
                                            Attachment.builder().color("#36a64f") // 메시지 색상
                                                    .fields( // 메시지 본문 내용
                                                            contents.stream()
                                                                    .map(this::generateSlackField)
                                                                    .collect(Collectors.toList())
                                                    ).build())
                            )
                    )
            );
        } catch (IOException e) {
            log.error("Slack send Err", e);
        }
    }

    /**
     * Slack Field 생성
     */
    private Field generateSlackField(String value) {
        return Field.builder()
                .value(value)
                .valueShortEnough(false)
                .build();
    }
}
