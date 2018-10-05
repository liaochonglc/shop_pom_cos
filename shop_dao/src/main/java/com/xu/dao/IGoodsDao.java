package com.xu.dao;

import com.xu.entity.Goods;

import java.util.List;

public interface IGoodsDao {

    List<Goods> queryAll();

    int addGoods(Goods goods);

    List<Goods> queryNew();

    int delGoods(Integer id);
}
