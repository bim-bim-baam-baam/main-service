package errorhack.mainservice.client.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "rest.client.clustering", ignoreUnknownFields = false)
public class ClusteringRestClientProperty extends AbstractRestClientProperties {
    public ClusteringRestClientProperty(String baseUrl) {
        super(baseUrl);
    }
} 