package mieten17.services;

import mieten17.dto.ResponseMessage;
import mieten17.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WSService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    public void notifyFrontend(final String message) {
        ResponseMessage response = new ResponseMessage(message);
        simpMessagingTemplate.convertAndSend("/topic/messages", response);
    }

    public void notifyUser(String userName, Message message, String chatId) {
        simpMessagingTemplate.convertAndSendToUser(userName, "/topic/private-messages/" + chatId, message);

//        messagingTemplate.convertAndSendToUser(userName, "/topic/private-messages", message);
    }

    public void read(Long userId, Long fromUserId, String chatId) {
        String userName = userService.getUserNameParticipant(userId).getUsername();
        List<Long> unreadMessagesId = messageService.getUnreadMessages(userId, fromUserId);
        if (unreadMessagesId.size() > 0) {
            messageService.updateStatus(unreadMessagesId);
        }

        simpMessagingTemplate.convertAndSendToUser(userName,
                "/topic/read-messages/" + chatId, unreadMessagesId);

    }
}
