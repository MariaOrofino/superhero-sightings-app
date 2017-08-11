
$(document).ready(function() {
    showHeroTable();    
    $("#addHeroButton").click(function() {
        showAddHeroForm();
    });    
    $("#showHerosButton").click(function() {
        showHeroTable();
    });
});

function showHeroTable() {
    $("#showHerosDiv").show();
    $("#addHeroDiv").hide();
    $("#showHerosButton").addClass("active");
    $("#addHeroButton").removeClass("active");
    $(".toggleHeader").text("Heros");
}

function showAddHeroForm() {
    $("#showHerosDiv").hide();
    $("#addHeroDiv").show();
    $("#showHerosButton").removeClass("active");
    $("#addHeroButton").addClass("active");
    $(".toggleHeader").text("Add Hero");
}