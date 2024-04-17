<?php

include 'conexion.php';

$id = $_POST['id'];

$consulta = "delete from users where id = '".$id."'";
mysqli_query($conexion,$consulta);
mysqli_close($conexion);

// $id = $_GET['id'] ?? null;

// $consultaEliminar = mysqli_query($conexion, "DELETE FROM users WHERE id = '$id'");

// if ($consultaEliminar) {
//     echo ' El usuario: ', $id, ' fue eliminado correctamente.';
// } else {
//     echo ' Error al intentar eliminar el usuario: ', $id, mysqli_error($conexion);
// }

?>