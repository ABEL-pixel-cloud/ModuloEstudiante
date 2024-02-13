# ModuloResultados

<h1 align=center >API REST RESULTADOS (MICROSERVICIO) </h1>
<p>
El Microservicio de Resultados se caracteriza por su enfoque centrado en la automatización 
exhaustiva del proceso de admisión al Bootcamp Betek. Desde el momento en que se 
reciben los aspirantes aceptados, el sistema se encarga automáticamente de enviar un 
correo con la documentación requerida a cada aspirante.  Los documentos enviados por los 
aspirantes serán almacenados automáticamente en una base de datos para su posterior 
gestión y aprobación. Una vez que el documento del estudiante ha sido aprobado, el 
sistema envía automáticamente un correo electrónico con las credenciales necesarias para 
acceder al entrenamiento, y simultáneamente asigna al aspirante a una cohorte específica. Con la 
finalización de este proceso, se concluye la admisión al Bootcamp Betek
</p>

<h2>Tecnologías Utilizadas: </h2>
<p>
Java 17<br>
Spring Boot 3.2.2<br>
Gestor de dependencias Maven<br>
Motor de Base de Datos MySQL<br>
Persistencia de datos JPA e Hibernate<br>
Integración continua con GitHub Actions<br>
Despliegue con Railway<br>
<p>



<h2>Dependencias Utilizadas: </h2>
<p>
Spring for RabbitMQ<br>
Spring Data JPA<br>
Java Mail Sender<br>
Spring Web<br>
MySQL Driver<br>
JUnit (Testeo de pruebas unitarias)<br>
Swagger (Documentación de la API)
<p>


<h2>Patrones de Diseño Utilizados </h2>
<p>
Uso de DTO (Objeto de Transferencia de Datos): <br><br>
En el microservicio Resultado, se implementa el uso de DTOs para facilitar la transferencia eficiente de datos entre los diferentes componentes del sistema. Los DTOs se utilizan para representar la información relativa a los aspirantes, documentos, correos electrónicos y otras entidades relevantes. <br><br>


Patrón State (Estado):<br><br>
En el contexto del microservicio Resultado, se implementa el patrón State para controlar el estado de los documentos enviados por los aspirantes a lo largo del proceso de gestión y aprobación. Este patrón permite que un documento cambie su comportamiento y su estado interno en función de su estado actual. 
<p>

<h2>Diagrama UML</h2>

<img src="https://github.com/ABEL-pixel-cloud/ModuloResultados/blob/main/Diagramas/UML.png" alt="Diagrama Uml">


<h2>Diagrama Entidad Relación</h2>

<img src="https://github.com/ABEL-pixel-cloud/ModuloResultados/blob/main/Diagramas/DiagramaBaseDeDatos.PNG" alt="Diagrama entidad relacion">

<h2>Enpoints </h2>

<h3>Enpoint para enviar correo manualmente  </h3>

<h3>Post: api/v1/aspirante </h3>

<p>crear nuevo aspirante  en la base de datos con la información proporcionada en el cuerpo de la solicitud.<p><br><br>
<p>Parámetros de entrada: <p><br><br>
  
<ul>
    <li>tipoDeDocumento</li>
    <li>documento</li>
    <li>nombresCompletos</li>
   <li>telefono</li>
    <li>correo</li>
    <li>tipoDePerfil</li>
   <li>estadoDeProceso</li>
    <li>resultadoPruebaGorilla</li>
    <li>linkDePrueba</li>
   <li>admitido</li>
    <li>financiador</li>
    <li>programa</li>
   <li>observacion</li>
</ul>

<h3>Ejemplo de la solicitud: </h3>
<pre><code>
{
  
  "tipo_De_Documento": "string",
  "documento": 0,
  "nombres_Completos": "string",
  "telefono": 0,
  "correo": "string",
  "tipo_De_Perfil": "string",
  "estado_De_Proceso": "string",
  "resultado_Prueba_Gorilla": 0,
  "link_De_Prueba": "string",
  "admitido": true,
  "financiador": "string",
  "programa": "string",
  "observacion": "string"

}
</code></pre>

<h3>La API devuleve un mensaje notificando que se ha creado el aspirante: </h3>
<pre><code>
{
  
se ha creado el aspirante
}
</code></pre>

<h3>Enpoint para listar aspirantes </h3>
<p>Sin parámetros de entrada.<p><br><br>
<p>Ejemplo de solicitud:<p><br><br>
<p>La API devolverá los aspirantes encontrados en formato JSON:<p><br><br>

 <p>'http://localhost:8080/v1/Aspirante/Listar-Aspirante'<p>

 
<pre><code>
 [
  {
    "idaspirante": 0,
    "tipo_De_Documento": "string",
    "documento": 0,
    "nombresCompletos": "string",
    "telefono": 0,
    "correo": "string",
    "tipoDePerfil": "string",
    "estadoDeProceso": "string",
    "resultadoPruebaGorilla": 0,
    "linkDePrueba": "string",
    "admitido": true,
    "financiador": "string",
    "programa": "string",
    "observacion": "string",
    "estudiante": {
      "idEstudiante": 0,
      "nombre": "string",
      "cedula": "string",
      "programa": "string",
      "cohorte": {
        "cohorte": "string"
      },
      "aspirante": "string"
    }
  }
]
  </code></pre>




  















