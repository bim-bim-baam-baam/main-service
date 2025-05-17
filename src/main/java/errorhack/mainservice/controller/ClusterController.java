package errorhack.mainservice.controller;

import errorhack.mainservice.dto.VersionDto;
import errorhack.mainservice.model.UrlRequest;
import errorhack.mainservice.service.ClusterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClusterController {

    private final ClusterService clusterService;

    @PostMapping("/clusters/{url}")
    public VersionDto getClusters(@RequestBody UrlRequest url) {
        return clusterService.getClusters(url.url());
    }

}
