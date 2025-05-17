package errorhack.mainservice.service;

import errorhack.mainservice.client.ClusteringClient;
import errorhack.mainservice.client.ParsingClient;
import errorhack.mainservice.model.KirillPackageData;
import errorhack.mainservice.model.PackageData;
import errorhack.mainservice.model.ParsingEntry;
import errorhack.mainservice.model.PetrClusterSummary;
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

    @Async
    public void processLogs(String url) {
        List<ParsingEntry> parsingEntries = parsingClient.parseUrl(url);
        log.info("Received {} parsing entries", parsingEntries.size());

        // Convert parsing entries to the format expected by clustering APIs
        List<Map<String, String>> packageDataList = parsingEntries.stream()
                .map(entry -> {
                    Map<String, String> data = new HashMap<>();
                    data.put("package", entry.pack());
                    data.put("error_type", "error"); // You might want to extract this from errors
                    data.put("description", entry.errors());
                    data.put("programming_language", "java"); // You might want to detect this
                    return data;
                })
                .collect(Collectors.toList());

        // Call Petr's clustering endpoints
        PackageData petrData = new PackageData(packageDataList);
        List<Map<String, Object>> petrClusters = clusteringClient.petrClusterPackages(petrData);
        PetrClusterSummary petrSummary = clusteringClient.petrGetClustersSummary(petrData);
        
        log.info("Petr's clustering results: {} clusters", petrSummary.getTotalClusters());
        log.info("Language statistics: {}", petrSummary.getLanguageStats());

        // Call Kirill's clustering endpoint
        KirillPackageData kirillData = new KirillPackageData(packageDataList, 5); // You might want to make this configurable
        List<Map<String, Object>> kirillClusters = clusteringClient.kirillClusterPackages(kirillData);
        
        log.info("Kirill's clustering results: {} clusters", kirillClusters.size());

        // TODO: Process and store the clustering results
    }
}
