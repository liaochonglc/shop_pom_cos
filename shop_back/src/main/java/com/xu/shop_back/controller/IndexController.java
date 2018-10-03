package com.xu.shop_back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/topage/{pagename}")
    public String toHtmlPage(@PathVariable("pagename") String pagename){
         return pagename;
    }
}
