create table comentarios (
Id int primary key,
Comentario varchar(150),
Usuario_id int,
Post_id int,
foreign key (Usuario_id) references usuario.users(id),
foreign key (Post_id) references usuario.posts(Id)
);

create table posts (
Id int primary key,
Titulo varchar(150),
Fecha_Publicacion TIMESTAMP,
Contenido varchar (500),
Estatus CHAR(8) CHECK(Estatus in('Activo','Inactivo')),
Usuario_id int,
Categorias_id int,
foreign key (Usuario_id) references usuario.users(id),
foreign key (Categorias_id) references usuario.categorias(Id)
)


create table categorias (
Id int primary key,
Categoria varchar (30)
)


create table etiquetas (
  Id int primary key,
  Nombre_etiqueta varchar(30)
)