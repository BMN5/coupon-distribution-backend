package com.example.coupon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReactForwardingController {
    @GetMapping("/{path:^(?!api|static|assets).*$}")
    public String forwardToReact() {
        return "forward:/index.html";
    }
}
