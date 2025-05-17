package errorhack.mainservice.client;

import errorhack.mainservice.model.KirillPackageData;
import errorhack.mainservice.model.PackageData;
import errorhack.mainservice.model.PetrClusterSummary;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;
import java.util.Map;

public interface ClusteringClient {
    @PostExchange("/petr/cluster")
    List<Map<String, Object>> petrClusterPackages(@RequestBody PackageData packageData);

    @PostExchange("/petr/cluster/summary")
    PetrClusterSummary petrGetClustersSummary(@RequestBody PackageData packageData);

    @PostExchange("/kirill/cluster")
    List<Map<String, Object>> kirillClusterPackages(@RequestBody KirillPackageData packageData);
} 