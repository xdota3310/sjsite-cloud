package com.lr.blog.article.interceptor;

import com.lr.blog.article.config.InterceptorConfig;
import com.lr.common.constant.JwtConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年12月10日
 */
public class WebInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(InterceptorConfig.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String []  allowDomain= {"http://www.sjsite.com:81","http://www.sjsite.com"};
        Set<String> allowedOrigins= new HashSet<String>(Arrays.asList(allowDomain));
        String originHeader=request.getHeader("Origin");
        if (allowedOrigins.contains(originHeader)) {
            response.setHeader("Access-Control-Allow-Origin", originHeader);
        }

        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Control-Allow-Origin,request-ajax,Access-Control-Allow-Credentials");

        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");

        response.setHeader("X-Powered-By", "Jetty");

        String method = request.getMethod();

        Cookie[] cookies = request.getCookies();

        String token = "";

        LOGGER.warn(method);
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(JwtConstant.TOKEN.equals(cookie.getName()) && !"/article/index/init".equals(request.getServletPath())) {

                }
            }
        }

//        /article/index/init

        LOGGER.info("用户token：" + token);
//        if(token != null) {
//            String reqUrl = "http://www.sjsso.com:8082/sso/token/tokenCheck";
//            String content = "token=" + token;
//            String result = HttpUtil.sendGet(reqUrl, content);
//            if("true".equals(result)) {
//                return true;
//            }
//        }

//        response.sendRedirect("http://www.sjsso.com:8082/sso/Login/doLogin?" +
//        "url=http://www.sjblog.com:8081/sjblog/article/index/init");
        return true;
    }
}

