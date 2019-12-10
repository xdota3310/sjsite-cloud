package com.lr.blog.article.controller;

import com.lr.blog.article.model.DO.ArticleDO;
import com.lr.blog.article.model.VO.NewArticleVO;
import com.lr.blog.article.model.VO.query.ArtiPageVo;
import com.lr.blog.article.service.ArticleService;
import com.lr.common.base.PageResVO;
import com.lr.common.base.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页
 * 游客访问接口
 *
 * @author shijie.xu
 * @since 2019年01月03日
 */
@RestController
@RequestMapping(value = "/article")
public class ArticleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    ArticleService articleService;

//    https://www.kancloud.cn/yunye/axios/234845

    @RequestMapping(value = "/test")
    @ResponseBody
    public ResultResponse test() {
        return ResultResponse.createBySuccess("123131321321");
    }

    @PostMapping(value = "/index/init")
    @ResponseBody
    public ResultResponse<PageResVO> index(HttpServletRequest request, @RequestBody ArtiPageVo pageVO) {
//        PageResVO pageResVO = articleService.getByPage(pageVO).getData();
        return articleService.getByPage(pageVO);
    }

    @GetMapping(value = "/detail")
    @ResponseBody
    public ResultResponse<ArticleDO> getArticle(HttpServletRequest request, @RequestParam String aid) {
        return articleService.getArticle(aid);
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResultResponse createArticle(HttpServletRequest request, @RequestBody NewArticleVO newArticleVO) {
        LOGGER.info("新建文章：" + newArticleVO.toString());
        return articleService.createArticle(newArticleVO);
    }

}
