package errorhack.mainservice.dto;

import lombok.Value;

import java.util.List;

/**
 * DTO for {@link errorhack.mainservice.entity.Cluster}
 */
@Value
public class ClusterDto {
    Long id;
    String name;
    String version;
    List<PackageErrorDto> packages;
}
