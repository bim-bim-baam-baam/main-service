package errorhack.mainservice.client;

import errorhack.mainservice.model.ParsingEntry;
import org.springframework.web.service.annotation.PostExchange;

public interface ParsingClient {

    @PostExchange("/parse")
    ParsingEntry parseUrl(String url);
}
