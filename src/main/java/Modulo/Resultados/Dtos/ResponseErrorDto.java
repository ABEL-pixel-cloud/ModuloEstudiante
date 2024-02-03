package Modulo.Resultados.Dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // genera metodos getter para todos los metodos de la clase
@Setter
@NoArgsConstructor // genera un constructor sin argumentos
public class ResponseErrorDto {
    private  String message;

    private Integer code;

    public ResponseErrorDto(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
