package mieten17.controllers.User;


import jakarta.servlet.http.HttpSession;
import mieten17.config.MyUserDetails;
import mieten17.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NotificationController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/find-count-notifications")
    @ResponseBody
    public Object findCountNotifications(HttpSession session,
                                         @AuthenticationPrincipal MyUserDetails userDetails) {

        Integer countMsgNotRead = messageService.findCountMsgNotRead(userDetails.getId());
        session.setAttribute("notificationsMsg", countMsgNotRead);
        return countMsgNotRead;
    }

}