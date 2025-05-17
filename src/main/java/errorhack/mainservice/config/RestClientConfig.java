package errorhack.mainservice.config;

import errorhack.mainservice.client.ClusteringClient;
import errorhack.mainservice.client.LLMClient;
import errorhack.mainservice.client.LLMParserClient;
import errorhack.mainservice.client.ParsingClient;
import errorhack.mainservice.client.properties.AbstractRestClientProperties;
import errorhack.mainservice.client.properties.ClusteringRestClientProperty;
import errorhack.mainservice.client.properties.LLMRestClientProperty;
import errorhack.mainservice.client.properties.ParsingRestClientProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@EnableConfigurationProperties({
    ParsingRestClientProperty.class,
    ClusteringRestClientProperty.class,
    LLMRestClientProperty.class
})
public class RestClientConfig {

    @Bean
    public ParsingClient parsingClient(RestClient.Builder builder, ParsingRestClientProperty properties) {
        RestClient restClient = buildBaseRestClient(builder, properties).build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(ParsingClient.class);
    }

    @Bean
    public LLMClient llmClient(RestClient.Builder builder, LLMRestClientProperty properties) {
        RestClient restClient = buildBaseRestClient(builder, properties).build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(LLMClient.class);
    }

    @Bean
    public LLMParserClient llmParserClient(RestClient.Builder builder, LLMRestClientProperty properties) {
        RestClient restClient = buildBaseRestClient(builder, properties).build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(LLMParserClient.class);
    }

    @Bean
    public ClusteringClient clusteringClient(RestClient.Builder builder, ClusteringRestClientProperty properties) {
        RestClient restClient = buildBaseRestClient(builder, properties).build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(ClusteringClient.class);
    }

    private RestClient.Builder buildBaseRestClient(
            RestClient.Builder builder, AbstractRestClientProperties properties) {
        return builder.baseUrl(properties.getBaseUrl());
    }
}
