package com.lr.blog.article.model.VO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 首页文章实体
 *
 * @author shijie.xu
 * @since 2019年12月11日
 */
public class ArticleHomeVO {
    /**
     * 文章Id
     */
    private Integer articleId;
    /**
     * 文章id对称加密
     */
    private transient String articleCode;
    @JsonFormat(pattern="yyyy-MM-dd HH:MM",timezone = "GMT+8")
    /**
     *创建时间
     */
    private Date modifyTime;
    /**
     * 文章节选
     */
    private String excerpt;
    /**
     * 内容标题
     */
    private String title;
}
