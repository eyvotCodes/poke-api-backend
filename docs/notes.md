# Notas Generales

## 1. Primeros Pasos 

> *Lunes 10 de Marzo de 2025*\
> Dado que se contaba con medio día disponible. Se destinó a revisar en qué consistía la prueba, sus
> implicaciones y posibles formas de abordarla. Se eligieron las herramientas de desarrollo, se realizó el setup de
> las herramientas elegidas, se creó una página de administración para el proyecto y se comenzaron a
> definir el API del proyecto.

### Herramientas Elegidas

**Desarrollo**

- Versión LTS más reciente de **Java** (21) compatible con AWS ([desde enero de 2024](https://docs.aws.amazon.com/elasticbeanstalk/latest/platforms/platform-history-javase.html)).
- Versión estable más reciente de **Spring Boot** (3.4.3).
- Versión más reciente de **Maven** (project building, dependency management).
- **SDKMAN!** (package management).

**Deploy**

- **AWS Elastic Beanstalk** (opción práctica/válida para la prueba).

**Administrativas**

- **Notion** para “progress tracking” mediante una base de datos de tareas con vista de **backlog** y **board.**

Decisiones tomadas respecto a las tecnologías elegidas:

- Se usará SDKMAN! para instalar: Java, Maven y Spring Boot.
- Se usará **Spring CLI** para inicializar el proyecto (incluido con Spring Boot via SDKMAN!).
- Se usará **Corretto** (adaptación de Amazon del OpenJDK, lo usa internamente para probar sus servicios).
- Se compartirá **[página de Notion](https://fleyva.notion.site/1b377f27d8f480a68e0ac479a516e15f)** de la práctica (proporcionará mayor visibilidad a los evaluadores).

## 2. Arquitectura & PokéAPI

> *Martes 11 de Marzo de 2025*\
> Se concluyó la definición del API del proyecto, se definió la estructura de directorios del proyecto
> y se consumió/procesó la data de PokéAPI.

### Arquitectura Del Proyecto

Se optó por una N-Tier Architecture de 2 capas (una para lógica de negocio y otra para las interacciones externas).

La capa de lógica de negocio se mantendría lo más *limpia* posible (sin dependencias externas), garantizando de
esta forma que el código sea *testeable* y agnóstico de framework. El desacoplamiento de esta capa, se realizaría
a través de *inversión de dependencias*. En esta capa se procesaría la data recolectada de PokéAPI dándole un formato
más sencillo para su posterior exposición a un Front-end. A esta capa se le conoce como "dominio" en Arquitectura
Hexagonal (dentro del proyecto se encuentra como "core").

La capa de interacciones externas manejaría cualquier tipo de servicio con contacto directo al exterior (peticiones
a PokéAPI y exposición de data a través de un API REST). A esta capa se le conoce como "infraestructura" en
Arquitectura Hexagonal.

Para más detalles sobre la definición del API y estructura de directorios del proyecto, revisar las descripciones
las tareas *MT-8* y *MT-9* del board del proyecto ([página de Notion](https://fleyva.notion.site/1b377f27d8f480a68e0ac479a516e15f)).

**¿Cómo Funciona El Proyecto?**

El siguiente diagrama describe de forma básica:

~~~mermaid
graph TD;
    front[Frontend] <--> api[REST API];
    subgraph Infrastructure A
        api --> cache[Cache];
    end
    subgraph Core
        fmt[Formatter] --> api;
    end
    subgraph Infrastructure B
        cache <--> poke[PokéAPI]; 
    end
    cache --> fmt;
~~~

Ejemplos de formateo de datos de PokéAPI:

~~~txt
(offset, limit) -> page
(evolutions tree) -> list of evolution paths
(list of abilities) -> string of comma separated values
(list of types) -> string of comma separated values
~~~

## 3. REST API

> *Miércoles 12 de Marzo de 2025*\
> Este día no se disponía de mucho tiempo. Se dedicó a exponer la data recolectada/formateada de PokéAPI por medio
> de un API REST. Además se eligieron las herramientas de desarrollo del Front-end y se hizo el setup del proyecto. 

### Herramientas Elegidas

**Desarrollo**

- **NVM**. Manejador de versiones para Node.js.
- **Node.js**. Sin uso directo, para administrar dependencias via NPM.
- **Vite**. Builder del proyecto, inicializador del proyecto de React.
- **React**. Librería de UI principal del proyecto.
- **Tailwind**. Framework de CSS.
- **Shadcn**. Librería de componentes.

**Deploy**

- **AWS S3**. Ideal para sitios estáticos (costo por storage muy bajo).

## 4. Front-end

> *Jueves 13 de Marzo de 2025*\
> Se definió la estructura de directorios del proyecto del front-end, se consumieron los end-points del API REST
> del Back-end y se dio algo de diseño al Front-end.

Algunos directorios importantes fueron:

- `./` root del proyecto, puede contener archivos de configuración.
- `./src/` código fuente de la aplicación.
- `./src/api/` manejo de peticiones a APIs externas.
- `./src/components/` componentes reutilizables.
- `./src/pages/` páginas que representan una vista de la aplicación, utilizan componentes y conservan un estado.

## 5. Cache & Deploy

> *Viernes 14 de Marzo de 2025*
> Se implementó un sistema de Caché en memoria para las respuestas de PokéAPI y se hizo deploy tanto del
> Back-end como del Front-end.

Este día se hizo submit del formulario para evaluación de la prueba, dado que "5 días hábiles" era el tiempo
contemplado para la prueba. Sin embargo, dado que no se pudo dedicar "full time" los días entre semana, se optó por
hacer algunas mejoras el fin de semana en pequeñas ventanas de tiempo disponibles.
