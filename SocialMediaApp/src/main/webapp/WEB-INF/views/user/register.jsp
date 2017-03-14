<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>
<html>
<head>
  <title>User Registration</title>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js" type="text/javascript"></script>
  <script type="text/javascript">
    $(function () {
      $("#submitBtn").click(function () {
        var password = $("#password").val();
        var confirmPassword = $("#confirmPassword").val();
        
        if (password == confirmPassword)
        {
          $("#user").submit();
        }
        else
        {
          alert("Passwords do not match!");
          return false;
        }
        
        var checked = $("#termsOfService1").prop("checked");
        if (!checked)
       	{
       	  alert("You must accept our terms of service!");
       	  return false;
       	}
        
        if ($("input[name='accountType']:checked").length == 0)
       	{
        	alert("Please selected an account type.");
        	return false;
       	}
        	
      });
    });
  </script>
</head>
<body>
<h1>
  User Registration  
</h1>

<!-- MVC (Model, View, Controller)
          Data,  JSP,  Java
-->

<form:form commandName="user" method="POST">
  Username: <form:input path="username"/> <br/>
  Password: <form:password path="password"/> <br/>
  Confirm Password: <input type="password" id="confirmPassword" /><br/>
  
  <h2>Account Type</h2>  

  <form:radiobuttons path="accountType" items="${mySpecialAccountTypes}" element="div"/><br/>
  
  <form:checkbox path="termsOfService" label="Do you accept our Terms of Service"/> <br/><br/>
  
  <input type="button" id="submitBtn" value="Submit"/>
</form:form>

</body>
</html>
