package Modulo.Resultados.Exceptions;

import org.springframework.http.HttpStatusCode;

public class ResultadosApiException extends RuntimeException{


    private HttpStatusCode code;


    public ResultadosApiException(String message) {
        super(message);
    }

    public ResultadosApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultadosApiException(String message, Throwable cause,HttpStatusCode code) {
        super(message, cause);
        this.code=code;
    }
    public ResultadosApiException(String message,HttpStatusCode code) {
        super(message);
        this.code=code;
    }

    public HttpStatusCode getCode() {
        return code;
    }
}
