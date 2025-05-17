package errorhack.mainservice.service;

import errorhack.mainservice.dto.VersionDto;
import errorhack.mainservice.entity.Version;
import errorhack.mainservice.entity.VersionStatus;
import errorhack.mainservice.mapper.ClusterMapper;
import errorhack.mainservice.repository.ClusterRepository;
import errorhack.mainservice.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClusterService {

    private final ClusterRepository clusterRepository;
    private final VersionRepository versionRepository;

    private final ClusterMapper clusterMapper;
    private final MLService mlService;

    public VersionDto getClusters(String url) {

        Optional<Version> version = versionRepository.getVersionByUrl(url);

        if (version.isPresent()) {
            return new VersionDto(
                    version.get().getStatus(),
                    version.get().getClusters().stream().map(clusterMapper::toClusterDto).toList()
            );
        }

        mlService.processLogs(url);

        return new VersionDto(VersionStatus.PARSING, List.of());
    }
}
