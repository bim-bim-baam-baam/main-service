package errorhack.mainservice.mapper;

import errorhack.mainservice.dto.PackageErrorDto;
import errorhack.mainservice.entity.PackageError;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PackageErrorMapper {
    PackageError toEntity(PackageErrorDto packageErrorDto);

    PackageErrorDto toPackageErrorDto(PackageError packageError);
}
