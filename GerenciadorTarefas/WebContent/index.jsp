<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="includes/head.html"%>
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col text-center">
				<h1>Bem-vindo ao TasksManager!</h1>
				<div class="container-btn">
					<%
						if (request.getSession().getAttribute("usuario") != null) {
						out.print("<a href=\"home.jsp\" class=\"btn btn-primary\">Minha conta</a>");
					} else {
						out.print("<a href=\"login.jsp\" class=\"btn btn-secondary\">Entrar</a>");
						out.print("<a href=\"cadastro.jsp\" class=\"btn btn-primary\">Cadastre-se</a>");
					}
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>