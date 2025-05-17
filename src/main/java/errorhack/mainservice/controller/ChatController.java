package errorhack.mainservice.controller;

import errorhack.mainservice.dto.ChatDto;
import errorhack.mainservice.dto.ChatHistoryDto;
import errorhack.mainservice.response.MessageResponse;
import errorhack.mainservice.service.ChatService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/allChats")
    public List<ChatDto> getAllChats() {
        return chatService.getAllChats();
    }

    @PostMapping("/create")
    public ResponseEntity<ChatDto> createChat(@RequestParam String title, @RequestParam String context) {
        return ResponseEntity.ok().body(chatService.createChat(title, context));
    }

    @PostMapping("/message")
    public ResponseEntity<MessageResponse> sendMessage(@RequestParam Long chatId, @RequestParam String sender, @RequestParam String message) {
        return ResponseEntity.ok().body(chatService.sendMessage(chatId, sender, message));
    }

    @PatchMapping("/context")
    public ResponseEntity<ChatDto> updateContext(@RequestParam Long chatId, @RequestParam String context) {
        return ResponseEntity.ok().body(chatService.updateContext(chatId, context));
    }

    @GetMapping("/history")
    public ResponseEntity<List<ChatHistoryDto>> chatHistory(@RequestParam Long chatId) {
        return ResponseEntity.ok().body(chatService.getChatHistory(chatId));
    }
}
