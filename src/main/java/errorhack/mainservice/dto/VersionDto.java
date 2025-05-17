package errorhack.mainservice.dto;

import errorhack.mainservice.entity.VersionStatus;
import lombok.Value;

import java.util.List;

@Value
public class VersionDto {
    VersionStatus status;
    List<ClusterDto> clusters;
}
