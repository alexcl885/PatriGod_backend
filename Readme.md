# 🌍 PatriGod Backend

Este proyecto contiene el **backend** de la aplicación web **PatriGod**, responsable de administrar todos los datos relacionados con las Ciudades Patrimonio de la Humanidad en España. El frontend consumirá estos datos mediante una API REST para su visualización y gestión.

---

## 📚 Índice

1. [Creacion del proyecto](#creacion-del-proyecto)
2. [Implementacion de Docker](#creacion-del-proyecto)
3. [Creacion de Carpetas](#creacion-de-carpetas)
4. [Programando proyecto](#programando-proyecto)
    - [Base del proyecto](#base-del-proyecto)
    - [Subclases clase Articulo](#subclases-monumento-evento-comida)
    - [Ranking](#ranking)
    - [Email](#email)
    - [Spring Security + JWT](#programando-proyecto) 
5. [Autor](#autor)


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
- Se permite el acceso a todas las rutas (`/**`) *(esto es útil para pruebas, pero en producción deberías proteger rutas específicas)*.
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
                .requestMatchers("/**").permitAll() // Se puede restringir a rutas específicas
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
1º Instalar Ollama desde su pagina inicial
Linux -> curl -fsSL https://ollama.com/install.sh | sh
2º Correr un modelo -> mistral
```bash
ollama run mistral
```
3º Añadir dependencia maven sobre ollama
https://mvnrepository.com/artifact/org.springframework.ai/spring-ai-starter-model-ollama/1.0.0-RC1?utm_source=chatgpt.com

4ºAñadir configuracion de application.properties


---




## Autor 
Realizado por Alejandro Copado López

---

## 📖 Documentación y Buenas Prácticas

### 📝 Documentación de la API con Swagger/OpenAPI

Para facilitar el desarrollo y la integración con el frontend, es recomendable documentar la API REST usando **Swagger** (OpenAPI). Esto permite visualizar y probar los endpoints desde una interfaz web.

- **Dependencia Maven**:
  ```xml
  <dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.8</version>
</dependency>
  ```
- **Acceso a Swagger UI**:  http://localhost:8080/swagger-ui/index.html#/
  Una vez arrancada la aplicación, accede a [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/) para ver y probar la documentación interactiva de la API.

---
## 📝 Pendiente/Futuras mejoras

### 🛡️ Seguridad Adicional

- **Roles y permisos**:  
  Considera implementar roles más granulares (por ejemplo, ADMIN, EDITOR, USER) y proteger rutas sensibles.
- **Validación de datos**:  
  Usa anotaciones como `@Valid`, `@NotNull`, `@Email`, etc. en los DTOs y entidades para validar la entrada de datos.
- **Gestión de errores global**:  
  Implementa un controlador de errores global con `@ControllerAdvice` para devolver respuestas coherentes ante excepciones.

---

### 📦 DTOs y Mappers

Para separar la lógica de persistencia de la lógica de presentación, utiliza DTOs (Data Transfer Objects) y mappers (por ejemplo, MapStruct):

- **Ventajas**:
  - Evita exponer entidades directamente.
  - Permite adaptar la respuesta a las necesidades del frontend.
- **Ejemplo**:
  ```java
  public class CiudadDTO {
      private Long id;
      private String nombre;
      // ...
  }
  ```

---


### 📚 Recursos útiles

- [Documentación oficial de Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Guía de Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Guía de Spring Security](https://spring.io/projects/spring-security)
- [Guía de Swagger/OpenAPI](https://springdoc.org/)

---

## 🚀 Despliegue

- Puedes desplegar la aplicación en servicios como **Heroku**, **Railway**, **Render**, **AWS**, **Azure**, etc.
- Para producción, configura variables de entorno seguras y usa una base de datos gestionada.

---

- Implementar sistema de notificaciones push.
- Añadir internacionalización (i18n) para soportar varios idiomas.
- Mejorar la gestión de imágenes (almacenamiento en S3, Cloudinary, etc.).
- Añadir tests de integración y de extremo a extremo (E2E).
- Mejorar la experiencia de usuario en el frontend con feedback en tiempo real.
