# Sistema-ventas-spring-boot
 Un sistema de ventas usando Java 8, Spring Boot, H2
 
# Servicio
  Todos los servicios retornan una estructura comun como GenericResponseDto<T>:  
  	{
    	"data": "T",
	    "errors": "...",
	    "status": "..."
	}
  	Donde "T" es un Objeto String, List, Object.
    Status: Es un Enum, indicando el exito o no de la operacion.
    
- GET ../api/consultas/ListarProductos
	Retorna Lista de Productos registrados (ProductoDto).
	Ejemplo:
	output:
	{
    "data": [
		        {
		            "codigo": "code1",
		            "marca": "marca1",
		            "modelo": "modelo1",
		            "precio": 22.99
		        },
		        {
		            "codigo": "code2",
		            "marca": "marca2",
		            "modelo": "modelo2",
		            "precio": 31.0
		        },
		        {
		            "codigo": "code3",
		            "marca": "marca3",
		            "modelo": "modelo3",
		            "precio": 56.72
		        }
	    ],
	    "errors": null,
	    "status": "SUCCESS"
	}

- GET ../api/consultas/ListarClientes
	Retorna Lista de Clientes registrados (ClienteDto).
	Ejemplo:
	output:	
	{
    "data": [
	        {
	            "nombre": "name2",
	            "apellido": "ape2",
	            "direccion": "---",
	            "dni": 456,
	            "telefono": null
	        },
	        {
	            "nombre": "name3",
	            "apellido": "ape3",
	            "direccion": "---",
	            "dni": 789,
	            "telefono": null
	        },
	        {
	            "nombre": "name1",
	            "apellido": "ape1",
	            "direccion": "---",
	            "dni": 123,
	            "telefono": null
	        }
	    ],
	    "errors": null,
	    "status": "SUCCESS"
	}

- POST ../api/operaciones/GuardarOrdenParcial
	Registra las Ordenes iniciales del Cliente.
	Retorna Orden parcial de compra por parte del cliente (OrdenParcialResponseDto).
	Input:
		{
		    "cliente": {
		        "nombre": "nombre cliente",
		        "apellido": "apellido cliente",
		        "direccion": "direccionAA",
		        "dni": 2233444,
		        "telefono": 2111111
		    },
		    "producto": {
		        "codigo": "code6",
		        "marca": "marca6",
		        "modelo": "modelo6",
		        "precio": 22.99
		    }
		}
	
	Ejemplo:
	output:
		{
		    "data": {
		        "productos": [
		            {
		                "codigo": "code6",
		                "marca": "marca6",
		                "modelo": "modelo6",
		                "precio": 22.99
		            }
		        ],
		        "ordenNumero": "00000001",
		        "cliente": {
		            "nombre": "nombre cliente",
		            "apellido": "apellido cliente",
		            "direccion": "direccionAA",
		            "dni": 2233444,
		            "telefono": 2111111
		        },
		        "subTotal": 22.99,
		        "Unidades": 1
		    },
		    "errors": null,
		    "status": "SUCCESS"
		}
	
##	En caso de ejecutar por segunda vez el mismo Input, se registrara un segundo producto para el mismo cliente.
		output:
		{
		    "data": {
		        "productos": [
		            {
		                "codigo": "code6",
		                "marca": "marca6",
		                "modelo": "modelo6",
		                "precio": 22.99
		            },
		            {
		                "codigo": "code6",
		                "marca": "marca6",
		                "modelo": "modelo6",
		                "precio": 22.99
		            }
		        ],
		        "ordenNumero": "00000001",
		        "cliente": {
		            "nombre": "nombre cliente",
		            "apellido": "apellido cliente",
		            "direccion": "direccionAA",
		            "dni": 2233444,
		            "telefono": 2111111
		        },
		        "subTotal": 45.98,
		        "Unidades": 2
		    },
		    "errors": null,
		    "status": "SUCCESS"
		}
	
	
