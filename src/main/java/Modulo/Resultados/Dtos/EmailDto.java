package Modulo.Resultados.Dtos;


import lombok.*;

@Getter // genera metodos getter para todos los metodos de la clase
@Setter
@NoArgsConstructor // genera un constructor sin argumentos
@AllArgsConstructor // constructor con todos los parametros
@ToString //Anotación que indica a Lombok que genere automáticamente el método toString().
public class EmailDto {

    private String[] toUser;
    private String subject;

    private String message;




}
