package errorhack.mainservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="chat_history")
public class ChatHistory {
    @Column(nullable = false)
    private Long chatId;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String message;
}
