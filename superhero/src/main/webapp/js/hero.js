
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
    $("#heroHeader").text("Heros");
}

function showAddHeroForm() {
    $("#showHerosDiv").hide();
    $("#addHeroDiv").show();
    $("#showHerosButton").removeClass("active");
    $("#addHeroButton").addClass("active");
    $("#heroHeader").text("Add Hero");
}