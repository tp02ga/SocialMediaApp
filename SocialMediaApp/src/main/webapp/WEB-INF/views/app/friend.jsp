<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>
<html>
<head>
  <title>Add a Friend</title>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js" type="text/javascript"></script>
  <script type="text/javascript">
    $(function () {
    	$("#yesBtn").click(function () {
    		$("#action").val("Yes");
    		$("#searchForm").submit();
    	});
    });
  
  </script>
</head>
<body>
<h1>Add a Friend</h1>

<form:form commandName="searchForm" method="POST">
  <form:hidden path="action"/>
  <form:input path="searchString"/>
  <input type="submit" value="Search"/>
  
	<c:if test="${!empty searchResult}">
	  <br/>
	  Would you like to add ${searchResult.username} as a friend? <input type="button" value="Yes" id="yesBtn" /> 
	</c:if>
</form:form>


</body>
</html>
