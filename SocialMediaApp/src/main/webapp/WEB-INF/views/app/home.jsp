<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>
<html>
<head>
  <title>Home</title>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js" type="text/javascript"></script>
  <script type="text/javascript">
    $(function () {
    	$("#addAFriendDiv").show(1000);
    	
    	$("#addAFriendDiv").click(function () {
    		window.location.href = "friend.htm";
    	});
    	
    	$("input[id^='yesBtn']").click(function () {
    		var userId = $(this).attr("id");
    		userId = userId.substring(6);
    		$("#pendingFriendshipUserId").val(userId);
    		$("#action").val("acceptFriendRequest");
    		$("#friendshipForm").submit();
    	});
    	
    	$("input[id^='noBtn']").click(function () {
    		var userId = $(this).attr("id");
        userId = userId.substring(5);
        $("#pendingFriendshipUserId").val(userId);
        $("#action").val("declineFriendRequest");
        $("#friendshipForm").submit();
      });
    });
  
  </script>
</head>
<body>

<form:form commandName="message" method="POST">
  <div style="font-weight: bold;">Share a Message:</div> <form:textarea style="width: 400px;height: 80px;" path="content" />
  <div style="margin-left: 308px;">
    <input type="submit" id="submitBtn" value="Post Message"/>
  </div>
  
</form:form>

<c:if test="${!empty friendships}">
  <form:form commandName="friendshipForm" method="POST">
    <form:hidden path="pendingFriendshipUserId"/>
    <form:hidden path="action"/>
	  <h2>Pending Friendships</h2>
	  <c:forEach items="${friendships}" var="friendship">
	    User: <b>${friendship.userId.username}</b> wishes to be your friend.  Do you accept this request? <input type="button" value="Yes" id="yesBtn${friendship.userId.id}"/> <input type="button" value="No" id="noBtn${friendship.userId.id}"/> <br/> 
	  </c:forEach>
  </form:form>
</c:if>

<!-- 
  for (Message message : messages)
  {
  
  }
 -->
<h2>Messages</h2>

<c:forEach items="${messages}" var="message">
  ${message.user.username}: ${message.content} <br/>
</c:forEach>

</body>
</html>
