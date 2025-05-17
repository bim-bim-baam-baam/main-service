package errorhack.mainservice.service;

import errorhack.mainservice.client.ClusteringClient;
import errorhack.mainservice.client.LLMParserClient;
import errorhack.mainservice.client.ParsingClient;
import errorhack.mainservice.model.KirillPackageData;
import errorhack.mainservice.model.PackageData;
import errorhack.mainservice.model.ParsingEntry;
import errorhack.mainservice.model.PetrClusterSummary;
import errorhack.mainservice.entity.Version;
import errorhack.mainservice.entity.VersionStatus;
import errorhack.mainservice.model.ParsingEntry;
import errorhack.mainservice.model.UrlRequest;
import errorhack.mainservice.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MLService {

    private final ParsingClient parsingClient;
    private final ClusteringClient clusteringClient;
    private final LLMParserClient llmParserClient;

    // TODO: Process and store the clustering results
    private final VersionRepository versionRepository;

    @Async
    public void processLogs(String url) {

        Version version = new Version();
        version.setUrl(url);
        updateStatus(version, VersionStatus.PARSING);

        List<ParsingEntry> parsingEntries = parsingClient.parseUrl(new UrlRequest(url));

        updateStatus(version, VersionStatus.LLM_PARSING);

        // Get pre-computed LLM parsed data
        List<Map<String, String>> packageDataList = llmParserClient.getLLMParsedData();
        log.info("Received {} LLM parsed entries", packageDataList.size());

        updateStatus(version, VersionStatus.CLUSTERING);

        // Call Petr's clustering endpoints
        PackageData petrData = new PackageData(packageDataList);
        List<Map<String, Object>> petrClusters = clusteringClient.petrClusterPackages(petrData);
        PetrClusterSummary petrSummary = clusteringClient.petrGetClustersSummary(petrData);
        
        log.info("Petr's clustering results: {} clusters", petrSummary.getTotalClusters());
        log.info("Language statistics: {}", petrSummary.getLanguageStats());

        // // Call Kirill's clustering endpoint
        // KirillPackageData kirillData = new KirillPackageData(packageDataList, 5); // You might want to make this configurable
        // List<Map<String, Object>> kirillClusters = clusteringClient.kirillClusterPackages(kirillData);
        
        // log.info("Kirill's clustering results: {} clusters", kirillClusters.size());

        // TODO: ПОХОД В ДИПСИК

        updateStatus(version, VersionStatus.OK);
    }

    void updateStatus(Version version, VersionStatus status) {
        version.setStatus(status);
        version = versionRepository.save(version);
    }
}
