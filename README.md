# Cuevanu — API REST con Persistencia JPA

API REST desarrollada con Spring Boot para gestionar películas y directores de cine.

## Tecnologías
- Java 25
- Spring Boot 3.4.5
- JPA / Hibernate
- H2 (base de datos en memoria)

## Cómo arrancar el proyecto

1. Clona el repositorio:
   git clone <url-del-repositorio>

2. Abre el proyecto en IntelliJ IDEA

3. Ejecuta la clase principal:
   NombreprovisionaldelproyectoApplication.java

4. La API estará disponible en:
   http://localhost:8080

5. Consola H2 (para ver las tablas):
   http://localhost:8080/h2-console
   JDBC URL: jdbc:h2:mem:peliculasdb

## Endpoints principales

### Directores
- GET    /api/v1/directores
- GET    /api/v1/directores/{id}
- POST   /api/v1/directores
- PUT    /api/v1/directores/{id}
- DELETE /api/v1/directores/{id}

### Películas
- GET    /api/v1/peliculas
- GET    /api/v1/peliculas/{id}
- POST   /api/v1/peliculas
- PUT    /api/v1/peliculas/{id}
- DELETE /api/v1/peliculas/{id}
- GET    /api/v1/peliculas/buscar?titulo=
- GET    /api/v1/peliculas/director/{id}

## Autores
- Juan José Álvarez López
- Ahmed El Founti Amakhtari
