package com.xu.service;

import com.xu.entity.Goods;

import java.util.List;

public interface IGoodsService {

    List<Goods> queryAll();

    Goods addGoods(Goods goods);

    List<Goods> queryNew();
}
