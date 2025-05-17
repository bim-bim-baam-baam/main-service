package errorhack.mainservice.client;

import errorhack.mainservice.model.AskRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;


public interface LLMClient {
    @PostExchange("/ask")
    String ask(@RequestBody AskRequest request);
}
