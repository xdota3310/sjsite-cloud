package com.lr.search.service;

import com.lr.search.model.Item;

import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2020年06月27日
 */
public interface HTMLService {
    List<Item> getGoodsFromJD();
    void getGoodsFromTB();
}
