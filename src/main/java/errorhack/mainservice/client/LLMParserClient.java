package errorhack.mainservice.client;

import org.springframework.web.service.annotation.GetExchange;

import java.util.List;
import java.util.Map;

public interface LLMParserClient {
    @GetExchange("/get_llm_parsed_data")
    List<Map<String, String>> getLLMParsedData();
} 