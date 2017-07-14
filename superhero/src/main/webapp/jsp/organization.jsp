<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Organizations</title>
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
                    <li class="active"><a class="nav-link" href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                </ul>
                <a href="${pageContext.request.contextPath}/reportSighting" class="btn btn-danger navbar-btn">Report Sighting</a>
            </div>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-6">
                    <h2 class="text-center">Hero Organizations</h2>
                    <table class="table table-bordered table-hover table-responsive table-striped">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>City</th>
                                <th>State</th>
                                <th>ZIP Code</th>
                                <th></th>
                            </tr>                            
                        </thead>
                        <tbody>
                            <c:forEach var="org" items="${orgs}">
                                <tr>
                                    <td>${org.orgName}</td>
                                    <td>${org.city}</td>
                                    <td>${org.state}</td>
                                    <td>${org.zipcode}</td>
                                    <td>
                                        <a href="deleteOrg/${org.orgID}">Delete</a> | 
                                        <a href="editOrg/${org.orgID}">Edit</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-sm-6">
                    <h2 class="text-center">Add Organization</h2>
                    <form action="${pageContext.request.contextPath}/addOrg" method="post">
                        <div class="form-group">
                            <label for="orgName">Organization Name:</label>
                            <input id="orgName" name="orgName" type="text" maxlength="45" class="form-control"/>                            
                        </div>                        
                        <div class="form-group">
                            <label for="orgDescription">Description:</label>
                            <textarea id="orgDescription" name="orgDescription" maxlength="300" class="form-control" rows="10"></textarea>
                        </div>                                                
                        <div class="form-group">
                            <label for="orgStreetAddress">Street Address:</label>
                            <input id="orgStreetAddress" name="orgStreetAddress" type="text" maxlength="45" class="form-control"/>                            
                        </div>  
                        <div class="form-group">
                            <label for="orgCity">City:</label>
                            <input id="orgCity" name="orgCity" type="text" maxlength="45" class="form-control"/>                            
                        </div>  
                        <div class="form-group">
                            <label for="orgState">State (must use acronym):</label>
                            <input id="orgState" name="orgState" type="text" maxlength="2" class="form-control"/>                            
                        </div>  
                        <div class="form-group">
                            <label for="orgZipcode">ZIP Code:</label>
                            <input id="orgZipcode" name="orgZipcode" type="text" maxlength="10" class="form-control"/>                            
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
