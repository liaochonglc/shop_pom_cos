package com.xu.shop_solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopSolrApplicationTests {

    @Autowired
    private SolrClient solrClient;

    @Test
    public void contextLoads() {


    }

    @Test
    public void delete() throws IOException, SolrServerException {
        //根据id删除索引库
//        solrClient.deleteById("1");

        //根据查询结果删除索引库
        solrClient.deleteByQuery("*:*");

        solrClient.commit();
    }
}
