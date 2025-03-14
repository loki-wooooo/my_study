package com.example.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class FirstServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcom to the First service.";
    }

    @GetMapping("/message")
    public String message(
            @RequestHeader("first-request") final String header
    ) throws Exception {
        log.info(header);
        return "Hello World In First Service.";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, there. This is a message from First Service.";
    }
}
