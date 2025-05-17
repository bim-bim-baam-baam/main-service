package errorhack.mainservice.controller;

import errorhack.mainservice.dto.ClusterDto;
import errorhack.mainservice.dto.VersionDto;
import errorhack.mainservice.service.ClusterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClusterController {

    private final ClusterService clusterService;

    @GetMapping("/clusters/{url}")
    public VersionDto getClusters(@PathVariable String url) {
        return clusterService.getClusters(url);
    }

}
