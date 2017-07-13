<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Locations</title>
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
                    <li class="active"><a class="nav-link" href="${pageContext.request.contextPath}/location">Locations</a></li>
                    <li><a class="nav-link" href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                </ul>
                <a href="${pageContext.request.contextPath}/reportSighting" class="btn btn-danger navbar-btn">Report Sighting</a>
            </div>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-6">
                    <h2 class="text-center">Locations</h2>
                    <table class="table table-bordered table-hover table-responsive table-striped">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>City</th>
                                <th>State</th>
                                <th>ZIP Code</th>
                                <th>Lat.</th>
                                <th>Long.</th>
                                <th></th>
                            </tr>                            
                        </thead>
                        <tbody>
                            <c:forEach var="location" items="${locations}">
                                <tr>
                                    <td>${location.locationName}</td>
                                    <td>${location.city}</td>
                                    <td>${location.state}</td>
                                    <td>${location.zipcode}</td>
                                    <td>${location.latitude}</td>
                                    <td>${location.longitude}</td>
                                    <td>
                                        <a href="deleteLocation/${location.locationID}">Delete</a> | 
                                        <a href="editLocation/${location.locationID}">Edit</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-sm-6">
                    <h2 class="text-center">Add Location</h2>
                    <form action="${pageContext.request.contextPath}/addLocation" method="post">
                        <div class="form-group">
                            <label for="locationName">Location Name:</label>
                            <input id="locationName" name="locationName" type="text" maxlength="45" class="form-control"/>                            
                        </div>                        
                        <div class="form-group">
                            <label for="locationDescription">Description:</label>
                            <textarea id="locationDescription" name="locationDescription" maxlength="300" class="form-control" rows="10"></textarea>
                        </div>                                                
                        <div class="form-group">
                            <label for="locationStreetAddress">Street Address:</label>
                            <input id="locationStreetAddress" name="locationStreetAddress" type="text" maxlength="45" class="form-control"/>                            
                        </div>  
                        <div class="form-group">
                            <label for="locationCity">City:</label>
                            <input id="locationCity" name="locationCity" type="text" maxlength="45" class="form-control"/>                            
                        </div>  
                        <div class="form-group">
                            <label for="locationState">State (must use acronym):</label>
                            <input id="locationState" name="locationState" type="text" maxlength="2" class="form-control"/>                            
                        </div>  
                        <div class="form-group">
                            <label for="locationZipcode">ZIP Code:</label>
                            <input id="locationZipcode" name="locationZipcode" type="text" maxlength="10" class="form-control"/>                            
                        </div>         
                        <div class="form-group">
                            <label for="locationLatitude">Latitude:</label>
                            <input id="locationLatitude" name="locationLatitude" type="text" class="form-control"/>                            
                        </div>     
                        <div class="form-group">
                            <label for="locationLongitude">Longitude:</label>
                            <input id="locationLongitude" name="locationLongitude" type="text" class="form-control"/>                            
                        </div>     
                        <button type="submit" class="btn btn-primary">Submit</button> 
                    </form>
                </div>
            </div>
        </div>
        
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
