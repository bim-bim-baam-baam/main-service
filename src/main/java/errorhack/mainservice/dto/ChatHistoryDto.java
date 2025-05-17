package errorhack.mainservice.dto;

import lombok.Value;

/**
 * DTO for {@link errorhack.mainservice.entity.ChatHistory}
 */
@Value
public class ChatHistoryDto {
    Long chatId;
    String sender;
    String message;
}
