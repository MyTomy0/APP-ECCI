<?php

include 'conexion.php';

$id = $_GET['id'];

$consulta = "select * from users where id = '$id'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
    $users[] = array_map('utf8_encode', $fila);
}

echo json_encode($users);
$resultado -> close();

// $id = $_GET['id'] ?? null;
// if ($id) {
//     $consulta = "SELECT * FROM users WHERE id = $id";
// } else {
//     $consulta = "SELECT * FROM users";
// }

// $result = mysqli_query($conexion, $consulta);
// $users = mysqli_fetch_all($result, MYSQLI_ASSOC);
// echo json_encode($users);

?>