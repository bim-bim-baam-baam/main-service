package errorhack.mainservice.mapper;

import errorhack.mainservice.dto.ClusterDto;
import errorhack.mainservice.entity.Cluster;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {PackageErrorMapper.class})
public interface ClusterMapper {
    Cluster toEntity(ClusterDto clusterDto);

    @AfterMapping
    default void linkPackages(@MappingTarget Cluster cluster) {
        cluster.getPackageErrors().forEach(packageError -> packageError.setCluster(cluster));
    }

    ClusterDto toClusterDto(Cluster cluster);
}
