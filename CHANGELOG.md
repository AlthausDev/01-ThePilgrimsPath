# Changelog

All notable changes to this project will be documented in this file.
See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

## [0.1.0](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/compare/v0.2.0...v0.1.0) (2024-01-14)

### ⚠ BREAKING CHANGES

* Renovación completa del proyecto utilizando JPA, Hibernate y Spring Boot.

Este commit marca el inicio del proyecto. Se ha reestructurado y está pendiente de esr reescrito todo el código para
aprovechar JPA, Hibernate y Spring Boot, mejorando el acceso a datos y la arquitectura de la aplicación.

# BREAKING CHANGE: Este commit introduce cambios importantes en el código existente."

* Renovación completa del proyecto utilizando JPA, Hibernate y Spring Boot.

Este commit marca el inicio del proyecto. Se ha reestructurado y está pendiente de esr reescrito todo el código para
aprovechar JPA, Hibernate y Spring Boot, mejorando el acceso a datos y la arquitectura de la aplicación.

# BREAKING CHANGE: Este commit introduce cambios importantes en el código existente."

### Features

* Agregado caso de uso 'ExportarEstancias', incluida vista y controlador, y realizadas las modificaciones
  necesarias. ([c7dead3](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/c7dead3dd735f4066e0771936739b4c5fc0fbf27))
* Añadida carga de nacionalidades y establecimiento de parada
  random ([a51782c](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/a51782cfeeee94d9baa88faf11c0ebe4cbbd8d3e))
* Añadida clase de sesion base, actualizada ventana de
  Login ([04a44ea](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/04a44ea97eca1aebf31de2919db747609ea580ff))
* Añadida entidad
  Credenciales. ([8c10d63](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/8c10d63255cee1f2ab0a30866cb54e687de21faa))
* Añadida entidad User y Enum Perfil. Añadidas dependencias en Pom y
  application.properties ([9d616bf](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/9d616bf4fc24c58e67a18e2fcf099f64d786353d))
* Añadidas entidades a la capa de
  modelo. ([dee30d5](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/dee30d5b6c1702b6ad7fad99601bb2452eef29fb))
* Añadidas funciones a credenciales, modificada forma de tratar los datos, terminado el controlador y vista para el
  registro de
  usuario. ([32a0f37](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/32a0f37213e5c66d54c6f64b76a69f8c7b3e8edd))
* Añadidas implementaciones de servicio para todas las
  entidades ([762c396](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/762c396b74ce813e55b2044024c0461fd17ed046))
* Añadido autenticado de usuario y util de encriptado de
  contraseñas ([2207532](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/2207532d24429990d4b3d0e6ed0aa6f9277e1075))
* Añadido caso de uso registro de
  parada ([e8aa576](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/e8aa576a558ae5b9cb07e73a1f70a930343023e4))
* Añadido caso de uso sellar carnet y generar
  estancia. ([60588fc](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/60588fcf75bf40c9563ce821111e6fd80e43aea4))
* Añadido formulario de login en
  .net. ([d208b5b](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/d208b5bbde0057fbeac8d69a636c736b4f817735))
* Añadido formulario de login en
  .net. ([1995dd9](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/1995dd9d3af4124f6a4d8a48e2aa6442bb34ab35))
* Añadido los Service, pendiente de Implementacion. Modificación del archivo
  README.md ([aec014a](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/aec014ac6c1f39ad93c9edd44e7d2ac5f2614c46))
* Añadido patrón Singleton, añadidos métodos a
  StartUpManager ([73dd6a4](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/73dd6a4482d118cf069e6d80c45d29d115a6049d))
* Añadido Service, Repository y ServiceImpl de User. Ahora User está embebido correctamente en credenciales. Ajustes en
  los
  registros ([2c778ad](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/2c778ad600095ea3570fc4fc8b74f1129aae9206))
* Añadido ValidationService, implementadas algunas funcionalidades en otros
  servicios ([626e364](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/626e36499c532c9a5f0a9d9aba09c4854e9c4db2))
* Añadido XMLReader y XMLWriter, actualización de pom y
  constantes ([01fb114](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/01fb1143eaf6e391141fc2b624ed6cad2270686c))
* Añadidos métodos para el establecimiento de sesión, WIP
  menu ([de4dbda](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/de4dbda7d6bf345742a5ca62f4f4fdcf49fe0468))
* Añadidos peregrinos y paradas de prueba al iniciar la
  aplicación ([e2ca591](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/e2ca591304ec3c123348737549a9bbbf201cf698))
* Añadidos repositorios por entidad y arreglada conectividad con base de
  datos. ([7480a9f](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/7480a9fa86fe9cb55d0927f50c80724cdda30cdf))
* Añadidos repositorios y servicio genérico más su implementación con
  logger. ([8b25fe7](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/8b25fe7cf167ecfbb35b67e9c21177ad6913a8ef))
* Implementado proceso de verificación de credenciales desde la ventana de
  login ([2a83b9d](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/2a83b9dba34b5a68730e7252360d9e2031a8d466))
* Implementado vista y controlador para gestionar el registro de usuario. Requiere
  revisión ([70a5a99](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/70a5a998bdfb61a12a58a90adc53d5aadc68f54f))
* Mejora de CoreRepository, CoreService y
  CoreServiceImpl ([bc1db73](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/bc1db730dc1df5851580a477c5426c27dd334954))
* Mejoradas medidas de validación de formato, agregado timeout al login en caso de repetidos intentos
  fallidos. ([bbe74ca](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/bbe74ca9258255b84931dd9e6bf17b6f5178d887))
