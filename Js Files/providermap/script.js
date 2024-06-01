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



function getInfo() {
    $.getJSON("get_info.php", function (data) {
        console.log(data); // Add this line to log the fetched data
        var dataText = "";
        for (var i = 0; i < data.length; i++) {
            dataText += "Name: " + data[i].name + ", Phone: " + data[i].phone + "\n";
        }
        document.getElementById("dataTextArea").value = dataText;
    });
}

// Function to display data in text field
function showData(data) {
    // Assuming you have an input element with id "dataTextField"
    var textField = document.getElementById("dataTextField");
    textField.value = data[0].name + ": " + data[0].phone; // Displaying the first item in the array as an example
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



