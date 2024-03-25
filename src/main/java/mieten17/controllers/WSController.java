package mieten17.controllers;

import jakarta.servlet.http.HttpSession;
import mieten17.models.Message;
import mieten17.services.WSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WSController {
    @Autowired
    private WSService service;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody final Message message) {
        service.notifyFrontend(message.getBody());
    }

    @PostMapping("/send-private-message/{id}")
    public void sendPrivateMessage(@PathVariable final String id,
                                   @RequestBody final Message message) {
//        service.notifyUser(id, message.getBody());
    }

    @PostMapping("/read")
    public void read(
            @RequestParam("userId") Long id,
            @RequestParam("fromUserId") Long fromUserId,
            @RequestParam("chatId") String chatId,
            HttpSession session) {
        service.read(id, fromUserId, chatId, session);
    }
}
