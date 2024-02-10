package mieten17.services;

import jakarta.servlet.http.HttpSession;
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

    public Integer notifyUser(String userName, Long fromUserId, Message message, String chatId) {
        simpMessagingTemplate.convertAndSendToUser(userName, "/topic/private-messages/" + chatId, message);
        Integer count = messageService.findCountMsgNotRead(fromUserId);
        simpMessagingTemplate.convertAndSendToUser(userName, "/topic/notificationsMsg", count);
        return count;
    }

    public void read(Long userId, Long fromUserId, String chatId, HttpSession session) {
        String userName = userService.getUserNameParticipant(userId).getUsername();
        String fromUser = userService.getUserNameParticipant(fromUserId).getUsername();

        List<Long> unreadMessagesId = messageService.getUnreadMessages(userId, fromUserId);
        Integer NewUnreadMessagesId = 0;
        if (unreadMessagesId.size() > 0) {
            messageService.updateStatus(unreadMessagesId);

            NewUnreadMessagesId = messageService.findCountMsgNotRead(fromUserId);
            session.setAttribute("notificationsMsg", NewUnreadMessagesId);
        }

        simpMessagingTemplate.convertAndSendToUser(userName, "/topic/read-messages/" + chatId, unreadMessagesId);
        simpMessagingTemplate.convertAndSendToUser(fromUser, "/topic/read-notificationsMsg", NewUnreadMessagesId);

    }
}
