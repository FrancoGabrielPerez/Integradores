# Integradores
## Listado de endpoints Integrador 3:

**Ej. 2.a)** Dar de alta un estudiante.

	Request Url: http://localhost:8080/estudiantes/alta
	Method: POST
	        
	En el body enviar en formato JSON los datos de un nuevo estudiante.
	Ejemplo: 
	Request Headers:
		{
			"Content-Type": "application/json"
		}
	Request Body:
		{
			"nombre": "Tito",
			"apellido": "Alvarez",
			"edad": 22,
			"ciudadResidencia": "Puerto Rico",
			"genero": "Male",
			"dni": 91748144,
			"libreta": 248231
		}

**Ej. 2.b)** Matricular un estudiante en una carrera.
		
	Request Url: http://localhost:8080/carreras/matricular
	Method: POST
	
	En el body se deben enviar, en formato formulario, la libreta del Estudiante y el nombre de la Carrera.
	Ejemplo:
	Request Headers:
		{
			"content-type": "multipart/form-data;
		}
	Request Body:
		{
			"libreta": "248231",
			"carrera": "Structural Engineer"
		}

**Ej. 2.c)** Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
		
	Request Url: http://localhost:8080/estudiantes/ordenadosPorApellidoYNombre
	Method: GET
		
**Ej. 2.d)** Recuperar un estudiante, en base a su número de libreta universitaria.

	Request Url: http://localhost:8080/estudiantes/{id}
	Method: GET
	Ejemplos de id: 100001, 248231, 100122.

**Ej. 2.e)** Recuperar todos los estudiantes, en base a su género.

	Request Url: http://localhost:8080/estudiantes/genero/{genero}
	Method: GET
	Ejemplos de genero: male, female, agender, non-binary, etc.

**Ej. 2.f)** Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.

	Request Url: http://localhost:8080/carreras/carrerasPorCantEstudiantes
	Method: GET

**Ej. 2.g)** Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.

	Request Url: http://localhost:8080/estudiantes/buscarPor?carrera={carrera}&ciudad={ciudad}
	Method: GET
	Ejemplo: carrera=Physical Therapy Assistant, ciudad=Sukth
	
**Ej. 2.h)** Generar un reporte de las carreras, que para cada carrera incluya información de los
inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y
presentar los años de manera cronológica.
	
	Request Url: http://localhost:8080/carreras/informeCarreras
	Method: GET
