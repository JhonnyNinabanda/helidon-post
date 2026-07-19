# Backend - API REST con Helidon 4.5

## Descripción

Backend desarrollado con Java 21, Helidon SE 4.5 y PostgreSQL.

La aplicación implementa una API REST con operaciones CRUD completas para las entidades:

- Users
- Posts
- Comments
- Albums
- Photos
- Todos

La persistencia se realiza mediante PostgreSQL utilizando Helidon DbClient y HikariCP.

---

# Tecnologías Utilizadas

| Tecnología | Versión |
|------------|----------|
| Java | 21 |
| Helidon SE | 4.5.0 |
| PostgreSQL | 17 |
| Gradle | 9.x |
| DbClient | 4.5.0 |
| HikariCP | Integrado |

---

# Arquitectura

```text
com.programacion.web

├── config
│   └── DbClientProvider
│
├── model
│   ├── User
│   ├── Post
│   ├── Comment
│   ├── Album
│   ├── Photo
│   └── Todo
│
├── repository
│   ├── UserRepository
│   ├── UserRepositoryImpl
│   ├── PostRepository
│   ├── PostRepositoryImpl
│   ├── CommentRepository
│   ├── CommentRepositoryImpl
│   ├── AlbumRepository
│   ├── AlbumRepositoryImpl
│   ├── PhotoRepository
│   ├── PhotoRepositoryImpl
│   ├── TodoRepository
│   └── TodoRepositoryImpl
│
├── service
│   ├── UserService
│   ├── PostService
│   ├── CommentService
│   ├── AlbumService
│   ├── PhotoService
│   └── TodoService
│
├── handler
│   ├── UserHandler
│   ├── PostHandler
│   ├── CommentHandler
│   ├── AlbumHandler
│   ├── PhotoHandler
│   └── TodoHandler
│
├── response
│   ├── ErrorResponse
│   └── MessageResponse
│
└── util
    └── CorsUtil
```

---

# Entidades Implementadas

## User

```text
id
name
username
email
addressStreet
addressSuite
addressCity
addressZipcode
addressGeoLat
addressGeoLng
phone
website
companyName
companyCatchPhrase
companyBs
```

## Post

```text
id
userId
title
body
```

## Comment

```text
id
postId
name
email
body
```

## Album

```text
id
userId
title
```

## Photo

```text
id
albumId
title
url
thumbnailUrl
```

## Todo

```text
id
userId
title
completed
```

---

# Endpoints REST

## Users

```http
GET    /api/users
GET    /api/users/{id}
POST   /api/users
PUT    /api/users/{id}
DELETE /api/users/{id}
```

## Posts

```http
GET    /api/posts
GET    /api/posts/{id}
POST   /api/posts
PUT    /api/posts/{id}
DELETE /api/posts/{id}
```

## Comments

```http
GET    /api/comments
GET    /api/comments/{id}
POST   /api/comments
PUT    /api/comments/{id}
DELETE /api/comments/{id}
```

## Albums

```http
GET    /api/albums
GET    /api/albums/{id}
POST   /api/albums
PUT    /api/albums/{id}
DELETE /api/albums/{id}
```

## Photos

```http
GET    /api/photos
GET    /api/photos/{id}
POST   /api/photos
PUT    /api/photos/{id}
DELETE /api/photos/{id}
```

## Todos

```http
GET    /api/todos
GET    /api/todos/{id}
POST   /api/todos
PUT    /api/todos/{id}
DELETE /api/todos/{id}
```

---

# Total de Endpoints

```text
Users      = 5
Posts      = 5
Comments   = 5
Albums     = 5
Photos     = 5
Todos      = 5

Total = 30 endpoints REST
```

---

# Configuración CORS

Se habilitó CORS para permitir solicitudes desde:

```text
http://localhost:5173
```

Cabeceras configuradas:

```http
Access-Control-Allow-Origin
Access-Control-Allow-Methods
Access-Control-Allow-Headers
```

---

# Validación de Claves Foráneas

Se implementó validación de FK antes de realizar INSERT y UPDATE.

Relaciones validadas:

```text
Posts    -> userId  -> users.id
Comments -> postId  -> posts.id
Albums   -> userId  -> users.id
Photos   -> albumId -> albums.id
Todos    -> userId  -> users.id
```

Respuesta de error:

```http
400 Bad Request
```

```json
{
  "message": "userId no existe"
}
```

---

# Flujo de la Aplicación

```text
Cliente
   ↓
Helidon WebServer
   ↓
Handlers
   ↓
Services
   ↓
Repositories
   ↓
DbClient
   ↓
HikariCP
   ↓
PostgreSQL
```

---

# Ejecución

## Compilar

```bash
gradlew build
```

## Ejecutar

```bash
gradlew run
```

Servidor:

```text
http://localhost:8080
```

---

# Requisitos

- Java 21
- PostgreSQL
- Gradle

---

# Autor

**JHONNY EDUARDO NINABANDA PAMBABAY**
