<?php

include 'conexion.php';

$id = $_POST['id'];
$name = $_POST['name'];
$lastname = $_POST['lastname'];
$code = $_POST['code'];
$date = $_POST['date'];
$email = $_POST['email'];
$password = $_POST['password'];

$consulta = "update users set name = '".$name."',lastname = '".$lastname."',
code = '".$code."', date = '".$date."', email = '".$email."', password = '".$password."' where id = '".$id."',";

mysqli_query($conexion, $consulta);
mysqli_close($conexion);

// $data = file_get_contents('php://input');

// $userData = json_decode($data, true);

// if (!$userData || empty($userData['id'])) {
//     http_response_code(400);
//     echo json_encode([
//         'error',
//         'Faltan datos requeridos en la solicitud',
//     ]);
//     exit;
// }

// $id = $userData['id'];
// $name = $userData['name'] ?? '';
// $lastname = $userData['lastname'] ?? '';
// $code = $userData['code'] ?? '';
// $date = $userData['date'] ?? '';
// $email = $userData['email'] ?? '';
// $password = $userData['password'] ?? '';

// $consulta = "UPDATE users SET 
//                 name = '$name',
//                 lastname = '$lastname',
//                 code = '$code',
//                 date = '$date',
//                 email = '$email',
//                 password = '$password'
//                 WHERE id = '$id'";

// if (mysqli_query($conexion, $consulta)) {
//     echo "'Usuario con ID $id actualizado exitosamente";
// } else {
//     http_response_code(500);
//     echo json_encode([
//         'error',
//         'Error al actualizar usuario: ' . mysqli_error($conexion),
//     ]);
// }

// mysqli_close($conexion);

?>
