package com.xu.shop_solr.controller;

import com.xu.entity.Goods;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/solr/")
public class SolrController {

    @Autowired
    private SolrClient solrClient;

    @RequestMapping("query")
    public String query(String keyword, Model model) {
        SolrQuery solrQuery = new SolrQuery();
        //判断关键字是否为空
        if (keyword == null || keyword.trim().equals("")) {
            solrQuery.setQuery("*:*");
        } else {
            solrQuery.set("goods_info:" + keyword);
        }

        ArrayList<Goods> list = new ArrayList<>();
        try {
            QueryResponse query = solrClient.query(solrQuery);
            SolrDocumentList results = query.getResults();
            for (SolrDocument document : results) {
                Goods goods = new Goods();
                goods.setId(Integer.parseInt(document.getFieldValue("id") + ""));
                goods.setTitle(document.getFieldValue("gtitle") + "");
                goods.setGimage(document.getFieldValue("gimage") + "");
                goods.setPrice(Double.parseDouble(document.getFieldValue("gprice") + ""));
                list.add(goods);
            }


        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("list",list);
        model.addAttribute("keyword",keyword);
        return "searchlist";
    }


    @RequestMapping("add")
    @ResponseBody
    public boolean solrAdd(@RequestBody Goods goods) {
        //创建一个用于储存于solr库中的对象
        SolrInputDocument solrInputFields = new SolrInputDocument();
        solrInputFields.addField("id", goods.getId());
        solrInputFields.addField("gtitle", goods.getTitle());
        solrInputFields.addField("gimage", goods.getGimage());
        solrInputFields.addField("ginfo", goods.getGinfo());
        solrInputFields.addField("gprice", goods.getPrice());

        //将solrInputFileds对象持久化到solr数据库
        try {
            solrClient.add(solrInputFields);
            solrClient.commit();
            return true;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
