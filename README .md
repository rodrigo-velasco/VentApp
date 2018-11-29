## 1. Instalación
- Descargar todo el proyecto de este repositorio. 
- Abrir en Android Studio 
- Ejecutar en teléfono celular usando un cable USB. Esto causara la descarga e instalación directamente en el celular. 
    * NOTA: Se debe habilitar la instalación de apps de origen desconocido. Esto se hace en Ajustes -> Seguridad -> Origenes desconocidos.  

## 2. Diagrama Conceptual

![alt text](https://pi7e-davidalvaradov414989.codeanyapp.com/RodrigoPI/1_dConceptual.jpg "Diagrama conceptual")

## 3. Historias de usuario

 ID | Quien | Que | Para que
----|-------|-----| --------
1   | Vendedor | Se registra en el sistema usando sus datos de Nombre, Dirección, Teléfono y correo electrónico.  | Para hacer uso de la aplicación y realizar compras.
2   | Vendedor | Puede agregar productos que quiera a una lista. | Para visualizar cuáles y cuántos productos ha pedido.
3   | Vendedor | Puede registrar si el pago será de contado o en abonos. | Para que se ajuste a las necesidades del usuario.
4   | Vendedor | Puede registrar si el pago se hará por semana, quincena o mes.  | Para que se ajuste a las necesidades del usuario.
5   | Vendedor | Puede registrar el día de la semana y el horario en que se recogerá el abono. | Para que se ajuste a las necesidades del usuario.
6   | Vendedor | Puede cancelar la compra de un producto antes de empezar a pagar por él. | Para no tener que empezar a pagar por un producto que se eligió por accidente.
7   | Vendedor | Puede visualizar el listado de productos adquiridos. | Para ver el listado de abonos realizados de cada producto.
8   | Vendedor | Puede confirmar el estatus de los productos ya pagados y que faltan por pagar.  | Para visualizar la suma total de lo abonado y lo que adeuda.
9  | Vendedor |Puede registrar nuevos productos en la aplicación, introduciendo descripción, precio y una fotografía.  | Para que usuarios interesados puedan realizar la compra del producto. 
10  | Vendedor | Puede dar de baja productos ya registrados. | Para deshacerse de productos no tan vendidos o cambiarlos por nuevas versiones.
11  | Vendedor | Puede editar los datos de un producto. | Para poder actualizar descripciones o fotografías cuando corresponda y hacer ofertas a clientes.


## 4. Casos de uso

![alt text](https://pi7e-davidalvaradov414989.codeanyapp.com/RodrigoPI/3_cu_controlVentas.jpg "Casos de uso")

## 5. Diagrama entidad - relación

![alt text](https://pi7e-davidalvaradov414989.codeanyapp.com/RodrigoPI/2_dDB.jpg "Entidad-Relación")

## 6. Diccionario de datos

# Clientes
- Función: Almacenar los clientes y sus datos.

Campo    | Tipo de dato | Descripción
---------|--------------|------------
ID	     | Integral	    | Identificador único para cada cliente, utilizado también para referenciarlo en otras tablas.
Nombre   |	Texto       | Nombre completo del cliente.
Dirección|	Texto	    | Domicilio del cliente.
Teléfono | Integral     | Número de teléfono del cliente.
Email	 |  Texto       | Correo electrónico del cliente.

# Productos
- Función: Almacenar los productos que se tienen y datos relevantes.

Campo      |  Tipo de dato | Descripción
-----------|---------------|------------
ID	       |   Integral	   | Identificador único de cada producto, utilizado también para referenciarlo en otras tablas.
Nombre	   |    Texto	   | Nombre del producto.
Descripción|	Texto	   | Descripción general del producto.
Precio	   |    Decimal	   | Precio del producto en pesos, puede incluir centavos.
Foto	   |    Imagen	   | Fotografía del producto.

# Abonos
- Función: Almacenar los datos de los abonos que se han hecho. 

Campo         |  Tipo de dato | Descripción
--------------|---------------|------------
 ID	          |	   Integral   |  Identificador único del abono, utilizado también para referenciarlo en otras tablas.
 ID_Cliente	  |    Integral	  |  Identificador del cliente que debe pagar el abono.
 Estado	      |	    Texto     |  Estado actual del abono. (Pagado, por cobrar, atrasado, cancelado, etc.).
 Monto	      |    Decimal	  |  Monto a pagar para este abono.
 Periodo	  |     Texto	  |  Tipo de periodo a cobrar. (Quincenal, semanal, mensual, etc.).
 Proximo_Cobro|	  Hora/Fecha  |  Fecha y hora en la que se debe cobrar este abono.


# Compras_Cliente
- Función: Llevar registro de las compras realizadas.

Campo       |  Tipo de dato | Descripción
------------|---------------|------------
ID_Cliente	|    Integral	| Identificador del cliente que realizó la compra
ID_Producto	|    Integral	| Identificador de los productos comprados.
ID_Abono	|    Integral	| Identificador de los abonos adjuntos a esta compra.
Pagado	    |    Booleano	| Pagado: Si/No.


## 7. Interfaces de usuario

# Login:

![alt text](https://pi7e-davidalvaradov414989.codeanyapp.com/RodrigoPI/1_login.png "Login")

# Registro

![alt text](https://pi7e-davidalvaradov414989.codeanyapp.com/RodrigoPI/2_registrarVendedor.png "Registro")

# Agregar cliente

![alt text](https://pi7e-davidalvaradov414989.codeanyapp.com/RodrigoPI/3_agregarCliente.png "Agregar Cliente")

# Agregar producto

![alt text](https://pi7e-davidalvaradov414989.codeanyapp.com/RodrigoPI/4_agregarProducto.png "Agregar producto")

# Nueva venta

![alt text](https://pi7e-davidalvaradov414989.codeanyapp.com/RodrigoPI/8_nuevaVenta.png "Nueva venta")

# Ventas

![alt text](https://pi7e-davidalvaradov414989.codeanyapp.com/RodrigoPI/5_ventas.png "Ventas")

# Clientes

![alt text](https://pi7e-davidalvaradov414989.codeanyapp.com/RodrigoPI/6_clientes.png "Clientes")

# Productos

![alt text](https://pi7e-davidalvaradov414989.codeanyapp.com/RodrigoPI/7_productos.png "Producto")
