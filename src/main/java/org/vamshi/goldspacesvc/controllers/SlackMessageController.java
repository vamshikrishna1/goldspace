package org.vamshi.goldspacesvc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vamshi.goldspacesvc.models.SlackMessage;

@RestController
@RequestMapping("/api/slack-message")
public class SlackMessageController {

    @PostMapping
    public HttpStatus sendSlackMessage(@RequestBody SlackMessage slackMessage) {
        return slackMessage.sendMessage();
    }


}
