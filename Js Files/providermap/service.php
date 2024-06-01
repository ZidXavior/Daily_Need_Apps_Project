<?php
// Database connection
$servername = "localhost";
$username = "root";
$password = "";
$database = "provideraccount";

// Create connection
$conn = new mysqli($servername, $username, $password, $database);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Check if a service request is received
if (isset($_POST['req'])) {
    $req = $_POST['req'];

    // Prepare SQL statement to insert the request into the table
    $sql = "INSERT INTO request (req) VALUES ('$req')";

    if ($conn->query($sql) === TRUE) {
        echo "Service request submitted successfully";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
}

// Check if a cancel request is received
if (isset($_POST['cancel'])) {
    // Clear the column
    $sql = "TRUNCATE TABLE request";

    if ($conn->query($sql) === TRUE) {
        echo "Service request canceled successfully";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
}

// Close database connection
$conn->close();
?>
