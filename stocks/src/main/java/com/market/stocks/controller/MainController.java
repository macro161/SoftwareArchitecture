package com.market.stocks.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Component
public class MainController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
