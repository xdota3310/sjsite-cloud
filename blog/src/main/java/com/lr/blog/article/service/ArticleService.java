package com.lr.blog.article.service;

import com.lr.blog.article.model.DO.ArticleDO;
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
    ResultResponse<PageResVO> getByPage(ArtiPageVo pageVO);

    /**
     * 获取文章详情
     * @param aid
     * @return
     */
    ResultResponse<ArticleDO> getArticle(String aid);

    /**
     * 新建文章
     * @param newArticleVO
     * @return
     */
    ResultResponse createArticle(NewArticleVO newArticleVO);
}
