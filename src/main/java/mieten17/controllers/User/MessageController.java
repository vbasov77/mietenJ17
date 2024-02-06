package mieten17.controllers.User;

import mieten17.config.MyUserDetails;
import mieten17.models.Message;
import mieten17.models.Obj;
import mieten17.repositories.AddressRepository;
import mieten17.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/user")
public class MessageController {

    @Autowired
    private RandomService randomService;
    @Autowired
    private WSService wsService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ObjService objService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private CreateUserService createUserService;

    @GetMapping("/my_msg")
    public String myMsgPage(Model model, @AuthenticationPrincipal MyUserDetails user) {
        List<Object> messages = messageService.myMessages(user.getId());
        List<Object> msgArr = new ArrayList<>();
        if (messages.size() > 0) {
            for (int i = 0; i < messages.size(); i++) {
                Object[] row = (Object[]) messages.get(i);
                msgArr.add(row);
            }
        } else {
            msgArr = null;
        }
        model.addAttribute("messages", msgArr);
        return "messages/my_msg";
    }

    @RequestMapping(value = "/delete_chat", method = RequestMethod.GET)
    public String deleteChat(
            @RequestParam("to_user_id") Long toUserId,
            @RequestParam("from_user_id") Long fromUserId,
            @RequestParam("obj_id") Long objId) {
        messageService.deleteChat(fromUserId, toUserId, objId);
        return "redirect:/user/my_msg";
    }


    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String view(@RequestParam("obj_id") Long objId,
                       @RequestParam("to_user_id") Long toUserId,
                       Model model, @AuthenticationPrincipal MyUserDetails user) {
        List<Message> messages = messageService.getMsgToUsers(toUserId, user.getId(), objId);
        Long userId = user.getId();

        if (messages.size() != 0) {
            int countMess = messages.size();
            List<Long> ids = new ArrayList<>();

            //Изменили статус сообщения после прочтения
            for (int i = 0; i < countMess; i++) {
                if (messages.get(i).getToUserId().equals(user.getId()) && messages.get(i).getStatus() == 0) {
                    ids.add(messages.get(i).getId());
                    messages.get(i).setStatus(1);
                }
            }

        } else {
            messages = null;
        }
        Long forChatId;
        Obj obj = objService.getObjById(objId);

        if (obj.getUserId().equals(userId)) {
            forChatId = toUserId;
        } else {
            forChatId = userId;
        }

        String opponent = createUserService.getUserById(toUserId).getUsername();
        String chatId = obj.getId() + "&" + obj.getUserId() + "&" + forChatId;

        model.addAttribute("messages", messages);
        model.addAttribute("fromUserId", userId);
        model.addAttribute("toUserId", toUserId);
        model.addAttribute("objId", objId);
        model.addAttribute("toUserName", opponent);
        model.addAttribute("photo", obj.getPathStrOne());
        model.addAttribute("chatId", chatId);
        model.addAttribute("address", addressRepository.findAddressById(objId).getLocality().toString());
        return "messages/view_msg";
    }

    @RequestMapping(value = "/add_message", method = RequestMethod.POST)
    @ResponseBody
    public Object addMessage(@RequestParam("to_user_id") Long toUserId, @RequestParam("from_user_id") Long fromUserId,
                             @RequestParam("obj_id") Long objId,
                             @RequestParam("body") String body,
                             @RequestParam("chatId") String chatId
    ) {

        DateFormat dateFormat = new SimpleDateFormat("dd:MM:yy HH:mm:ss");
        Date date = new Date();
        String format = dateFormat.format(date);
        Message message = new Message();
        message.setObjId(objId);
        message.setFromUserId(fromUserId);
        message.setToUserId(toUserId);
        message.setBody(body);
        message.setCreatedAt(format);
        Message newMsg = new Message();
        newMsg = messageService.addMsg(message);
        Map<String, Object> msg = new HashMap<>();
        msg.put("bool", true);
        msg.put("id", newMsg.getId());
        msg.put("body", newMsg.getBody());
        msg.put("date", newMsg.getCreatedAt());


        wsService.notifyUser(userService.getUserNameParticipant(toUserId).getUsername(), message, chatId);
        return msg;
    }

    @RequestMapping(value = "/delete_msg", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    @ResponseBody
    public Object deleteMsg(@RequestParam("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("answer", "ok");
        messageService.delete(id);
        return response;
    }


}

