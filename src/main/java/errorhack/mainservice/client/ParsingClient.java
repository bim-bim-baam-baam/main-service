package errorhack.mainservice.client;

import errorhack.mainservice.model.ParsingEntry;
import errorhack.mainservice.model.UrlRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

public interface ParsingClient {

    @PostExchange("/parse")
    List<ParsingEntry> parseUrl(@RequestBody UrlRequest url);
}
