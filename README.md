# Cruz del Sur - Backend

Backend del sistema **Cruz del Sur**, desarrollado con **Spring Boot**.

## Descripción

Este backend expone una API REST para gestionar usuarios, clientes, viajes, reservas y autenticación mediante JWT.

El sistema utiliza Spring Security, validaciones, conexión a MySQL y arquitectura por capas.

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Spring Security
- JWT
- MySQL
- Maven
- Lombok

## Funcionalidades principales

- Autenticación con JWT.
- Encriptación de contraseñas con BCrypt.
- CRUD de usuarios.
- CRUD de clientes.
- CRUD de viajes.
- CRUD de reservas.
- Validaciones con Jakarta Validation.
- Manejo global de errores.
- Integración con frontend Angular.
- Configuración CORS.

## Requisitos previos

- Java 17 o superior
- MySQL
- Maven o Maven Wrapper
- Base de datos MySQL creada

## Instalación

git clone https://github.com/marrrcelop/cruz-del-sur-backend.git
cd cruz-del-sur-backend

## Configuración de base de datos local

Crear la base de datos en MySQL:

CREATE DATABASE bdcruzdelsur;

Configurar la conexión en:

src/main/resources/application.properties

Configuración local:

spring.application.name=CruzDelSur
spring.datasource.url=jdbc:mysql://localhost:3306/bdcruzdelsur
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.port=8080

## Ejecución local

En Windows:

mvnw.cmd spring-boot:run

En Linux o macOS:

./mvnw spring-boot:run

El backend quedará disponible en:

http://localhost:8080

## Autenticación

Endpoint de login:

POST /auth/login

Body de ejemplo:

{
"username": "admin@cruzdelsur.pe",
"password": "123456"
}

Si las credenciales son correctas, el backend devuelve un token JWT.

Para consumir rutas protegidas se debe enviar el token en el header:

Authorization: Bearer TOKEN_AQUI

## Endpoints principales

## Autenticación

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/auth/login` | Iniciar sesión y obtener token JWT |

## Usuarios

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/usuarios` | Listar usuarios |
| POST | `/usuarios` | Crear usuario |
| PUT | `/usuarios/{id}` | Actualizar usuario |
| DELETE | `/usuarios/{id}` | Eliminar usuario |

## Clientes

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/clientes` | Listar clientes |
| GET | `/clientes/{id}` | Buscar cliente por ID |
| GET | `/clientes/nombre/{nombre}` | Buscar cliente por nombre |
| POST | `/clientes` | Crear cliente |
| PUT | `/clientes/{id}` | Actualizar cliente |
| DELETE | `/clientes/{id}` | Eliminar cliente |

## Viajes

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/viajes` | Listar viajes |
| GET | `/viajes/{id}` | Buscar viaje por ID |
| GET | `/viajes/precio/{precio}` | Buscar viaje por precio |
| POST | `/viajes` | Crear viaje |
| PUT | `/viajes/{id}` | Actualizar viaje |
| DELETE | `/viajes/{id}` | Eliminar viaje |

## Reservas

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/reservas` | Listar reservas |
| GET | `/reservas/{id}` | Buscar reserva por ID |
| POST | `/reservas` | Crear reserva |
| PUT | `/reservas/{id}` | Actualizar reserva |
| DELETE | `/reservas/{id}` | Eliminar reserva |

## Despliegue en la nube

Para cumplir con despliegue, el backend debe estar publicado en una plataforma cloud como:

- Railway
- Render
- Fly.io

También se debe usar una base de datos MySQL en la nube.

## Configuración recomendada para producción

En producción se recomienda usar variables de entorno:

server.port=${PORT:8080}

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false

También se recomienda mover la clave JWT a una variable de entorno:

jwt.secret=${JWT_SECRET}

## Variables de entorno sugeridas

DB_URL=jdbc:mysql://HOST:PUERTO/NOMBRE_BD
DB_USER=USUARIO_BD
DB_PASSWORD=PASSWORD_BD
JWT_SECRET=CLAVE_SECRETA_SEGURA
PORT=8080

## CORS

Para desarrollo local, el backend permite peticiones desde:

http://localhost:4200

Para producción, se debe agregar la URL pública del frontend desplegado:

https://URL-DE-TU-FRONTEND

## URL del backend desplegado

https://URL-DE-TU-BACKEND

## Frontend relacionado

Repositorio del frontend:

https://github.com/marrrcelop/cruz-del-sur-frontend-main.git

## Pruebas

Para ejecutar pruebas:

mvnw.cmd test

Nota: las pruebas pueden requerir conexión a MySQL si cargan el contexto completo de Spring.

## Estado del proyecto

El backend cuenta con API REST, autenticación JWT, validaciones, manejo de errores, servicios, repositorios y conexión con MySQL.

Para producción se recomienda reforzar la seguridad, proteger endpoints administrativos, mover credenciales a variables de entorno y desplegar el backend junto con una base de datos en la nube.