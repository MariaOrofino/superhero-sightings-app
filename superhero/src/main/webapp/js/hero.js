$(document).ready(function() {
    $("#addHeroDiv").hide();
    $("#showHerosButton").addClass("active");
    
    $("#addHeroButton").click(function() {
        $("#showHerosDiv").hide();
        $("#addHeroDiv").show();
        $("#showHerosButton").removeClass("active");
        $("#addHeroButton").addClass("active");
    });
    
    $("#showHerosButton").click(function() {
        $("#showHerosDiv").show();
        $("#addHeroDiv").hide();
        $("#showHerosButton").addClass("active");
        $("#addHeroButton").removeClass("active");
    });
});