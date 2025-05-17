package errorhack.mainservice.config;

import errorhack.mainservice.client.ParsingClient;
import errorhack.mainservice.client.properties.AbstractRestClientProperties;
import errorhack.mainservice.client.properties.ParsingRestClientProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@EnableConfigurationProperties(ParsingRestClientProperty.class)
public class RestClientConfig {

    @Bean
    public ParsingClient parsingClient(RestClient.Builder builder, ParsingRestClientProperty properties) {
        RestClient restClient = buildBaseRestClient(builder, properties).build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(ParsingClient.class);
    }

    private RestClient.Builder buildBaseRestClient(
            RestClient.Builder builder, AbstractRestClientProperties properties) {

        return builder.baseUrl(properties.getBaseUrl());
    }
}
