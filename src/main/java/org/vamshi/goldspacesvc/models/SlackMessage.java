package org.vamshi.goldspacesvc.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.BeanUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


@Data
@NoArgsConstructor
@CommonsLog
public class SlackMessage {
    private String text;
    private final String slackWebhook = "https://hooks.slack.com/services/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";

    public SlackMessage(String message) {
        this.text = message;
    }

    public HttpStatus sendMessage() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        SlackMessage slackMessage = new SlackMessage(text);
        HttpEntity<SlackMessage> httpEntity = new HttpEntity<>(slackMessage, headers);
        RestTemplate restTemplate = BeanUtils.instantiateClass(RestTemplate.class);
        log.info("SEDING SLACK MESSAGE WITH TEXT:" + text);
        return restTemplate.exchange(slackWebhook, HttpMethod.POST, httpEntity, String.class).getStatusCode();
    }


}
