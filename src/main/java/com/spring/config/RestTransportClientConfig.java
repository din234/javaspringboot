package com.spring.config;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClientBuilder;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;


/**
 * Tạo Elasticsearch Clients sử dụng Java Low Level REST Client và High level REST Client
 * Link tham khảo:
 * https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/java-rest-low.html
 * https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.clients.transport
 */
@Configuration
public class RestTransportClientConfig {

    @Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int esPort;

    @Value("${elasticsearch.username}")
    private String esUsername;

    @Value("${elasticsearch.password}")
    private String esPassword;

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final CredentialsProvider creProvider = new BasicCredentialsProvider();
        creProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(esUsername,esPassword));

        RestClientBuilder clientBuilder = RestClient.builder(new HttpHost(esHost,esPort,"http"))
                .setHttpClientConfigCallback(new HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                        return httpAsyncClientBuilder.setDefaultCredentialsProvider(creProvider);
                    }
                });
        return new RestHighLevelClient(clientBuilder);
    };

}