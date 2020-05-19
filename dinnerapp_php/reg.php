<?php
require "db.php";

$username = $_POST['username'];
$password = password_hash($_POST['password'], PASSWORD_DEFAULT);
$email = $_POST['email'];
if ($username && $password && $email) {
    $mysql_query = "INSERT into users (username, password, email) VALUES ('$username', '$password', '$email')";
    if($conn->query($mysql_query) === TRUE) {
        echo "Vartotojo vardas: ".$username."<br>Slaptažodis: ".$password;
    } else {
        echo "Error: ".$mysql_query."<br>".$conn->error;
    }
} else {
    echo "Error: Empty fields";
}

$conn->close();

?>