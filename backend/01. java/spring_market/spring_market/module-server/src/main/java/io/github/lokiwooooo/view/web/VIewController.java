package io.github.lokiwooooo.view.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VIewController {
    /**
     * spa router 이용한 경로를 위해 필요함.
     */
    @GetMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/index.html";
    }
}