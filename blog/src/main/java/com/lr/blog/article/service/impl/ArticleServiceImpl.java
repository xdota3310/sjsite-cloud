package com.lr.blog.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lr.blog.article.common.ArticleConstant;
import com.lr.blog.article.common.exception.ArticleException;
import com.lr.blog.article.dao.ArticleMapper;
import com.lr.blog.article.model.DO.ArticleDO;
import com.lr.blog.article.model.DTO.ArticleDetailDTO;
import com.lr.blog.article.model.VO.ArticleHomeVO;
import com.lr.blog.article.model.VO.NewArticleVO;
import com.lr.blog.article.model.VO.query.ArtiPageVo;
import com.lr.blog.article.service.ArticleService;
import com.lr.common.base.PageResVO;
import com.lr.common.base.PageVO;
import com.lr.common.base.ResultResponse;
import com.lr.common.utils.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
    @SuppressWarnings({"rawtypes", "unchecked"})
    public PageResVO getByPage(ArtiPageVo pageVO) {
        int pageNum = pageVO.getPageNum() == null ? 1 : pageVO.getPageNum();
        int pageSize = pageVO.getPageSize() == null ? 10 : pageVO.getPageSize();
        pageVO.setQuery(pageVO.getQuery() == null ? "" : pageVO.getQuery());
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleHomeVO> articleDOList = articleMapper.getByPage(pageVO);
        dataMask(articleDOList);
        PageInfo<ArticleHomeVO> pageInfo = new PageInfo(articleDOList);
        return (PageResVO<ArticleHomeVO>) new PageResVO((int) pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public ArticleDetailDTO getArticle(String aid) {
        ArticleDO articleDO;
        ArticleDetailDTO articleDetailDTO = null;
        try {
            articleDO = articleMapper.selectByPrimaryKey(Integer.valueOf(aid));
            if(articleDO != null){
                articleDetailDTO  = new ArticleDetailDTO();
                BeanUtils.copyProperties(articleDO,articleDetailDTO);
            }
        } catch (Exception e) {
            LOGGER.error(ExceptionUtil.getErrorString(e));
        }
        return articleDetailDTO;
    }

    @Override
    public int createArticle(NewArticleVO newArticleVO) {
        ArticleDO articleDO = new ArticleDO();
        if(newArticleVO != null) {
            if(newArticleVO.getTitle() == null) {
                throw new ArticleException("title cant be null");
            }
            if(newArticleVO.getContent() == null) {
                throw new ArticleException("content cant be null");
            }
            articleDO.setTitle(newArticleVO.getTitle());
            articleDO.setContent(newArticleVO.getContent());
            articleDO.setAuthorId(0);
            articleDO.setStatus(ArticleConstant.PRECHECK);
            articleDO.setType(newArticleVO.getType());
            int res = articleMapper.insertSelective(articleDO);
            return res;
        } else {
            throw new ArticleException("createArticle failed");
        }
    }

    private void dataMask(List<ArticleHomeVO> list) {
        if(list == null || list.size() <= 0) {
            return;
        }
        for(ArticleHomeVO articleHomeVO : list) {
            //节选文章前550个字符
            articleHomeVO.setExcerpt((articleHomeVO.getExcerpt() == null ? "" : articleHomeVO.getExcerpt().substring(0, 550)) + "......");
        }
    }
}