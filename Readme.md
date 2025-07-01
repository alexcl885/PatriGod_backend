# 🌍 PatriGod Backend

Este proyecto contiene el **backend** de la aplicación web **PatriGod**, responsable de administrar todos los datos relacionados con las Ciudades Patrimonio de la Humanidad en España. El frontend consumirá estos datos mediante una API REST para su visualización y gestión.

---

## 📚 Índice

1. [Creacion del proyecto](#creacion-del-proyecto)
2. [Implementacion de Docker](#creacion-del-proyecto)
4. [Seguridad con Variables de Entorno](#seguridad-con-variables-de-entorno)
5. [Creacion de Carpetas](#creacion-de-carpetas)
6. [Diagrama UML de la base de datos](#diagrama-uml-de-la-base-de-datos)
7. [Rutas de la API REST](#rutas-de-la-api-rest)
8. [Programando proyecto](#programando-proyecto)
    - [Base del proyecto](#base-del-proyecto)
    - [Subclases clase Articulo](#subclases-monumento-evento-comida)
    - [Ranking](#ranking)
    - [Email](#email)
    - [Spring Security + JWT](#spring-security--jwt) 
    - [Swagger por OpenApi](#documentación-automática-con-swagger-y-springdoc-openapi)
6. [Autor](#autor)


---

## Creacion del proyecto 

Mediante una extension de Visual Studio Code llamada **Spring Initializr Java Support** he podido crear este proyecto. 

### 🧩 Dependencias Iniciales

Durante la creación del proyecto, se añadieron las siguientes dependencias fundamentales para comenzar con el desarrollo del backend:

- 🌐 **Spring Web** – Para construir servicios RESTful y manejar solicitudes HTTP.
- 🗃️ **Spring Data JPA** – Para gestionar la persistencia de datos con JPA y Hibernate.
- ♻️ **Spring Boot DevTools** – Para mejorar la experiencia de desarrollo con recarga automática.
- ✍️ **Lombok** – Para reducir el *boilerplate* en clases Java (getters, setters, etc.).
- 🐬 **MySQL Driver** – Para la conexión con una base de datos MySQL.


---

##  Implementacion de Docker

Para facilitar el despliegue y la gestión de servicios, he implementado **Docker** 🐳 en el proyecto. En la raíz del repositorio he creado un archivo llamado `docker-compose.yml`, el cual contendrá la configuración necesaria para levantar los contenedores requeridos.

En este caso, se utilizaré **Adminer** como interfaz gráfica para gestionar la base de datos de manera sencilla y visual.

---

### Seguridad con Variables de Entorno

Con el objetivo de mejorar la seguridad, se utilizará un archivo `.env` para almacenar variables sensibles, como:

- Usuario y contraseña de la base de datos
- Nombre del contenedor
- Nombre de la base de datos

Esto evita exponer información crítica directamente en el código o en el archivo `docker-compose.yml`.
Por lo que añado en el .gitignore el .env y para dejar un rastro de la información aqui estaría los datos
del archivo.

```txt
MYSQL_ROOT_PASSWORD=copado
MYSQL_USERNAME=root
MYSQL_PORT=33306
MYSQL_HOST=localhost
MYSQL_DATABASE=patrigod
ADMINER_PORT=8181
SERVICE_PORT=8080
```

---

### 📂 Archivos relevantes

- `docker-compose.yml` → Define los servicios Docker (como MySQL y Adminer).
- `.env` → Almacena las variables de entorno (seguras y reutilizables).
- `scripts/initdb.sql` → Inicializa el contenedor con ese .sql.

---

### ✅ Ventajas de esta implementación

- 🔐 Mayor seguridad mediante variables de entorno.
- ⚙️ Configuración rápida del entorno con un solo comando.
- 📊 Interfaz visual (Adminer) para gestionar y consultar la base de datos fácilmente.

---
## Diagrama UML de la base de datos

```plantuml
@startuml
skinparam classAttributeIconSize 0
skinparam linetype ortho
skinparam class {
    BackgroundColor White
    ArrowColor Black
    BorderColor Black
}

class Ciudad {
  -id : bigint
  -nombre : varchar
  -comunidad_autonoma : varchar
  -provincia : varchar
  -descripcion : text
  -imagen_principal : varchar
  -fecha_patrimonio : date
  -latitud : decimal
  -longitud : decimal
  -puntuacion : double
}

abstract class Articulo {
  -id : bigint
  -nombre : varchar
  -descripcion : text
  -ciudad_id : bigint
}

class Comida {
  -id : bigint
  -apto_vegetarianos : boolean
  -calorias_aprox : int
  -origen : varchar
  -tipo : varchar
  -acompañamientos_recomendados : varchar
  -curiosidades : text
  -imagen : varchar
  -ingredientes_principales : varchar
  -momento_consumo : varchar
}

class Evento {
  -id : bigint
  -duracion : int
  -fecha : date
  -hora_evento : time
  -informacion_evento : varchar
  -imagen : varchar
  -lugar : varchar
  -organizador : varchar
  -precio : varchar
  -tipo_evento : varchar
  -web_oficial : varchar
}

class Monumento {
  -id : bigint
  -altura : double
  -curiosidades : varchar
  -declaracion_unesco : varchar
  -epoca_construccion : varchar
  -estilo_arquitectonico : varchar
  -horario_visitas : varchar
  -imagen : varchar
  -materiales_principales : varchar
  -precio_entrada : varchar
  -ubicacion : varchar
}

class Puntuacion {
  -id : bigint
  -puntuacion : float
  -user_id : bigint
  -articulo_id : bigint
}

class Usuario {
  -id : bigint
  -username : varchar
  -email : varchar
  -password : varchar
  -tipo : enum
  -activo : boolean
  -suscrito : boolean
  -fecha_creacion : datetime
}

' Relaciones
Ciudad "1" -- "0..*" Articulo : contiene
Articulo <|-- Comida
Articulo <|-- Evento
Articulo <|-- Monumento

Articulo "1" -- "0..*" Puntuacion : tiene
Usuario "1" -- "0..*" Puntuacion : da
@enduml
```
## Creacion de Carpetas

Voy a programar este proyecto mediante una estructura por capas:
```txt
📦 com.patrigod.patrigod
│
├── 📁 componentes
│   └── Clases auxiliares y reutilizables.
│
├── 📁 configuraciones
│   └── Configuración de seguridad, CORS, Swagger, BBDD, etc.
│
├── 📁 controladores
│   └── Expone las rutas HTTP (API REST).
│       Anotados con @RestController.
│
├── 📁 modelos
│   └── Clases @Entity que representan las tablas de la base de datos.
│
├── 📁 repos
│   └── Interfaces que extienden JpaRepository para acceder a datos.
│       Spring se encarga de implementarlas.
│
├── 📁 servicios
│   └── Lógica de negocio de la aplicación.
│       Clases anotadas con @Service.
│
└── 📄 Application.java
    └── Clase principal con el método main.
        Anotada con @SpringBootApplication.
```

## 🧠 ¿Para qué sirve cada carpeta?

| Carpeta         | Propósito principal                                                                 |
|-----------------|--------------------------------------------------------------------------------------|
| `componentes`   | Clases reutilizables como inicializadores, mappers, utilidades, validadores, etc.  |
| `configuraciones` | Configuración del proyecto (seguridad, BBDD, propiedades globales, CORS, Swagger). |
| `controladores` | Reciben las peticiones HTTP y devuelven respuestas. Actúan como capa REST/API.     |
| `modelos`       | Representan las entidades de base de datos. Se usan con JPA/Hibernate.             |
| `repos`         | Interfaz entre la app y la base de datos. Consultas automáticas con Spring Data.   |
| `servicios`     | Contienen la lógica de negocio (qué hacer con los datos). 


---

## Rutas de la API REST

A continuación se detallan todas las rutas (endpoints) expuestas por el backend, indicando el método HTTP, la URL, el propósito, los roles autorizados y ejemplos de uso.  
Esta tabla sirve como referencia rápida para desarrolladores y para la integración con el frontend.

---

## 🏙️ Ciudad (`/api/city`)

| Método | Ruta                       | Descripción                                         | Rol autorizado         |
|--------|----------------------------|-----------------------------------------------------|------------------------|
| GET    | `/api/city`              | Listar todas las ciudades                           | Público                |
| GET    | `/api/city/{id}`         | Obtener detalles de una city por ID               | Público                |
| POST   | `/api/city`              | Crear nueva city                                  | ADMINISTRADOR          |
| PUT    | `/api/city/{id}`         | Actualizar city                                   | ADMINISTRADOR          |
| DELETE | `/api/city/{id}`         | Eliminar city                                     | ADMINISTRADOR          |
| GET    | `/api/city/rankMonumento`| Ranking ciudades por media de monumentos            | Público                |
| GET    | `/api/city/rankComida`   | Ranking ciudades por media de comidas               | Público                |
| GET    | `/api/city/rankEvento`   | Ranking ciudades por media de eventos               | Público                |

---

## 🏛️ Monumento (`/api/monumento`)

| Método | Ruta                         | Descripción                                 | Rol autorizado         |
|--------|------------------------------|---------------------------------------------|------------------------|
| GET    | `/api/monumento`             | Listar todos los monumentos                 | Público                |
| GET    | `/api/monumento/{id}`        | Obtener detalles de un monumento            | Público                |
| POST   | `/api/monumento`             | Crear nuevo monumento                       | ADMINISTRADOR          |
| PUT    | `/api/monumento/{id}`        | Actualizar monumento                        | ADMINISTRADOR          |
| DELETE | `/api/monumento/{id}`        | Eliminar monumento                          | ADMINISTRADOR          |

---

## 🍽️ Comida (`/api/comida`)

| Método | Ruta                      | Descripción                                 | Rol autorizado         |
|--------|---------------------------|---------------------------------------------|------------------------|
| GET    | `/api/comida`             | Listar todas las comidas                    | Público                |
| GET    | `/api/comida/{id}`        | Obtener detalles de una comida              | Público                |
| POST   | `/api/comida`             | Crear nueva comida                          | ADMINISTRADOR          |
| PUT    | `/api/comida/{id}`        | Actualizar comida                           | ADMINISTRADOR          |
| DELETE | `/api/comida/{id}`        | Eliminar comida                             | ADMINISTRADOR          |

---

## 🎭 Evento (`/api/evento`)

| Método | Ruta                      | Descripción                                 | Rol autorizado         |
|--------|---------------------------|---------------------------------------------|------------------------|
| GET    | `/api/evento`             | Listar todos los eventos                    | Público                |
| GET    | `/api/evento/{id}`        | Obtener detalles de un evento               | Público                |
| POST   | `/api/evento`             | Crear nuevo evento                          | ADMINISTRADOR          |
| PUT    | `/api/evento/{id}`        | Actualizar evento                           | ADMINISTRADOR          |
| DELETE | `/api/evento/{id}`        | Eliminar evento                             | ADMINISTRADOR          |

---

## ⭐ Puntuación (`/api/puntuacion`)

| Método | Ruta                          | Descripción                                 | Rol autorizado         |
|--------|-------------------------------|---------------------------------------------|------------------------|
| GET    | `/api/puntuacion`             | Listar todas las puntuaciones               | Autenticado            |
| POST   | `/api/puntuacion`             | Crear nueva puntuación                      | Autenticado            |
| PUT    | `/api/puntuacion/{id}`        | Actualizar puntuación                       | Autenticado            |
| DELETE | `/api/puntuacion/{id}`        | Eliminar puntuación                         | Autenticado            |

---

## 👤 Usuario (`/api/usuario`)

| Método | Ruta                      | Descripción                                 | Rol autorizado         |
|--------|---------------------------|---------------------------------------------|------------------------|
| GET    | `/api/usuario`            | Obtener usuario autenticado                 | Autenticado            |
| POST   | `/api/usuario/register`   | Registrar nuevo usuario                     | Público                |
| GET    | `/api/usuario/{id}`       | Obtener usuario por ID                      | ADMINISTRADOR          |
| PUT    | `/api/usuario/{id}`       | Actualizar usuario                          | ADMINISTRADOR          |
| DELETE | `/api/usuario/{id}`       | Eliminar usuario                            | ADMINISTRADOR          |

---

## 🔐 Autenticación (`/api/auth`)

| Método | Ruta                      | Descripción                                 | Rol autorizado         |
|--------|---------------------------|---------------------------------------------|------------------------|
| POST   | `/api/auth/login`         | Autenticación y obtención de JWT            | Público                |
| POST   | `/api/auth/refresh`       | Refrescar token JWT                         | Público                |

---

## ✉️ Email (`/api/email`)

| Método | Ruta                      | Descripción                                 | Rol autorizado         |
|--------|---------------------------|---------------------------------------------|------------------------|
| POST   | `/api/email/send`         | Enviar email (test o notificación)          | Público / Autenticado  |

---

## 🤖 Chat IA Ollama (`/api/ollama`)

| Método | Ruta                      | Descripción                                 | Rol autorizado         |
|--------|---------------------------|---------------------------------------------|------------------------|
| POST   | `/api/ollama/chat`        | Chatbot IA sobre ciudades patrimonio         | Autenticado            |

---

## 🛡️ Seguridad y Roles

- **Público:** Acceso sin autenticación (por ejemplo, registro, login, consulta de ciudades, rankings).
- **Autenticado:** Requiere JWT válido (usuarios registrados).
- **ADMINISTRADOR:** Requiere JWT con rol de administrador (gestión de entidades, usuarios, etc.).

El backend utiliza JWT para la autenticación. El token debe enviarse en el header `Authorization: Bearer <token>` en las rutas protegidas.

---

## 📑 Ejemplo de petición autenticada

```http
GET /api/city HTTP/1.1
Host: localhost:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6...
```

---

## 📝 Notas adicionales

- Todas las rutas devuelven respuestas en formato JSON.
- Los endpoints de administración requieren rol `ADMINISTRADOR`.
- Los endpoints de puntuación y chat requieren usuario autenticado.
- El endpoint de chat IA solo responde sobre ciudades patrimonio de la humanidad en España.
- Para probar la API de forma interactiva, accede a `/swagger-ui/index.html`.

---

## Programando proyecto

A partir de crear todas las carpetas, ya empieza lo bueno ya que empiezo a programar poco a poco el backend. 

He pensado en realizarlo poco a poco entonces he pensado hacer entidad por entidad con todas sus cosas para que se muestren por lo menos todas 
los datos de cada entidad y los pasos serían los siguientes:
### Base del proyecto

#### ✅ Paso 1: Crear el modelo

📁 Carpeta: `/modelos`

Creo el modelo de mi entidad (por ejemplo, `Ciudad.java`), que representa una tabla en la base de datos. Aquí se definen los atributos y se anotan con `@Entity`, `@Id`, `@Column`, etc.

---

#### ✅ Paso 2: Crear el repositorio

📁 Carpeta: `/repos`

Creo el repositorio de la entidad con el nombre `RepoEntidad`, por ejemplo `RepoCiudad`. Esta interfaz extiende `JpaRepository` y me permite acceder a los datos sin escribir consultas SQL manualmente.

---

#### ✅ Paso 3: Crear el servicio

📁 Carpeta: `/servicios`

Aquí creo el servicio de la entidad con el nombre `ServiEntidad`, por ejemplo `ServiCiudad`. Esta clase contiene la lógica de negocio, se comunica con el repositorio y será usada por el controlador.

---

#### ✅ Paso 4: Crear el controlador

📁 Carpeta: `/controladores`

Finalmente, creo el controlador con el nombre `EntidadController`, por ejemplo `CiudadController`. Este controlador define las rutas REST (`GET`, `POST`, `PUT`, `DELETE`) que permiten interactuar con la entidad desde el exterior.

---

#### 🔁 Y así con todas las entidades...

Repetiré este proceso con cada entidad de mi proyecto (mo del todo como Articulo y sus subclases ya que las he programado de otra manera que se ve abajo), asegurándome de que **cada una tenga su modelo, repositorio, servicio y controlador**.

De esta manera, el backend estará bien estructurado, escalable y fácil de mantener.

### Clase abstracta articulo y subclases: Monumento,Evento y comida.

La entidad `Articulo` define los atributos comunes a todos los elementos calificables (monumentos, eventos y comidas). Cada subtipo hereda de Articulo y solo declara los campos específicos de su propia tabla, compartiendo la clave primaria id.

#### `Articulo` (Entidad Padre)
```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Monumento.class, name = "monumento"),
    @JsonSubTypes.Type(value = Comida.class,    name = "comida"),
    @JsonSubTypes.Type(value = Evento.class,    name = "evento")
})
public abstract class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ciudad_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_articulo_ciudad"))
    private Ciudad city;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;
}
```

- **@Entity**: Define la clase como entidad JPA.
- **@Inheritance(JOINED)**: Crea una tabla principal `articulo` y tablas hijas que comparten la misma PK.
- **@JsonTypeInfo / @JsonSubTypes**: Configura Jackson para incluir un campo `type` en JSON, indicando la subclase concreta.

#### Subclases (`Monumento`, `Evento`, `Comida`)
Cada subclase **hereda** de `Articulo` y sólo declara sus campos específicos:

```java
@Entity
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("monumento")
@Data @NoArgsConstructor
public class Monumento extends Articulo {
    @Column(length = 255)
    private String imagen;
}
```

```java
@Entity
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("comida")
@Data @NoArgsConstructor
public class Comida extends Articulo {
    @Column(length = 255)
    private String imagen;
}
```

```java
@Entity
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("evento")
@Data @NoArgsConstructor
public class Evento extends Articulo {
    private LocalDate fecha;
}
```

- **@PrimaryKeyJoinColumn(name = "id")**: Indica que la PK de la entidad hija es exactamente la misma que la PK de `Articulo`.
- **@JsonTypeName**: Nombre que se usa en el JSON para este subtipo.


####  Esquema de Base de Datos

- **Tabla `articulo`** (padre): contiene `id`, `ciudad_id`, `nombre`, `descripcion`.
- **Tablas hijas (`monumento`, `evento`, `comida`)**: PK `id` como FK a `articulo.id`, más sus columnas propias (`imagen`, `fecha`, etc.).
- **Herencia `JOINED`** en JPA mapea directamente esta estructura.


---


### Ranking

#### Ranking por Ciudades

En este punto vamos a tener que realizar DTO.

**¿Que es una DTO?**
Una DTO es una objeto especialmente diseñado para representar el resultado exacto de la consulta que querramos obtener.

Entonces para rankear por ciudades segun sus articulos, he tenido que 
crear una DTO:

En este caso he utilizado una interfaz en vez de una clase. ¿Por que?
He realizado una interfaz ya que solo me interesa leer datos, por lo que 
realizo una interfaz para que salga mas ligera mi aplicación.

En esta tabla se ve algunas de las diferencias entre realizar una interfaz o una clase con sus atributos y todo.

#### 🆚 Diferencia entre un DTO de clase y un DTO de interfaz

| Característica                              | DTO clase (`@Data`, `class`)     | DTO interfaz (`interface`)       |
|---------------------------------------------|----------------------------------|----------------------------------|
| Se puede modificar (`setters`)              | ✅ Sí                             | ❌ No (solo lectura)             |
| Control total de la lógica                  | ✅ Puedes usar constructores, lógica | ❌ Solo lectura directa      |
| Útil para APIs o lógica de negocio          | ✅ Muy útil                       | ❌ Solo para leer datos de consulta |
| Más ligero para proyecciones simples        | 🔸 Algo más pesado                | ✅ Muy ligero                    |

Por lo que creo la siguiente interfaz DTO:

```java
public interface RankingCiudadDTO {
    Integer getPosicion();
    Long getCiudad_id();
    String getCiudad_nombre();
    Double getPuntuacion_promedio();
}
```

#### Repo: RepoCiudad: `findRankingCiudadesByPuntuacionPromedio`

Este método realiza una consulta SQL compleja que obtiene el **ranking de las ciudades Patrimonio de la Humanidad** basado en su **puntuación promedio**. Se utiliza la función `ROW_NUMBER()` para asignar una posición a cada city según su puntuación promedio, de mayor a menor.

##### Descripción de la Consulta

La consulta se divide en dos partes:

1. **Subconsulta Principal**: Realiza la agregación de la puntuación promedio de las ciudades, tomando en cuenta los artículos asociados a cada city y su respectiva puntuación. En esta subconsulta se hace el siguiente procesamiento:
   - Se obtiene el ID de la city (`ciudad_id`) y su nombre (`ciudad_nombre`).
   - Se calcula la puntuación promedio (`puntuacion_promedio`) de los artículos asociados a cada city.
   - Se utilizan varias uniones (JOIN) entre las tablas `city`, `articulo`, `puntuacion`, `comida`, `evento` y `monumento` para asociar las puntuaciones de cada categoría (comida, evento, monumento).
   - Se agrupan los resultados por el ID y nombre de la city.

2. **Aplicación de `ROW_NUMBER()`**: Utiliza la función de ventana `ROW_NUMBER()` para asignar una posición a cada city según su puntuación promedio, ordenando los resultados de manera descendente (de mayor a menor puntuación).

```sql
SELECT ROW_NUMBER() OVER (ORDER BY puntuacion_promedio DESC) AS posicion, 
       ciudad_id, ciudad_nombre, puntuacion_promedio
FROM (
    SELECT c.id AS ciudad_id, 
           c.nombre AS ciudad_nombre, 
           AVG(p.puntuacion) AS puntuacion_promedio 
    FROM city c
    JOIN articulo a ON a.ciudad_id = c.id
    JOIN puntuacion p ON p.articulo_id = a.id
    LEFT JOIN comida co ON co.id = a.id
    LEFT JOIN evento e ON e.id = a.id
    LEFT JOIN monumento m ON m.id = a.id
    GROUP BY c.id, c.nombre
) AS ranking
ORDER BY puntuacion_promedio DESC
```

#### Servicio: ServiCiudad: `obtenerRankingDeCiudades`

Este método realiza un ranking de las **ciudades Patrimonio de la Humanidad**, basándose en la **puntuación promedio** de sus Eventos, Monumentos y Comidas.

##### Pasos del Método

1. **Consulta SQL**: Se realiza una consulta SQL para obtener el ranking de las ciudades con su puntuación promedio. Esta información se almacena en una lista de objetos `RankingCiudadDTO`.

2. **Lista Vacía**: Se crea una lista vacía llamada `ciudadesCompletas` donde se almacenarán las ciudades completas con su puntuación.

3. **Obtención de IDs de Ciudades**: Se obtiene una lista de los IDs de las ciudades presentes en el ranking, que luego se utilizan para consultar las ciudades completas.

4. **Consulta de Ciudades por ID**: Con los IDs obtenidos, se consultan todas las ciudades correspondientes en la base de datos.

5. **Asociación de Puntuación**: Se asocia la puntuación promedio de cada city (proveniente del DTO) al objeto `Ciudad` y se agrega a la lista final `ciudadesCompletas`.

6. **Devolver Lista**: Finalmente, se devuelve la lista de **ciudades**, ahora con su puntuación promedio.

```java
public List<Ciudad> obtenerRankingDeCiudades() {
    List<RankingCiudadDTO> ranking = repoCiudad.findRankingCiudadesByPuntuacionPromedio();
    List<Ciudad> ciudadesCompletas = new ArrayList<>();
    List<Long> ciudadIds = ranking.stream()
                                  .map(RankingCiudadDTO::getCiudad_id)
                                  .collect(Collectors.toList());
    
    List<Ciudad> ciudades = repoCiudad.findAllById(ciudadIds); 
    for (RankingCiudadDTO dto : ranking) {
        Optional<Ciudad> ciudadOpt = ciudades.stream()
                                             .filter(city -> city.getId().equals(dto.getCiudad_id()))
                                             .findFirst();
        if (ciudadOpt.isPresent()) {
            Ciudad city = ciudadOpt.get();
            city.setPuntuacion(dto.getPuntuacion_promedio()); 
            ciudadesCompletas.add(city); 
        }
    }
    return ciudadesCompletas; 
}
```
### 🏆 Ranking por Artículos

En este apartado se ha desarrollado una funcionalidad para generar un **ranking de ciudades** basado en la **puntuación media de sus artículos**, dividiéndolos en tres categorías:

- 🏛️ Monumentos  
- 🍽️ Comidas  
- 🎭 Eventos  

El objetivo es mostrar de forma ordenada las ciudades con mejor valoración en cada tipo de artículo, facilitando así una visualización clara del patrimonio más destacado por parte de los usuarios.

---

## 🔧 ¿Cómo está implementado?

### 📁  Repositorio `RepoCiudad`

Se han definido tres consultas nativas (`@Query`) utilizando SQL con la función `ROW_NUMBER()` para obtener la posición de cada city en el ranking.

#### 🏛️ Ranking por Monumentos

```java
@Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY AVG(COALESCE(p.puntuacion, 0)) DESC) AS posicion, " +
    "c.id AS ciudad_id, c.nombre AS ciudad_nombre, AVG(COALESCE(p.puntuacion, 0)) AS puntuacion_media " +
    "FROM city c " +
    "JOIN articulo a ON c.id = a.ciudad_id " +
    "JOIN monumento m ON a.id = m.id " +
    "JOIN puntuacion p ON a.id = p.articulo_id " +
    "GROUP BY c.id " +
    "ORDER BY puntuacion_media DESC",
    nativeQuery = true)
List<RankingArticuloDTO> findRankingByMonumento();
```
#### 🏛️ Ranking por Comidas
```java
@Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY AVG(COALESCE(p.puntuacion, 0)) DESC) AS posicion, " +
    "c.id AS ciudad_id, c.nombre AS ciudad_nombre, AVG(COALESCE(p.puntuacion, 0)) AS puntuacion_media " +
    "FROM city c " +
    "JOIN articulo a ON c.id = a.ciudad_id " +
    "JOIN comida co ON a.id = co.id " +
    "JOIN puntuacion p ON a.id = p.articulo_id " +
    "GROUP BY c.id " +
    "ORDER BY puntuacion_media DESC",
    nativeQuery = true)
List<RankingArticuloDTO> findRankingByComida();
```
#### 🎭 Ranking por Eventos
```java
@Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY AVG(COALESCE(p.puntuacion, 0)) DESC) AS posicion, " +
    "c.id AS ciudad_id, c.nombre AS ciudad_nombre, AVG(COALESCE(p.puntuacion, 0)) AS puntuacion_media " +
    "FROM city c " +
    "JOIN articulo a ON c.id = a.ciudad_id " +
    "JOIN evento e ON a.id = e.id " +
    "JOIN puntuacion p ON a.id = p.articulo_id " +
    "GROUP BY c.id " +
    "ORDER BY puntuacion_media DESC",
    nativeQuery = true)
List<RankingArticuloDTO> findRankingByEvento();
```
### 💼  Servicio ServiCiudad

Aquí se hace la llamada a los métodos del repositorio para obtener las listas ya ordenadas:
```java
public List<RankingArticuloDTO> findRankingByMonumento() {
    return repoCiudad.findRankingByMonumento();
}

public List<RankingArticuloDTO> findRankingByComida() {
    return repoCiudad.findRankingByComida();
}

public List<RankingArticuloDTO> findRankignByEvento() {
    return repoCiudad.findRankingByEvento();
}
```

### 🌐 3. Controlador CiudadController

En el controlador, se exponen estas funcionalidades como endpoints GET, lo que permite al frontend o a cualquier cliente consumir esta información a través de la API.
```java
/**
 * 
 * @return lista ciudades(DTO) segun la media de monumentos de cada city
 */
    @GetMapping("/rankMonumento")
    public List<RankingArticuloDTO> rankingMonumento() {
        return serviCiudad.findRankingByMonumento();
    }
/**
 * 
 * @return lista ciudades(DTO) segun la media de comidas de cada city
 */
    @GetMapping("/rankComida")
    public List<RankingArticuloDTO> rankingComida() {
        return serviCiudad.findRankingByComida();
    }

/**
 * 
 * @return lista ciudades(DTO) segun la media de eventos de cada city
 */
    @GetMapping("/rankEvento")
    public List<RankingArticuloDTO> rankingEvento() {
        return serviCiudad.findRankignByEvento();
    }
```
---
### Email

Este proyecto integra un sistema de envío de correos electrónicos usando [Resend.com](https://resend.com), una plataforma moderna para gestionar emails transaccionales y notificaciones en aplicaciones web.

---

#### ✉️ Objetivo

El objetivo principal es **enviar correos a los usuarios** para mantenerlos informados sobre las **últimas novedades, actualizaciones y eventos importantes** de la aplicación.

---

#### 🛠️ Funcionalidades

- Envío automático de correo de bienvenida al registrarse.
- Suscripción a notificaciones por correo.
- Envío periódico o manual de actualizaciones relevantes de la plataforma.
- Futuras mejoras para gestionar preferencias de notificación de usuarios.

---

#### 📧 ¿Por qué Resend.com?

Resend.com fue elegido por:

- Fácil integración en proyectos modernos.
- API sencilla y bien documentada.
- Alta fiabilidad y rapidez en el envío de correos.

Actualmente, estamos en fase de testeo utilizando la cuenta gratuita de Resend.com, lo que implica que **solo es posible enviar correos a la dirección `alexcopado2005@gmail.com`**. 

Para enviar emails a otros destinatarios será necesario verificar un dominio propio y actualizar el plan de Resend.com. Esta ampliación está prevista para futuras versiones del proyecto, permitiendo así enviar correos a todos los usuarios registrados.

---

#### ⚙️ Configuración en Spring Boot

En el proyecto se define un bean para la integración con Resend usando la clave API almacenada en `application.properties`:

```java
package com.patrigod.patrigod.configuraciones;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.resend.Resend;

@Configuration
public class ResendConfig {

    @Value("${resend.api.key}")
    private String resendApiKey;

    @Bean
    public Resend resendClient() {
        return new Resend(resendApiKey);
    }
}
```
#### Configuracion application.properties
```properties
resend.api.key=re_WUevzQu6_22MWhkPCTjYXtgLdFNBmTwcr
```

#### Envio email al registrar un usuario
Cuando un usuario se registra además de realizar una peticion POST para poder registrar al usuario también lo que se hara sera enviar un correo para que vea el usuario que se ha aplicado bien su correo electronico:

```java
public boolean sendEmail(String to, String subject, String htmlContent) {
        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Patrigod <onboarding@resend.dev>")
                .to(to)
                .subject(subject)
                .html(htmlContent)
                .build();

        try {
            CreateEmailResponse response = resend.emails().send(params);
            System.out.println("Email enviado con ID: " + response.getId());
            return response.getId() != null;
        } catch (ResendException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendWelcomeEmail(String to) {
        String subject = "¡Bienvenido a Patrigod!";
        String htmlContent = getBaseTemplate("""
            <h2>Gracias por registrarte 🎉</h2>
            <p>Estamos encantados de tenerte con nosotros. A partir de ahora estarás al tanto de todas las novedades de la aplicación.</p>
        """);
        return sendEmail(to, subject, htmlContent);
    }
```

---

### Spring Security + JWT

He implementado **Spring Security** en mi backend y he añadido seguridad a mi aplicación mediante **JWT (JSON Web Tokens)**.

---

### 🔐 ¿Cómo lo he implementado?

Para manejar la autenticación y validación del token JWT, he creado dos clases dentro del paquete `componentes`:

- `JwtAuthenticationFilter.java`
- `JwtUtil.java`

---

### 🧱 `JwtAuthenticationFilter.java`

Este filtro se ejecuta **una vez por cada petición** (gracias a que extiende de `OncePerRequestFilter`) y se encarga de:

1. Extraer el token JWT del encabezado `Authorization`.
2. Validar el token.
3. Autenticar al usuario si el token es válido.

```java
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired 
    ServiDetalleUsuario servicioDetalleUsuario;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull FilterChain chain) throws ServletException, IOException {
            
        final String authHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwtToken);
            } catch (ExpiredJwtException e) {
                System.out.println("Token expirado: " + e.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = servicioDetalleUsuario.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwtToken, userDetails)) {
                var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
```
### 🔧 `JwtUtil.java`
Esta clase se encarga de generar y validar los tokens JWT, así como extraer datos de ellos como el nombre de usuario o el rol.

```java
@Component
public class JwtUtil {

    private static final String SECRET_KEY = "miClaveSuperSecretaQueNadieVaAAdivinarJamas";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 horas

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().iterator().next().getAuthority());
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}

```
---

### ⚙️ Configuración de Seguridad

Además, tengo una carpeta llamada `configuraciones` que contiene las siguientes clases:

- `ConfiguracionSeguridad.java`
- `WebConfig.java`

---

### 🔐 `ConfiguracionSeguridad.java`

Esta clase define toda la configuración relacionada con **Spring Security**, incluyendo la autenticación, el uso del filtro JWT y la política de sesiones.

#### ✅ Funcionalidades principales:

- Se desactiva CSRF.
- El acceso a las rutas esta protegido por el rol que se tenga.
- Se configura la sesión como **stateless**.
- Se integra el filtro `JwtAuthenticationFilter` antes del filtro estándar `UsernamePasswordAuthenticationFilter`.
- Se define un `AuthenticationProvider` que usa un `UserDetailsService` personalizado (`ServiDetalleUsuario`).
- Se usa `BCryptPasswordEncoder` para codificar contraseñas.

```java
@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .authorizeHttpRequests(auth -> auth
                        // Admins y usuarios autenticados
                        .requestMatchers(
                                "/api/ollama/chat/**",
                                "/api/puntuacion/**")
                        .authenticated()

                        // rutas públicas
                        .requestMatchers(
                                "/api/auth/**",
                                "/api/auth/*/**",
                                "/api/city/**",
                                "/api/usuario/**",
                                "/api/email/**",
                                "/swagger-ui/**",
                                "v3/api-docs/**")
                        .permitAll()
                        // solo administradores
                        .requestMatchers(
                                "/api/comida/**",
                                "/api/evento/**",
                                "/api/monumento/**",
                                "/api/admin/**")
                        .hasRole("ADMINISTRADOR")
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider()) 
            .cors(Customizer.withDefaults())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new ServiDetalleUsuario();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```
### 🌐 `WebConfig.java` – Configuración CORS
Este archivo configura los CORS para permitir que el frontend (por ejemplo, en Vite ejecutándose en http://localhost:5173) pueda comunicarse con el backend sin errores de política de origen cruzado.
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Ruta del backend
                .allowedOrigins("http://localhost:5173") // Frontend en Vite
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

---

### 👤 Registro de usuarios y usuario autenticado

Para registrar un nuevo usuario y obtener el usuario actualmente autenticado, he creado un controlador llamado `UsuarioController.java`.

---

#### 📝 Registro de un nuevo usuario

Ruta: `POST /register`

Este endpoint permite registrar un nuevo usuario en la base de datos. Antes de guardarlo:

- Se valida que la contraseña no sea nula y tenga más de 4 caracteres.
- Se codifica la contraseña con `BCryptPasswordEncoder`.
- Se inicializan campos como:
  - `activo` (true)
  - `fechaCreacion` (fecha actual)
  - `tipo` (por defecto `USUARIO`)
  - `suscrito` (false)

```java
@PostMapping("/register")
public ResponseEntity<Usuario> register(@RequestBody Usuario u) {
    if (u.getPassword() == null || u.getPassword().length() <= 4) {
        return ResponseEntity.badRequest().body(null);
    }
    u.setPassword(passwordEncoder.encode(u.getPassword()));
    u.setActivo(true);
    u.setFechaCreacion(LocalDateTime.now());
    u.setTipo(TipoUsuario.USUARIO); // Asignación por defecto
    u.setSuscrito(false);
    return ResponseEntity.ok(serviUsuario.save(u));
}
```
### 🙋 Obtener el usuario autenticado
Este endpoint devuelve el usuario actualmente autenticado mediante el token JWT.
```java
@GetMapping
public Usuario getUser() {
    Usuario u = serviUsuario.getLoggedUser();
    u.setPassword(""); // Se limpia la contraseña por seguridad
    return u;
}

```
#### Método de servicio para obtener el usuario autenticado
Dentro de la clase ServiUsuario, se implementa el método getLoggedUser() que obtiene el usuario en base al nombre de usuario que contiene el contexto de seguridad (SecurityContextHolder):
```java
public Usuario getLoggedUser(){
    Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();
    return repoUsuario.findByUsername(authentication.getName()).get(0);
}
```

---

### Chat Bot IA con OLlama

El proyecto integra un chatbot inteligente usando **Ollama**, una plataforma local para ejecutar modelos de lenguaje avanzados (LLM) como Llama 3, Mistral, etc. El objetivo es ofrecer a los usuarios una experiencia conversacional sobre las **Ciudades Patrimonio de la Humanidad en España**.

#### 🧩 ¿Cómo funciona la integración?

- **Backend Spring Boot** expone un endpoint REST `/api/ollama/chat` que recibe mensajes del usuario.
- El controlador `ChatController` prepara la petición para Ollama, añadiendo un mensaje de sistema que limita las respuestas a temas relacionados con las ciudades patrimonio.
- El backend envía la petición a Ollama (que debe estar corriendo localmente en `http://localhost:11434/api/chat`) y devuelve la respuesta generada por el modelo de IA al frontend.

#### ⚙️ Configuración y dependencias

1. **Instalación de Ollama**  
   Descarga e instala Ollama desde [https://ollama.com/](https://ollama.com/).  
   En Linux, puedes usar:
   ```bash
   curl -fsSL https://ollama.com/install.sh | sh
   ```

2. **Descarga y ejecuta un modelo**  
   Por ejemplo, para usar Mistral:
   ```bash
   ollama run mistral
   ```
   O para Llama 3:
   ```bash
   ollama run llama3
   ```

3. **Configuración en Spring Boot**  
   - El endpoint de Ollama debe estar accesible en `http://localhost:11434/api/chat`.
   - En el backend, se utiliza un `RestTemplate` para enviar peticiones POST a Ollama.
   - El controlador añade un mensaje de sistema para guiar el comportamiento del modelo.

4. **Dependencias Maven**  
   Añade la dependencia de Spring Web y Jackson para manejar las peticiones y el JSON.

#### 📝 Ejemplo de uso del endpoint

- **Ruta:** `POST /api/ollama/chat`
- **Body:**
  ```json
  {
    "prompt": "¿Qué monumentos famosos hay en Córdoba?",
    "model": "llama3"
  }
  ```
- **Respuesta:**  
  El modelo responde solo sobre temas relacionados con las ciudades patrimonio, siguiendo el prompt de sistema.

#### 🛡️ Seguridad y control

- El sistema fuerza al modelo a responder únicamente sobre las ciudades patrimonio de la humanidad en España.
- Si el usuario pregunta sobre otro tema, el bot responde indicando que solo puede hablar de ese ámbito.

#### 📄 Código relevante (`ChatController.java`)

```java
@PostMapping("/chat")
    public ResponseEntity<String> chatWithOllama(@RequestBody Map<String, String> body) {
        String prompt = body.get("prompt");
        String model = body.getOrDefault("model", "llama3.2");
        if (prompt == null || prompt.isBlank()) {
            return ResponseEntity.badRequest().body("Falta el prompt en la solicitud.");
        }

        // Mensaje de sistema para guiar el modelo
        String systemPrompt = "Eres un asistente experto en las Ciudades Patrimonio de la Humanidad en España. " +
                "Solo puedes responder sobre temas relacionados con estas ciudades. Si la pregunta no tiene relación, " +
                "indica amablemente que solo puedes responder sobre las ciudades patrimonio de la humanidad en España. " +
                "Si necesitas ayuda, estoy aquí para ello.";

        try {
            Map<String, Object> jsonBody = new HashMap<>();
            jsonBody.put("model", model);
            jsonBody.put("stream", false);

            List<Map<String, String>> messages = new ArrayList<>();
            // Mensaje de sistema
            Map<String, String> systemMessage = new HashMap<>();
            systemMessage.put("role", "system");
            systemMessage.put("content", systemPrompt);
            messages.add(systemMessage);
            // Mensaje del usuario
            Map<String, String> userMessage = new HashMap<>();
            userMessage.put("role", "user");
            userMessage.put("content", prompt);
            messages.add(userMessage);
            jsonBody.put("messages", messages);

            String json = objectMapper.writeValueAsString(jsonBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(json, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(OLLAMA_URL, request, String.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(response.getStatusCode())
                        .body("Error de Ollama: " + response.getBody());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al llamar a Ollama: " + e.getMessage());
        }
    }
```

#### 🚀 Ventajas de la integración

- **Privacidad:** Todo el procesamiento de IA ocurre localmente, sin depender de servicios externos.
- **Personalización:** El prompt de sistema permite adaptar el comportamiento del bot a las necesidades del proyecto.
- **Facilidad de uso:** El backend abstrae la complejidad y expone un endpoint sencillo para el frontend.

---

###  Documentación automática con Swagger y SpringDoc OpenAPI

Este proyecto utiliza **SpringDoc OpenAPI** para generar documentación interactiva de la API REST basada en el estándar **OpenAPI 3.0** (Swagger).

---

#### 📦 Dependencia utilizada

En el archivo `pom.xml` se ha añadido la siguiente dependencia:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.8</version>
</dependency>
```
Esta dependencia proporciona una interfaz web interactiva y documentación en formato JSON de todos los endpoints definidos en los controladores REST del proyecto.

#### 🚀 ¿Qué funcionalidades aporta?

- Generación automática del documento **OpenAPI 3.0** desde los controladores y modelos.
- Interfaz **Swagger UI** en navegador para probar endpoints fácilmente.
- Compatible con **Spring Boot 3+**.
- Soporte para anotaciones como `@RestController`, `@GetMapping`, `@PostMapping`, etc.
- Compatible con seguridad (**Spring Security + JWT**).

---

#### 🌐 URLs importantes

Una vez ejecutado el proyecto, puedes acceder a:

- **Swagger UI (interfaz interactiva)**:  
  [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

- **Documentación en formato JSON (OpenAPI)**:  
  [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---
### Autor
Alejandro Copado López