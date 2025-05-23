package errorhack.mainservice.service;

import errorhack.mainservice.client.LLMClient;
import errorhack.mainservice.dto.ChatDto;
import errorhack.mainservice.dto.ChatHistoryDto;
import errorhack.mainservice.entity.Chat;
import errorhack.mainservice.entity.ChatHistory;
import errorhack.mainservice.mapper.ChatHistoryMapper;
import errorhack.mainservice.mapper.ChatMapper;
import errorhack.mainservice.model.AskRequest;
import errorhack.mainservice.repository.ChatHistoryRepository;
import errorhack.mainservice.repository.ChatRepository;
import errorhack.mainservice.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatHistoryRepository chatHistoryRepository;
    private final ChatMapper chatMapper;
    private final ChatHistoryMapper chatHistoryMapper;
    private final LLMClient llmClient;

    // 1. Получить все чаты без истории
    public List<ChatDto> getAllChats() {
        return chatRepository.findAll().
                stream()
                .map(chatMapper::toChatDto)
                .collect(Collectors.toList());
    }

    // 2. Создать новый чат
    public ChatDto createChat(String title, String context) {
        Chat chat = new Chat();
        chat.setTitle(title);
        chat.setContext(context);
        Chat savedChat = chatRepository.save(chat);
        return chatMapper.toChatDto(savedChat);
    }

    // 3. Отправить сообщение в чат
    public MessageResponse sendMessage(Long chatId, String sender, String message) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found"));
        ChatHistory history = new ChatHistory();
        history.setChatId(chat.getId());
        history.setMessage(message);
        history.setSender(sender);
        ChatHistory savedHistory = chatHistoryRepository.save(history);
        ChatHistoryDto chatHistoryDto = chatHistoryMapper.toChatHistoryDto(savedHistory);

        String prompt = chat.getContext();
        String petrAns = llmClient.ask(new AskRequest(prompt, message));


        // Сохраняем ответ от PETR в историю
        ChatHistory petrHistory = new ChatHistory();
        petrHistory.setChatId(chat.getId());
        petrHistory.setMessage(petrAns);
        petrHistory.setSender("PETR");
        chatHistoryRepository.save(petrHistory);

        return new MessageResponse(petrAns);
    }

    // 4. Изменить контекст чата
    public ChatDto updateContext(Long chatId, String newContext) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat not found"));
        chat.setContext(newContext);
        Chat updatedChat = chatRepository.save(chat);
        return chatMapper.toChatDto(updatedChat);
    }

    // 5. Получить историю сообщений по чату
    public  List<ChatHistoryDto> getChatHistory(Long chatId) {
        return chatHistoryRepository.findByChatId(chatId)
                .stream()
                .map(chatHistoryMapper::toChatHistoryDto)
                .collect(Collectors.toList());
    }
}