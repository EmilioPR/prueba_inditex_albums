# Prueba Java Developer Inditex

### Descripción del proyecto
El proyecto consta de un único microservicio que expone tres endpoints para la recuperación y presentación de datos, 
según las instrucciones del documento *prueba_java_developer_inditex.pdf*. 

El microservicio está desarrollado en Java 17y Spring Boot 3.2.2. Se ha utilizado Maven como gestor de dependencias 
y para la construcción del proyecto.

### Requisitos
Para la ejecución del proyecto es necesario tener instalado en el equipo:

- Java 17
- Maven 3.8.3

### Instalación
No existe un proceso de instalación como tal. Para disponer del código basta con clonar el repositorio con el comando:

*git clone **https://github.com/EmilioPR/prueba_inditex_albums.git***

### Estructura del proyecto
La estructura del proyecto es la habitual en un proyecto de Spring Boot. Se compone de las siguientes carpetas y archivos:

- *src/main/java*: Contiene el código fuente del proyecto.
- *src/main/resources*: Contiene los archivos de configuración del proyecto.
- *src/test/java*: Contiene los tests del proyecto.
- *src/test/resources*: Contiene los archivos de configuración de los tests.
- *pom.xml*: Archivo de configuración de Maven.

El código utiliza una base de datos H2 en memoria, que se inicializa tanto al arrancar el microservicio como al ejecutar los tests de integración.

Se ha utilizado JPA para la persistencia de datos.

La arquitectura del proyecto es hexagonal, con una capa de dominio, una capa de aplicación y una capa de infraestructura.

### Configuración
La configuración del proyecto se encuentra en el archivo *application.properties* de la carpeta *src/main/resources*. en ese 
archivo se pueden configurar los parámetros de conexión a la base de datos, así como los endpoints a los que se accede desde el microservicio
para recuperar los datos de los álbumes y las fotos.

### Ejecución
Para ejecutar el proyecto, basta con clonar el repositorio y ejecutar:

1) En la raíz del proyecto, *mvn spring-boot:run*
2) También es posible ejecutar el proyecto desde el IDE de desarrollo, como IntelliJ IDEA o Eclipse.
3) Otra posibilidad es lade construir el proyecto con Maven y ejecutar el jar generado
   con el comando *java -jar target/microservicio-0.0.1-SNAPSHOT.jar*

El microservicio consta de tres endpoints:

- */album//album/save_all_albums*: Recupera todos los álbumes de la API de JSONPlaceholder, así como las fotos, y los guarda en la base de datos,
  manteniendo la relación entre álbumes y fotos mediante una FK.
- */album/albums_from_bbdd*: Recupera todos los álbumes de la base de datos, así como las fotos vinciuladas a cada álbum.
- */album/albums_from_api*: Recupera todos los álbumes de la API de JSONPlaceholder, así como las fotos vinculadas a cada álbum.

La salida de los endpoints */album/albums_from_bbdd* y */album/albums_from_api* es un JSON con la siguiente estructura:

```json
{
    "albums": [
        {
            "id": 1,
            "title": "ALBUM_1",
            "userId": 1,
            "photos": [
                {
                    "id": 1,
                    "title": "accusamus beatae ad facilis cum similique qui sunt",
                    "url": "https://via.placeholder.com/600/92c952",
                    "thumbnailUrl": "https://via.placeholder.com/150/92c952"
                },
                {
                    "id": 2,
                    "title": "reprehenderit est deserunt velit ipsam",
                    "url": "https://via.placeholder.com/600/771796",
                    "thumbnailUrl": "https://via.placeholder.com/150/771796"
                }
             ]
        }
        {
            ...
        }
    ]
}
```

### Tests
Los tests del proyecto se encuentran en la carpeta *src/test/java*. Se han desarrollado tests unitarios y de integración.
En el archivo application.properties de la carpeta *src/test/resources* se encuentra la configuración de la base de datos H2 en memoria, 
así como los endpoints mock que se utilizan en los tests de integración.
La ejecución de los tests se realiza con el comando *mvn test*, pero también es posible ejecutar los tests desde el IDE de desarrollo.

### Autor
El autor del proyecto es Emilio Postigo Riancho.

### Licencia
El proyecto se distribuye bajo licencia MIT.

```

