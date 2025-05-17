package errorhack.mainservice.client.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "rest.client.llm", ignoreUnknownFields = false)
public class LLMRestClientProperty extends AbstractRestClientProperties {
    public LLMRestClientProperty(String baseUrl) {
        super(baseUrl);
    }
}