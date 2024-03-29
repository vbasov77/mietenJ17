package mieten17.exceptions;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {
    @ExceptionHandler({AccessDeniedException.class})
    public String handleAccessDeniedException(Model model) {
        model.addAttribute("error", "У вас нет доступа к этой странице!");
        return "errors/forbidden";
    }
}
