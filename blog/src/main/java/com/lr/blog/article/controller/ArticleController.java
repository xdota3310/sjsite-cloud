package com.lr.blog.article.controller;

import com.lr.blog.article.common.ArticleConstant;
import com.lr.blog.article.model.DO.ArticleDO;
import com.lr.blog.article.model.DTO.ArticleDetailDTO;
import com.lr.blog.article.model.VO.ArticleHomeVO;
import com.lr.blog.article.model.VO.NewArticleVO;
import com.lr.blog.article.model.VO.query.ArtiPageVo;
import com.lr.blog.article.service.ArticleService;
import com.lr.common.base.PageResVO;
import com.lr.common.base.ResultResponse;
import com.lr.common.constant.BaiDuTranslateConstant;
import com.lr.common.utils.ExceptionUtil;
import com.lr.common.utils.HttpUtil;
import com.lr.common.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
            pageResVO = articleService.get(pageVO);
        } catch (Exception e) {
            LOGGER.error("参数: " + pageVO.toString() + "\n" + "异常信息:" + ExceptionUtil.getErrorString(e));
            return ResultResponse.createByError(ResultResponse.EXCEPTIONFAIL, "服务器开小差了！！！");
        }
        return ResultResponse.createBySuccess(pageResVO);
    }

    @GetMapping(value = "/detail")
    @ResponseBody
    public ResultResponse<ArticleDetailDTO> getArticle(@RequestParam String path) {
        try {
            ArticleDetailDTO articleDetailDTO = articleService.getArticleByPath(path);
            return ResultResponse.createBySuccess(articleDetailDTO);
        } catch (Exception e) {
            LOGGER.error(ExceptionUtil.getErrorString(e));
            return ResultResponse.createByError(ResultResponse.BUISSNESSFAIL, "服务器开小差了！！！");
        }
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResultResponse createArticle(HttpServletRequest request, @RequestBody NewArticleVO newArticleVO) {
        LOGGER.info("新建文章：" + newArticleVO.toString());
        try {
            String q = newArticleVO.getTitle();
            String sign = MD5Util.md5(BaiDuTranslateConstant.APPID + q + BaiDuTranslateConstant.SALT + BaiDuTranslateConstant.KEY);
            String param = "q=" + q + "&from=zh&to=en&appid=" + BaiDuTranslateConstant.APPID + "&salt=" + BaiDuTranslateConstant.SALT + "&sign=" + sign;
            try {
                String httpMessage = HttpUtil.sendGet(BaiDuTranslateConstant.HTTP_URL, param);
                String res = BaiDuTranslateConstant.getRes(httpMessage);
                if(BaiDuTranslateConstant.ERROR_CODE.equals(res)) {
                    throw new RuntimeException("BaiDuTranslate: " + httpMessage);
                } else {
                    newArticleVO.setPath(res.replaceAll(" ", "-"));
                }
            } catch (Exception e) {
                LOGGER.error(ExceptionUtil.getErrorString(e));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = new Date();
                newArticleVO.setPath(ArticleConstant.TEMP_PREFIX + simpleDateFormat.format(date));
            }
            setExcerpt(newArticleVO);
            articleService.createArticle(newArticleVO);
            return ResultResponse.createBySuccess();
        } catch (Exception e) {
            LOGGER.error(ExceptionUtil.getErrorString(e));
            return ResultResponse.createByError(ResultResponse.EXCEPTIONFAIL, "creating article fails!!！");
        }
    }

    private void setExcerpt(NewArticleVO newArticleVO) {
        //节选文章前550个字符
        newArticleVO.setExcerpt((newArticleVO.getContent() == null ? "" : (newArticleVO.getContent().length() > 550 ? newArticleVO.getContent().substring(0, 550) : newArticleVO.getContent())) + "......");
    }
}
