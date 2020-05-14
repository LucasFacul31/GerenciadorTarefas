
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
<title>Cadastro</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<form action="Cadastro.do" method="post" class="form-default"
					oninput='senha_confirm.setCustomValidity(senha_confirm.value != senha.value ? "As senhas não coincidem" : "")'>
					<%!Object message;%>
					<%
						message = request.getSession().getAttribute("message");
					if (message != null) {
						out.print("<div class=\"alert alert-danger\" role=\"alert\">" + message + "</div>");
						request.getSession().removeAttribute("message");
					}
					%>
					<h1>Cadastro</h1>
					<div class="form-group">
						<label for="nome">Nome</label> <input type="text"
							class="form-control" id="nome" placeholder="Digite seu nome"
							name="nome" required>
					</div>
					<div class="form-group">
						<label for="email">E-mail</label> <input type="email"
							class="form-control" id="email" placeholder="Digite seu e-mail"
							name="email" required>
					</div>
					<div class="form-group">
						<label for="senha">Senha</label> <input type="password"
							minlength="6" class="form-control" id="senha"
							placeholder="Digite sua senha" name="senha" required>
					</div>
					<div class="form-group">
						<label for="senha_confirm">Confirme sua senha</label> <input
							type="password" minlength="6" class="form-control"
							id="senha_confirm" placeholder="Digite sua senha novamente"
							name="senha_confirm" required>
					</div>
					<button type="submit" class="btn btn-primary">Cadastrar</button>
					<p class="mt-3">
						Já está cadastrado? <a href="login.jsp">Faça login!</a>
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>