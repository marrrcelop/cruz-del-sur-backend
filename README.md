# Cruz del Sur - Backend

Backend para un sistema de reservas de viajes de la empresa Cruz del Sur.

Permite gestionar usuarios, clientes, viajes, reservas y autenticación mediante JWT.

## Tecnologías

- Java 17
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- Spring Security
- JWT
- MySQL
- Maven

## Requisitos

Antes de ejecutar el proyecto necesitas tener instalado:

- Java 17 o superior
- MySQL
- Maven, o usar el wrapper incluido en el proyecto
- Una base de datos MySQL creada

## Configuración de base de datos

El proyecto usa la siguiente configuración en:

```text
src/main/resources/application.properties
spring.application.name=CruzDelSur
spring.datasource.url=jdbc:mysql://localhost:3306/bdcruzdelsur
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.port=8080
Debes crear una base de datos llamada:
CREATE DATABASE bdcruzdelsur;
Ejecución del proyecto
En Windows:
mvnw.cmd spring-boot:run
En Linux o macOS:
./mvnw spring-boot:run
El backend quedará disponible en:
http://localhost:8080
Autenticación
El sistema usa JWT.
Para iniciar sesión:
POST /auth/login
Body:
{
  "username": "correo@ejemplo.com",
  "password": "123456"
}
Si las credenciales son correctas, el backend devuelve un token JWT.
Para consumir rutas protegidas se debe enviar el token en el header:
Authorization: Bearer TOKEN_AQUI
CORS
El backend permite peticiones desde:
http://localhost:4200
Esto está pensado para un frontend en Angular.
Endpoints principales
Autenticación
Método	Ruta	Descripción
POST	/auth/login	Iniciar sesión y obtener token JWT

Usuarios
Método	Ruta	Descripción
GET	/usuarios	Listar usuarios
POST	/usuarios	Crear usuario
PUT	/usuarios/{id}	Actualizar usuario
DELETE	/usuarios/{id}	Eliminar usuario

Clientes
Método	Ruta	Descripción
GET	/clientes	Listar clientes
GET	/clientes/{id}	Buscar cliente por ID
GET	/clientes/nombre/{nombre}	Buscar cliente por nombre
POST	/clientes	Crear cliente
PUT	/clientes/{id}	Actualizar cliente
DELETE	/clientes/{id}	Eliminar cliente

Viajes
Método	Ruta	Descripción
GET	/viajes	Listar viajes
GET	/viajes/{id}	Buscar viaje por ID
GET	/viajes/edad/{edad}	Buscar viaje por precio
POST	/viajes	Crear viaje
PUT	/viajes/{id}	Actualizar viaje
DELETE	/viajes/{id}	Eliminar viaje

Nota: actualmente la ruta /viajes/edad/{edad} llama internamente a una búsqueda por precio. Se recomienda cambiarla a /viajes/precio/{precio}.
Reservas
Método	Ruta	Descripción
GET	/reservas	Listar reservas
GET	/reservas/{id}	Buscar reserva por ID
POST	/reservas	Crear reserva
PUT	/reservas/{id}	Actualizar reserva
DELETE	/reservas/{id}	Eliminar reserva

Prueba protegida
Método	Ruta	Descripción
GET	/api/saludo	Endpoint protegido con JWT

Modelos principales
Usuario
{
  "id_usuario": 1,
  "nombres": "Administrador",
  "correo": "admin@correo.com",
  "contrasena_hash": "123456",
  "rol": "Administrador"
}
La contraseña se encripta con BCrypt antes de guardarse en la base de datos.
Cliente
{
  "id_cliente": 1,
  "nombres": "Juan",
  "apellidos": "Pérez",
  "correo": "juan@correo.com",
  "documento": "12345678"
}
Viaje
{
  "id_viaje": 1,
  "origen": "Lima",
  "destino": "Arequipa",
  "fecha": "2026-07-04",
  "hora": "10:30:00",
  "tipo_servicio": "VIP",
  "precio": 80.0,
  "estado": "Disponible"
}
Reserva
{
  "id_reserva": 1,
  "cliente": {
    "id_cliente": 1
  },
  "viaje": {
    "id_viaje": 1
  },
  "numero_asiento": 12,
  "metodo_pago": "Tarjeta",
  "estado_pago": "Pagado",
  "estado_reserva": "Confirmada",
  "fecha_registro": "2026-07-04T10:30:00"
}
Seguridad
/auth/** es público.
/usuarios está público actualmente.
Las demás rutas requieren JWT.
Los roles se generan según el campo rol del usuario:Administrador se convierte en ADMIN
Cualquier otro valor se convierte en USER

Pruebas
Para ejecutar las pruebas en Windows:
mvnw.cmd test
Para ejecutar las pruebas en Linux o macOS:
./mvnw test
Importante: las pruebas actuales intentan cargar el contexto completo de Spring y requieren conexión a MySQL.
Si MySQL no está iniciado o la base de datos bdcruzdelsur no existe, las pruebas pueden fallar.
Estructura general del proyecto
src/main/java/com/reservas/CruzDelSur
├── config
│   ├── SecurityConfig.java
│   └── JwtAuthenticationFilter.java
├── controller
│   ├── AuthController.java
│   ├── ClienteController.java
│   ├── DemoController.java
│   ├── ReservaController.java
│   ├── UsuarioController.java
│   └── ViajeController.java
├── dto
│   └── AuthRequest.java
├── entity
│   ├── Cliente.java
│   ├── Reserva.java
│   ├── Usuario.java
│   └── Viajes.java
├── repository
│   ├── ClienteRepository.java
│   ├── ReservaRepository.java
│   ├── UsuarioRepository.java
│   └── ViajeRepository.java
├── service
│   ├── ClienteService.java
│   ├── JwtService.java
│   ├── ReservaService.java
│   ├── UsuarioService.java
│   └── ViajeService.java
└── CruzDelSurApplication.java
Pendientes recomendados
Crear script SQL inicial para las tablas.
Mover la clave JWT a variables de entorno.
Corregir la ruta /viajes/edad/{edad} por /viajes/precio/{precio}.
Proteger mejor los endpoints de /usuarios.
Agregar validaciones a los datos recibidos.
Agregar respuestas HTTP más claras, como 404, 400 y 401.
Crear pruebas usando una base de datos de prueba.