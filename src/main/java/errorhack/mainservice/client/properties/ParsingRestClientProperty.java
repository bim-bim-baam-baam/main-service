package errorhack.mainservice.client.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "rest.client.parsing", ignoreUnknownFields = false)
public class ParsingRestClientProperty extends AbstractRestClientProperties {
    public ParsingRestClientProperty(String baseUrl) {
        super(baseUrl);
    }
}
