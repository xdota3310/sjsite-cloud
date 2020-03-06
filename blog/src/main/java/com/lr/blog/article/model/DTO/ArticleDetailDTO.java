package com.lr.blog.article.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 文章详情
 *
 * @author shijie.xu
 * @since 2019年12月22日
 */
public class ArticleDetailDTO {
    /**
     * 内容标题
     */
    private String title;
    /**
     * 内容文字
     */
    private String content;
    /**
     * 内容更改时的GMT unix时间戳
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:MM",timezone = "GMT+8")
    private Date modifyTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
