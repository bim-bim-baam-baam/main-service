package errorhack.mainservice.dto;

import lombok.Value;
import java.util.List;

/**
 * DTO for {@link errorhack.mainservice.entity.Chat}
 */
@Value
public class ChatDto {
    Long id;
    String title;
    String context;
}
