package com.lr.blog.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lr.blog.article.common.ArticleConstant;
import com.lr.blog.article.dao.ArticleMapper;
import com.lr.blog.article.model.DO.ArticleDO;
import com.lr.blog.article.model.VO.NewArticleVO;
import com.lr.blog.article.model.VO.query.ArtiPageVo;
import com.lr.blog.article.service.ArticleService;
import com.lr.common.base.PageResVO;
import com.lr.common.base.PageVO;
import com.lr.common.base.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 实现类
 *
 * @author shijie.xu
 * @since 2019年01月04日
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public ResultResponse<PageResVO> getByPage(ArtiPageVo pageVO) {
        int pageNum = pageVO.getPageNum() == null ? 1 : pageVO.getPageNum();
        int pageSize = pageVO.getPageSize() == null ? 10 : pageVO.getPageSize();
        pageVO.setQuery(pageVO.getQuery() == null ? "" : pageVO.getQuery());
        LOGGER.info("pageNum:" + Integer.toString(pageNum) + " pageSize:" + Integer.toString(pageSize));
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDO> articleDOList = articleMapper.getByPage(pageVO);
        PageInfo pageInfo = new PageInfo(articleDOList);
        PageResVO pageResVO = new PageResVO();
        pageResVO.setSum((int) pageInfo.getTotal());
        pageResVO.setResList(pageInfo.getList());
        return ResultResponse.createBySuccess(pageResVO);
    }

    @Override
    public ResultResponse<ArticleDO> getArticle(String aid) {
        LOGGER.info("article id: " + aid);
        ArticleDO articleDO = new ArticleDO();
        if(!StringUtils.isEmpty(aid)) {
            try {
                articleDO = articleMapper.selectByPrimaryKey(Integer.valueOf(aid));
            } catch (Exception e) {
                LOGGER.error(e.toString());
                return ResultResponse.createByError("-2", "invalid article id!!!");
            }
            if(articleDO != null) {
                return ResultResponse.createBySuccess(articleDO);
            } else {
                return ResultResponse.createByError("-1", "no match article!!!");
            }
        } else {
            return ResultResponse.createByError("-2", "invalid article id!!!");
        }
    }

    @Override
    public ResultResponse createArticle(NewArticleVO newArticleVO) {
        ArticleDO articleDO = new ArticleDO();
        if(newArticleVO != null) {
            if(newArticleVO.getTitle()==null){
                return ResultResponse.createByError("-3", "title cant be null");
            }
            if(newArticleVO.getContent()==null){
                return ResultResponse.createByError("-3", "content cant be null");
            }
            if(newArticleVO.getType()==null){
                return ResultResponse.createByError("-3", "title cant be null");
            }
            articleDO.setTitle(newArticleVO.getTitle());
            articleDO.setContent(newArticleVO.getContent());
            articleDO.setAuthorId(0);
            articleDO.setStatus(ArticleConstant.PRECHECK);
            articleDO.setType(newArticleVO.getType());
            int res = articleMapper.insertSelective(articleDO);
            LOGGER.info("新建文章："+ articleDO.getAid());
            return ResultResponse.createBySuccess(res);
        }else {
            return ResultResponse.createByError("-3", "invalid article");
        }


    }
}
