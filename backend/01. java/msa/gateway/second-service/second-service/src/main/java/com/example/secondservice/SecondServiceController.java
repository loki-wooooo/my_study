package com.example.secondservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class SecondServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcom to the Second service.";
    }

    @GetMapping("/message")
    public String message(
            @RequestHeader("second-request") final String header
    ) throws Exception {
        log.info(header);
        return "Hello World In Second Service.";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, there. This is a message from Second Service.";
    }

}
