package com.lr.search;

import com.alibaba.fastjson.JSON;
import com.lr.search.model.Item;
import com.lr.search.service.HTMLService;
import net.minidev.json.JSONValue;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2020年06月26日
 */
@SpringBootTest
public class ElasticSearchTest {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    HTMLService htmlService;

    @Test
    public void connectionTest() throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.from(0);
        sourceBuilder.size(1);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(sourceBuilder);
//        searchRequest.
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }

    @Test
    public void createIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("xsj_test_index");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }

    @Test
    public void getIndex() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest("xsj_test_index");
        GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(getIndexResponse));
    }

    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("xsj_test_index");
        AcknowledgedResponse deleteIndexResponse = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
    }

    @Test
    public void docOperate() throws IOException {
        IndexRequest request = new IndexRequest("xsj_test_index");
        request.id("1");
        String jsonString = "{" +
        "\"user\":\"kimchy\"," +
        "\"postDate\":\"2013-01-30\"," +
        "\"message\":\"trying out Elasticsearch\"" +
        "}";
        request.source(jsonString, XContentType.JSON);

        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
    }

    @Test
    public void docGetOperate() throws IOException {
        GetRequest getRequest = new GetRequest(
        "xsj_test_index");

        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());

    }

    @Test
    public void addData(){
        IndexRequest request = new IndexRequest("xsj_test_index");
        List<Item> goodsFromJD = htmlService.getGoodsFromJD();
        goodsFromJD.forEach(item -> {
            request.source(JSON.toJSONString(item), XContentType.JSON);
            try {
                restHighLevelClient.index(request, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void searchJD() throws IOException {
        SearchRequest searchRequest = new SearchRequest("xsj_test_index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "码出高效")
        .fuzziness(Fuzziness.AUTO)
        .prefixLength(3)
        .maxExpansions(10);

        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);




        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(search);
    }






}
