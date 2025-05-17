package errorhack.mainservice.mapper;

import errorhack.mainservice.dto.ChatHistoryDto;
import errorhack.mainservice.entity.ChatHistory;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChatHistoryMapper {
    ChatHistory toEntity(ChatHistoryDto chatHistoryDto);

    ChatHistoryDto toChatHistoryDto(ChatHistory chatHistory);
}
