# Deploy

Dado que se realizó el día de la entrega, se optó por la siguiente estrategia:

1. Crear un usuario IAM en AWS para uso exclusivo de este proyecto y posterior CI/CD.
2. Realizar primer deploy de forma manual con usuario root de AWS.
3. Si sobra tiempo, implementar CI/CD.

También se evaluó la posibilidad de usar el *AWS CDK*, dado que existe interés por aprender esta herramienta,
pero se determinó que podría agregar contratiempos innecesarios (en caso de algún imprevisto), así que
se optó por "la opción segura" (punto 2) y si todo salía bien, podría haber espacio para experimentar o mejorar
el proceso.

## Build Del Proyecto

Antes de crear el build del proyecto, se debe cambiar el puerto de ejecución del *Tomcat* embebido de Spring Boot
al `5000` ta como indica la [documentación](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/java-quickstart.html#java-quickstart-create-app). Para
ello, solo hay que modificar el `application.properties` y agregar la línea:

~~~
server.port=5000
~~~

Para crear el ejecutable a desplegar, únicamente hay que usar el siguiente comando de maven en el directorio
del proyecto:

~~~
$ mvn clean package 
~~~

Como output, producirá el build del proyecto dentro del directorio /target. Si no se ha modificado nada referente
al nombre/versión del proyecto, lucirá con un sufijo de la siguiente forma:

~~~
./target/poke-api-backend-0.0.1-SNAPSHOT.jar
~~~

## Elastic Beanstalk

Los pasos que se siguieron fueron los siguientes:

1. Crear **nueva aplicación** en servicio Elastic Beanstalk.
2. Crear **nuevo ambiente** dentro de la aplicación recién creada, el cual hará el setup de toda la infraestructura
necesaria para el proyecto (incluída una instancia de EC2) en función de los parámetros introducidos por la consola
de administración.
3. Seleccionar lenguaje de programación utilizado para el proyecto (Java/Corretto 21).
4. Adjuntar el build del proyecto `./target/poke-api-backend-0.0.1-SNAPSHOT.jar` (opcional, se puede hacer después).
5. Será necesario crear un nuevo Rol en el servicio IAM para permitir a EC2 hacer su setup correspondiente, dicho Rol
debe tener los siguientes permisos: `AWSElasticBeanstalkWebTier`, `AWSElasticBeanstalkWorkerTier` y
`AWSElasticBeanstalkMulticontainerDocker` (como lo indica la [documentación](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/concepts-roles-instance.html)).
6. Una vez creado el Rol, usarlo en el campo de perfil de instancia de EC2.
7. Habilitar acceso por IP pública (esto para términos de la práctica, no se manejaría información sensible,
pero en una infraestructura para un proyecto real, esto no es la mejor opción por riesgos de seguridad).
8. Dejar la configuración de capacidad por defecto (storage via contenedor, una única instancia, arquitectura x84_64, etc).
9. Sin embargo, para efectos de esta práctica, se removió opción de auto-escalamiento (el proyecto se ejecuta solo en una `t3.micro`).
10. En cuanto a monitoreo, se optó solo por el básico (viene el "mejorado" seleccionado por defecto).
11. Se desactivaron las actualizaciones de plataforma programadas (no son necesarias para esta prueba).
12. El resto de configuración se dejó por defecto (despliegue "all at once", sin notificaciones por email, sin software extra).
13. Revisar el resumen de la configuración y finalizar el proceso en caso de ser correcta.

Se redireccionará a la pantalla de notificaciones/eventos del ambiente recién creado, el proceso tomará algunos minutos,
al finalizar el ambiente pasará de estado "gris" a "verde" y estará listo para usarse.
