package Modulo.Resultados.Exceptions;



import Modulo.Resultados.Dtos.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

