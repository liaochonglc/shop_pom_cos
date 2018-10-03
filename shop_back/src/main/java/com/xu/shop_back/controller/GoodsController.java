package com.xu.shop_back.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.gson.Gson;
import com.xu.entity.Goods;
import com.xu.service.IGoodsService;
import com.xu.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/goods/")
public class GoodsController {

    @Reference
    private IGoodsService goodsService;

    @Autowired
    private FastFileStorageClient fileClient;

    @Value("${image.path}")
    private String imgPath;

    @RequestMapping("goodslist")
    public String goodsList(Model model) {
        System.out.println("GoodsController的queryall执行了...");
        List<Goods> goods = goodsService.queryAll();
        model.addAttribute("goods", goods);
        model.addAttribute("path", imgPath);
        return "goodslist";
    }

    /**
     * 商品添加
     *
     * @return
     */
    @RequestMapping("goodsadd")
    public String goodsAdd(@RequestParam("file") MultipartFile file, Goods goods) throws IOException {
        System.out.println("originalName:" + file.getOriginalFilename());
        System.out.println("name:" + file.getName());
        System.out.println("contentType:" + file.getContentType());

        //将文件上传到fastDFS上的文件储存系统,并且返回在storege上的路径
        StorePath storePath = fileClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), "JPG", null);
        String fullPath = storePath.getFullPath();
        System.out.println("fullPath:" + fullPath);

        goods.setGimage(imgPath + fullPath);
        //通过主键回填拿到存进数据库的商品对象
        goods = goodsService.addGoods(goods);

        //将商品信息存入到solr的索引库中
        String status = HttpClientUtil.sentJsonPost("http://localhost:8082/solr/add", new Gson().toJson(goods));
        HttpClientUtil.sentJsonPost("http://localhost:8083/item/createhtml",new Gson().toJson(goods));
        System.out.println("status:" + status);


        return "redirect:/goods/goodslist";

    }


}
