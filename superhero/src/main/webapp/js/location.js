$(document).ready(function() {
    showLocationTable();
    $("#showAddLocationFormButton").click(function() {
        showAddLocationForm();
    });
    $("#showLocationTableButton").click(function() {
        showLocationTable();
    });
});

function showLocationTable() {
    $("#showAddLocationFormButton").removeClass("active");
    $("#addLocationDiv").hide();
    $("#showLocationTableButton").addClass("active");
    $("#locationTableDiv").show();
    $(".toggleHeader").text("Locations");
    
}

function showAddLocationForm() {
    $("#showLocationTableButton").removeClass("active");
    $("#locationTableDiv").hide();
    $("#showAddLocationFormButton").addClass("active");
    $("#addLocationDiv").show();
    $(".toggleHeader").text("Add Location");
}