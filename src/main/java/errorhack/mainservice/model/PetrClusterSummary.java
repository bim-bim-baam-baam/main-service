package errorhack.mainservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.Map;

@Value
public class PetrClusterSummary {
    @JsonProperty("total_clusters")
    int totalClusters;
    Map<String, Integer> languageStats;
} 