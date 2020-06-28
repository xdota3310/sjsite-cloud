package com.lr.search.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品
 *
 * @author shijie.xu
 * @since 2020年06月27日
 */
@Data
@Accessors(chain = true)
public class Item {
    String title;
    String price;
    String img;
}
