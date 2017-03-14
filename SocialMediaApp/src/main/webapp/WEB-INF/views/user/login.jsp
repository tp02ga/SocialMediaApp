<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Login  
</h1>

<!-- MVC (Model, View, Controller)
          Data,  JSP,  Java
-->

<!-- User.java
     ----------
     private String username; 
     private String password;
     
     getters/setters
     
     JSPs -> Java Objects
 -->
<form:form commandName="user" method="POST">
  Username: <form:input path="username"/> <br/>
  Password: <form:password path="password"/>
  
  <input type="submit" id="submitBtn" value="Submit"/>
  <div style="color: red;">${result}</div>
</form:form>

</body>
</html>
