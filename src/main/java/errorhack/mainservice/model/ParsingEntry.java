package errorhack.mainservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ParsingEntry(@JsonProperty("package") String pack,
                           @JsonProperty String link,
                           @JsonProperty String errors) {
}
