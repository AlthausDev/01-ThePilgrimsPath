# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

## [0.2.0](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/compare/v0.1.1-0...v0.2.0) (2023-12-19)


### ⚠ BREAKING CHANGES

* **registro:** Los métodos ahora consultan directamente a la base de datos en lugar de los distintos ficheros a excepción de nacionalidades.

### Features

* added sql database ([b343145](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/b34314532f29861cb561ac686d66d1be8ab66e8b))
* Agregar DAO para AdminParada ([c3d34b1](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/c3d34b16c84fabc42b3200ff49d08c0f55eb6c11))
* Agregar DAO restantes ([5dbb96f](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/5dbb96f3bc46e991741c35ae6235c2291a4c92b7))
* agregar métodos y ajustar menú en la clase Menu, modificar lógica de setPerfil en la clase Sesion,  corregir método mostrarDatosParadaActual en la clase Menu ([db70030](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/db700306d19f343af2d4937e4678a4bd79ccb139))
* Ahora cuando se realiza el sellado del Carnet, se actualizan las paradas del peregrino y la tabla estancias ([f69dd57](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/f69dd575fec0387c86ce4d09240a06db23e2c683))
* Ajuste de Login, Sesión y la exportación del carnet. También se ha modificado la manera en la que se validan los datos ([a2bfcce](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/a2bfccebf29ad736efde91ef22964cc2de9a03f7))
* añadidas inserciones con datos iniciales al script SQL ([e72d7c8](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/e72d7c8e45712d72018196078eb3ca2af4d5cc59))
* **database:** implementar patrón Singleton y clases FactoryConexion y MySqlConexion ([71a16bf](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/71a16bf8dbbf55293dea885038b0f063577c1af9))
* modificacion del menú, del método de asignación del perfil y progreso con la refactorización del método para exportar carnets, también se han arreglado varios errores a la hora de realizar consultads de la tabla credenciales ([9b428d2](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/9b428d252e6906963f957b63e7c7853d127a6a42))
* **persistence:** agregar clases y trabajar en conector para acceso a base de datos ([ad84215](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/ad84215ca9c22d32e4613c582b5760fb48c38441))
* **registro:** mejorar lógica de creación de nueva parada y nuevo peregrino ([bd09f5c](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/bd09f5c8acd7c8e008565427fcf306cf9992d65f))
* Se añade DAO para credenciales, sin clase propia ([573d48e](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/573d48edccf25435a1104addfcc7df3cb052fc0b))


### Bug Fixes

* arreglos de fallos varios ([dd8fd76](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/dd8fd7661c55bfbdba541bcdf76c4e90bedddf26))
* consultas SQL de las implementaciones DAO y script SQL ([1ddd884](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/1ddd884d404067f093d5b4a57786aabf66392488))
* Errores varios ([b5b9d96](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/b5b9d966cd39a0aaa691470eff4f7152a672b616))
* lectura de paises, calculo aleatorio de distancia entre paradas ([740c711](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/740c711a7411301618b3301c7502319b76443fd2))
* various fix ([ef5d9c6](https://github.com/AlthausDev/JavaProyect.01-GestorPeregrinos/commit/ef5d9c64da82fa58b7ba343151ed471f4433166d))

### 0.1.1-0 (2023-11-12)
