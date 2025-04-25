# App Matrícula

## Descripción
App Matrícula es una aplicación de gestión de matrículas desarrollada con Spring Boot. Permite administrar cursos y usuarios a través de una API RESTful, facilitando el proceso de matrícula en instituciones educativas.

## Tecnologías Utilizadas
- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- PostgreSQL
- Lombok
- MapStruct
- Swagger/OpenAPI para documentación de API
- Maven

## Requisitos Previos
- JDK 21
- PostgreSQL
- Maven

## Configuración
1. Clonar el repositorio
2. Crear una base de datos PostgreSQL llamada `bd_matricula`
3. Configurar las credenciales de la base de datos en `src/main/resources/application.properties`
4. Ejecutar el proyecto con Maven: `mvn spring-boot:run`

## Estructura del Proyecto
```
appmatricula/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── edu/cibertec/appmatricula/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── exception/
│   │   │       ├── mapper/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── AppmatriculaApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## Características Principales
- Gestión de cursos (crear, leer, actualizar, eliminar)
- Gestión de usuarios (crear, leer, actualizar, eliminar)
- Activación/desactivación de cursos y usuarios
- Validación de datos de entrada
- Manejo de excepciones personalizado
- Documentación de API con Swagger

## API Endpoints

### Cursos
- `GET /api/cursos`: Obtener todos los cursos
- `GET /api/cursos/active`: Obtener cursos activos
- `GET /api/cursos/{id}`: Obtener curso por ID
- `GET /api/cursos/code/{code}`: Obtener curso por código
- `POST /api/cursos`: Crear un nuevo curso
- `PUT /api/cursos/{id}`: Actualizar curso
- `DELETE /api/cursos/{id}`: Eliminar curso
- `PATCH /api/cursos/{id}/toggle-active`: Cambiar estado activo del curso

### Usuarios
- `GET /api/usuarios`: Obtener todos los usuarios
- `GET /api/usuarios/{id}`: Obtener usuario por ID
- `GET /api/usuarios/username/{username}`: Obtener usuario por nombre de usuario
- `POST /api/usuarios`: Crear un nuevo usuario
- `PUT /api/usuarios/{id}`: Actualizar usuario
- `DELETE /api/usuarios/{id}`: Eliminar usuario
- `PATCH /api/usuarios/{id}/toggle-active`: Cambiar estado activo del usuario

## Documentación de la API
La documentación de la API está disponible en:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/api-docs`

## Base de Datos
La aplicación utiliza PostgreSQL como base de datos. El esquema incluye las siguientes tablas:
- `cursos`: Almacena información sobre los cursos
- `usuarios`: Almacena información sobre los usuarios

## Contribución
Para contribuir al proyecto:
1. Hacer fork del repositorio
2. Crear una rama para la nueva funcionalidad
3. Enviar un pull request

## Licencia
Este proyecto está bajo la licencia MIT.