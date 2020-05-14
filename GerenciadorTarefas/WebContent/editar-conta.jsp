
<%
	if (request.getSession().getAttribute("usuario") == null) {
	request.getSession().setAttribute("message", "Usuário não autenticado");
	response.sendRedirect("login.jsp");
	return;
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="service.UsuarioService,model.Usuario"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="includes/head.html"%>
<title>Editar conta</title>
</head>
<body>
	<%!Usuario usuario;%>
	<%
		usuario = (Usuario) request.getSession().getAttribute("usuario");
	%>
	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<form action="EditUsuario.do" method="post" class="form-default"
					oninput='new_senha_confirm.setCustomValidity(new_senha_confirm.value != new_senha.value ? "As senhas não coincidem" : "")'>
					<%!Object message;%>
					<%
						message = request.getSession().getAttribute("message");
					if (message != null) {
						out.print("<div class=\"alert alert-danger\" role=\"alert\">" + message + "</div>");
						request.getSession().removeAttribute("message");
					}
					%>
					<h1>Editar usuário</h1>
					<div class="form-group">
						<label for="titulo">Nome</label> <input type="text"
							class="form-control" id="nome" placeholder="Digite seu nome"
							name="nome" value="<%=usuario.getNome()%>" required>
					</div>
					<div class="form-group">
						<label for="titulo">Seu e-mail</label> <input type="email"
							class="form-control" id="email"
							placeholder="Digite seu e-mail atual" name="email" required>
					</div>
					<div class="form-group">
						<label for="titulo">Senha atual</label> <input type="password"
							class="form-control" id="old_senha"
							placeholder="Digite sua senha atual" name="old_senha" required>
					</div>
					<div class="form-group">
						<label for="titulo">Nova senha</label> <input type="password"
							class="form-control" id="new_senha" minlength="6"
							placeholder="Digite sua nova senha" name="new_senha" required>
					</div>
					<div class="form-group">
						<label for="titulo">Confirme sua nova senha</label> <input
							type="password" class="form-control" id="new_senha_confirm"
							minlength="6" placeholder="Confirme sua nova senha"
							name="new_senha_confirm" required>
					</div>
					<a href="home.jsp" class="btn btn-secondary">Voltar</a>
					<button type="submit" class="btn btn-primary">Atualizar</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>