<%
	if (request.getSession().getAttribute("usuario") == null) {
	request.getSession().setAttribute("message", "Usuário não autenticado");
	response.sendRedirect("login.jsp");
	return;
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>
</head>
<body>
	<%
		request.getSession().invalidate();
		response.sendRedirect("login.jsp");
	%>
</body>
</html>