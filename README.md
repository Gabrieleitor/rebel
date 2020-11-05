# Operación Fuego de Quasar

Esté programa fue diseñado para ayudar a Han Solo en su lucha contra el imperio galáctico.


## Swagger

Cuenta con el apoyo de la librería swagger para documentar el API.

Se encuentra en el siguiente link:

[https://prueba-rebel.herokuapp.com/swagger-ui.html#/](https://prueba-rebel.herokuapp.com/swagger-ui.html#/)

## Despliegue

para desplegar la aplicación requiere dos variables de entorno la cuales conectan con la base de datos H2 y tener Java 8+ (OpenJDK / OracleJDK)

-Duser.base=sa
-Dnombre.base=testdb

el comando para desplegar en local seria:

mvnw spring-boot:run -Duser.base=sa -Dnombre.base=testdb

## Funcionalidad

1. Información satélites
	https://prueba-rebel.herokuapp.com/satellites
 
- listar 
- Agregar
- Modificar
- Eliminar
	
2. Codificar información.
	https://prueba-rebel.herokuapp.com/topsecret
	
- Obtiene la ubicación de la nave y el mensaje que emite.
	
3. Codificar información por partes.
	https://prueba-rebel.herokuapp.com/topsecret_split
	
- Obtiene la ubicación de la nave y el mensaje que emite en diferentes mensajes.
