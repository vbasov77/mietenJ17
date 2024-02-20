package mieten17.exceptions;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class})
    public String handleAccessDeniedException(
            Exception ex, WebRequest request) {
        System.out.println("Кроется в ex++++++++++++++++" + ex);
        return "errors/forbidden";
    }

}
