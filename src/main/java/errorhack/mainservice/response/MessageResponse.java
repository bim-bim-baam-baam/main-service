package errorhack.mainservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MessageResponse(@JsonProperty String text) {
}
