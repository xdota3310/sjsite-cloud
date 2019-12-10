package com.lr.blog.article.config;

import com.lr.blog.article.interceptor.WebInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2019年12月10日
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    WebInterceptor webInterceptor() {
        return new WebInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration addInterceptor = registry.addInterceptor(webInterceptor());
//        addInterceptor.addPathPatterns("/**");
//
//        addInterceptor.excludePathPatterns("/article/index/**");
    }
}
