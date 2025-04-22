# 🌍 PatriGod Backend

Este proyecto contiene el **backend** de la aplicación web **PatriGod**, responsable de administrar todos los datos relacionados con las Ciudades Patrimonio de la Humanidad en España. El frontend consumirá estos datos mediante una API REST para su visualización y gestión.

---

## 📚 Índice

1. [Creacion del proyecto](#creacion-del-proyecto)
2. [Implementacion de Docker](#creacion-del-proyecto)
3. [Autor](#autor)


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

## Autor 
Realizado por Alejandro Copado López
