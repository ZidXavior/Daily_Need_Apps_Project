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

// Prepare SQL statement to delete all entries from the table
$sql = "DELETE FROM map";
$conn->query($sql);

// Close database connection

?>
