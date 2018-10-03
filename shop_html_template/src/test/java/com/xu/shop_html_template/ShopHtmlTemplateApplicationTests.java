package com.xu.shop_html_template;

import freemarker.template.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopHtmlTemplateApplicationTests {

	@Autowired
	private Configuration configuration;

	@Test
	public void contextLoads() throws Exception {
//		Template template = configuration.getTemplate("test.ftl");
//		Map<String, Object> map = new HashMap();
//		map.put("key","World");
//		Writer writer = new FileWriter("F:\\aa\\test.html");
//		template.process(map,writer);
		String path = this.getClass().getResource("/").getPath();
		System.out.println(path);

	}

}
