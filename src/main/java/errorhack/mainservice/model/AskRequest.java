package errorhack.mainservice.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public record AskRequest(@JsonProperty String prompt, @JsonProperty String message) {
}
