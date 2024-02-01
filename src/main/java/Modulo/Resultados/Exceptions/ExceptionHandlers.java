package Modulo.Resultados.Exceptions;



import Modulo.Resultados.Dtos.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;


@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(value = {ResultadosApiException.class})
    public ResponseEntity<ResponseError> handlerApiException(ResultadosApiException e){
        ResponseError res=new ResponseError(e.getMessage(),e.getCode().value());
        return new ResponseEntity<ResponseError>(res,e.getCode());

    }

    @ExceptionHandler(value = { NoSuchElementException.class })
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        ResponseError error=new ResponseError("Error inesperado: " + e.getMessage(), 404);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

