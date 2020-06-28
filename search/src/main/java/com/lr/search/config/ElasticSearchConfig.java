package com.lr.search.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2020年06月26日
 */
@Slf4j
@Configuration
public class ElasticSearchConfig {

    @Value("${elasticsearch.server.ip}")
    private String ip;
    @Value("${elasticsearch.server.port}")
    private String port;
    @Value("${elasticsearch.server.scheme}")
    private String scheme;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        log.info("ip:{}, port:{}", ip, port);
        return new RestHighLevelClient(RestClient.builder(new HttpHost(ip, Integer.parseInt(port), scheme)));
    }
}
