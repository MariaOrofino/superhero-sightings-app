<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Report Sighting</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
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
            <h2 class="text-center">Report Sighting</h2>
            <form action="${pageContext.request.contextPath}/addSighting" method="post">                        
                <div class="form-group">
                    <label for="sightingDate">Date:</label>
                    <input id="sightingDate" name="sightingDate" type="date" class="form-control"/>                            
                </div>    
                <div class="form-group">
                    <label for="heroSightingSelection">Hero:</label>
                    <select class="form-control" id="heroSightingSelection">
                        <c:forEach var="hero" items="${heros}">
                            <option value="${hero.heroID}">${hero.heroName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="locationSightingSelection">Location:</label>
                    <select class="form-control" id="locationSightingSelection">
                        <c:forEach var="location" items="${locations}">
                            <option value="${location.locationID}">${location.locationName}<c:if test="${location.streetAddress} != null">,${location.streetAddress}</c:if></option>
                        </c:forEach>
                    </select>
                </div>                      
                <button type="submit" class="btn btn-primary">Submit</button> 
            </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        
    </body>
</html>

