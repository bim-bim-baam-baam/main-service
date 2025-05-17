package errorhack.mainservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
public class PackageData {
    List<Map<String, String>> data;
} 