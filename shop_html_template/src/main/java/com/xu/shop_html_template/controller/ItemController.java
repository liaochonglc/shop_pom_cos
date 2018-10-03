package com.xu.shop_html_template.controller;

import com.xu.entity.Goods;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

@Controller
@RequestMapping("/item/")
public class ItemController {

    @Autowired
    private Configuration freemarkConfig;

    @RequestMapping("createhtml")
    public String createHtml(@RequestBody Goods goods, HttpServletRequest request) {
        Writer writer = null;
        try {
            Template template = freemarkConfig.getTemplate("item.ftl");
            HashMap<String, Object> map = new HashMap<>();
            String contextPath = request.getContextPath();
            map.put("goods", goods);
            map.put("context",contextPath);
            String path = this.getClass().getResource("/").getPath() + "static/page/" + goods.getId() + ".html";
            System.out.println("path:"+path);
             writer = new FileWriter(path);
            template.process(map, writer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

}
