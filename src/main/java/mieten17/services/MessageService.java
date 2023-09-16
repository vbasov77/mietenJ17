package mieten17.services;


import mieten17.models.Message;
import mieten17.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMsgToUsers(Long toUserId, Long fromUserId, Long objId) {
        List<Message> messages = (List<Message>) messageRepository.getMsgToUsers(toUserId, fromUserId, objId);
        return messages;
    }

    public List<Object> myMessages(Long authId) {
        return messageRepository.myMessages(authId);
    }

    public void updateStatus(List<Long> ids) {
        messageRepository.updateStatus(ids);
    }

    public Message findMessageByIdAndStatus(Long id, int status) {
        return messageRepository.findMessageByIdAndStatus(id, status);
    }

    public Message addMsg(Message message) {
        return messageRepository.saveAndFlush(message);
    }

    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    public void deleteChat(Long fromUserId, Long toUserId, Long objId) {
        messageRepository.deleteChat(fromUserId, toUserId, objId);
    }



}
