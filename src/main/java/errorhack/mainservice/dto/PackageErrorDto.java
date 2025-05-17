package errorhack.mainservice.dto;

import lombok.Value;

/**
 * DTO for {@link errorhack.mainservice.entity.PackageError}
 */
@Value
public class PackageErrorDto {
    Long id;
    String name;
    String description;
}
