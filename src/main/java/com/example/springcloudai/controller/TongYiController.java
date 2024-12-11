package com.example.springcloudai.controller;

import com.example.springcloudai.service.TongYiSimpleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

/**
 * @author: zlt
 * @date: 2024/7/28
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@RestController
@RequestMapping("/ai")
@CrossOrigin
@Slf4j
public class TongYiController {
    @Resource
    private TongYiSimpleService tongYiSimpleService;

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "message") String message) {
        return tongYiSimpleService.chat(message);
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> image(@RequestParam(value = "message") String message) {
        String b64Str = tongYiSimpleService.image(message);
        byte[] imageBytes = Base64.getDecoder().decode(b64Str);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // 或者 MediaType.IMAGE_PNG 等，取决于图片格式

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
