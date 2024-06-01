<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);

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

// Check if latitude, longitude, and label values are received from the AJAX request
if (isset($_POST['lat']) && isset($_POST['lng']) && isset($_POST['label'])) {
    // Get latitude, longitude, and label values from the AJAX request
    $lat = $_POST['lat'];
    $lng = $_POST['lng'];
    $label = $_POST['label'];

    // Prepare SQL statement to insert data into the table
    $sql = "INSERT INTO map (lat, lng, label) VALUES ('$lat', '$lng', '$label')";

    if ($conn->query($sql) === TRUE) {
        echo "New record created successfully";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
}

// Check if delete request is received
if (isset($_POST['delete'])) {
    // Delete all entries from the table
    $sql = "DELETE FROM map";

    if ($conn->query($sql) === TRUE) {
        echo "All records deleted successfully";
    } else {
        echo "Error deleting records: " . $conn->error;
    }
}

// Retrieve existing latitude, longitude, and label values from the database
$sql = "SELECT lat, lng, label FROM map";
$result = $conn->query($sql);

$coordinates = array();
if ($result->num_rows > 0) {
    // Output data of each row
    while($row = $result->fetch_assoc()) {
        $coordinates[] = array('lat' => $row['lat'], 'lng' => $row['lng'], 'label' => $row['label']);
    }
}

// Close database connection
$conn->close();

// Output coordinates as JSON
echo json_encode($coordinates);
?>
