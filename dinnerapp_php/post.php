<?php
require "db.php";

$title = $_POST['title'];
$dinnerType = $_POST['dinnerType'];
$deliver = $_POST['deliver'];
$price = $_POST['price'];
$payment = $_POST['payment'];

$mysql_query = "INSERT into dinner_offers (title, dinnerType, deliver, price, payment) VALUES ('$title', '$dinnerType', '$deliver', $price, '$payment')";
if($conn->query($mysql_query) === TRUE) {
    echo "Pridėta į duomenų bazę";
} else {
    echo "Error: ".$mysql_query."<br>".$conn->error;
}
$conn->close();

?>