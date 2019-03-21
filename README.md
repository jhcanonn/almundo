# almundo - CallCenter Project
Existe un call center donde hay 3 tipos de empleados: operador, supervisor y director. El proceso de la atención de una llamada telefónica en primera instancia debe ser atendida por un operador, si no hay ninguno libre debe ser atendida por un supervisor, y de no haber tampoco supervisores libres debe ser atendida por un director.

# Extras/Plus
* Dar alguna solución sobre que pasa con una llamada cuando no hay ningun empleado libre.
R/ En este caso se pensó en un espacio de llamadas en espera, de modo que se implementó una Lista llamada 'callWaiting' para almacenar dichas llamadas.
* Dar alguna solución sobre	qué	pasa con una llamada cuando	entran más de 10 llamadas concurrentes.
R/ Depende de los empleados que se tengan en la compañia, es decir, si llegan 20 llamadas concurrentes y existen 20 empleados pues las llamadas son atendidas de inmediato, siempre y cuando esten disponibles todos los empleados; en otro escenario si son por ejemplo 15 empleados y llegan 20 llamadas, entonces 15 serán atendidas y 5 irán al espacio de llamadas en espera y apenas se desocupe un empleado recibe la primera llamada que se encuentre en cola, y asi sucesivamente hasta responder todas las llamadas.
* Agregar los tests unitarios que sean convenientes.
R/ Se agregó un escenario adicional que se relaciona con la segunda pregunta llamada 'check20Calls()'.
* Agregar documentacion de código.
R/ Todas las clases se encuentran documentadas, incluyendo el test unitario.

# Nota:
Decidí implementar los puntos extras ya que estos representan un reto para mí y se prestan para aumentar mis conocimientos, ademas de que me gusta mucho terminar un proyecto en su completitud.