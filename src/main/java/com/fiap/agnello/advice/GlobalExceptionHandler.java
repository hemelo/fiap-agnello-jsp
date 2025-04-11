package com.fiap.agnello.advice;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
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

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNoResourceFound(NoResourceFoundException ex) {
        ModelAndView mv = new ModelAndView("errors/404");
        mv.addObject("mensagem", "Recurso não encontrado.");
        return mv;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleGeneric(Exception ex) {
        ModelAndView mv = new ModelAndView("errors/500");
        mv.addObject("mensagem", "Erro interno no servidor.");
        log.error("Erro interno no servidor", ex);
        return mv;
    }
}