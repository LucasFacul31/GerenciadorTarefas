
<%
	if (request.getSession().getAttribute("usuario") != null) {
	response.sendRedirect("home.jsp");
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
<title>Login</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<form action="Login.do" method="post" class="form-default">
					<%!Object message;%>
					<%
						message = request.getSession().getAttribute("message");
					if (message != null) {
						out.print("<div class=\"alert alert-danger\" role=\"alert\">" + message + "</div>");
						request.getSession().removeAttribute("message");
					}
					%>
					<h1>Login</h1>
					<div class="form-group">
						<label for="email">E-mail</label> <input type="email"
							class="form-control" id="email" placeholder="Digite seu e-mail"
							name="email" required>
					</div>
					<div class="form-group">
						<label for="senha">Senha</label> <input type="password"
							class="form-control" id="senha" placeholder="Digite sua senha"
							name="senha" required>
					</div>
					<button type="submit" class="btn btn-primary">Entrar</button>
					<p class="mt-3">
						Ainda não é um membro? <a href="cadastro.jsp">Cadastre-se hoje
							mesmo!</a>
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>