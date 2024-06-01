var map = L.map('map').setView([23.702965, 90.451160], 10);
var markerModeEnabled = false;
var markers = [];

L.tileLayer('https://api.maptiler.com/maps/streets/{z}/{x}/{y}.png?key=ZnHfBprMGZAWBgxNny1R',{
    tileSize: 512,
    zoomOffset: -1,
    minZoom: 1,
    attribution: "\u003ca href=\"https://www.maptiler.com/copyright/\" target=\"_blank\"\u003e\u0026copy; MapTiler\u003c/a\u003e \u003ca href=\"https://www.openstreetmap.org/copyright\" target=\"_blank\"\u003e\u0026copy; OpenStreetMap contributors\u003c/a\u003e",
    crossOrigin: true
}).addTo(map);

function togglePlaceMarkerMode() {
    markerModeEnabled = !markerModeEnabled;
}

function deleteMarkers() {
    // Send request to delete markers from database
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "store_coordinates.php", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("delete=true");

    // Remove markers from map
    markers.forEach(function(marker) {
        map.removeLayer(marker);
    });
    markers = [];
    
}

function addMarker(latlng) {
    var marker = L.marker(latlng).addTo(map);
    markers.push(marker);

    marker.on('click', function(e) {
        var label = prompt("Enter label for the marker:");
        if (label !== null) {
            marker.bindTooltip(label).openTooltip();
            // Send latitude, longitude, and label to PHP script using AJAX
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.open("POST", "store_coordinates.php", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("lat=" + latlng.lat + "&lng=" + latlng.lng + "&label=" + encodeURIComponent(label));
        }
    });
}

function requestService() {
    // Check if there are any markers on the map
    if (markers.length === 0) {
        alert("Please place markers on the map before requesting a service.");
        return; // Exit the function if no markers are present
    }

    var randomNumber = Math.floor(10000000 + Math.random() * 90000000); // Generate 8-digit random number
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "service.php", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("req=" + randomNumber);
    alert("Confirm Service!");
}


function cancelService() {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "service.php", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("cancel=true");
    // Display a popup message
    alert("Service has been complete!.");
    deleteMarkers();
}

// Function to add markers for existing coordinates
function addExistingMarkers(coordinates) {
    coordinates.forEach(function(coord) {
        var marker = L.marker([coord.lat, coord.lng]).addTo(map);
        marker.bindTooltip(coord.label).openTooltip(); // Bind label as tooltip
        markers.push(marker);
    });
}

// AJAX request to retrieve existing coordinates from database
var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var coordinates = JSON.parse(this.responseText);
        addExistingMarkers(coordinates);
    }
};
xmlhttp.open("GET", "get_coordinates.php", true);
xmlhttp.send();

function onMapClick(e) {
    if (markerModeEnabled) {
        var clickedLatLng = e.latlng;
        addMarker(clickedLatLng);

        // Send latitude, longitude to PHP script using AJAX
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("POST", "store_coordinates.php", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("lat=" + clickedLatLng.lat + "&lng=" + clickedLatLng.lng);
    }
}

map.on('click', onMapClick);