* Mejoras en el menú y en la gestión de
  sesión ([4cb7b61](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/4cb7b6166d9a950f337989ff14a89d4b2d96abbb))

### Bug Fixes

* Ahora encuentra el bean
  solicitado ([e21daec](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/e21daecbebda9264c6213c5729b14fb47e7a20b4))
* Ajustes en Repository, Service y ServiceImlp,
  WIP ([0202bae](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/0202bae5937c6532b30124e7b4b7ec0e4fce8c24))
* Arreglada carga del menu, y empezando trabajo en el
  login ([261c99c](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/261c99c6ae6eefe5c6e736382e10f244342b529f))
* Arreglada la relación entre Parada y
  Peregrino ([20d9cc7](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/20d9cc73b8f1c0b867c9fc51966ad0a0f2bff9f8))
* Arregladas anotaciones y arranque de la
  aplicación ([b2a837b](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/b2a837b867924736fa8e71c3491c72bcb94dbe69))
* Arreglado orden de carga de componentes e
  inyeccciones ([6ed6e05](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/6ed6e05255f2bcbb76ba7dfaa8acee3b2e71f470))
* Arreglado registro de peregrino, modificada clase User a clase intermedia, ajustada
  encriptacion ([f7f2192](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/f7f2192b86e646defde45518ea5b805c0cb576d2))
* Arreglo de errores en el registro de
  peregrinos ([2e3931a](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/2e3931abae69f1ddbdb53df2ef18ba7886a392b2))
* Arreglos en Login (WIP), añadidos más datos demo en carga
  inicial ([3ab2005](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/3ab2005a4164022a00e09e38f4a8c33d345c50dd))
* Conexion a la
  BB.DD ([c654e10](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/c654e10c0abfeaf05230d3db06ba3d5a58384c3c))
* Corregida asignación de User a la sesión, solucionado problema en la exportación de carnet en XML, y movido el método
  de inicio de sesión desde Credenciales a
  Sesion ([f5e81ec](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/f5e81ec68943bea337a0e6c2a2b9dc0dc14d7eb2))
* Relacion User-Credenciales y
  CredencialesRepository ([9c95d65](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/9c95d65faa24269df9d6d6800137622f97722e23))


* commit inicial con cambios
  importantes ([c9f1182](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/c9f11826976b9a3015674e98a5a7a51a92465651))
* commit inicial con cambios
  importantes ([4915314](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/4915314ab288921c1e8aa9e1cf42a3b940eefbdc))

## [0.2.0](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/compare/v0.1.1-0...v0.2.0) (2023-12-19)

### ⚠ BREAKING CHANGES

* **registro:** Los métodos ahora consultan directamente a la base de datos en lugar de los distintos ficheros a
  excepción de nacionalidades.

### Features

* added sql
  database ([b343145](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/b34314532f29861cb561ac686d66d1be8ab66e8b))
* Agregar DAO para
  AdminParada ([c3d34b1](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/c3d34b16c84fabc42b3200ff49d08c0f55eb6c11))
* Agregar DAO
  restantes ([5dbb96f](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/5dbb96f3bc46e991741c35ae6235c2291a4c92b7))
* agregar métodos y ajustar menú en la clase Menu, modificar lógica de setPerfil en la clase Sesion, corregir método
  mostrarDatosParadaActual en la clase
  Menu ([db70030](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/db700306d19f343af2d4937e4678a4bd79ccb139))
* Ahora cuando se realiza el sellado del Carnet, se actualizan las paradas del peregrino y la tabla
  estancias ([f69dd57](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/f69dd575fec0387c86ce4d09240a06db23e2c683))
* Ajuste de Login, Sesión y la exportación del carnet. También se ha modificado la manera en la que se validan los
  datos ([a2bfcce](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/a2bfccebf29ad736efde91ef22964cc2de9a03f7))
* añadidas inserciones con datos iniciales al script
  SQL ([e72d7c8](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/e72d7c8e45712d72018196078eb3ca2af4d5cc59))
* **database:** implementar patrón Singleton y clases FactoryConexion y
  MySqlConexion ([71a16bf](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/71a16bf8dbbf55293dea885038b0f063577c1af9))
* modificacion del menú, del método de asignación del perfil y progreso con la refactorización del método para exportar
  carnets, también se han arreglado varios errores a la hora de realizar consultads de la tabla
  credenciales ([9b428d2](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/9b428d252e6906963f957b63e7c7853d127a6a42))
* **persistence:** agregar clases y trabajar en conector para acceso a base de
  datos ([ad84215](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/ad84215ca9c22d32e4613c582b5760fb48c38441))
* **registro:** mejorar lógica de creación de nueva parada y nuevo
  peregrino ([bd09f5c](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/bd09f5c8acd7c8e008565427fcf306cf9992d65f))
* Se añade DAO para credenciales, sin clase
  propia ([573d48e](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/573d48edccf25435a1104addfcc7df3cb052fc0b))

### Bug Fixes

* arreglos de fallos
  varios ([dd8fd76](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/dd8fd7661c55bfbdba541bcdf76c4e90bedddf26))
* consultas SQL de las implementaciones DAO y script
  SQL ([1ddd884](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/1ddd884d404067f093d5b4a57786aabf66392488))
* Errores
  varios ([b5b9d96](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/b5b9d966cd39a0aaa691470eff4f7152a672b616))
* lectura de paises, calculo aleatorio de distancia entre
  paradas ([740c711](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/740c711a7411301618b3301c7502319b76443fd2))
* various
  fix ([ef5d9c6](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/ef5d9c64da82fa58b7ba343151ed471f4433166d))

### 0.1.1-0 (2023-11-12)
