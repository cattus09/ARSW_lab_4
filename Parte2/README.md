## Escuela Colombiana de Ingeniería

## Arquitecturas de Software

# Componentes y conectores - Parte I.

El ejercicio se debe traer terminado para el siguiente laboratorio (Parte II).

#### Middleware- gestión de planos.


## Antes de hacer este ejercicio, realice [el ejercicio introductorio al manejo de Spring y la configuración basada en anotaciones](https://github.com/ARSW-ECI/Spring_LightweightCont_Annotation-DI_Example).

En este ejercicio se va a construír un modelo de clases para la capa lógica de una aplicación que permita gestionar planos arquitectónicos de una prestigiosa compañia de diseño. 

![](img/ClassDiagram1.png)

1. Configure la aplicación para que funcione bajo un esquema de inyección de dependencias, tal como se muestra en el diagrama anterior.


	Lo anterior requiere:

	* Agregar las dependencias de Spring.

![image](https://github.com/cattus09/ARSW_lab_4/assets/98556822/6fec972b-0d03-4fef-901c-9029416a2c62)

.
	* Agregar la configuración de Spring.

![image](https://github.com/cattus09/ARSW_lab_4/assets/98556822/31d85d43-b3c6-4813-ac01-b196391cb7f4)


.
	* Configurar la aplicación -mediante anotaciones- para que el esquema de persistencia sea inyectado al momento de ser creado el bean 'BlueprintServices'.

![image](https://github.com/cattus09/ARSW_lab_4/assets/98556822/444b6cba-3594-44ba-8e41-cabf99b03658)


2. Complete los operaciones getBluePrint() y getBlueprintsByAuthor(). Implemente todo lo requerido de las capas inferiores (por ahora, el esquema de persistencia disponible 'InMemoryBlueprintPersistence') agregando las pruebas correspondientes en 'InMemoryPersistenceTest'.

![image](https://github.com/cattus09/ARSW_lab_4/assets/98556822/8907a84d-4311-4da8-8201-2a4e143cf949)



3. Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.

	Creamos una clase Main, esta registra nuestros usuarios.

	![](img/ARSW-LAB3 FOTO3.1.png)


	Ejecutamos el programa para probar su correcto funcionamiento.

	![](img/ARSW-LAB3 FOTO3.2.png)


4. Se quiere que las operaciones de consulta de planos realicen un proceso de filtrado, antes de retornar los planos consultados. Dichos filtros lo que buscan es reducir el tamaño de los planos, removiendo datos redundantes o simplemente submuestrando, antes de retornarlos. Ajuste la aplicación (agregando las abstracciones e implementaciones que considere) para que a la clase BlueprintServices se le inyecte uno de dos posibles 'filtros' (o eventuales futuros filtros). No se contempla el uso de más de uno a la vez:

	Creamos una clase de tipo interface en esta definimos lo metodos filter y filters, filters se encargara de recibir una lista y enviarsela a filter para  que este la pueda filtrar.

	![](img/ARSW-LAB3 FOTO4.1.png)

	* (A) Filtrado de redundancias: suprime del plano los puntos consecutivos que sean repetidos.

		![](img/ARSW-LAB3 FOTO4.2.png)

	* (B) Filtrado de submuestreo: suprime 1 de cada 2 puntos del plano, de manera intercalada.

		![](img/ARSW-LAB3 FOTO4.3.png)

5. Agrege las pruebas correspondientes a cada uno de estos filtros, y pruebe su funcionamiento en el programa de prueba, comprobando que sólo cambiando la posición de las anotaciones -sin cambiar nada más-, el programa retorne los planos filtrados de la manera (A) o de la manera (B). 

	Prueba de filtrado de redundancia.

	![](img/ARSW-LAB3 FOTO5.1.png)

	Ejecucion de la prueba.

	![](img/ARSW-LAB3 FOTO5.2.png)


	Prueba de filtrado de submuestreo.

	![](img/ARSW-LAB3 FOTO5.3.png)

	Ejecucion de la prueba.

	![](img/ARSW-LAB3 FOTO5.4.png)
