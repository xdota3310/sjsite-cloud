package com.lr.blog.article.service;

import com.lr.blog.article.model.DO.ArticleDO;
import com.lr.blog.article.model.DTO.ArticleDetailDTO;
import com.lr.blog.article.model.VO.ArticleHomeVO;
import com.lr.blog.article.model.VO.NewArticleVO;
import com.lr.blog.article.model.VO.query.ArtiPageVo;
import com.lr.common.base.PageResVO;
import com.lr.common.base.PageVO;
import com.lr.common.base.ResultResponse;

/**
 * 文章主题相关服务
 *
 * @author shijie.xu
 * @since 2019年01月04日
 */

public interface ArticleService {

    /**
     * 首页初始化
     * @param pageVO
     * @return
     */
    PageResVO<ArticleHomeVO> getByPage(ArtiPageVo pageVO);
    /**
     * 首页初始化
     * @return
     */
    PageResVO<ArticleHomeVO> get(ArtiPageVo pageVO);

    /**
     * 获取文章详情
     * @param aid
     * @return
     */
    ArticleDetailDTO getArticleById(String aid);

    ArticleDetailDTO getArticleByPath(String path);

    /**
     * 新建文章
     * @param newArticleVO
     * @return
     */
    int createArticle(NewArticleVO newArticleVO);
}
