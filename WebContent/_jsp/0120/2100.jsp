<%@ include file="/include/head.jsp" %>
<!DOCTYPE>
<!--  -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>

	<div>
		
		<span>driver:<%=application.getInitParameter("driver") %></span>
		<span>url:<%=application.getInitParameter("url") %></span>
		<span>user:<%=application.getInitParameter("user") %></span>
		<span>pass:<%=application.getInitParameter("pass") %></span>
		
	</div>

</body>
</html>