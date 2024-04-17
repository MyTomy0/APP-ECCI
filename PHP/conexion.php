<?php
$hostname='localhost';
$database='usuario';
$username='root';
$password='';

$conexion=new mysqli($hostname,$username,$password,$database);

if (isset($conexion)){
    echo 'Conexion exitosa: ';
}else {
    echo 'No hay conexion: ';
}

?>