- GET ../api/operaciones/OrdenParcial/{numeroOrden}
	Retorna Orden parcial de compra ACTIVA (OrdenParcialResponseDto).
	Ejemplo:
	GET ../api/operaciones/OrdenParcial/00000001
	output:
	{
	    "data": {
	        "productos": [
	            {
	                "codigo": "code6",
	                "marca": "marca6",
	                "modelo": "modelo6",
	                "precio": 22.99
	            },
	            {
	                "codigo": "code6",
	                "marca": "marca6",
	                "modelo": "modelo6",
	                "precio": 22.99
	            }
	        ],
	        "ordenNumero": "00000001",
	        "cliente": {
	            "nombre": "nombre cliente",
	            "apellido": "apellido cliente",
	            "direccion": "direccionAA",
	            "dni": 2233444,
	            "telefono": 2111111
	        },
	        "subTotal": 45.98,
	        "Unidades": 2
	    },
	    "errors": null,
	    "status": "SUCCESS"
	}
	
- GET ../api/operaciones/CancelarOrden/{numeroOrden}
	Cancela la Orden parcial de compra ACTIVA.
	Retorna Orden parcial de compra INACTIVA (OrdenParcialResponseDto).
	Ejemplo:
	GET ../api/operaciones/CancelarOrden/00000001
	{
	    "data": {
	        "productos": [
	            {
	                "codigo": "code6",
	                "marca": "marca6",
	                "modelo": "modelo6",
	                "precio": 22.99
	            },
	            {
	                "codigo": "code6",
	                "marca": "marca6",
	                "modelo": "modelo6",
	                "precio": 22.99
	            }
	        ],
	        "ordenNumero": "00000001",
	        "cliente": {
	            "nombre": "nombre cliente",
	            "apellido": "apellido cliente",
	            "direccion": "direccionAA",
	            "dni": 2233444,
	            "telefono": 2111111
	        },
	        "subTotal": 45.98,
	        "Unidades": 2
	    },
	    "errors": null,
	    "status": "SUCCESS"
	}

##	En caso de volver a ejecutar el servicio Cancelar Orden.. este devuelve el siguiente mensaje:
	output:
	{
	    "data": "",
	    "errors": [
	        "Codigo de Orden no existe o no esta activo"
	    ],
	    "status": "NOT_FOUND"
	}

- GET ../api/operaciones/FinalizarOrden/{numeroOrden}
	Registra la FACTURA u Orden de compra final del Cliente.
	Inhabilita la orden preliminar y no disponibiliza el producto contenido en el detalle de la venta.
	Retorna Factura de compra final para el cliente (OrdenCompraResponseDto).

	Ejemplo:
	GET	../api/operaciones/FinalizarOrden/00000001
	output:
	{
	    "data": {
	        "productos": [
	            {
	                "codigo": "code6",
	                "marca": "marca6",
	                "modelo": "modelo6",
	                "precio": 22.99
	            }
	        ],
	        "ordenNumero": "00000001",
	        "cliente": {
	            "nombre": "nombre cliente",
	            "apellido": "apellido cliente",
	            "direccion": "direccionAA",
	            "dni": 2233444,
	            "telefono": 2111111
	        },
	        "total": 22.99,
	        "barCode": "qX9yh0wm2EsOSYYYCvkt",
	        "Fecha": "2021-07-19 09:56:02",
	        "Unidades": 1
	    },
	    "errors": null,
	    "status": "SUCCESS"
	}

- GET ../api/operaciones/ListarFacturasByCliente/{dniCliente}
	Retorna Lista de Facturas registradas para un cliente (OrdenCompraResponseDto).
	Ejemplo:
	GET	../api/operaciones/ListarFacturasByCliente/2233444
	output:
	{
    	"data": [
	        {
	            "productos": [
	                {
	                    "codigo": "code6",
	                    "marca": "marca6",
	                    "modelo": "modelo6",
	                    "precio": 22.99
	                }
	            ],
	            "ordenNumero": "00000001",
	            "cliente": {
	                "nombre": "nombre cliente",
	                "apellido": "apellido cliente",
	                "direccion": "direccionAA",
	                "dni": 2233444,
	                "telefono": 2111111
	            },
	            "total": 22.99,
	            "barCode": "qX9yh0wm2EsOSYYYCvkt",
	            "Fecha": "2021-07-19 09:56:02",
	            "Unidades": 1
	        }
	    ],
	    "errors": null,
	    "status": "SUCCESS"
	}
