package com.fiap.agnello.advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFound(EntityNotFoundException ex) {
        ModelAndView mv = new ModelAndView("errors/404");
        mv.addObject("mensagem", ex.getMessage());
        return mv;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleBadRequest(IllegalArgumentException ex) {
        ModelAndView mv = new ModelAndView("errors/400");
        mv.addObject("mensagem", ex.getMessage());
        return mv;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ModelAndView handleResponseStatus(ResponseStatusException ex) {
        HttpStatusCode status = ex.getStatusCode();
        ModelAndView mv = new ModelAndView("errors/" + status.value());
        mv.addObject("mensagem", ex.getReason());
        return mv;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleGeneric(Exception ex) {
        ModelAndView mv = new ModelAndView("errors/500");
        mv.addObject("mensagem", "Erro interno no servidor.");
        return mv;
    }
}