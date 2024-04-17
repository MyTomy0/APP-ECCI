<?php

include 'conexion.php';

$id = $_POST['id'];
$name = $_POST['name'];
$lastname = $_POST['lastname'];
$code = $_POST['code'];
$date = $_POST['date'];
$email = $_POST['email'];
$password = $_POST['password'];

$consulta = "insert into users values('".$id."','".$name."','".$lastname."','".$code."','".$date."','".$email."','".$password."')";

// $consulta = "INSERT INTO users (name, lastname, code, date, email, password) 
// VALUES ('$name', '$lastname', '$code', '$date', '$email', '$password')";

// if (isset($consulta)) {
//     echo ' email ', $email, ' registrado correctamente';
// } else {
//     echo ' No se creo email';
// }

mysqli_query($conexion, $consulta);
mysqli_close($conexion);

?>