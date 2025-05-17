package errorhack.mainservice.service;

import errorhack.mainservice.client.ParsingClient;
import errorhack.mainservice.entity.Version;
import errorhack.mainservice.entity.VersionStatus;
import errorhack.mainservice.model.ParsingEntry;
import errorhack.mainservice.model.UrlRequest;
import errorhack.mainservice.repository.VersionRepository;
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
    private final VersionRepository versionRepository;

    @Async
    public void processLogs(String url) {

        Version version = new Version();
        version.setUrl(url);
        updateStatus(version, VersionStatus.PARSING);

        List<ParsingEntry> parsingEntries = parsingClient.parseUrl(new UrlRequest(url));

        updateStatus(version, VersionStatus.LLM_PARSING);

        // TODO: LLM parsing

        updateStatus(version, VersionStatus.CLUSTERING);

        // TODO: CLUSTERING

        // TODO: ПОХОД В ДИПСИК

        updateStatus(version, VersionStatus.OK);
    }

    void updateStatus(Version version, VersionStatus status) {
        version.setStatus(status);
        version = versionRepository.save(version);
    }
}
