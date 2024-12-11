package com.example.springcloudai.service;

import com.alibaba.cloud.ai.tongyi.chat.TongYiChatModel;
import com.alibaba.cloud.ai.tongyi.image.TongYiImagesModel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.stereotype.Service;

/**
 * @author: zlt
 * @date: 2024/7/28
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Service
@Slf4j
public class TongYiSimpleService {
    @Resource
    private TongYiChatModel chatClient;
    @Resource
    private TongYiImagesModel imageClient;

    public String chat(String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return chatClient.call(prompt).getResult().getOutput().getContent();
    }

    public String image(String message) {
        ImagePrompt prompt = new ImagePrompt(message);
        Image image = imageClient.call(prompt).getResult().getOutput();
        System.out.println("====url: " + image.getUrl());
        System.out.println("====b64Json: " + image.getB64Json());
        return image.getB64Json();
    }
}
