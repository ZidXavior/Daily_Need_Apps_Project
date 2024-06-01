<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);

// Database connection for useraccount
$servername = "localhost";
$username = "root";
$password = "";
$database = "useraccount";

// Create connection for useraccount
$conn = new mysqli($servername, $username, $password, $database);

// Check connection for useraccount
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Check if a service request is received
if (isset($_POST['req'])) {
    $req = $_POST['req'];

    // Retrieve user_id, name, and phone from session table
    $session_query = "SELECT user_id, name, phone FROM session"; 
    $session_result = $conn->query($session_query);

    if ($session_result) {
        $session_row = $session_result->fetch_assoc();
        $user_id = $session_row['user_id'];
        $name = $session_row['name'];
        $phone = $session_row['phone'];

        // Prepare SQL statement to insert the request into the request table
        $sql = "INSERT INTO request (req, user_id, name, phone) VALUES ('$req', '$user_id', '$name', '$phone')";

        if ($conn->query($sql) === TRUE) {
            echo "Service request submitted successfully";
        } else {
            echo "Error inserting request into request table: " . $conn->error;
        }
    } else {
        echo "Error retrieving session data: " . $conn->error;
    }
}

// Check if a cancel request is received
if (isset($_POST['cancel'])) {
    // Clear the request table
    $sql = "TRUNCATE TABLE request";

    if ($conn->query($sql) === TRUE) {
        echo "Service request canceled successfully";
    } else {
        echo "Error clearing request table: " . $conn->error;
    }
}

// Close database connection
$conn->close();
?>
