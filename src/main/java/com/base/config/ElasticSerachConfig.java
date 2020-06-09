package com.base.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @description:
 * @author: 小猴子
 * @date: 2020-01-07 10:19
 */

/*@Configuration
@ComponentScan("com.base.config")*/
public class ElasticSerachConfig  {

   @Bean
   public RestHighLevelClient client(){
       RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("127.0.0.1",9200,"http"));
       RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
       return client;
   }

}
