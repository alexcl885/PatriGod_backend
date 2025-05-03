# 🌍 PatriGod Backend

Este proyecto contiene el **backend** de la aplicación web **PatriGod**, responsable de administrar todos los datos relacionados con las Ciudades Patrimonio de la Humanidad en España. El frontend consumirá estos datos mediante una API REST para su visualización y gestión.

---

## 📚 Índice

1. [Creacion del proyecto](#creacion-del-proyecto)
2. [Implementacion de Docker](#creacion-del-proyecto)
3. [Creacion de Carpetas](#creacion-de-carpetas)
4. [Programando proyecto](#programando-proyecto)
4. [Autor](#autor)


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

### 🔐 Seguridad con Variables de Entorno

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

## Programando proyecto

A partir de crear todas las carpetas, ya empieza lo bueno ya que empiezo a programar poco a poco el backend. 

He pensado en realizarlo poco a poco entonces he pensado hacer entidad por entidad con todas sus cosas para que se muestren por lo menos todas 
los datos de cada entidad y los pasos serían los siguientes:

### ✅ Paso 1: Crear el modelo

📁 Carpeta: `/modelos`

Creo el modelo de mi entidad (por ejemplo, `Ciudad.java`), que representa una tabla en la base de datos. Aquí se definen los atributos y se anotan con `@Entity`, `@Id`, `@Column`, etc.

---

### ✅ Paso 2: Crear el repositorio

📁 Carpeta: `/repos`

Creo el repositorio de la entidad con el nombre `RepoEntidad`, por ejemplo `RepoCiudad`. Esta interfaz extiende `JpaRepository` y me permite acceder a los datos sin escribir consultas SQL manualmente.

---

### ✅ Paso 3: Crear el servicio

📁 Carpeta: `/servicios`

Aquí creo el servicio de la entidad con el nombre `ServiEntidad`, por ejemplo `ServiCiudad`. Esta clase contiene la lógica de negocio, se comunica con el repositorio y será usada por el controlador.

---

### ✅ Paso 4: Crear el controlador

📁 Carpeta: `/controladores`

Finalmente, creo el controlador con el nombre `EntidadController`, por ejemplo `CiudadController`. Este controlador define las rutas REST (`GET`, `POST`, `PUT`, `DELETE`) que permiten interactuar con la entidad desde el exterior.

---

## 🔁 Y así con todas las entidades...

Repetiré este proceso con cada entidad de mi proyecto (mo del todo como Articulo y sus subclases ya que las he programado de otra manera que se ve abajo), asegurándome de que **cada una tenga su modelo, repositorio, servicio y controlador**.

De esta manera, el backend estará bien estructurado, escalable y fácil de mantener.

## Clase abstracta articulo y subclases: Monumento,Evento y comida.

La entidad `Articulo` define los atributos comunes a todos los elementos calificables (monumentos, eventos y comidas). Cada subtipo hereda de Articulo y solo declara los campos específicos de su propia tabla, compartiendo la clave primaria id.

### `Articulo` (Entidad Padre)
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
    private Ciudad ciudad;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;
}
```

- **@Entity**: Define la clase como entidad JPA.
- **@Inheritance(JOINED)**: Crea una tabla principal `articulo` y tablas hijas que comparten la misma PK.
- **@JsonTypeInfo / @JsonSubTypes**: Configura Jackson para incluir un campo `type` en JSON, indicando la subclase concreta.

### Subclases (`Monumento`, `Evento`, `Comida`)
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

---

### 📦 Esquema de Base de Datos

- **Tabla `articulo`** (padre): contiene `id`, `ciudad_id`, `nombre`, `descripcion`.
- **Tablas hijas (`monumento`, `evento`, `comida`)**: PK `id` como FK a `articulo.id`, más sus columnas propias (`imagen`, `fecha`, etc.).
- **Herencia `JOINED`** en JPA mapea directamente esta estructura.

## Ranking

### Ranking por Ciudades

En este punto vamos a tener que realizar DTO.

**¿Que es una DTO?**
Una DTO es una objeto especialmente diseñado para representar el resultado exacto de la consulta que querramos obtener.

Entonces para rankear por ciudades segun sus articulos, he tenido que 
crear una DTO:

En este caso he utilizado una interfaz en vez de una clase. ¿Por que?
He realizado una interfaz ya que solo me interesa leer datos, por lo que 
realizo una interfaz para que salga mas ligera mi aplicación.

En esta tabla se ve algunas de las diferencias entre realizar una interfaz o una clase con sus atributos y todo.

### 🆚 Diferencia entre un DTO de clase y un DTO de interfaz

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

Este método realiza una consulta SQL compleja que obtiene el **ranking de las ciudades Patrimonio de la Humanidad** basado en su **puntuación promedio**. Se utiliza la función `ROW_NUMBER()` para asignar una posición a cada ciudad según su puntuación promedio, de mayor a menor.

##### Descripción de la Consulta

La consulta se divide en dos partes:

1. **Subconsulta Principal**: Realiza la agregación de la puntuación promedio de las ciudades, tomando en cuenta los artículos asociados a cada ciudad y su respectiva puntuación. En esta subconsulta se hace el siguiente procesamiento:
   - Se obtiene el ID de la ciudad (`ciudad_id`) y su nombre (`ciudad_nombre`).
   - Se calcula la puntuación promedio (`puntuacion_promedio`) de los artículos asociados a cada ciudad.
   - Se utilizan varias uniones (JOIN) entre las tablas `ciudad`, `articulo`, `puntuacion`, `comida`, `evento` y `monumento` para asociar las puntuaciones de cada categoría (comida, evento, monumento).
   - Se agrupan los resultados por el ID y nombre de la ciudad.

2. **Aplicación de `ROW_NUMBER()`**: Utiliza la función de ventana `ROW_NUMBER()` para asignar una posición a cada ciudad según su puntuación promedio, ordenando los resultados de manera descendente (de mayor a menor puntuación).

```sql
SELECT ROW_NUMBER() OVER (ORDER BY puntuacion_promedio DESC) AS posicion, 
       ciudad_id, ciudad_nombre, puntuacion_promedio
FROM (
    SELECT c.id AS ciudad_id, 
           c.nombre AS ciudad_nombre, 
           AVG(p.puntuacion) AS puntuacion_promedio 
    FROM ciudad c
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

5. **Asociación de Puntuación**: Se asocia la puntuación promedio de cada ciudad (proveniente del DTO) al objeto `Ciudad` y se agrega a la lista final `ciudadesCompletas`.

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
                                             .filter(ciudad -> ciudad.getId().equals(dto.getCiudad_id()))
                                             .findFirst();
        if (ciudadOpt.isPresent()) {
            Ciudad ciudad = ciudadOpt.get();
            ciudad.setPuntuacion(dto.getPuntuacion_promedio()); 
            ciudadesCompletas.add(ciudad); 
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

Se han definido tres consultas nativas (`@Query`) utilizando SQL con la función `ROW_NUMBER()` para obtener la posición de cada ciudad en el ranking.

#### 🏛️ Ranking por Monumentos

```java
@Query(value = "SELECT ROW_NUMBER() OVER (ORDER BY AVG(COALESCE(p.puntuacion, 0)) DESC) AS posicion, " +
    "c.id AS ciudad_id, c.nombre AS ciudad_nombre, AVG(COALESCE(p.puntuacion, 0)) AS puntuacion_media " +
    "FROM ciudad c " +
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
    "FROM ciudad c " +
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
    "FROM ciudad c " +
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
 * @return lista ciudades(DTO) segun la media de monumentos de cada ciudad
 */
    @GetMapping("/rankMonumento")
    public List<RankingArticuloDTO> rankingMonumento() {
        return serviCiudad.findRankingByMonumento();
    }
/**
 * 
 * @return lista ciudades(DTO) segun la media de comidas de cada ciudad
 */
    @GetMapping("/rankComida")
    public List<RankingArticuloDTO> rankingComida() {
        return serviCiudad.findRankingByComida();
    }

/**
 * 
 * @return lista ciudades(DTO) segun la media de eventos de cada ciudad
 */
    @GetMapping("/rankEvento")
    public List<RankingArticuloDTO> rankingEvento() {
        return serviCiudad.findRankignByEvento();
    }
```
---


## Autor 
Realizado por Alejandro Copado López
