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

<h3>Enpoint para crear aspirante </h3>
<h3>POST: api/v1/aspirante </h3>
<p>crear nuevo aspirante  en la base de datos con la información proporcionada en el cuerpo de la solicitud.<p>
<p>Parámetros de entrada: <p>
  
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


<h3>Enpoint para cargar archivos </h3>
<h3>POST:/api/documentos/cargarArchivos </h3>
<p>parámetros de entrada<p>
<ul>
    <li>tipoDocumento: Cedula(String)</li>
    <li>archivoActa: archivo a subir</li>
    <li>archivoDocumento: archivo a subir</li>
</ul>

<p>La API gurdará los archivos en la base de datos:<p>
<pre><code>
{
  "mensaje": "archivo subido correctamente"
}

</code></pre>



<h3>Enpoint para agregar el estado de los documentos </h3>
<h3>POST: /api/documentos/agregarEstadoDocumento </h3>
<p>parámetros de entrada<p>
<ul>
    <li>numero del aspirante: (String)</li>
    <li>estado del documento:false/true(Boolean)</li>
</ul>
<p>La API devolverá el estado de la documentacion para ese aspirante en formato JSON:<p>

<pre><code>
  {
  "mensaje": "estado agregado"
  }
</code></pre>

<h3>Enpoint para asignar cohorte al estudiante </h3>
<h3>POST: /api/documentos/cargarArchivos </h3>
<p>parámetros de entrada<p>
<ul>
    <li>idEstudiante: (String)</li>
    <li>cohorte:(String)</li>
</ul>
<p>La API devolverá el mensaje que se ha asignado la cohorte correctamente en formato JSON:<p>

<pre><code>
  {
  "mensaje": "cohorte asignada correctamente"
  }
</code></pre>


<h3>Enpoint para listar documentos de aspirantes </h3>
<h3>GET:api/documentos/listarDocumentos </h3>
<p>Sin parámetros de entrada.<p>
<p>Ejemplo de solicitud:<p>
<p>'http://localhost:8080/api/documentos/listarDocumentos'<p>
<p>La API devolverá los aspirantes encontrados junto con la documentacion enviada en formato JSON:<p>
 
<pre><code>
[
  {
    "id": 0,
    "nombreacta": "string",
    "urlacta": "string",
    "tipoDocumentoacta": "string",
    "tamanoacta": 0,
    "nombredocumentocedula": "string",
    "urldocumentocedula": "string",
    "tipoDocumentocedula": "string",
    "tamanodocumentocedula": 0,
    "nombreAspirante": "string",
    "cedulaAspirante": 0
  }
]
</code></pre>

<h3>Enpoint para listar aspirantes </h3>
<h3>GET: v1/Aspirante/Listar-Aspirante </h3>
<p>Sin parámetros de entrada.<p>
<p>Ejemplo de solicitud:<p>
<p>'http://localhost:8080/v1/Aspirante/Listar-Aspirante'<p>
<p>La API devolverá los aspirantes encontrados en formato JSON:<p>

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

<h3>Enpoint para descardar documento del aspirante </h3>
<h3>GET: /api/documentos/descargarCedula/{id} </h3>
<p> parámetros de entrada.<p>
  
  <ul>
    <li>id: cadena de 32 digitos hexadecimal(String)</li>
</ul>
<p>La API genera una url de descarga:<p>

<pre><code>
  
   Download file
  
</code></pre>

<h3>Enpoint para descardar acta del aspirante </h3>
<h3>GET: /api/documentos/descargarActa/{id} </h3>
<p> parámetros de entrada.<p>
  
  <ul>
    <li>id: cadena de 32 digitos hexadecimal(String)</li>
</ul>
<p>La API genera una url de descarga:<p>

<pre><code>
  
   Download file
  
</code></pre>


<h3>Integración Continua con GitHub Actions</h3>
<p>Se utiliza GitHub Actions en este proyecto para automatizar las pruebas y el despliegue, lo que garantiza una mayor calidad del código y una entrega más rápida y confiable.</p>

<h3>Despliegue con Railway</h3>
<p>Railway se utiliza para el despliegue continuo de este proyecto, lo que permite una integración continua fluida y una implementación rápida y segura de nuevas funcionalidades.</p>




  







  


  




  















