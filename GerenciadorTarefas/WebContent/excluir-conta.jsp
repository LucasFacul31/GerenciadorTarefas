
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
<%@ include file="includes/head.html"%>
<title>Excluir conta</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<form action="DeleteUsuario.do" method="post" class="form-default">
					<%!Object message;%>
					<%
						message = request.getSession().getAttribute("message");
					if (message != null) {
						out.print("<div class=\"alert alert-danger\" role=\"alert\">" + message + "</div>");
						request.getSession().removeAttribute("message");
					}
					%>
					<input type="hidden" name="confirm" value="1" />
					<h1>Excluir conta</h1>
					<p>
						Tem certeza que deseja excluir sua conta?<br><b>Esta ação não pode ser desfeita!</b>
					</p>
					<a href="home.jsp" class="btn btn-secondary">Voltar</a>
					<button type="submit" class="btn btn-danger">Excluir</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>