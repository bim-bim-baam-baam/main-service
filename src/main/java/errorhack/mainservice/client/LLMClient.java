package errorhack.mainservice.client;

import errorhack.mainservice.model.AskRequest;
import errorhack.mainservice.model.ParsingEntry;
import errorhack.mainservice.response.MessageResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

public interface LLMClient {
    @GetExchange("/ask")
    String ask(@RequestBody AskRequest request);
}
