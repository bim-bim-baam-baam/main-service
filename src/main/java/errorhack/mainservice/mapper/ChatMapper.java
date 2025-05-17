package errorhack.mainservice.mapper;

import errorhack.mainservice.dto.ChatDto;
import errorhack.mainservice.entity.Chat;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ChatHistoryMapper.class})
public interface ChatMapper {
    Chat toEntity(ChatDto chatDto);
    ChatDto toChatDto(Chat chat);
}
