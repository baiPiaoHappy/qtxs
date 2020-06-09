package com.base.utils;

import com.alibaba.fastjson.JSON;

import com.base.bean.QueryObjectElastic;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @description:
 * @author: 小猴子
 * @date: 2020-01-07 13:19
 */
public class ElasticSerachUtil {

    /**
     *  查询
     * @param queryObjectElastic
     * @param client
     * @return
     */
    public static Object querySearch(QueryObjectElastic queryObjectElastic, RestHighLevelClient client) {
        try {
            GetRequest getRequest = new GetRequest(queryObjectElastic.getIndex(),queryObjectElastic.getType(),queryObjectElastic.getId());
            GetResponse getResponse = client.get(getRequest,RequestOptions.DEFAULT);
            return getResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; //JSONObject.toJSON(); //searchRequestBuilder.setQuery(queryBuilder);
    }


    /**
     *  条件查询
     * @param queryObjectElastic
     * @param client
     * @return
     */
    public static Object fuzzySearchByCondition(QueryObjectElastic queryObjectElastic,RestHighLevelClient client) {

        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name",queryObjectElastic.getKey())
                                                                                        .fuzziness(Fuzziness.AUTO)
                                                                                        .prefixLength(3)
                                                                                        .maxExpansions(10);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.fuzzyQuery("name",queryObjectElastic.getKey()));
        builder.from(queryObjectElastic.getFrom());
        builder.size(queryObjectElastic.getSize());
        builder.timeout(queryObjectElastic.getTimeOut());
        builder.highlighter();
        builder.query(matchQueryBuilder);

        SearchRequest request = new SearchRequest(queryObjectElastic.getIndex());
        request.types(queryObjectElastic.getType());
        request.source(builder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHits hits = searchResponse.getHits();
        return hits;

    }

    /**
     * 模糊查询
     * @param queryObjectElastic
     * @param client
     * @return
     */
    public static  Object fuzzySearch(QueryObjectElastic queryObjectElastic,RestHighLevelClient client){
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("tw_content",queryObjectElastic.getKey()));
        builder.from(queryObjectElastic.getFrom());
        builder.size(queryObjectElastic.getSize());
        builder.timeout(queryObjectElastic.getTimeOut());
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        //highlightBuilder.field("tw_content");
        highlightBuilder.fields().add(new HighlightBuilder.Field("tw_content"));
        builder.highlighter(highlightBuilder);

        SearchRequest request = new SearchRequest();
        request.source(builder);
        List<Map<String, String>> result =new ArrayList<Map<String, String>>();
       // Map<String,String> map = new HashMap<String, String>();

        try {
            SearchResponse response = client.search(request,RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            queryObjectElastic.setCount(hits.getTotalHits().value);
            for (SearchHit hit : hits) {
                Map<String,String> map = new HashMap<String, String>();
                map.put("id",hit.getId());
                map.put("question",hit.getSourceAsMap().get("question").toString());
                map.put("index",hit.getIndex());
                map.put("type",hit.getType());

                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                String name = (String) sourceAsMap.get("tw_content");
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                if( highlightFields != null ){
                    HighlightField nameField = highlightFields.get("tw_content");

                    if(nameField!=null){
                        Text[] fragments = nameField.getFragments();
                        StringBuffer stringBuffer = new StringBuffer();
                        for (Text str : fragments) {
                            stringBuffer.append(str.string());
                        }
                        name = stringBuffer.toString();
                        map.put("tw_content",name);
                    }
                }
                result.add(map);
            }
            Map<String,Object> resultMap = new HashMap<String, Object>();
            resultMap.put("list",result);
            resultMap.put("page",queryObjectElastic);
            return resultMap;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


    /**
     * 新增 插入
     * @param queryObjectElastic
     * @param client
     * @return
     */
    public static Object add(QueryObjectElastic queryObjectElastic,RestHighLevelClient client){
        IndexRequest request = new IndexRequest(queryObjectElastic.getIndex(),queryObjectElastic.getType(),queryObjectElastic.getId());
        if(queryObjectElastic.getJsonKeys() != null){
            request.source(queryObjectElastic.getJsonKeys(), XContentType.JSON);
        }else if(queryObjectElastic.getMapKeys() != null){
            request.source(queryObjectElastic.getMapKeys());
        }else{
            return null;
        }
        IndexResponse response = null;
        try {
            response = client.index(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 删除
     * @param index
     * @param type
     * @param id
     * @return
     */
    public static JSON delete(String index,String type,String id){

        return null;
    }

}
