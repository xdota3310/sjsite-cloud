package com.lr.blog.article.controller;

import com.lr.blog.article.model.DO.ArticleDO;
import com.lr.blog.article.model.DTO.ArticleDetailDTO;
import com.lr.blog.article.model.VO.NewArticleVO;
import com.lr.blog.article.model.VO.query.ArtiPageVo;
import com.lr.blog.article.service.ArticleService;
import com.lr.common.base.PageResVO;
import com.lr.common.base.ResultResponse;
import com.lr.common.utils.ExceptionUtil;
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
    public ResultResponse<PageResVO> index(@RequestBody ArtiPageVo pageVO) {
        PageResVO pageResVO = null;
        try {
            pageResVO = articleService.getByPage(pageVO);
        } catch (Exception e) {
            LOGGER.error(pageVO.toString() + "\n" + ExceptionUtil.getErrorString(e));
            return ResultResponse.createByError(ResultResponse.EXCEPTIONFAIL, "服务器开小差了！！！");
        }
        return ResultResponse.createBySuccess(pageResVO);
    }

    @GetMapping(value = "/detail")
    @ResponseBody
    public ResultResponse<ArticleDetailDTO> getArticle(@RequestParam String aid) {
        ArticleDetailDTO articleDetailDTO = articleService.getArticle(aid);
        if(articleDetailDTO != null) {
            return ResultResponse.createBySuccess(articleDetailDTO);
        } else {
            return ResultResponse.createByError(ResultResponse.BUISSNESSFAIL, "no matches article!!!");
        }
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResultResponse createArticle(HttpServletRequest request, @RequestBody NewArticleVO newArticleVO) {
        LOGGER.info("新建文章：" + newArticleVO.toString());
        try {
            articleService.createArticle(newArticleVO);
            return ResultResponse.createBySuccess();
        }catch (Exception e){
            LOGGER.error(ExceptionUtil.getErrorString(e));
            return ResultResponse.createByError(ResultResponse.EXCEPTIONFAIL,"creating article fails!!！");
        }


    }

}
