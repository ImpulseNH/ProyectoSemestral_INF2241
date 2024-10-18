# Gestión de pasajeros y buses en una empresa de transporte

El proyecto consta de un sistema de administración y gestión de buses y pasajeros, el cuál contiene apartados visuales tanto para administradores como para los clientes.

![Imagen1](https://github.com/user-attachments/assets/b32f681d-72a9-43a9-b12a-d4ef9f56c8e9)

### Diseño conceptual de clases del Dominio

La jerarquía de clases se diseñó de la siguiente forma:

* La clase **Viaje**, que está compuesta por el origen del viaje, el destino correspondiente, su hora de partida, hora de llegada, el valor del viaje, un objeto de tipo Bus, que servirá para determinar si el viaje está listo para comenzar y saber qué bus lo realizará, y finalmente un arreglo de pasajeros en donde se almacenará al pasajero que haya comprado asiento para ese viaje;
* la clase **Bus**, que contiene su patente, su servicio, su valor y su capacidad;
* la clase **Cliente**, que extiende de la clase Pasajero, donde esta guarda la edad y el teléfono;
* la clase **Pasajero**, que es padre de Cliente y está formada por el nombre y Rut del pasajero;
* y finalmente la clase **Empresa**, que corresponde a la clase de más alto nivel y se usará como sistema, la cual contiene un arreglo de buses, viajes y clientes.

## Datos

### Datos iniciales

La inclusión de datos iniciales se realiza mediante una lectura de archivos txt en formato CSV que está incluido dentro de la carpeta del proyecto, específicamente en la carpeta llamada **datos**. Este archivo está de la siguiente forma:

El método que realiza esta función se llama **importarDatos**, el cual está implementado en la clase **Empresa**.

Este proceso se realiza al momento de ejecutar el programa. Si la importación de datos fue correcta se mostrará un mensaje por consola indicando el éxito del proceso.

### Exportar datos

Se implementó la opción de exportar los datos a un archivo .txt, en cada panel (Viajes, Buses y Clientes).

Cabe destacar que esta exportación es solo como uso de información, debido a que no está en formato CSV por lo que no podrá ser utilizada para volver a importar dicho dato.

Este proceso de exportación se realiza en el método **exportarDatos**, el cual está implementado en la clase **Empresa**.

![Imagen2](https://github.com/user-attachments/assets/3b952a8f-c4e5-4604-a9c6-086a167972a9)

# Diseño

### Anidación de colección de objetos

En el proyecto se contempla una anidación de dos niveles, la cual corresponde al funcionamiento principal de la lógica del negocio. Esta anidación consta de 3 clases: Empresa, Viaje y Pasajero. Dicha anidación comienza con Empresa (clase de más alto nivel) la cual contiene una colección de Viajes y en donde esta clase (Viaje) contiene una colección de Pasajeros.

### Diagrama UML

![Diagrama UML (EP3)](https://github.com/user-attachments/assets/e88e0245-aaa1-42a1-bbe8-ea7a59d76d2c)

A grandes rasgos, en el diagrama se puede apreciar que existen 4 clases que encapsulan las JCF. Principalmente se tiene que la clase **Empresa** (clase de más alto nivel) contiene 3 de estas colecciones encapsuladas, las cuales son: **ColeccionBuses**, **ColeccionViajes** y **ColeccionClientes**. Luego, para el siguiente y último nivel de anidación, se tiene la clase **Viaje** que contiene una colección de pasajeros, además de un objeto de tipo bus. Finalmente, está la clase **Cliente** que extiende de la clase Pasajero.

# Funcionalidad

### Gestión de datos (CRUD)

El proyecto fue diseñado para usarse completamente a través de una interfaz gráfica, por lo que la manipulación de datos (agregar, editar, eliminar) es a través de ventanas.

![Imagen3](https://github.com/user-attachments/assets/f70f8713-1120-48d8-ab07-fa46732db362)

### Funcionalidades propias

El proyecto contiene varias funcionalidades propias.

Algunas de ellas son:

1) Sistema de puntos para futuros descuentos
2) Buscar al pasajero con el RUT más antiguo de todo el sistema
3) Buscar al pasajero con asiento impar de todo el sistema

Cabe destacar que ambas funcionalidades solo toman en cuenta viajes que tengan buses asignados.

Dentro del programa, es posible ver los resultados de estas funciones propias en el menú llamado ‘Funciones propias’, el cual está en el apartado de ‘Administración’.

**IMPORTANTE:** Para asignar un bus a un viaje, debe haber al menos un bus disponible (en el panel de buses se puede saber cuál está disponible) y luego presionar el botón de asignar y elegir el bus a asignar. Una vez asignado, se podrá administrar al pasajero del viaje y además el viaje se podrá ver en el apartado ‘Buscar’ en donde se puede comprar el pasaje.

# Encapsulamiento y principios OO

El proyecto contempla los tres pilares más importantes de la programación orientada a objetos. En primer lugar, se tiene el encapsulamiento el cual está aplicado en la clase del dominio, donde cada atributo tiene una visibilidad privada y dicho atributo solo es accesible mediante los correspondientes getters y setters, además de que se encapsula cada estructura de datos en colecciones aparte que se encargan solamente de manipular dicha estructura. En segundo lugar, se aplica herencia, la cual se evidencia en las clases **Pasajero** y **Cliente**, donde **Cliente** extiende de **Pasajero** y donde además **Cliente** es Padre de **ClienteNormal** y **ClienteSuscrito**, jerarquizando la clase según la relación entre ellas. En tercer y último lugar se tiene la abstracción, donde cada componente esconde detalles importantes de implementación de tal forma que solo se vea qué hace, pero no cómo lo hace. Esto se evidencia en cada clase del dominio, donde cada clase realiza lo que le corresponde y delega tareas que estén fuera de su responsabilidad sin saber cómo estas trabajan.

### Herencia

En cuanto a herencia, la clase Padre que contiene el método a sobrescribir corresponde a la clase **Pasajero**. Dicha clase contiene un método implementado llamado **descuento**, el cual retorna un entero (en esa clase retorna un 0 debido a que un pasajero no es apto para ningún descuento) que corresponde al descuento que se aplicará en una compra. Las clases hijas que sobrescriben este método son: **ClienteNormal** y **ClienteSuscrito**. Ambas clases proveen una implementación distinta al método **descuento**.

### Abstracción

En el proyecto, la clase abstracta corresponde a la clase **Cliente**, la cual extiende de la clase **Pasajero** y es padre de **ClienteNormal** y **ClienteSuscrito**. Como se mencionó anteriormente, las clases hijas de la clase **Cliente** son las que sobreescriben el método **descuento** ya que este método se hereda desde esta clase (Cliente). Por otro lado, en esta clase se declara un método abstracto llamado **descuentoEspecial**.

El propósito es tener un descuento especial en donde la empresa sea la que decida cuándo lo aplicará. El método **descuento** que está sobrescrito en estas clases se puede probar en el apartado ‘Buscar’ del programa, al momento de comprar un pasaje.

### Interfaz

En el programa, la interfaz se llama **IPromocionable**, la cual la implementan las clases **Viaje**, **Bus** y **ClienteSuscrito**. El propósito de esta interfaz es unir clases que no tengan mucha relación entre sí mediante alguna característica en común, en este caso el costo final de un pasaje. Esto se realizará mediante un método llamado **promoción**, en donde su función será dar más accesibilidad a precios y específicamente para el cliente suscrito aumentará la ganancia de puntos, a diferencia del cliente común.

# Manejo de excepciones

En el proyecto se contemplan varias capturas de excepciones propias del lenguaje, en donde la principal es la **NumberFormatException**, que sucede cuando en un TextField se requiere un número y se ingresa un carácter.

Un ejemplo de la captura de esta excepción está en la clase **VentanaAgregarViaje**, dentro del package **InterfazGrafica**.

Además, en el proyecto existen 2 clases que extienden de la subclase **RuntimeException**, las cuales se llaman **AsientoIncorrectoException** y **CantidadIncorrectaException**.

En primer lugar, la excepción **AsientoIncorrectoException** es lanzada cuando en el apartado de administración se desea agregar a un pasajero a un viaje y el asiento que se intenta agregar no está dentro del rango válido. Esto está presente en la clase **VentanaAgregarPasajero** del package **InterfazGrafica** cuya parte del código se encuentra en la línea 83 de la clase mencionada anteriormente.

Cabe mencionar que se manda como parámetro la capacidad del bus al que se le está intentando agregar al pasajero, debido a que el mensaje que mostrará la excepción especificará el número máximo que se debería ingresar.

Por otro lado, se tiene la **CantidadIncorrectaException** que también es lanzada en el apartado de administración, específicamente en la clase **VentanaAgregarBus** dentro del package **InterfazGrafica**, y ocurre cuando se intenta agregar un bus y la patente ingresada no tiene específicamente 6 caracteres. Esta parte del código se ubica en la línea 68 de la clase mencionada anteriormente.

Es importante destacar que, si bien estas excepciones son lanzadas, no son capturadas por un **try-catch**, lo cual interrumpirá la ejecución del programa y arrojará la descripción de la excepción por consola.
