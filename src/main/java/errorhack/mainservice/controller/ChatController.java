package errorhack.mainservice.controller;

import errorhack.mainservice.dto.ChatDto;
import errorhack.mainservice.dto.ChatHistoryDto;
import errorhack.mainservice.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;

    // 1. Получить все чаты без истории
    @GetMapping
    public List<ChatDto> getAllChats() {
        return chatService.getAllChats();
    }

    // 2. Создать новый чат
    @PostMapping
    public ChatDto createChat(@RequestParam String title, @RequestParam(required = false) String context) {
        return chatService.createChat(title, context);
    }

    // 3. Отправить сообщение в чат
    @PostMapping("/{chatId}/messages")
    public ChatHistoryDto sendMessage(
            @PathVariable Long chatId,
            @RequestParam String sender,
            @RequestParam String message) {
        return chatService.sendMessage(chatId, sender, message);
    }

    // 4. Изменить контекст чата
    @PatchMapping("/{chatId}/context")
    public ChatDto updateContext(@PathVariable Long chatId, @RequestParam String context) {
        return chatService.updateContext(chatId, context);
    }
}
