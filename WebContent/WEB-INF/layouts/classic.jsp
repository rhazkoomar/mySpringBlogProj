<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layouts/taglib.jsp" %>
<%@ include file="../layouts/cssJss.jsp" %>
<!DOCTYPE html>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<!-- <link rel="stylesheet" href="/WEB-INF/resources/bootstrap-3.2.0/dist/css/bootstrap.min.css"> -->




 


<!-- Optional theme -->

<!-- Latest compiled and minified CSS -->
<!--  <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">


<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">


<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
 <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
-->




<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title"></tiles:getAsString></title>
</head>
<body>
<tilesx:useAttribute name="current" />


 <div  class="container">
 <div class= "navbar navbar-default" role="navigation">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" >JBA</a>
          </div>
          <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="${current =='index' ? 'active':''}"><a href="<spring:url value="/index.html"/>">Home</a></li>
              <security:authorize access=" hasRole('ROLE_ADMIN') ">
              <li class="${current =='users' ? 'active':''}"><a href='<spring:url value="/users.html"></spring:url>'>Users</a></li>
             </security:authorize>
             <li class="${current =='register' ? 'active':''}"><a href='<spring:url value="/register.html"></spring:url>'>User Register</a></li>
             <security:authorize access="! isAuthenticated()">
             <li class="${current =='login' ? 'active':''}"><a href='<spring:url value="/login.html"></spring:url>'>Login</a></li>
             </security:authorize>
             <security:authorize access=" isAuthenticated()">
             
             <li class="${current =='account' ? 'active':''}"><a href='<spring:url value="/account.html"></spring:url>'>My account</a></li>
             <li><a href='<spring:url value="/logout"></spring:url>'>Log out</a></li>
             
              </security:authorize>
             
            </ul>
            
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </div>
 
 
 	<tiles:insertAttribute name="body"></tiles:insertAttribute>
	<br>
	<br>
	<center>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</center>
</div>
</body>
</html>