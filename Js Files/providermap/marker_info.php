<?php
// Database connection parameters
$servername = "your_servername";
$username = "your_username";
$password = "your_password";
$dbname_provider = "provideraccount";
$dbname_user = "useraccount";

// Create connection to provideraccount database
$conn_provider = new mysqli($servername, $username, $password, $dbname_provider);
if ($conn_provider->connect_error) {
    die("Connection failed: " . $conn_provider->connect_error);
}

// Create connection to useraccount database
$conn_user = new mysqli($servername, $username, $password, $dbname_user);
if ($conn_user->connect_error) {
    die("Connection failed: " . $conn_user->connect_error);
}

// Query to retrieve user_id from request table in provideraccount database
$sql_request = "SELECT user_id FROM request";
$result_request = $conn_provider->query($sql_request);

if ($result_request->num_rows > 0) {
    // Fetch each user_id from request table
    while ($row = $result_request->fetch_assoc()) {
        $user_id = $row["user_id"];

        // Query to retrieve name and phone from info table in useraccount database
        $sql_info = "SELECT name, phone FROM info WHERE user_id = '$user_id'";
        $result_info = $conn_user->query($sql_info);

        if ($result_info->num_rows > 0) {
            // Fetch name and phone for the corresponding user_id
            while ($row_info = $result_info->fetch_assoc()) {
                $name = $row_info["name"];
                $phone = $row_info["phone"];
                
                // Prepare data to be sent back as JSON
                $data[] = array(
                    "name" => $name,
                    "phone" => $phone
                );
            }
        }
    }
}

// Close connections
$conn_provider->close();
$conn_user->close();

// Send data back as JSON
header('Content-Type: application/json');
echo json_encode($data);
?>
