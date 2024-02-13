package Modulo.Resultados.Exceptions;



import Modulo.Resultados.Dtos.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;


@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(value = {ResultadosApiException.class})
    public ResponseEntity<ResponseErrorDto> handlerApiException(ResultadosApiException e){
        ResponseErrorDto res=new ResponseErrorDto(e.getMessage(),e.getCode().value());
        return new ResponseEntity<ResponseErrorDto>(res,e.getCode());

    }

    @ExceptionHandler(value = { NoSuchElementException.class })
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        ResponseErrorDto error=new ResponseErrorDto("Error inesperado: " + e.getMessage(), 404);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }





}



