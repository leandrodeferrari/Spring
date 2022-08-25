# Spring 1
1- EGG NEWS
Vamos a crear un sitio de Noticias llamado EggNews.
.Para ello necesitaremos crear una entidad llamada Noticia. Crearemos los servicios, repositorios y 
controladores necesarios para que todo funcione.
. Necesitamos que la entidad Noticia tenga Título, cuerpo y una foto.
. Por ahora el Usuario podrá crear, modificar y eliminar noticias.
Desarrollaremos:
. Vista inicio, donde se encuentren tarjetas(bootstrap) con el título y la foto de cada noticia, 
ordenadas de más reciente a más antigüa.
. Vista noticia, donde a través de la tarjeta del inicio accedemos a la noticia completa.
. Vista panelAdmin donde gestionaremos las noticias.

# Spring 2
1- EGG NEWS
Vamos a completar nuestro EggNews.
. Crearemos tres nuevas entidades: Usuario, Periodista y Administrador.
. Usuario tendrá como atributos nombreUsuario, password y fecha de alta.
. Periodista y Administrador extienden de Usuario.
. Periodista tendrá como atributo extra cantidadNoticias(ArrayList) y sueldoMensual.
. Agregaremos a la entidad Noticia el atributo Creador que la vincule con un
Periodista(relación).
. En el panelAdmin el periodista podrá acceder a crear y modificar noticias.
. Administrador podrá crear, modificar y eliminar noticias. También podrá dar de alta y baja a
periodistas, así como indicar cuál va a ser su sueldoMensual.
. Usuario debe loguearse para ver las noticias pero perderá la posibilidad de ingresar a
panelAdmin.
. Periodista y Administrador deben loguearse también para poder acceder a sus funciones.
