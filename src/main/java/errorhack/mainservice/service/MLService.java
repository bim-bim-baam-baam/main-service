package errorhack.mainservice.service;

import errorhack.mainservice.client.ParsingClient;
import errorhack.mainservice.model.ParsingEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MLService {

    private final ParsingClient parsingClient;

    @Async
    public void processLogs(String url) {

        List<ParsingEntry> parsingEntries = parsingClient.parseUrl(url);

        log.info(parsingEntries.toString());
    }
}
