<?php
// Database connection parameters
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "provideraccount";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Retrieve data from the request table
$sql = "SELECT name, phone FROM request";
$result = $conn->query($sql);

$data = array();

if ($result->num_rows > 0) {
    // Output data of each row
    while($row = $result->fetch_assoc()) {
        $data[] = $row;
    }
}

// Close connection
$conn->close();

// Output data as JSON
header('Content-Type: application/json');
echo json_encode($data);
?>