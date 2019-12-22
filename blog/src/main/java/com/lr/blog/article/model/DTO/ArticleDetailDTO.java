package com.lr.blog.article.model.DTO;

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
     * 内容缩略名
     */
    private String slug;
    /**
     * 内容文字
     */
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
