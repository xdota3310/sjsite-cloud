package com.lr.blog.article.dao;

import com.lr.blog.article.model.DO.ArticleDO;
import com.lr.blog.article.model.VO.ArticleHomeVO;
import com.lr.common.base.PageVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(ArticleDO record);

    int insertSelective(ArticleDO record);

    /**
     * 根据ID查询
     * @param aid
     * @return
     */
    ArticleDO selectByPrimaryKey(Integer aid);

    /**
     * 根据路由路径查询
     * @param path
     * @return
     */
    ArticleDO selectByPath(String path);

    int updateByPrimaryKeySelective(ArticleDO record);

    int updateByPrimaryKeyWithBLOBs(ArticleDO record);

    int updateByPrimaryKey(ArticleDO record);


    /**
     * 首页文章初始化
     * @param pageVO
     * @return
     */
    List<ArticleHomeVO> getByPage(PageVO pageVO);


}