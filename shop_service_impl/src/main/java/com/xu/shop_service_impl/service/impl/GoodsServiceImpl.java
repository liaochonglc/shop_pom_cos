package com.xu.shop_service_impl.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xu.dao.IGoodsDao;
import com.xu.entity.Goods;
import com.xu.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private IGoodsDao goodsDao;

    @Override
    public List<Goods> queryAll() {
        System.out.println("GoodsServiceImpl的queryAll执行了...");
        return goodsDao.queryAll();
    }

    @Override
    public Goods addGoods(Goods goods) {
         goodsDao.addGoods(goods);
         return goods;
    }

    @Override
    public List<Goods> queryNew() {
        return goodsDao.queryNew();
    }
}
