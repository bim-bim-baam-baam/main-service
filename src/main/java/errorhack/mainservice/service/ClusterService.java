package errorhack.mainservice.service;

import errorhack.mainservice.dto.ClusterDto;
import errorhack.mainservice.repository.ClusterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClusterService {

    private final ClusterRepository clusterRepository;

    public List<ClusterDto> getClusters(String url) {
        return List.of();
    }
}
