package mieten17.controllers;

import mieten17.models.Message;
import mieten17.models.Obj;
import mieten17.models.User;
import mieten17.repositories.AddressRepository;
import mieten17.services.MessageService;
import mieten17.services.ObjService;
import mieten17.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MessageController {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ObjService objService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping("/my_msg")
    public String myMsgPage(Model model, @AuthenticationPrincipal User user) {
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
        System.out.println(toUserId);
        messageService.deleteChat(fromUserId, toUserId, objId);
        return "redirect:/my_msg";
    }


    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String view(@RequestParam("obj_id") Long objId, @RequestParam("to_user_id") Long toUserId,
                       Model model, @AuthenticationPrincipal User user) {
        List<Message> messages = messageService.getMsgToUsers(toUserId, user.getId(), objId);

        Long userId = user.getId();

        if (messages.size() != 0) {
//            if (messages.get(0).getFromUserId() != userId) {
//                toUserId = messages.get(0).getFromUserId();
//            } else {
//                toUserId = messages.get(0).getToUserId();
//            }
            int countMess = messages.size();
            List<Long> ids = new ArrayList<>();

            //Изменили статус сообщения после прочтения
            for (int i = 0; i < countMess; i++) {
                if (messages.get(i).getToUserId().equals(user.getId()) && messages.get(i).getStatus() == 0) {
                    ids.add(messages.get(i).getId());
                    messages.get(i).setStatus(1);
                }
            }
            if (!ids.isEmpty()) {
                messageService.updateStatus(ids);
            }
        } else {
            messages = null;
        }

        Obj obj = objService.getObjById(objId);
        String opponent = userService.getUserById(toUserId).getUsername();

        model.addAttribute("messages", messages);
        model.addAttribute("fromUserId", userId);
        model.addAttribute("toUserId", toUserId);
        model.addAttribute("objId", objId);
        model.addAttribute("toUserName", opponent);
        model.addAttribute("photo", obj.getPathStrOne());
        model.addAttribute("address", addressRepository.findAddressById(objId).getLocality().toString());
        return "messages/view_msg";
    }

    @RequestMapping(value = "/add_message", method = RequestMethod.POST)
    @ResponseBody
    public Object addMessage(@RequestParam("to_user_id") Long toUserId, @RequestParam("from_user_id") Long fromUserId,
                             @RequestParam("obj_id") Long objId,
                             @RequestParam("body") String body) {

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
        return msg;
    }

    @RequestMapping(value = "/notified", method = RequestMethod.POST)
    public String view(@RequestParam("to_user_id") Long toUserId, @RequestParam("obj_id") Long objId,
                       @RequestParam("array_id") Long[] arrayId,
                       Model model, @AuthenticationPrincipal User user) {

        List<Message> notified = new ArrayList<>();
        int count = arrayId.length;
        for (int i = 0; i < count; i++) {
            Message message = messageService.findMessageByIdAndStatus(arrayId[i], 1);
//            if ($not->status == 1) {
//                $notified [] = $not;
//            }
        }
//        exit(json_encode($notified));
        return null;
    }

    @RequestMapping(value = "/delete_msg", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteMsg(@RequestParam("id") Long id) {
        System.out.println(id);
        Map<String, Object> response = new HashMap<>();
        response.put("answer", "ok");
        messageService.delete(id);
        return response;
    }


}

