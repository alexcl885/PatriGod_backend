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

Repetiré este proceso con cada entidad de mi proyecto (por ejemplo, `Ciudad`, `Usuario`, `Comentario`, etc.), asegurándome de que **cada una tenga su modelo, repositorio, servicio y controlador**.

De esta manera, el backend estará bien estructurado, escalable y fácil de mantener.


## Autor 
Realizado por Alejandro Copado López
