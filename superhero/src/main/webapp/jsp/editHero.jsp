<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Hero</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">  
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Hero Sightings</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a class="nav-link" href="${pageContext.request.contextPath}/">Home</a></li>
                    <li><a class="nav-link" href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                    <li><a class="nav-link" href="${pageContext.request.contextPath}/hero">Heros</a></li>
                    <li><a class="nav-link" href="${pageContext.request.contextPath}/location">Locations</a></li>
                    <li><a class="nav-link" href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                </ul>
                <a href="${pageContext.request.contextPath}/reportSighting" class="btn btn-danger navbar-btn">Report Sighting</a>
            </div>
        </nav>
        <div class="container">            
            <h2 class="text-center">Edit Hero</h2>
            <form action="${pageContext.request.contextPath}/updateHero" method="post">
                <div class="form-group">
                    <label for="heroName">Hero Name:</label>
                    <input name="heroName" type="text" class="form-control" value="${hero.heroName}"/>                            
                </div>                        
                <div class="form-group">
                    <label for="heroDescription">Hero Description:</label>
                    <textarea name="heroDescription" class="form-control" rows="10">${hero.description}</textarea>
                </div>                        
                <input name="heroID" type="hidden" value="${hero.heroID}"/>
                <button type="submit" class="btn btn-primary">Submit</button> 
            </form>
        </div>
        
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
