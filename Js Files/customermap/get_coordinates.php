<?php
// Database connection
$servername = "localhost";
$username = "root";
$password = "";
$database = "useraccount";

// Create connection
$conn = new mysqli($servername, $username, $password, $database);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Prepare SQL statement to select latitude and longitude values from the table
$sql = "SELECT lat, lng, label FROM map";
$result = $conn->query($sql);

$coordinates = array();

// Fetch result and store coordinates in an array
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $coordinates[] = $row;
    }
}

// Convert coordinates array to JSON format and output
echo json_encode($coordinates);

// Close database connection
$conn->close();
?>
