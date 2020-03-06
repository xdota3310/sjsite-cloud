package com.lr.blog.article.model.VO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 首页文章实体
 *
 * @author shijie.xu
 * @since 2019年12月11日
 */
public class ArticleHomeVO implements Serializable {
    /**
     * 文章Id
     */
    private Integer articleId;
    /**
     * 文章路由
     */
    private String path;

    /**
     *创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:MM",timezone = "GMT+8")
    private Date createTime;
    /**
     * 文章节选
     */
    private String excerpt;
    /**
     * 内容标题
     */
    private String title;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ArticleHomeVO{" + "articleId=" + articleId + ", path='" + path + '\'' + ", createTime=" + createTime + ", excerpt='" + excerpt + '\'' + ", title='" + title + '\'' + '}';
    }
}
