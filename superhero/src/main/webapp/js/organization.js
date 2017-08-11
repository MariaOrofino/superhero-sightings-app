$(document).ready(function() {
    showOrgsTable();
    $("#showOrgsButton").click(function() {
        showOrgsTable();
    });
    $("#addOrgButton").click(function() {
        showAddOrgForm();
    });
});

function showOrgsTable() {
    $(".toggleHeader").text("Hero Organizations");
    $("#addOrgButton").removeClass("active");
    $("#addOrgForm").hide();
    $("#showOrgsButton").addClass("active");
    $("#viewOrgsTable").show();
}

function showAddOrgForm() {
    $(".toggleHeader").text("Add Organization");
    $("#showOrgsButton").removeClass("active");
    $("#viewOrgsTable").hide();
    $("#addOrgButton").addClass("active");
    $("#addOrgForm").show();
}