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
    
    // Get user_id from session table
    $user_id_query = "SELECT user_id FROM session"; // Modify this query as per your table structure
    $user_id_result = $conn->query($user_id_query);
    $user_id_row = $user_id_result->fetch_assoc();
    $user_id = $user_id_row['user_id'];

    // Prepare SQL statement to insert data into the table
    $sql = "INSERT INTO map (lat, lng, label, user_id) VALUES ('$lat', '$lng', '$label', '$user_id')";

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

// Retrieve existing latitude, longitude, label, and user_id values from the database
$sql = "SELECT lat, lng, label, user_id FROM map";
$result = $conn->query($sql);

$coordinates = array();
if ($result->num_rows > 0) {
    // Output data of each row
    while($row = $result->fetch_assoc()) {
        $coordinates[] = array('lat' => $row['lat'], 'lng' => $row['lng'], 'label' => $row['label'], 'user_id' => $row['user_id']);
    }
}

// Close database connection
$conn->close();

// Output coordinates as JSON
echo json_encode($coordinates);
?>
