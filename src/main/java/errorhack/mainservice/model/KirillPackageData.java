package errorhack.mainservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
public class KirillPackageData {
    List<Map<String, String>> data;
    @JsonProperty("n_clusters")
    int nClusters;
} 