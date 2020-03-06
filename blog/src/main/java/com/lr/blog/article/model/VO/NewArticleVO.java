package com.lr.blog.article.model.VO;

/**
 * 文章新建的实体
 *
 * @author shijie.xu
 * @since 2019年01月29日
 */
public class NewArticleVO {
    private String title;
    private String content;
    private Integer authorId;
    private String type;
    private String status;
    /**
     * 文章路由
     */
    private String path;
    /**
     * 文章节选
     */
    private String excerpt;

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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    @Override
    public String toString() {
        return "NewArticleVO{" + "title='" + title + '\'' + ", content='" + content + '\'' + ", authorId=" + authorId + ", type='" + type + '\'' + ", status='" + status + '\'' + ", path='" + path + '\'' + ", excerpt='" + excerpt + '\'' + '}';
    }
}
