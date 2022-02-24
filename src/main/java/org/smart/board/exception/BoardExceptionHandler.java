package org.smart.board.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BoardExceptionHandler {

    @ExceptionHandler
    public String errorHandler(Exception e, Model model) {
        String message = e.getMessage();
        model.addAttribute("errorMsg", message);

        return "/error";
    }
}
