package errorhack.mainservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UrlRequest(@JsonProperty String url) {
}
